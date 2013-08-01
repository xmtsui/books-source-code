package javathreads.examples.ch05.example2;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import javathreads.examples.ch05.*;

public class ScoreLabel extends JLabel implements CharacterListener {
    private AtomicInteger score = new AtomicInteger(0);
    private AtomicInteger char2type = new AtomicInteger(-1);
    private AtomicReference<CharacterSource> generator = null;
    private AtomicReference<CharacterSource> typist = null;

    public ScoreLabel (CharacterSource generator, CharacterSource typist) {
        this.generator = new AtomicReference(generator);
        this.typist = new AtomicReference(typist);

        if (generator != null)
             generator.addCharacterListener(this);
        if (typist != null)
             typist.addCharacterListener(this);       
    }

    public ScoreLabel () {
        this(null, null);
    }

    public void resetGenerator(CharacterSource newGenerator) {
        CharacterSource oldGenerator;

        if (newGenerator != null)
            newGenerator.addCharacterListener(this);

        oldGenerator = generator.getAndSet(newGenerator);
        if (oldGenerator != null)
            oldGenerator.removeCharacterListener(this);
    }

    public void resetTypist(CharacterSource newTypist) {
        CharacterSource oldTypist;

        if (newTypist != null)
            newTypist.addCharacterListener(this);

        oldTypist = typist.getAndSet(newTypist);
        if (oldTypist != null)
            oldTypist.removeCharacterListener(this);
    }

    public void resetScore() {
        score.set(0);
        char2type.set(-1);
        setScore();
    }

    private void setScore() {
        // This method will be explained later in chapter 7
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setText(Integer.toString(score.get()));
            }
        });
    }

    public void newCharacter(CharacterEvent ce) {
        int oldChar2type;
 
        // Previous character not typed correctly - 1 point penalty
        if (ce.source == generator.get()) {
            oldChar2type = char2type.getAndSet(ce.character);

            if (oldChar2type != -1) {
                score.decrementAndGet();
                setScore();
            }
        }
        // If character is extraneous - 1 point penalty
        // If character does not match - 1 point penalty
        else if (ce.source == typist.get()) {
            while (true) {
                oldChar2type = char2type.get();

                if (oldChar2type != ce.character) {
                    score.decrementAndGet();
                    break;
                } else if (char2type.compareAndSet(oldChar2type, -1)) {
                    score.incrementAndGet();
                    break;
                }
            }

            setScore();
        }
    } 
}
