package javathreads.examples.ch12.example3;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;
import javathreads.examples.ch12.*;

public class TypeServer extends TCPNIOServer {
    static String testString = "Thisisateststring";
    static class ClientInfo {
        ByteBuffer inBuf = ByteBuffer.allocateDirect(512);
        ByteBuffer outBuf = ByteBuffer.allocateDirect(512);
        boolean outputPending = false;
        SocketChannel channel;
    }
    Map allClients = new HashMap();
    Charset encoder = Charset.forName("UTF-8");

    protected void handleClient(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
       ClientInfo ci = (ClientInfo) allClients.get(sc);
        if (ci == null)
            throw new IllegalStateException("Unknown client");
        if (key.isWritable())
            send(sc, ci);
        if (key.isReadable())
            recv(sc, ci);
    }

    private void recv(SocketChannel sc, ClientInfo ci) throws IOException {
        ci.channel.read(ci.inBuf);
        ByteBuffer tmpBuf = ci.inBuf.duplicate();
        tmpBuf.flip();
        int bytesProcessed = 0;
        boolean doneLoop = false;
        while (!doneLoop) {
            byte b;
            try {
                b = tmpBuf.get();
            } catch (BufferUnderflowException bue) {
                // Processed all data in buffer
               ci.inBuf.clear();
                doneLoop = true;
                break;
            }
            switch(b) {
                case TypeServerConstants.WELCOME:
                    bytesProcessed++;
                    break;
                case TypeServerConstants.GET_STRING_REQUEST:
                    bytesProcessed++;
                    if (ci.outputPending) {
                        // Client is backed up. We can't append to
                        // the byte buffer because it's in the wrong
                        // state. We could allocate another buffer
                        // here and change our send method to know
                        // about multiple buffers, but we'll just
                        // assume that the client is dead
                        break;
                    }
                    ci.outBuf.put(TypeServerConstants.GET_STRING_RESPONSE);
                    ByteBuffer strBuf = encoder.encode(testString);
		    ci.outBuf.putShort((short) strBuf.remaining());
                    ci.outBuf.put(strBuf);
                    ci.outBuf.flip();
                    send(sc, ci);
                    break;
                case TypeServerConstants.GET_STRING_RESPONSE:
                    int startPos = tmpBuf.position();
                    try {
                        int nBytes = tmpBuf.getInt();
                        byte[] buf = new byte[nBytes];
                        tmpBuf.get(buf);
                        bytesProcessed += buf.length + 5;
                        String s = new String(buf);
                        // Send the string to the GUI
                        break;
                    } catch (BufferUnderflowException bue) {
                        // Processed all available data
                        ci.inBuf.position(ci.inBuf.position() + bytesProcessed);
                        doneLoop = true;
                    }
                    break;
            }
        }
    }

    private void send(SocketChannel sc, ClientInfo ci) throws IOException {
        int len = ci.outBuf.remaining();
        int nBytes = sc.write(ci.outBuf);
        if (nBytes != len) {
            // Client not ready to receive data
            ci.outputPending = true;
            ci.channel.register(selector,
                         SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        }
        else {
            ci.outBuf.clear();
            if (ci.outputPending) {
                ci.outputPending = false;
                ci.channel.register(selector, SelectionKey.OP_READ);
            }
        }
    }

    protected void registeredClient(SocketChannel sc) throws IOException {
        ClientInfo ci = new ClientInfo();
        ci.channel = sc;
        ci.outBuf.clear();
        ci.outBuf.put(TypeServerConstants.WELCOME);
        ci.outBuf.flip();
        allClients.put(sc, ci);
        send(sc, ci);
    }

    public static void main(String[] args) throws Exception {
        TypeServer ts = new TypeServer();
        ts.port = Integer.parseInt(args[0]);
        Thread t = new Thread(ts);
        t.start();
        System.out.println("Type server ready...Type CTRL-D to exit");
        while (System.in.read() > 0)
            ;
        ts.stopServer();
        t.join();
    }
}
