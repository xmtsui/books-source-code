package javathreads.examples.ch11.example2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import javathreads.examples.ch11.*;

public class AnimatedCharacterDisplayCanvas extends CharacterDisplayCanvas
             implements ActionListener, CharacterListener {

    private int curX;
    private Timer timer;

    public AnimatedCharacterDisplayCanvas(CharacterSource cs) {
        super(cs);
	timer = new Timer(100, this);
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

    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    public void setDone(boolean b) {
        if (!b)
           timer.start();
        else timer.stop();
    }
}
