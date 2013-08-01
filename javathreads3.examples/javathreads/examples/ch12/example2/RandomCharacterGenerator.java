package javathreads.examples.ch12.example2;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import javathreads.examples.ch12.*;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
    private char[] chars;
    private int curChar;
    private Random random = new Random();
    private CharacterEventHandler handler;
    private boolean done = true;
    private Lock lock = new ReentrantLock();
    private Condition cv = lock.newCondition();
    private String host;
    private int port;

    public RandomCharacterGenerator(String host, int port) {
        handler = new CharacterEventHandler();
        this.host = host;
        this.port = port;
    }

    private synchronized void getString() throws IOException {
        Socket sock = new Socket(host, port);
        DataInputStream reader = new DataInputStream(sock.getInputStream());
        reader.read();        // Welcome
        DataOutputStream writer = new DataOutputStream(sock.getOutputStream());
        byte b = TypeServerConstants.GET_STRING_REQUEST;
        writer.write(b);
        writer.flush();
        b = (byte) reader.readByte();
        if (b != TypeServerConstants.GET_STRING_RESPONSE) 
            throw new IllegalStateException("Bad recv state " + b);
        String s = reader.readUTF();
        chars = s.toCharArray();
        curChar = 0;
        sock.close();
    }

    public int getPauseTime(int minTime, int maxTime) {
        return (int) (minTime + ((maxTime-minTime)*random.nextDouble()));
    }

    public int getPauseTime() {
        return getPauseTime(2000, 5500);
    }

    public void addCharacterListener(CharacterListener cl) {
        handler.addCharacterListener(cl);
    }

    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }

    public void nextCharacter() {
        try {
            if (chars == null)
                getString();
            handler.fireNewCharacter(this,
                                    (int) chars[curChar++]);
            if (curChar == chars.length)
                    getString();
        } catch (IOException ioe) {
            // Put up a dialog box to alert user of error
        }
    }

    public void run() {
        try {
            lock.lock();
            while (true) {
                try {
                    if (done) {
                        cv.await();
                    } else {
                        nextCharacter();
                        cv.await(getPauseTime(), TimeUnit.MILLISECONDS);
                    }
                } catch (InterruptedException ie) {
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void setDone(boolean b) {
        try {
            lock.lock();
            done = b;

            if (!done) cv.signal();
        } finally {
            lock.unlock();
        }
    }
}
