package javathreads.examples.ch08.example4;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ScoreLabel extends JLabel implements CharacterListener {
    
    private volatile int score = 0;
    private int char2type = -1;
    private CharacterSource generator = null, typist = null;
    private Lock scoreLock = new ReentrantLock();
    private CharCounter counter;

    public ScoreLabel(CharacterSource generator, CharacterSource typist, CharCounter ct) {
        this.generator = generator;
        this.typist = typist;
        counter = ct;
        if (generator != null)
            generator.addCharacterListener(this);
        if (typist != null)
            typist.addCharacterListener(this);
    }

    public void resetGenerator(CharacterSource newGenerator) {
        try {
            scoreLock.lock();
            if (generator != null)
                generator.removeCharacterListener(this);
            generator = newGenerator;
            if (generator != null)
                generator.addCharacterListener(this);
        } finally {
            scoreLock.unlock();
        }
    }

    public void resetTypist(CharacterSource newTypist) {
        try {
            scoreLock.lock();
            if (typist != null)
                typist.removeCharacterListener(this);
            typist = newTypist;
            if (typist != null)
                typist.addCharacterListener(this);
        } finally {
            scoreLock.unlock();
        }
    }

    public void resetScore() {
       try {
           scoreLock.lock();
           score = 0;
           char2type = -1;
           setScore();
       } finally {
           scoreLock.unlock();
       }
    }

    private void setScore() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setText(Integer.toString(score));
            }
        });
    }

    public void newCharacter(CharacterEvent ce) {
        scoreLock.lock();
        try {
            if (ce.source == generator) {
                if (char2type != -1) {
                    score--;
                    setScore();
        	    counter.incorrectChar(char2type);
                }
                char2type = ce.character;
            }
            else {
                if (char2type != ce.character) {
                    score--;
        	    counter.incorrectChar(ce.character);
                } else {
                    score++;
        	    counter.correctChar(ce.character);
                    char2type = -1;
                }
            }
            setScore();
        } finally {
            scoreLock.unlock();
        }
    }
}
