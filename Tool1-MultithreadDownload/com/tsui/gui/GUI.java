/**
 * @Projectname Download_Tool--NetGothic
 * @FileName GUI.java
 * @Author MarioTsui
 * @Description Interface Design
 */

package com.tsui.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.tsui.threadlogic.MainThread;

/**
 * 界面设计
 * 
 * @author 崔晓旻
 * @version version-1.1增加了退出时的对话框,可以选择是否保存断点信息
 */

public class GUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
	 * 界面变量定义 主子线程定义
	 */
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;

	private JProgressBar jProgBar;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;

	private JLabel srcPathLabel;
	private JTextField srcPathInput;

	private JLabel savePathLabel;
	private JTextField savePathInput;

	private JLabel threadNumLabel;
	private JSpinner threadNumSpinner;

	private JButton select;
	private JButton start;
	private JButton restart;
	private JButton pause;
	private JButton quit;

	MainThread main = null;
	Thread threadMain = null;

	/**
	 * GUI构造函数
	 */
	public GUI()
	{
		super("TsuiTool");

		// 构件初始化
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();

		jProgBar = new JProgressBar(0, 100);
		jTextArea = new JTextArea(15, 20);
		jScrollPane = new JScrollPane(jTextArea);

		srcPathLabel = new JLabel("下载地址：");
		srcPathInput = new JTextField(20);
		savePathLabel = new JLabel("保存位置：");
		savePathInput = new JTextField(20);

		select = new JButton("另存为");
		start = new JButton("开始下载");
		pause = new JButton("暂停下载");
		restart = new JButton("继续下载");
		quit = new JButton("退出");

		// 初值为5，最小值0，最大值20，步长1
		threadNumSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 20, 1));
		threadNumLabel = new JLabel("线程数");

		// JFrame布局初始化
		setLayout(new BorderLayout());
		setSize(370, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		add(p1, BorderLayout.NORTH);
		add(p4, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);

		// Panel 1---BorderLayout.NORTH
		p1.add(jScrollPane);

		// Panel 4---BorderLayout.CENTER
		p4.setLayout(new GridLayout(2, 1));
		p4.add(p2);
		p4.add(p3);
		p2.add(srcPathLabel);

		p2.add(srcPathInput);
		srcPathInput.setText("http://bolofski.freeservers.com/tobewithyou.mp3");
		p2.add(jProgBar);
		jProgBar.setStringPainted(true);

		p2.add(threadNumLabel);
		p2.add(threadNumSpinner);
		p2.add(select);
		p3.add(savePathLabel);
		p3.add(savePathInput);

		// Panel 5---BorderLayout.SOUTH
		p5.add(start);
		// p5.add(pause);
		// p5.add(restart);
		p5.add(quit);

		// 添加Action
		// Button 开始下载 add Action
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				startAction(e);
			}
		});

		// Button 另存为 add Action
		select.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectAction(e);
			}

		});

		// Button 暂停下载 add Action
		pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pauseAction(e);
			}
		});

		// Button 继续下载 add Action
		restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				restartAction(e);
			}
		});

		// Button 退出 add Action
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				quitAction(e);
			}
		});
	}

	/**
	 * selectAction 事件响应（“另存为”按钮事件响应）
	 * 
	 * @param e
	 *            ActionEvent （另存为按钮事件）
	 * @return void
	 */
	private void selectAction(ActionEvent e)
	{
		String URL = srcPathInput.getText();
		if (URL.equals(""))
			jTextArea.setText("请输入下载地址");

		Frame f = new Frame("另存为");
		FileDialog fd = new FileDialog(f, "另存为", FileDialog.SAVE);
		int index = URL.lastIndexOf(".");
		String ends = URL.substring(index, URL.length());
		fd.setFile("*" + ends);
		fd.setVisible(true);
		try
		{
			String savepath = fd.getDirectory();
			String savename = fd.getFile();
			if (savename != null)
			{
				savePathInput.setText(savepath + savename);
			}
		} catch (Exception esave)
		{
		}
	}

	/**
	 * startAction 事件响应（“开始下载”按钮事件响应）
	 * 
	 * @param e
	 *            ActionEvent （“开始下载”按钮事件）
	 * @return void
	 */
	private void startAction(ActionEvent e)
	{

		if (srcPathInput.getText().equals(""))
		{
			jTextArea.setText("请输入完整的下载地址");
		}
		else if (savePathInput.getText().equals(""))
		{
			jTextArea.setText("请输入完整的保存地址");
		}
		else
		{
			// 主子线程传递参数
			String guiURL = srcPathInput.getText();
			String guiURI = savePathInput.getText();
			Integer guiThreadNum1 = (Integer) threadNumSpinner.getValue();
			int guiThreadNum = guiThreadNum1.intValue();
			// 主子线程初始化
			main = new MainThread(jTextArea, jProgBar, guiURL, guiURI,
					guiThreadNum);
			threadMain = new Thread(main);

			// 主子线程执行
			threadMain.start();

			// 从持久类中读取信息，并设置进度条的值域
			jProgBar.setMaximum((int) main.getProp().getContentLen());
			jProgBar.setMinimum(0);
		}
	}

	/**
	 * pauseAction 事件响应（“暂停下载”按钮事件响应）
	 * 
	 * @param e
	 *            ActionEvent （“暂停下载”按钮事件）
	 * @return void
	 */
	private void pauseAction(ActionEvent e)
	{
		jTextArea.append("Now Pausing...");
	}

	/**
	 * restartAction 事件响应（“重新下载”按钮事件响应）
	 * 
	 * @param ActionEvent
	 *            e （“重新下载”按钮事件）
	 * @return void
	 */
	private void restartAction(ActionEvent e)
	{
		jTextArea.append("Now restart...");
	}

	/**
	 * quitAction 事件响应（“退出”按钮事件响应）
	 * 
	 * @param e
	 *            ActionEvent （“退出”按钮事件）
	 * @return void
	 */
	private void quitAction(ActionEvent e)
	{
		jTextArea.append("Now quit...\n");
		int value = JOptionPane.showConfirmDialog((Component) null, "是否保存断点信息",
				"提示", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);

		if (main != null)
		{
			jTextArea.append("正在终止线程...\n");
			if (value == 0)
			{
				main.shutdownDownloadThread(value);
				System.exit(0);
			}
			else if (value == 1)
			{
				main.shutdownDownloadThread(value);
				System.exit(0);
			}
		}
		else
		{
			if (value != 2)
				System.exit(0);
		}
	}

}
