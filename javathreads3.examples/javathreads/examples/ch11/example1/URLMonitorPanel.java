package javathreads.examples.ch11.example1;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.util.Timer;

public class URLMonitorPanel extends JPanel implements URLPingTask.URLUpdate {

    Timer timer;
    URL url;
    URLPingTask task;
    JPanel status;
    JButton startButton, stopButton;

    public URLMonitorPanel(String url, Timer t) throws MalformedURLException {
	setLayout(new BorderLayout());
        timer = t;
        this.url = new URL(url);
        add(new JLabel(url), BorderLayout.CENTER);
	JPanel temp = new JPanel();
        status = new JPanel();
        status.setSize(20, 20);
        temp.add(status);
        startButton = new JButton("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                makeTask();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        stopButton = new JButton("Stop");
        stopButton.setEnabled(true);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                task.cancel();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });
        temp.add(startButton);
        temp.add(stopButton);
	add(temp, BorderLayout.EAST);
        makeTask();
    }

    private void makeTask() {
        task = new URLPingTask(url, this);
        timer.schedule(task, 0L, 5000L);
    }

    public void isAlive(final boolean b) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                status.setBackground(b ? Color.GREEN : Color.RED);
                status.repaint();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("URL Monitor");
        Container c = frame.getContentPane();
	c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        Timer t = new Timer();
        for (int i = 0; i < args.length; i++) {
            c.add(new URLMonitorPanel(args[i], t));
        }
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.show();
    }
}
