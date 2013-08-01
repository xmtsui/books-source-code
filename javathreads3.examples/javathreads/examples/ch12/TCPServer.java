package javathreads.examples.ch12;

import java.net.*;
import java.io.*;

public class TCPServer implements Cloneable, Runnable {
    Thread runner = null;
    ServerSocket server = null;
    Socket data = null;

    private boolean done = false;

    public synchronized void startServer(int port) throws IOException {
        if (runner == null) {
            server = new ServerSocket(port);
            runner = new Thread(this);
            runner.start();
        }
    }

    public synchronized void stopServer() {
        done = true;
        runner.interrupt();
    }

    protected synchronized boolean getDone() {
        return done;
    }

    public void run() {
        if (server != null) {
            while (!getDone()) {
                try {
                    Socket datasocket = server.accept();
                    TCPServer newSocket = (TCPServer) clone();

                    newSocket.server = null;
                    newSocket.data = datasocket;
                    newSocket.runner =
                        new Thread(newSocket);
                    newSocket.runner.start();
                } catch (Exception e) {}
            }
        } else {
            run(data);
        }
    }
 
    public void run(Socket data) {
    }
}
