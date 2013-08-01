package javathreads.examples.ch12.example1;

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
    private Socket sock;
    private DataInputStream reader;
    private DataOutputStream writer;

    public RandomCharacterGenerator(String host, int port) throws IOException {
        handler = new CharacterEventHandler();
        sock = new Socket(host, port);
        reader = new DataInputStream(sock.getInputStream());
        reader.read();        // Welcome
        writer = new DataOutputStream(sock.getOutputStream());
        getString();
    }

    private synchronized void getString() throws IOException {
        byte b = TypeServerConstants.GET_STRING_REQUEST;
        writer.write(b);
        writer.flush();
        b = (byte) reader.readByte();
        if (b != TypeServerConstants.GET_STRING_RESPONSE) 
            throw new IllegalStateException("Bad recv state " + b);
        String s = reader.readUTF();
        chars = s.toCharArray();
        curChar = 0;
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
        handler.fireNewCharacter(this,
                                (int) chars[curChar++]);
	if (curChar == chars.length) {
	    try {
	        getString();
	    } catch (IOException ioe) {
	        // Put up a dialog box to alert user of error
	    }
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
