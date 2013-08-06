package numbercruncher.graphutils;

import java.awt.*;
import java.awt.event.*;

/**
 * The base frame for all standalone demo programs.
 */
public abstract class DemoFrame extends Frame
{
    private String    title;        // window title
    private DemoPanel demoPanel;    // demo panel

    /**
     * Constructor.
     * @param title the window title
     * @param demoPanel the demo panel
     */
    protected DemoFrame(String title, DemoPanel demoPanel)
    {
        this(title, demoPanel, 600, 500);
    }

    /**
     * Constructor.
     * @param title the window title
     * @param demoPanel the demo panel
     * @param width the frame width
     * @param height the frame height
     */
    protected DemoFrame(String title, DemoPanel demoPanel,
                        int width, int height)
    {
        this.title     = title;
        this.demoPanel = demoPanel;
        setTitle(title);
        initFrame(width, height);
    }

    /**
     * Initialize the frame.
     * @param width the frame width
     * @param height the frame height
     */
    private void initFrame(int width, int height)
    {
        // Center the demo frame.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(width, height);
        setLocation((screenSize.width  - width )/2,
                    (screenSize.height - height)/2);

        // Add the demo panel.
        setLayout(new BorderLayout());
        add((Panel) demoPanel, BorderLayout.CENTER);

        // Initialize the demo.
        demoPanel.initializeDemo();

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
                resized();
            }
        });
    }

    /**
     * The frame was resized.
     */
    private void resized() { demoPanel.panelResized(); }

    /**
     * Update the display without repainting the background.
     * @param g the graphics context
     */
    public void update(Graphics g)
    {
        demoPanel.draw();
    }
}

