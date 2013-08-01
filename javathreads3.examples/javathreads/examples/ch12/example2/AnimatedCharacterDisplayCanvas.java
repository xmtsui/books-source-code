package javathreads.examples.ch12.example2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import javathreads.examples.ch12.*;

public class AnimatedCharacterDisplayCanvas extends CharacterDisplayCanvas
             implements CharacterListener, Runnable {

    private volatile boolean done = false;
    private int curX;
    private Lock lock = new ReentrantLock();
    private Condition cv = lock.newCondition();
    private Thread timer = null;

    public AnimatedCharacterDisplayCanvas(CharacterSource cs) {
        super(cs);
    }

    public synchronized void newCharacter(CharacterEvent ce) {
        curX = 0;
        tmpChar[0] = (char) ce.character;
        repaint();
    }

    public synchronized void paintComponent(Graphics gc) {
        if (tmpChar[0] == 0)
            return;
        Dimension d = getSize();
        int charWidth = fm.charWidth(tmpChar[0]);
        gc.clearRect(0, 0, d.width, d.height);
        gc.drawChars(tmpChar, 0, 1, curX++, fontHeight);
        if (curX > d.width - charWidth)
            curX = 0;
    }

    public void run() {
        try {
            lock.lock();
            while (true) {
                try {
                    if (done) {
                        cv.await();
                  } else {
                      repaint();
                      cv.await(100, TimeUnit.MILLISECONDS);
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
            if (timer == null) {
                timer = new Thread(this);
                timer.start();
           }
           if (!done) cv.signal();
        } finally {
            lock.unlock();
        }
    }
}
