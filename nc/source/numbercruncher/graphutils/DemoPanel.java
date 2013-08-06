package numbercruncher.graphutils;

/**
 * The demo panel interface.
 */
public interface DemoPanel
{
    /**
     * Initialize the demo.
     */
    void initializeDemo();

    /**
     * close the demo.
     */
    void closeDemo();

    /**
     * Draw the contents of the panel.
     */
    void draw();

    /**
     * Notification that the panel was resized.
     */
    void panelResized();
}