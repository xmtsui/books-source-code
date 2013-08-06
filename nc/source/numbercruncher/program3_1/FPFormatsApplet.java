package numbercruncher.program3_1;

import java.awt.*;
import java.applet.*;

/**
 * PROGRAM 3-1a: IEEE 754 Standard (Interactive Applet)
 *
 * Interactive decompose and recompose floating-point numbers
 * according to the IEEE 754 standard.
 */
public class FPFormatsApplet extends Applet
{
    //Initialize the applet
    public void init()
    {
        // Add the demo panel.
        setLayout(new BorderLayout());
        add(new FPFormatsPanel(), BorderLayout.CENTER);
    }
}