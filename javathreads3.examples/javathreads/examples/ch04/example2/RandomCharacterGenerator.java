package javathreads.examples.ch04.example2;

import java.util.*;
import javathreads.examples.ch04.*;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
    private static char[] chars;
    private static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
    static {
        chars = charArray.toCharArray();
    }

    private Random random;
    private CharacterEventHandler handler;
    private boolean done = true;

    public RandomCharacterGenerator() {
        random = new Random();
        handler = new CharacterEventHandler();
    }

    public int getPauseTime() {
        return (int) (Math.max(1000, 5000 * random.nextDouble()));
    }

    public void addCharacterListener(CharacterListener cl) {
        handler.addCharacterListener(cl);
    }

    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }

    public void nextCharacter() {
        handler.fireNewCharacter(this,
                                (int) chars[random.nextInt(chars.length)]);
    }

    public synchronized void run() {
        while (true) {
            try {
                if (done) {
                    wait();
                } else {
                    nextCharacter();
                    wait(getPauseTime());
                }
	    } catch (InterruptedException ie) {
	        return;
	    }
	}
    }

    public synchronized void setDone(boolean b) {
        done = b;

        if (!done)
            notify();
    }
}
