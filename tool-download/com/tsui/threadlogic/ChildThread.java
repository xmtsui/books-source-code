/**
 * @Projectname Download_Tool--NetGothic
 * @FileName ChildThread.java
 * @Author MarioTsui
 * @Description Child Thread
 */

package com.tsui.threadlogic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 * 子线程
 * 
 * @author 崔晓旻
 * @version version-1.0
 */
public class ChildThread extends Thread
{

	public static final int STATUS_HASNOT_FINISHED = 0;
	public static final int STATUS_HAS_FINISHED = 1;
	public static final int STATUS_HTTPSTATUS_ERROR = 2;

	private int threadID; // 线程号
	private int childStatus; // 线程当前下载状态
	private File tmpFile; // 线程临时文件
	private RandomAccessFile ranFile; // 线程临时文件
	private final CountDownLatch latch; // 子线程执行完后减1
	// private Semaphore gate;
	private volatile boolean run;

	// 下载信息变量定义
	private int ctThreadNum;
	private long ctThreadLen;
	private long ctStart; // 本线程开始位置
	private long ctEnd; // 本线程结束位置
	private String ctURI;
	private String ctURL;

	private MainThread main = null;
	private JProgressBar pb; // 进度条
	private JTextArea ta; // 显示下载信息

	/**
	 * ChildThread构造函数
	 * 
	 * @param m
	 *            MainThread
	 * @param latch
	 *            CountDownLatch
	 * @param ID
	 *            int 线程号
	 * @param ta
	 *            JTextArea
	 * @param pb
	 *            JProgressBar
	 */
	public ChildThread(MainThread m, CountDownLatch latch, int ID,
			JTextArea ta, JProgressBar pb)
	{
		main = m;
		this.latch = latch;
		this.ta = ta;
		this.pb = pb;
		this.run = true;
		ta.setSelectionStart(ta.getText().length());
		threadID = ID;
		childStatus = ChildThread.STATUS_HASNOT_FINISHED;

		ctThreadLen = m.getProp().getThreadLen();
		ctURL = m.getProp().getURL();
		ctURI = m.getProp().getURI();
		ctThreadNum = m.getProp().getThreadNum();
		ctStart = m.getProp().getStarts()[ID];
		ctEnd = m.getProp().getEnds()[ID];

		try
		{
			tmpFile = new File(ctURI + "_td" + ID);
			ranFile = new RandomAccessFile(tmpFile, "rw");
			ranFile.seek(ctStart - ctThreadLen * threadID);
			if (!tmpFile.exists())
			{
				tmpFile.createNewFile();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	};

	/**
	 * Sets the sorter to "run" mode. Called on the event dispatch thread.
	 */
	public void setRun()
	{
		run = true;
		// gate.release();
	}

	/**
	 * Sets the sorter to "step" mode. Called on the event dispatch thread.
	 */
	public void setPause()
	{
		run = false;
		// gate.release();
	}

	/**
	 * getChildThreadID 函数 获取子线程号
	 * 
	 * @return int
	 */
	public int getChildThreadID()
	{
		return threadID;
	}

	/**
	 * getChildThreadStatus 函数 获取子线程状态
	 * 
	 * @return int
	 */
	public int getChildThreadStatus()
	{
		return childStatus;
	}

	/**
	 * getChildThreadTempFile 函数 获取子线程临时文件
	 * 
	 * @return File
	 */
	public File getChildThreadTempFile()
	{
		return tmpFile;
	}

	/**
	 * run 函数 子线程执行
	 */
	public void run()
	{
		System.out.println("Thread " + threadID + " run ...");

		// 临时变量初始化
		HttpURLConnection con = null;
		InputStream inputS = null;
		BufferedOutputStream outputS = null;

		// 读取断点信息，若无断点临时文件则新建线程临时文件
		// readThreadBreakpoint();

		// 流初始化，文件流缓存，并设置成可以在尾部追加内容
		try
		{
			outputS = new BufferedOutputStream(new FileOutputStream(ranFile
					.getFD()));
		} catch (IOException e2)
		{
			e2.printStackTrace();
		}

		// 若连接出错，可尝试10此重新连接
		for (int i = 0; i < 10; i++)
		{
			// 连接出错后重新连接提示
			if (i > 0)
				System.out.println("Now thread " + threadID
						+ "is reconnect for the " + i
						+ "times , start position is " + ctStart);
			try
			{

				ctStart = main.getProp().getStarts()[threadID];
				ctEnd = main.getProp().getEnds()[threadID];
				URL url = new URL(ctURL);
				// 打开URLConnection
				con = (HttpURLConnection) url.openConnection();

				con.setAllowUserInteraction(true);

				// 设置连接超时时间为10000ms
				con.setConnectTimeout(10000);

				// 设置读取数据超时时间为10000ms
				con.setReadTimeout(10000);

				// 设置线程下载的起止区间
				if (ctStart < ctEnd)
				{
					con.setRequestProperty("Range", "bytes=" + ctStart + "-"
							+ ctEnd);
					System.out.println("Thread " + threadID
							+ " startPosition is " + ctStart);
					System.out.println("Thread " + threadID
							+ " endPosition is " + ctEnd);

					// 判断http status是否为HTTP/1.1 206 Partial Content或者200 OK
					// 如果不是以上两种状态，把status改为STATUS_HTTPSTATUS_ERROR
					if (con.getResponseCode() != HttpURLConnection.HTTP_OK
							&& con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL)
					{
						System.out.println("Thread " + threadID + ": code = "
								+ con.getResponseCode() + ", status = "
								+ con.getResponseMessage());
						childStatus = ChildThread.STATUS_HTTPSTATUS_ERROR;
						outputS.close();
						con.disconnect();
						System.out.println("Thread " + threadID + " finished.");
						latch.countDown();
						break;
					}

					// 打开源文件下载输入流
					inputS = con.getInputStream();

					int len = 0;// 记录每次读取的字节数
					long cntlen = ctStart - (threadID * ctThreadLen);// 记录已下载的字节数
					byte[] buff = new byte[1024];// 下载输入流接收缓冲区
					while ((len = inputS.read(buff)) != -1)
					{
						outputS.write(buff, 0, len);
						cntlen += len;
						ctStart += len;

						// 每读满4096个byte（一个内存页），往磁盘上flush一下
						if (cntlen % 4096 == 0)
						{
							outputS.flush();
							// 每flush一次，设置一次持久类中的断点信息
							main.getProp().setStarts(
									main.getProp().getStarts(), ctThreadNum,
									threadID, ctStart);
						}

						// 每循环一次，增加一次已下载长度
						main.getProp().setFinishedLen(len);
						// 并设置当前进度
						long tempLen = main.getProp().getFinishedLen();
						pb.setValue((int) tempLen - 1);
						// Thread.sleep(100);
					}

					// 设置一次持久类中的断点信息
					main.getProp().setStarts(main.getProp().getStarts(),
							ctThreadNum, threadID, ctStart);

					System.out.println("down finished length is："
							+ ((threadID * ctThreadLen) + cntlen));
					if (cntlen >= ctThreadLen)
					{
						childStatus = ChildThread.STATUS_HAS_FINISHED;
					}
					outputS.flush();
					outputS.close();
					inputS.close();
					con.disconnect();
				}
				else
				{
					childStatus = ChildThread.STATUS_HAS_FINISHED;
					con.disconnect();
				}

				System.out.println("Thread " + threadID + " finished.");
				ta.append("Thread" + threadID + "finished\n");
				// 子线程将latch减1
				latch.countDown();
				break;
			} catch (IOException e)
			{
				try
				{
					outputS.close();
					// inputS.close();
					con.disconnect();
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e1)
				{
					e1.printStackTrace();
				} catch (IOException e2)
				{
					e2.printStackTrace();
				}
				continue;
			}
			// catch (InterruptedException e1)
			// {
			// try
			// {
			// outputS.close();
			// inputS.close();
			// con.disconnect();
			// } catch (IOException e2)
			// {
			// e2.printStackTrace();
			// }
			// }
			// ;
		}// end of for
	}// end of run()
}// end of class