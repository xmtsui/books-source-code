/**
 * @Projectname DownloadTool
 * @FileName Execute.java
 * @Author MarioTsui
 * @Description Main method
 */

package com.tsui.run;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.tsui.gui.GUI;

/**
 * ³ÌÐòÆô¶¯
 * 
 * @author ´ÞÏþ•F
 * @version version-1.0
 */
public class Execute
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				GUI frame = new GUI();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
