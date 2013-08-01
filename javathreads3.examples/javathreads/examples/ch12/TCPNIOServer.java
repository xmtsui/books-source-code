package javathreads.examples.ch12;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.*;

public abstract class TCPNIOServer implements Runnable {
    protected ServerSocketChannel channel = null;
    private boolean done = false;
    protected Selector selector;
    protected int port = 8000;

    public void startServer() throws IOException {
        channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        ServerSocket server = channel.socket();
        server.bind(new InetSocketAddress(port));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public synchronized void stopServer() throws IOException {
        done = true;
        channel.close();
    }

    protected synchronized boolean getDone() {
        return done;
    }

    public void run() {
        try {
            startServer();
        } catch (IOException ioe) {
            System.out.println("Can't start server:  " + ioe);
            return;
        }
        while (!getDone()) {
            try {
                selector.select();
            } catch (IOException ioe) {
                System.err.println("Server error: " + ioe);
                return;
            }
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if (key.isReadable() || key.isWritable()) {
                    // Key represents a socket client
                    try {
                        handleClient(key);
                    } catch (IOException ioe) {
                        // Client disconnected
                        key.cancel();
                    }
                } else if (key.isAcceptable()) {
                    try {
                        handleServer(key);
                    } catch (IOException ioe) {
                        // Accept error; treat as fatal
                        throw new IllegalStateException(ioe);
                    }
                } else System.out.println("unknown key state");
                it.remove();
            }
        }
    }

    protected void handleServer(SelectionKey key) throws IOException {
         SocketChannel sc = channel.accept();
         sc.configureBlocking(false);
         sc.register(selector, SelectionKey.OP_READ);
         registeredClient(sc);
     }

    protected abstract void handleClient(SelectionKey key) throws IOException;
    protected abstract void registeredClient(SocketChannel sc) throws IOException;
}
