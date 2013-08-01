package javathreads.examples.ch02;

import java.awt.*;
import javax.swing.*;

public class CharacterDisplayCanvas extends JComponent implements CharacterListener {
    protected FontMetrics fm;
    protected char[] tmpChar = new char[1];
    protected int fontHeight;

    public CharacterDisplayCanvas() {
        setFont(new Font("Monospaced", Font.BOLD, 18));
        fm = Toolkit.getDefaultToolkit().getFontMetrics(getFont());
        fontHeight = fm.getHeight();
    }

    public CharacterDisplayCanvas(CharacterSource cs) {
        this();
        setCharacterSource(cs);
    }

    public void setCharacterSource(CharacterSource cs) {
        cs.addCharacterListener(this);
    }

    public Dimension preferredSize() {
        return new Dimension(fm.getMaxAscent() + 10,
                             fm.getMaxAdvance() + 10);
    }

    public synchronized void newCharacter(CharacterEvent ce) {
        tmpChar[0] = (char) ce.character;
        repaint();
    }

    protected synchronized void paintComponent(Graphics gc) {
        Dimension d = getSize();
        gc.clearRect(0, 0, d.width, d.height);
        if (tmpChar[0] == 0)
            return;
        int charWidth = fm.charWidth((int) tmpChar[0]);
        gc.drawChars(tmpChar, 0, 1,
                     (d.width - charWidth) / 2, fontHeight);
    }
}
