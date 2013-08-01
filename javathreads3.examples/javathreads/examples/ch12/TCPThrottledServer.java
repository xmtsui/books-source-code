package javathreads.examples.ch12;

import java.net.*;
import java.io.*;

public abstract class TCPThrottledServer implements Runnable {
    ServerSocket server = null;
    Thread[] serverThreads;
    volatile boolean done = false;

    public synchronized void startServer(int port, int nThreads) throws IOException {
        server = new ServerSocket(port);

        serverThreads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            serverThreads[i] = new Thread(this);
            serverThreads[i].start();
        }
    }

    public synchronized void setDone() {
        done = true;
    }

    public void run() {
        while (!done) {
            try {
                Socket data;
                data = server.accept();
                run(data);
            } catch (IOException ioe) {
                System.out.println("Accept error " + ioe);
            }
        }
    }

    public void run(Socket data) {
    }
}
