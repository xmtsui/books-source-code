package numbercruncher.graphutils;

import java.awt.*;
import java.awt.event.*;

/**
 * The header panel that displays the current function or a text label.
 */
class HeaderPanel extends Panel
{
    private static final String FUNCTION = "function";
    private static final String MESSAGE  = "message";

    private static final Color BACKGROUND_COLOR = Color.lightGray;
    private static final Color MAROON           = new Color(128, 0, 0);

    /** function
        card */     private Panel functionCard = new Panel();
    /** message
        card */     private Panel messageCard = new Panel();
    /** card
        layout */   private CardLayout cardLayout = new CardLayout();
    /** button
        panel */    private Panel buttonPanel = new Panel();
    /** image
        panel */    private ImagePanel imagePanel = new ImagePanel();
    /** header
        button */   private Button headerButton = new Button("Show functions");
    /** header
        label */    private Label label = new Label();

    /** function image */               private Image        image;
    /** the selected function */        private Plottable    function;
    /** function panel dimensions */    private Dimension    fpDimensions;
    /** parent graph panel */           private GraphPanel   graphPanel;

    /**
     * Constructor.
     * @param functions the array of functions to plot
     * @param graphPanel the parent graph panel
     */
    HeaderPanel(Plottable functions[], GraphPanel graphPanel)
    {
        this();
        this.graphPanel = graphPanel;

        fpDimensions = maxFunctionDimensions(functions);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(headerButton);

        imagePanel.setSize(fpDimensions);

        functionCard.add(buttonPanel,   BorderLayout.WEST);
        functionCard.add(imagePanel, BorderLayout.CENTER);

        cardLayout.show(this, FUNCTION);

        // Header button handler.
        headerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                HeaderPanel.this.graphPanel.doHeaderAction();   // callback
            }
        });
    }

    /**
     * Constructor.
     * @param headerText the header label text
     */
    HeaderPanel(String headerText)
    {
        this();

        label.setText(headerText);
        cardLayout.show(this, MESSAGE);
    }

    /**
     * Constructor.
     */
    private HeaderPanel()
    {
        setBackground(BACKGROUND_COLOR);
        setLayout(cardLayout);

        add(functionCard, FUNCTION);
        add(messageCard,  MESSAGE );

        functionCard.setBackground(BACKGROUND_COLOR);
        functionCard.setLayout(new BorderLayout());

        messageCard.setBackground(BACKGROUND_COLOR);
        messageCard.setLayout(new BorderLayout());

        label.setFont(new Font("Dialog", Font.BOLD, 12));
        label.setAlignment(Label.CENTER);
        label.setForeground(Color.black);

        messageCard.add(label, BorderLayout.CENTER);
    }

    /**
     * Set the function image.
     * @param image the image
     */
    void setImage(Image image) { this.image = image; }

    /**
     * Set the header label text in the default black color.
     * @param text the text
     */
    void setLabel(String text)
    {
        setLabel(text, Color.black);
    }

    /**
     * Set the header label text in color.
     * @param text the text
     * @param color the color
     */
    void setLabel(String text, Color color)
    {
        cardLayout.show(this, MESSAGE);
        label.setForeground(color);
        label.setText(text);
    }

    /**
     * Set a function and repaint the function panel.
     * @param xSelection the selected function index
     */
    void setFunction(Plottable function)
    {
        this.function = function;
        cardLayout.show(this, FUNCTION);
        imagePanel.repaint();
    }

    /**
     * Display an error message.
     * @param message the error message
     */
    void displayError(String message)
    {
        cardLayout.show(this, MESSAGE);
        label.setForeground(MAROON);
        label.setText("ERROR:  " + message);
    }

    /**
     * Return the minimum size of the header panel.  The height is either
     * the height of the tallest function in the image, or the height of the
     * header label.
     * @return the minimum size
     */
    public Dimension getMinimumSize()
    {
        int height = (fpDimensions != null)
                            ? fpDimensions.height
                            : label.getMinimumSize().height;

        return new Dimension(Short.MAX_VALUE, height);
    }

    /**
     * Return the preferred size of the header panel, which is its minimum size.
     * @return the preferred size
     */
    public Dimension getPreferredSize()
    {
        return getMinimumSize();
    }

    /**
     * Return the maximum display dimensions of the functions.
     * @param functions the functions to find roots for
     * @return the dimensions
     */
    private Dimension maxFunctionDimensions(Plottable functions[])
    {
        int maxWidth  = 0;
        int maxHeight = 0;

        // Loop over the functions array to find the maximum width and height.
        for (int i = 0; i < functions.length; ++i) {
            Rectangle r = functions[i].getRectangle();
            if (r.width > maxWidth) {
                maxWidth = r.width;
            }
            if (r.height > maxHeight) {
                maxHeight = r.height;
            }
        }

        return new Dimension(maxWidth, maxHeight);
    }

    /**
     * The function panel class.
     */
    private class ImagePanel extends Panel
    {
        /** image buffer */             private Image    buffer;
        /** buffer graphics context */  private Graphics bg;

        /**
         * Display the current function.
         */
        public void paint(Graphics g)
        {
            if (function == null) return;

            // Get the function's display region.
            Rectangle r = function.getRectangle();
            if (r == null) return;

            Dimension fp = this.getSize();

            // Create the image buffer.
            if (buffer == null) {
                buffer = createImage(fp.width, fp.height);
                bg     = buffer.getGraphics();
                bg.setColor(BACKGROUND_COLOR);
            }

            int sx1 = r.x;
            int sy1 = r.y;
            int sx2 = r.x + r.width  - 2;
            int sy2 = r.y + r.height - 1;

            int sw = sx2 - sx1 + 1;
            int sh = sy2 - sy1 + 1;

            int dx1 = (fp.width  - sw)/2;
            int dy1 = (fp.height - sh)/2;
            int dx2 = dx1 + sw - 1;
            int dy2 = dy1 + sh - 1;

            // Copy the image of the selected function into the buffer.
            bg.setPaintMode();
            bg.fillRect(0, 0, fp.width, fp.height);
            bg.drawImage(image, dx1, dy1, dx2, dy2,
                                sx1, sy1, sx2, sy2,
                                this);

            // Display the buffer.
            g.drawImage(buffer, 0, 0, this);
        }
    }
}