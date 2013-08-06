package numbercruncher.program16_2;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import numbercruncher.graphutils.*;
import numbercruncher.mathutils.Complex;

public class JuliaSetPanel extends GraphPanel
{
    private static final String PRESET_NAMES[] = {
        "",
        "Claws", "Snowflakes", "Turtles",
        "Serpents", "Amoebas", "Sparklers"
    };

    private static final float PRESET_REALS[] = {
        0f,
        0.30900264f, 0.33843684f, -0.3985014f,
        -0.8184639f, -0.3346212f, -0.5530404f
    };

    private static final float PRESET_IMAGINARIES[] = {
        0f,
        -0.0339787f, -0.4211402f, 0.5848901f,
        -0.2129812f, 0.6340579f, 0.5933997f
    };

    /** control panel */    private Panel fractalControlPanel = new Panel();
    /** bounds panel */     private Panel boundsPanel         = new Panel();

    /** real label */       private Label realLabel      = new Label("Real:");
    /** imaginary label */  private Label imaginaryLabel = new Label("Imaginary:");

    /** real text */        private TextField realText      = new TextField();
    /** imaginary text */   private TextField imaginaryText = new TextField();

    /** x-minimum label */  private Label xMinLabel = new Label("Min x:");
    /** x-maximum label */  private Label xMaxLabel = new Label("Max x:");
    /** y-minimum label */  private Label yMinLabel = new Label("Min y:");
    /** y-maximum label */  private Label yMaxLabel = new Label("Max y:");

    /** x-minimum text */   private Label xMinText  = new Label();
    /** x-maximum text */   private Label xMaxText  = new Label();
    /** y-minimum text */   private Label yMinText  = new Label();
    /** y-maximum text */   private Label yMaxText  = new Label();

    /** preset choices */   private Choice presets = new Choice();

    /** manual button */    private Button manualButton = new Button("Manual");
    /** random button */    private Button randomButton = new Button("Random");

    /** filler 1 */         private Label filler1 = new Label();
    /** filler 2 */         private Label filler2 = new Label();
    /** filler 3 */         private Label filler3 = new Label();
    /** filler 4 */         private Label filler4 = new Label();

    /** iteration counter */    private int        n          = 0;
    /** fractal plot thread */  private PlotThread plotThread = null;

    /** panel width */          private int   w;
    /** panel height */         private int   h;
    /** real value */           private float real;
    /** imaginary value */      private float imaginary;
    /** minimum x value */      private float xMin;
    /** maximum x value */      private float xMax;
    /** minimum y value */      private float yMin;
    /** maximum y value */      private float yMax;
    /** delta per pixel */      private float delta;
    /** previous minimum x */   private float oldXMin;
    /** previous maximun y */   private float oldYMax;

    /** zoom rectangle upper left row */        private int r1;
    /** zoom rectangle upper left column */     private int c1;
    /** zoom rectangle bottom right row */      private int r2;
    /** zoom rectangle bottom right column */   private int c2;

    /** complex value c */          private Complex c;
    /** true to stop run thread */  private boolean stopFlag = false;

    /** initial plot properties */
    private static PlotProperties plotProps =
                                        new PlotProperties(0, 0, 0, 0);

    /** random number generator */
    Random random = new Random(System.currentTimeMillis());

    /**
     * Constructor.
     */
    JuliaSetPanel()
    {
        super("Julia Set of z^2 + c", plotProps, false);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Fractal controls.
        realLabel.setFont(labelFont);
        realLabel.setAlignment(Label.RIGHT);
        imaginaryLabel.setFont(labelFont);
        imaginaryLabel.setAlignment(Label.RIGHT);
        xMinLabel.setFont(labelFont);
        xMinLabel.setAlignment(Label.RIGHT);
        xMinText.setFont(textFont);
        xMaxLabel.setFont(labelFont);
        xMaxLabel.setAlignment(Label.RIGHT);
        xMaxText.setFont(textFont);
        yMinLabel.setFont(labelFont);
        yMinLabel.setAlignment(Label.RIGHT);
        yMinText.setFont(textFont);
        yMaxLabel.setFont(labelFont);
        yMaxLabel.setAlignment(Label.RIGHT);
        yMaxText.setFont(textFont);

        // Fractal control panel.
        fractalControlPanel.setBackground(Color.lightGray);
        fractalControlPanel.setLayout(new GridLayout(0, 6, 2, 2));
        fractalControlPanel.add(realLabel);
        fractalControlPanel.add(realText);
        fractalControlPanel.add(xMinLabel);
        fractalControlPanel.add(xMinText);
        fractalControlPanel.add(yMinLabel);
        fractalControlPanel.add(yMinText);
        fractalControlPanel.add(imaginaryLabel);
        fractalControlPanel.add(imaginaryText);
        fractalControlPanel.add(xMaxLabel);
        fractalControlPanel.add(xMaxText);
        fractalControlPanel.add(yMaxLabel);
        fractalControlPanel.add(yMaxText);
        fractalControlPanel.add(filler1);
        fractalControlPanel.add(presets);
        fractalControlPanel.add(filler2);
        fractalControlPanel.add(filler3);
        fractalControlPanel.add(manualButton);
        fractalControlPanel.add(randomButton);
        addDemoControls(fractalControlPanel);

        // Load the preset choice names.
        for (int i = 0; i < PRESET_NAMES.length; ++i) {
            presets.addItem(PRESET_NAMES[i]);
        }

        // Preset choice handler.
        presets.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev)
            {
                int index = presets.getSelectedIndex();

                realText.setText(Float.toString(PRESET_REALS[index]));
                imaginaryText.setText(Float.toString(PRESET_IMAGINARIES[index]));

                startPlot();
            }
        });

        // Manual button handler.
        manualButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                presets.select(0);
                startPlot();
            }
        });

        // Random button handler.
        randomButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                float r1 = 2*random.nextFloat() - 1;
                float r2 = 2*random.nextFloat() - 1;

                realText.setText(Float.toString(r1));
                imaginaryText.setText(Float.toString(r2));

                presets.select(0);
                startPlot();
            }
        });
    }

    /**
     * Get the plot properties.
     */
    private void getProperties()
    {
        w     = plotProps.getWidth();
        h     = plotProps.getHeight();
        delta = plotProps.getXDelta();
        xMin  = plotProps.getXMin();
        xMax  = plotProps.getXMax();
        yMin  = yMax - h*delta;
        yMax  = plotProps.getYMax();
    }

    /**
     * Set the plot properties.
     */
    private void setProperties()
    {
        w     = plotProps.getWidth();
        h     = plotProps.getHeight();
        delta = 0.01f;
        xMin  = -(w/2)*delta;
        yMin  = -(h/2)*delta;
        xMax  = -xMin;
        yMax  = -yMin;

        plotProps.update(xMin, xMax, yMin, yMax);
    }

    /**
     * Display the current plot bounds in the text controls.
     */
    private void displayBounds()
    {
        xMinText.setText(Float.toString(xMin));
        xMaxText.setText(Float.toString(xMax));
        yMinText.setText(Float.toString(yMin));
        yMaxText.setText(Float.toString(yMax));
    }

    /**
     * Process the real and imaginary text fields.
     * @throws Exception if a field is invalid
     */
    private void processTextFields() throws Exception
    {
        if ((realText.getText().trim().length()      == 0) ||
            (imaginaryText.getText().trim().length() == 0))
        {
            throw new Exception("You must enter values for the " +
                                "real and imaginary parts.");
        }

        try {
            real = Float.valueOf(realText.getText()).floatValue();
        }
        catch(NumberFormatException ex) {
            throw new Exception("Invalid real part: " +
                                realText.getText());
        }

        try {
            imaginary = Float.valueOf(imaginaryText.getText()).floatValue();
        }
        catch(NumberFormatException ex) {
            throw new Exception("Invalid imaginary part: " +
                                imaginaryText.getText());
        }

        c = new Complex(real, imaginary);
    }

    /**
     * Start the plot.
     */
    private void startPlot()
    {
        try {
            processTextFields();
        }
        catch(Exception ex) {
            processUserError(ex.getMessage());
            return;
        }

        setHeaderLabel("Julia Set of z^2 + " + c.toString());

        stopPlot();     // stop currently running plot

        n = 0;
        draw();
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Plot a function.
     */
    protected void plotFunction()
    {
        // First time or not?
        if (n == 0) {
            setProperties();
        }
        else {
            getProperties();
        }
        displayBounds();

        if ((realText.getText().trim().length()      == 0) ||
            (imaginaryText.getText().trim().length() == 0)) return;

        stopPlot();

        // Start a new plot thread.
        stopFlag   = false;
        plotThread = new PlotThread();
        plotThread.start();

        ++n;
    }

    /**
     * Stop the plot.
     */
    private void stopPlot()
    {
        if ((plotThread != null) && (plotThread.isAlive())) {
            stopFlag = true;

            try {
                plotThread.join();
            }
            catch(Exception ex) {}
        }
    }

    /**
     * Mouse pressed on the plot:  Start the zoom rectangle.
     */
    public void mousePressedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        oldXMin = xMin;
        oldYMax = yMax;

        // Starting corner.
        c1 = ev.getX();
        r1 = ev.getY();

        // Ending corner.
        c2 = -1;
        r2 = -1;

        setXORMode();
    }

    /**
     * Mouse dragged on the plot:  Track the mouse to draw the zoom rectangle.
     */
    public void mouseDraggedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        // Erase the previous rectangle.
        if ((c2 != -1) && (r2 != -1)) {
            plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                          Math.abs(c1 - c2), Math.abs(r1 - r2),
                          Color.black);
        }

        // Current ending corner.
        c2 = ev.getX();
        r2 = ev.getY();

        // Calculate and display new zoom area bounds.
        xMin = oldXMin + delta*Math.min(c1, c2);
        xMax = oldXMin + delta*Math.max(c1, c2);
        yMin = oldYMax - delta*Math.max(r1, r2);
        yMax = oldYMax - delta*Math.min(r1, r2);
        displayBounds();

        // Draw the new rectangle.
        plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                      Math.abs(c1 - c2), Math.abs(r1 - r2),
                      Color.black);
    }

    /**
     * Mouse released on the plot:  End the zoom rectangle and
     * plot the zoomed area.
     */
    public void mouseReleasedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        // Draw the rectangle.
        if ((c2 != -1) && (r2 != -1)) {
            plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                          Math.abs(c1 - c2), Math.abs(r1 - r2),
                          Color.black);
        }

        // Plot the area in the rectangle.
        plotProps.update(xMin, xMax, yMin, yMax);
        draw();
    }

    //-----------//
    // Animation //
    //-----------//

    private static final int MAX_ITERS      = 32;
    private static final int ESCAPE_MODULUS =  2;

    /**
     * Graph thread class that iterates z^2 + c as z varies over
     * each point in the complex plane bounded by the rectangle
     * xMin, xMax, yMin, yMax.
     */
    private class PlotThread extends Thread
    {
        public void run()
        {
            // Loop over each graph panel pixel.
            for (int row = 0; row < h; ++row) {
                float y0 = yMax - row*delta;           // row => y0

                for (int col = 0; col < w; ++col) {
                    float   x0 = xMin + col*delta;     // col => x0
                    Complex z  = new Complex(x0, y0);  // z = x0 + y0i

                    if (stopFlag) return;

                    boolean escaped = false;
                    int     iters   = 0;
                    float   x       = x0;
                    float   y       = y0;
                    float   modulus;

                    // Iterate z^2 + c, keeping track of the
                    // iteration count.
                    do {
                        z = z.multiply(z).add(c);
                        modulus = z.modulus();
                        escaped = modulus > ESCAPE_MODULUS;
                    } while ((++iters < MAX_ITERS) && (!escaped));

                    // Escaped: Set the shade of gray based on the
                    // number of iterations needed to escape.  The
                    // more iterations, the darker the shade.
                    if (escaped) {
                        int k = 255 - (255*iters)/MAX_ITERS;
                        k = Math.min(k, 240);
                        plotPoint(col, row, new Color(k, k, k));
                    }

                    // No escape: Set the colors based on the
                    // last computed modulus.
                    else {
                        int m = ((int) (100*modulus))
                                            /ESCAPE_MODULUS + 1;
                        int r = (307*m)&255;
                        int g = (353*m)&255;
                        int b = (401*m)&255;
                        plotPoint(col, row, new Color(r, g, b));
                    }
                }

                // Draw a row of the graph.
                drawPlot();
                yield();
            }
        }
    }
}
