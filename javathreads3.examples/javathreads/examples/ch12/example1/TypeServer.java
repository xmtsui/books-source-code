package javathreads.examples.ch12.example1;

import java.io.*;
import java.net.*;
import javathreads.examples.ch12.*;

public class TypeServer extends TCPServer {
    public void run(Socket data) {
        try {
            DataOutputStream dos =
                   new DataOutputStream(data.getOutputStream());
            dos.writeByte(TypeServerConstants.WELCOME);
            DataInputStream dis =
                  new DataInputStream(data.getInputStream());
            while (true) {
                byte b = dis.readByte();
                if (b != TypeServerConstants.GET_STRING_REQUEST) {
                    System.out.println("Client sent unknown request " + b);
                    continue;
                }
                dos.writeByte(TypeServerConstants.GET_STRING_RESPONSE);
                dos.writeUTF("Thisisateststring");
                dos.flush();
            }
        } catch (Exception e) {
            System.out.println("Client terminating: " + e);
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        TypeServer ts = new TypeServer();
        ts.startServer(Integer.parseInt(args[0]));
        System.out.println("Server ready and waiting...");
    }
}
