package numbercruncher.graphutils;

import java.awt.*;
import java.applet.*;

/**
 * The base applet for all demo applets.
 */
public abstract class DemoApplet extends Applet
{
    private DemoPanel demoPanel;    // demo panel

    /**
     * Constructor.
     * @param demoPanel the demo panel
     */
    protected DemoApplet(DemoPanel demoPanel)
    {
        this.demoPanel = demoPanel;
    }

    /**
     * Applet initializer.
     */
    public void init()
    {
        // Add the demo panel.
        setLayout(new BorderLayout());
        add((Panel) demoPanel, BorderLayout.CENTER);

        // Initialize the demo.
        demoPanel.initializeDemo();
    }

    /**
     * Applet starter.
     */
    public void start()
    {
//	repaint();
        paint(this.getGraphics());
    }

    /**
     * Applet stopper.
     */
    public void stop()
    {
        demoPanel.closeDemo();
    }

    /**
     * Update the display without repainting the background.
     * @param g the graphics context
     */
    public void update(Graphics g)
    {
        paint(g);
    }

    /**
     * Redraw the contents of the demo panel.
     * @param g the graphics context
     */
    public void paint(Graphics g)
    {
        demoPanel.draw();
    }
}