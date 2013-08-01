package javathreads.examples.ch12;

import java.net.*;
import java.io.*;

public abstract class InterruptibleReader extends Thread {
    private Object lock = new Object();
    private InputStream is;
    private boolean done;
    private int buflen;

    protected void processData(byte[] b, int n) { }

    class ReaderClass extends Thread {
        public void run() {
            byte[] b = new byte[buflen];
            while (!done) {
                try {
                    int n = is.read(b, 0, buflen);
                    processData(b, n);
                } catch (IOException ioe) {
                    done = true;
                }
            }
            synchronized(lock) {
                lock.notify();
            }
        }
    }

    public InterruptibleReader(InputStream is) {
        this(is, 512);
    }

    public InterruptibleReader(InputStream is, int len) {
        this.is = is;
        buflen = len;
    }

    public void run() {
        ReaderClass rc = new ReaderClass();
        synchronized(lock) {
            rc.start();
            while (!done) {
                try {
                    lock.wait();
                } catch (InterruptedException ie) {
                    done = true;
		    rc.interrupt();
                    try {
                        is.close();
                    } catch (IOException ioe) {}
                }
            }
        }
    }
}
