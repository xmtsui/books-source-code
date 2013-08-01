package javathreads.examples.ch07.example2;

import java.lang.reflect.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import javathreads.examples.ch07.*;

public class ScoreLabel extends JLabel implements CharacterListener {
    
    private volatile int score = 0;
    private int char2type = -1;
    private CharacterSource generator = null, typist = null;
    private Lock scoreLock = new ReentrantLock();

    public ScoreLabel(CharacterSource generator, CharacterSource typist) {
        this.generator = generator;
        this.typist = typist;
        if (generator != null)
            generator.addCharacterListener(this);
        if (typist != null)
            typist.addCharacterListener(this);
    }

    public ScoreLabel() {
        this(null, null);
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
	if (SwingUtilities.isEventDispatchThread())
	    setText(Integer.toString(score));
	else try {
	    SwingUtilities.invokeAndWait(new Runnable() {
	    	public void run() {
		    setText(Integer.toString(score));
		}
	    });
	} catch (InterruptedException ie) {
	} catch (InvocationTargetException ite) {}
    }

    public void newCharacter(CharacterEvent ce) {
        scoreLock.lock();
        try {
            if (ce.source == generator) {
                if (char2type != -1) {
                    score--;
                    setScore();
                }
                char2type = ce.character;
            }
            else {
                if (char2type != ce.character) {
                    score--;
                } else {
                    score++;
                    char2type = -1;
                }
            }
            setScore();
        } finally {
            scoreLock.unlock();
        }
    }
}
