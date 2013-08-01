package javathreads.examples.ch04.example1;

import java.awt.*;
import javax.swing.*;
import javathreads.examples.ch04.*;

public class AnimatedCharacterDisplayCanvas extends CharacterDisplayCanvas implements CharacterListener, Runnable {

    private boolean done = true;
    private int curX = 0;
    private Thread timer = null;

    public AnimatedCharacterDisplayCanvas() {
    }

    public AnimatedCharacterDisplayCanvas(CharacterSource cs) {
        super(cs);
    }

    public synchronized void newCharacter(CharacterEvent ce) {
        curX = 0;
        tmpChar[0] = (char) ce.character;
        repaint();
    }

    protected synchronized void paintComponent(Graphics gc) {
        Dimension d = getSize();
        gc.clearRect(0, 0, d.width, d.height);
        if (tmpChar[0] == 0)
            return;
        int charWidth = fm.charWidth(tmpChar[0]);
        gc.drawChars(tmpChar, 0, 1,
                     curX++, fontHeight);
    }

    public synchronized void run() {
        while (true) {
            try {
                if (done) {
                    wait();
                } else {
                    repaint();
                    wait(100);
                }
            } catch (InterruptedException ie) {
                return;
            }
        }
    }

    public synchronized void setDone(boolean b) {
        done = b;

        if (timer == null) {
            timer = new Thread(this);
            timer.start();
        }
        if (!done)
            notify();
    }
 }
