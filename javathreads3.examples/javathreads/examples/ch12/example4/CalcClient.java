package javathreads.examples.ch12.example4;

import java.io.*;
import java.net.*;

public class CalcClient extends Thread {

    long n;
    Socket sock;

    public CalcClient(long n, String host, int port) throws UnknownHostException, IOException {
        this.n = n;
	sock = new Socket(host, port);
    }

    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            dos.writeLong(n);
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            System.out.println("Received answer " + dis.readLong());
        } catch (IOException ioe) {
            System.out.println("Socket error " + ioe);
        }
    }

    public static void main(String[] args) throws Exception {
        int nThreads = Integer.parseInt(args[0]);
        long n = Long.parseLong(args[1]);
        int port = Integer.parseInt(args[3]);
        CalcClient[] clients = new CalcClient[nThreads];
        for (int i = 0; i < nThreads; i++) {
            clients[i] = new CalcClient(n, args[2], port);
            clients[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            clients[i].join();
        }
    }
}
