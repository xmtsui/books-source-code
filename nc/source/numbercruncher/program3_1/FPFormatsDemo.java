package numbercruncher.program3_1;

import java.awt.*;
import java.awt.event.*;

/**
 * PROGRAM 3-1d: IEEE 754 Standard (Interactive Standalone Demo)
 *
 * Interactive decompose and recompose floating-point numbers
 * according to the IEEE 754 standard.
 */
public class FPFormatsDemo extends Frame
{
    public FPFormatsDemo()
    {
        setSize(new Dimension(610, 480));
        setBackground(SystemColor.scrollbar);
        setTitle("IEEE 754 Floating-Point Formats");

        // Add the demo panel.
        setLayout(new BorderLayout());
        add(new FPFormatsPanel(), BorderLayout.CENTER);

        // Window event handlers.
        addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent ev)
            {
                repaint();
            }

            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });

        // Resize event handler.
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent ev)
            {
                repaint();
            }
        });
    }

    //Main method
    public static void main(String[] args)
    {
        (new FPFormatsDemo()).setVisible(true);
    }
}