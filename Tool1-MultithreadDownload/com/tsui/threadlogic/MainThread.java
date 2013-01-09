/**
 * @Projectname Download_Tool--NetGothic
 * @FileName MainThread.java
 * @Author MarioTsui
 * @Description Main Thread,其中包括一个内部类，downloadProperty，该类实现了Serializable接口
 */

package com.tsui.threadlogic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 * 主线程类
 * 
 * @author 崔晓旻
 * @version version-1.2 增加判断源是否支持断点续传
 */
public class MainThread implements Runnable
{
	/**
	 * 主线程变量定义
	 */
	private CountDownLatch latch = null;// 用户阻塞主线程
	private JTextArea ta = new JTextArea(); // 用于主线程信息反馈
	private JProgressBar pb; // 进度条，用于下载完成后设置进度条为100%
	private ExecutorService exec = Executors.newCachedThreadPool();// 线程池，
	// 用于管理执行子线程
	private ChildThread[] childThread = null;// 子线程
	private DownloadProperty prop = null;// 主线程内部静态类，用于持久化下载信息，

	// 以及子线程访问下载信息

	/**
	 * MainThread构造函数
	 * 
	 * @param ta
	 *            JTextArea 界面信息
	 * @param pb
	 *            JProgressBar 进度条
	 * @param mtURL
	 *            接收用戶输入的URL
	 * @param mtURI
	 *            接收用戶输入的本地URI
	 * @param mtThreadNum
	 *            接收用户输入的线程数
	 */
	public MainThread(JTextArea ta, JProgressBar pb, String mtURL,
			String mtURI, int mtThreadNum)
	{
		prop = new DownloadProperty();
		// 初始化界面所用参数
		this.ta = ta;
		this.pb = pb;

		// 初始化持久类信息
		prop.setURL(mtURL);
		prop.setURI(mtURI);
		prop.setThreadNum(mtThreadNum);
		prop.setContentSize();

		// 初始化锁
		latch = new CountDownLatch(mtThreadNum);
		childThread = new ChildThread[mtThreadNum];
	};

	/**
	 * getProp 函数
	 * 
	 * @return DownloadProperty
	 */
	public DownloadProperty getProp()
	{
		return prop;
	}

	/**
	 * shutdownDownloadThread 函数 主线程用此函数终止子线程，并根据用户选择保存或者删除断点信息
	 * 
	 * @param value
	 *            int 接收用户选择的值
	 */
	public void shutdownDownloadThread(int value)
	{
		if (value == 0)
		{
			// 若用户选择保存断点信息，则终止所有线程，保留临时文件
			shutdownAndAwaitTermination(exec);

			// 设置断点信息
			ta.append("正在保存断点信息...\n");
			File temp = new File(prop.getURI());
			if (!temp.exists())
			{
				setBreakpoint();
			}
		}
		else
		{
			shutdownAndAwaitTermination(exec);
			// 若用户选择不保存断点信息，则删除所有临时文件
			ta.append("正在删除临时文件...\n");
			deleteTempFiles();
		}
	}

	/**
	 * deleteTempFiles 函数 删除临时文件
	 * 
	 * @param
	 * @return void
	 */
	private void deleteTempFiles()
	{
		boolean t;
		File tmp = new File(prop.getURI() + "_tmp");
		// 删除子线程临时文件
		for (int i = 0; i < prop.getThreadNum(); i++)
		{

			if (childThread[i].getChildThreadTempFile().exists())
			{
				t = childThread[i].getChildThreadTempFile().delete();
				if (t == false)
				{
					System.err.println("thread " + i
							+ " thread temp file delete failed!");
				}
				else
					System.out.println("thread temp file " + i + " deleted");
			}
		}
		if (tmp.exists())
		{
			t = tmp.delete();
			if (t == false)
			{
				System.err.println("temp file delete failed!");
			}
			else
				System.out.println("temp file deleted");
		}
	}

	/**
	 * judgeSourceResumeEnabled 函数 判断源是否支持断点续传
	 * 
	 * @param
	 * @return boolean
	 */
	private boolean judgeSourceResumeEnabled()
	{
		try
		{
			URL url = new URL(prop.getURL());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setAllowUserInteraction(true);
			con.setRequestProperty("Range", "bytes=1-2");

			// 设置连接超时时间为10000ms
			con.setConnectTimeout(10000);

			// 设置读取数据超时时间为10000ms
			con.setReadTimeout(10000);
			// 判断源是否支持断点续传
			if (con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL)
			{
				ta.append("该源不支持断点续传...\n");
				con.disconnect();
				return false;
			}
			else
			{
				ta.append("该源支持断点续传...\n");
				return true;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * setBreakponit 函数 主线程用此函数设置断点信息
	 * 
	 * @param
	 * @return void
	 */
	private void setBreakpoint()
	{
		try
		{
			if (prop.getURI() != null)
			{
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(prop.getSavePath() + "\\"
								+ prop.getSaveName() + "_tmp"));
				out.writeObject(prop);
				out.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		;
	}

	/**
	 * readThreadBreakpoint 函数 主线程用此函数读取断点信息
	 * 
	 * @param
	 * @return boolean
	 */
	private boolean readThreadBreakpoint()
	{
		try
		{
			File file = new File(prop.getURI() + "_tmp");
			if (file.exists())// 如果下载的目标文件存在，则反序列化
			{
				ta.append("读取断点信息...\n");
				ta.append("继续下载...\n");

				ObjectInputStream out = new ObjectInputStream(
						new FileInputStream(file));
				prop = (DownloadProperty) out.readObject();
				latch = new CountDownLatch(prop.getThreadNum());
				out.close();
				return true;
			}
			else
				return false;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
			return false;
		}
	}

	/**
	 * tempFileToTargetFile 函数 下载完成后，主线程用此函数合并子线程的临时文件
	 * 
	 * @param ChildThread
	 *            []
	 * @return void
	 */
	private void tempFileToTargetFile(ChildThread[] childThreads)
	{
		ta.append("正在组装文件...\n");
		try
		{
			String URI = prop.getURI();
			int threadNum = prop.getThreadNum();
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(URI, true));

			// 遍历所有子线程创建的临时文件，按顺序把下载内容写入目标文件中
			for (int i = 0; i < threadNum; i++)
			{
				BufferedInputStream inputStream = new BufferedInputStream(
						new FileInputStream(childThreads[i]
								.getChildThreadTempFile()));
				System.out.println("Now is file " + i);

				int len = 0;
				long count = 0;
				byte[] b = new byte[1024];

				while ((len = inputStream.read(b)) != -1)
				{
					count += len;
					outputStream.write(b, 0, len);
					if ((count % 4096) == 0)
					{
						outputStream.flush();
					}
				}
				inputStream.close();
				ta.append("组装临时文件 " + i + " 完成!\n");

				// 下载完成后删除临时文件
				ta.append("正在删除临时文件 " + i + " ...\n");
				File tmp = new File(prop.getURI() + "_tmp");
				if (childThreads[i].getChildThreadStatus() == ChildThread.STATUS_HAS_FINISHED)
				{
					childThreads[i].getChildThreadTempFile().delete();
					tmp.delete();
				}
				// 若子线程连接出错则删除临时文件,包括子线程临时文件和断点信息临时文件
				else if (childThreads[i].getChildThreadStatus() == ChildThread.STATUS_HTTPSTATUS_ERROR)
				{
					childThreads[i].getChildThreadTempFile().delete();
					tmp.delete();
				}
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * shutdownAndAwaitTermination 函数 下载完成后，终止线程池中的任务
	 * 
	 * @param pool
	 *            ExecutorService
	 * @return void
	 */
	private void shutdownAndAwaitTermination(ExecutorService pool)
	{
		for (int i = 0; i < prop.getThreadNum(); i++)
		{
			childThread[i].setPause();
			childThread[i].interrupt();
		}
		// 终止线程
		try
		{
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(1, TimeUnit.SECONDS))
			{
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(1, TimeUnit.SECONDS))
				{
					System.err.println("Pool did not terminate");
				}
			}
		} catch (InterruptedException ie)
		{
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * setChildThreadValue 函数 用于新下载时的子线程断点信息初始化
	 * 
	 * @param
	 * @return void
	 */
	private void setChildThreadValue()
	{
		int threadNum = prop.getThreadNum();
		long threadLen = prop.getThreadLen();
		long contentLen = prop.getContentLen();

		// 定义临时变量，保存断点信息
		long[] starts = new long[threadNum];
		long[] ends = new long[threadNum];

		// 设置断点的初始信息
		for (int i = 0; i < threadNum; i++)
		{

			// 每段数据的起始位置为(threadLength * i + 已下载长度)
			starts[i] = (long) i * threadLen;

			// 每个非尾段数据的结束位置为(threadLength * i + 已下载长度 - 1)
			if (i == (threadNum - 1))
			{
				ends[i] = contentLen;
			}
			else
			{
				ends[i] = threadLen * (i + 1) - 1;
			}
		}

		// 设置断点信息到持久类中
		prop.setStarts(starts, threadNum, -1, 0);
		prop.setEnds(ends, threadNum, -1, 0);

	}

	/**
	 * run 函数 主线程执行函数
	 */
	public void run()
	{
		ta.append("\n" + "寻找服务器资源..." + "\n");
		ta.append("\n" + "主线程已启动..." + "\n");

		// 判断是否支持断点续传
		if (judgeSourceResumeEnabled())
		{
			if (!readThreadBreakpoint())// 若支持，则分析是否有断点信息临时文件
			{
				// 若没有断点信息，说明本次执行为新下载
				setChildThreadValue();
			}
		}
		else
		{
			setChildThreadValue();
		}

		// 主线程执行临时变量定义，获取用户输入的下载信息
		String mtInfoEcho = null;
		String mtURL = prop.getURL();
		int mtThreadNum = prop.getThreadNum();
		long mtThreadLen = prop.getThreadLen();
		long mtContentLen = prop.getContentLen();
		childThread = new ChildThread[mtThreadNum];

		mtInfoEcho = "已获取的资源为：\n" + mtURL + "\n" + "下载资源的线程数" + mtThreadNum
				+ "\n";
		ta.append(mtInfoEcho);

		mtInfoEcho = "源长度为:" + new BigDecimal(mtContentLen / 1024).setScale(1)
				+ "KB\n";
		ta.append(mtInfoEcho);

		// 分配子线程
		for (int i = 0; i < mtThreadNum; i++)
		{

			mtInfoEcho = "\n线程" + i + "文件大小为"
					+ new BigDecimal(mtThreadLen / 1024).setScale(1) + "KB\n";
			ta.append(mtInfoEcho);

			ChildThread thread = new ChildThread(this, latch, i, ta, pb);
			childThread[i] = thread;
			exec.execute(thread);
		}

		try
		{
			// 等待CountdownLatch信号为0，表示所有子线程都结束。
			latch.await();
			pb.setValue((int) prop.getContentLen() - 1);
			// 把分段下载下来的临时文件中的内容写入目标文件中
			tempFileToTargetFile(childThread);
			exec.shutdown();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// 设置下载完成值100%
		pb.setValue(pb.getMaximum());
		ta.append("\n文件下载结束\n");
	}

	/**
	 * DownloadProperty 内部类 实现了序列化接口，用于持久化下载信息
	 * 
	 * @author 崔晓旻
	 * @version version-1.0
	 */
	public static class DownloadProperty implements Serializable
	{
		/**
		 * 下载信息有关变量定义
		 */
		private static final long serialVersionUID = 1;
		private String URL = null; // 源地址
		private String URI = null; // 目的地址（保存地址）

		private String saveName = null; // 保存文件名
		private String savePath = null; // 保存路径
		private int threadNum = 0;// 线程数
		private long contentLen = 0;// 源文件长度
		private long threadLen = 0;// 单个线程长度
		private volatile long finishedLen = 0;// 已完成的长度，初始值为0

		// 断点信息
		private volatile long[] starts;
		private volatile long[] ends;

		public DownloadProperty()
		{
		}

		/**
		 * setURL 函数 设置URL
		 * 
		 * @param URL
		 *            String
		 */
		public void setURL(String URL)
		{
			this.URL = URL;
		}

		/**
		 * getURL 函数
		 * 
		 * @return String
		 */
		public String getURL()
		{
			return URL;
		}

		/**
		 * setURI 函数 设置URI
		 * 
		 * @param URI
		 *            String
		 */
		public void setURI(String URI)
		{
			this.URI = URI;
		}

		/**
		 * getURI 函数
		 * 
		 * @return String
		 */
		public String getURI()
		{
			return URI;
		}

		/**
		 * setContentLen 函数 设置contentLen
		 * 
		 * @param len
		 *            long
		 */
		public void setContentLen(long len)
		{
			contentLen = len;
		}

		/**
		 * getContentLen 函数
		 * 
		 * @return long
		 */
		public long getContentLen()
		{
			return contentLen;
		}

		/**
		 * setThreadLen 函数 设置threadLen
		 * 
		 * @param len
		 *            long
		 */
		public void setThreadLen(long len)
		{
			threadLen = len;
		}

		/**
		 * getThreadLen 函数
		 * 
		 * @return long
		 */
		public long getThreadLen()
		{
			return threadLen;
		}

		/**
		 * setFinishedLen 函数 设置finishedLen
		 * 
		 * @param len
		 *            long
		 */
		public void setFinishedLen(long len)
		{
			finishedLen += len;
		}

		/**
		 * getFinishedLen 函数
		 * 
		 * @return long
		 */
		public long getFinishedLen()
		{
			return finishedLen;
		}

		/**
		 * setThreadNum 函数 设置threadNum
		 * 
		 * @param num
		 *            int
		 */
		public void setThreadNum(int num)
		{
			threadNum = num;
		}

		/**
		 * getThreadNum 函数
		 * 
		 * @return int
		 */
		public int getThreadNum()
		{
			return threadNum;
		}

		/**
		 * setStarts 函数 设置断点信息，包括两种方式的赋值 一是线程号为-1时，所有的断定信息更新
		 * 二是线程号不为-1时，相应线程的断定信息更新
		 * 
		 * @param starts
		 *            [] long
		 * @param threadNum
		 *            int
		 * @param threadID
		 *            int
		 * @param aThreadStart
		 *            long 某个线程的断点更新值
		 */
		public void setStarts(long starts[], int threadNum, int threadID,
				long aThreadStart)
		{
			if (threadID == -1)
			{
				this.starts = new long[threadNum];
				this.starts = starts;
			}
			else
			{
				this.starts[threadID] = aThreadStart;
			}
		}

		/**
		 * getStarts 函数 获取所有线程的断点信息
		 * 
		 * @return long[]
		 */
		public long[] getStarts()
		{
			return starts;
		}

		/**
		 * setEnds 函数 设置断点信息，包括两种方式的赋值 一是线程号为-1时，所有的断定信息更新
		 * 二是线程号不为-1时，相应线程的断定信息更新
		 * 
		 * @param ends
		 *            [] long
		 * @param threadNum
		 *            int
		 * @param threadID
		 *            int
		 * @param aThreadEnd
		 *            long 某个线程的断点更新值
		 */
		public void setEnds(long ends[], int threadNum, int threadID,
				long aThreadEnd)
		{
			if (threadID == -1)
			{
				this.ends = new long[threadNum];
				this.ends = ends;
			}
			else
			{
				this.ends[threadID] = aThreadEnd;
			}
		}

		/**
		 * getEnds 函数 获取所有线程的断点信息
		 * 
		 * @return long[]
		 */
		public long[] getEnds()
		{
			return ends;
		}

		/**
		 * getSaveName 函数 获取本地存储文件的文件名
		 * 
		 * @return String
		 */
		public String getSaveName()
		{
			saveName = URI
					.substring(URI.lastIndexOf("\\") + 1,
							URI.lastIndexOf("?") > 0 ? URI.lastIndexOf("?")
									: URI.length());
			if ("".equalsIgnoreCase(this.saveName))
			{
				this.saveName = UUID.randomUUID().toString();
			}
			return saveName;
		}

		/**
		 * getSaveName 函数 获取本地存储文件的路径
		 * 
		 * @return String
		 */
		public String getSavePath()
		{
			savePath = URI.substring(URI.indexOf(URI.charAt(0)), URI
					.lastIndexOf("\\") > 0 ? URI.lastIndexOf("\\") : URI
					.length());
			if ("".equalsIgnoreCase(this.savePath))
			{
				this.savePath = UUID.randomUUID().toString();
			}
			return savePath;
		}

		/**
		 * setContentSize 函数 用于主线程初始化contentLen和threadLen
		 */
		public void setContentSize()
		{
			try
			{
				for (int i = 0; i < 10; i++)
				{
					// 连接出错后重新连接提示
					if (i > 0)
						System.out.println("连接出错，正尝试重新连接...\n");
					if (i == 9)
					{
						System.out.println("连接10次未成功，退出\n");
						System.exit(0);
					}
					URL url = new URL(URL);
					// 打开URLConnection
					HttpURLConnection con = (HttpURLConnection) url
							.openConnection();
					con.setAllowUserInteraction(true);

					// 设置连接超时时间为10000ms
					con.setConnectTimeout(10000);

					// 设置读取数据超时时间为10000ms
					con.setReadTimeout(10000);

					// 判断http status是否为HTTP/1.1 206 Partial Content或者200 OK
					// 如果不是以上两种状态，把status改为STATUS_HTTPSTATUS_ERROR
					if (con.getResponseCode() != HttpURLConnection.HTTP_OK
							&& con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL)
					{
						System.out.println("");
						break;
					}
					contentLen = con.getContentLength();
					threadLen = contentLen / threadNum;
					// con.disconnect();
					break;
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			;

		}
	}
}
