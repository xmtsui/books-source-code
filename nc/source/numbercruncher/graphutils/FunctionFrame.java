package numbercruncher.graphutils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.*;
import java.net.*;

/**
 * The window that displays the image of all the functions to plot.
 * The user clicks on a function to select it.
 */
public class FunctionFrame extends Frame
{
    /** image of functions */           private Image    image;
    /** image width */                  private int      imageWidth;
    /** image height */                 private int      imageHeight;
    /** true if image error occurred */ private boolean  imageError = false;
    /** error label */                  private TextArea errorText;

    /** image buffer */                 private Image    buffer;
    /** buffer grapics context */       private Graphics bg;

    /** function selection index */     private int xSelection = 0;

    /** array of functions to plot */   private Plottable  functions[];
    /** root finder panel */            private GraphPanel graphPanel;

    /**
     * Constructor.
     * @param functions the array of functions to plot
     * @param functionImageFileName the name of the function image file
     * @param title the frame title
     * @param graphPanel the graph panel
     */
    public FunctionFrame(Plottable functions[], String functionImageFileName,
                         String title, GraphPanel graphPanel)
    {
        this.functions  = functions;
        this.graphPanel = graphPanel;

        initFrame(title);
        loadImage(functionImageFileName);
    }

    /**
     * Return the image.
     * @return the image
     */
    public Image getImage() { return image; }

    /**
     * Return the array of functions to plot.
     * @return the function array
     */
    public Plottable[] getFunctions() { return functions; }

    /**
     * Initialize the function frame.
     */
    private void initFrame(String title)
    {
        setTitle(title);
        setResizable(false);
        setBackground(Color.white);
        setLocation(25, 25);

        // Window event handlers.
        addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent ev)
            {
//                repaint();
                paint(FunctionFrame.this.getGraphics());
            }
        });

        // Mouse event handlers.
        addMouseListener(new MouseAdapter()
        {
            // Mouse click:  Select a function.
            public void mouseClicked(MouseEvent ev)
            {
                Insets in = getInsets();
                int x = ev.getX() - in.left;
                int y = ev.getY() - in.top;

                // Loop to find which function (if any) was clicked on.
                for (int i = 0; i < functions.length; ++i) {
                    Rectangle r = functions[i].getRectangle();
                    if (r.contains(x, y) && (i != xSelection)) {
                        xSelection = i;
                        repaint();

                        graphPanel.chooseFunction(i);  // callback
                        break;
                    }
                }
            }
        });
    }

    /**
     * Load the function image file.
     * @param functionImageFileName the file name of the image file
     */
    private void loadImage(String functionImageFileName)
    {
        Container parent = graphPanel.getParent();

        // Construct the URL string for the function image file.  For
        // a standalone application, the file should be in the current
        // working directory.  For an applet, the file should be in
        // the images subdirectory in the code base.
        String imageBase =
            (parent instanceof Frame)
                ? "file:///" + System.getProperty("user.dir") + "/"
                : ((Applet) parent).getCodeBase() + "images/";
        String urlString = imageBase + functionImageFileName;

        try {
            // Load the function image file.
            URL url = new URL(urlString);
            image = Toolkit.getDefaultToolkit().getImage(url);

            // Wait for the image to load.
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            tracker.waitForID(0);

            imageWidth  = image.getWidth(null);
            imageHeight = image.getHeight(null);

            // Did the image load sucessfully?
            if ((imageWidth <= 0) && (imageHeight <= 0)) {
                throw new Exception("Could not find \"" + urlString + "\"");
            }

            repaint();
        }
        catch(Exception ex) {
            imageError = true;

            // Load the error message into the frame instead.
            errorText = new TextArea("Error loading function image file:\n");
            errorText.append(ex.getMessage());
            errorText.setForeground(GraphPanel.MAROON);
            errorText.setEditable(false);

            setLayout(new BorderLayout());
            add(errorText, BorderLayout.CENTER);

            // Display the error message.
            setSize(520, 100);
            setTitle("***** ERROR *****");
            setVisible(true);
        }
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
     * Paint the function frame.
     * @param g the graphics context
     */
    public void paint(Graphics g)
    {
        // If there was an error, display the error message.
        if (imageError) {
            super.paint(g);
            return;
        }

        Insets in = getInsets();
        g.translate(in.left, in.top);

        // Create the image buffer.
        if (buffer == null) {
            buffer = createImage(imageWidth, imageHeight);
            bg     = buffer.getGraphics();
            bg.setColor(Color.white);
        }

        // Set the frame size to fit the image.
        setSize(imageWidth + in.left + in.right,
                imageHeight + in.top + in.bottom);

        // Draw the image into the buffer.
        bg.setPaintMode();
        bg.fillRect(0, 0, imageWidth, imageHeight);
        bg.drawImage(image, 0, 0, this);

        // Highlight the selected function.
        if (xSelection > -1) {
            Rectangle r = functions[xSelection].getRectangle();

            bg.setXORMode(Color.blue);
            bg.fillRect(r.x, r.y, r.width, r.height);
        }

        // Display the buffer.
        g.drawImage(buffer, 0, 0, this);
    }
}