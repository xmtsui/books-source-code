package numbercruncher.program15_5;

import java.awt.*;
import java.awt.event.*;
import numbercruncher.graphutils.*;

import numbercruncher.mathutils.PrimeFactors;

public class PrimePatternsPanel extends GraphPanel
{
    private static final String TITLE = "Streaking Primes";

    private static final float INIT_X_MIN = 0;
    private static final float INIT_X_MAX = 500;
    private static final float INIT_Y_MIN = 0;
    private static final float INIT_Y_MAX = 500;

    private static final String RUN_LABEL  = "Run";
    private static final String STOP_LABEL = "Stop";

    private static final int SPIRAL     = 0;
    private static final int DIAGONAL   = 1;
    private static final int SEQUENTIAL = 2;

    /** path choice labels */
    private static final String PATHS[] = {
        "Spiral", "Diagonal", "Sequential",
    };

    /** path choice */  private int path = SPIRAL;

    /** control
        panel */        private Panel     controlPanel = new Panel();
    /** size label */   private Label     sizeLabel    = new Label("Size:");
    /** size text */    private TextField sizeText     = new TextField("500");
    /** path label */   private Label     pathLabel    = new Label("Path:");
    /** path choice */  private Choice    pathChoice   = new Choice();
    /** start label */  private Label     startLabel   = new Label("Start with:");
    /** start text */   private TextField startText    = new TextField("41");
    /** run button */   private Button    runButton    = new Button(RUN_LABEL);

    /** size of square */           private int size;
    /** starting integer */         private int start;
    /** maximum integer */          private int max;

    /** prime sieve array */        private boolean isPrime[];

    /** primes plot thread */       private Thread  plotThread = null;
    /** true to stop thread */      private boolean stopFlag   = false;

    /** initial plot properties */
    private static PlotProperties plotProps =
                            new PlotProperties(INIT_X_MIN, INIT_X_MAX,
                                               INIT_Y_MIN, INIT_Y_MAX);

    /**
     * Constructor.
     */
    PrimePatternsPanel()
    {
        super(TITLE, plotProps, false);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Prime patterns controls.
        sizeLabel.setFont(labelFont);
        sizeLabel.setAlignment(Label.RIGHT);
        sizeText.setFont(textFont);
        pathLabel.setFont(labelFont);
        pathLabel.setAlignment(Label.RIGHT);
        startLabel.setFont(labelFont);
        startLabel.setAlignment(Label.RIGHT);
        startText.setFont(textFont);

        // Pattern options.
        for (int i = 0; i < PATHS.length; ++i) {
            pathChoice.add(PATHS[i]);
        }

        // Prime patterns control panel.
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeText);
        controlPanel.add(pathLabel);
        controlPanel.add(pathChoice);
        controlPanel.add(startLabel);
        controlPanel.add(startText);
        controlPanel.add(runButton);
        addDemoControls(controlPanel);

        // Path choice handler.
        pathChoice.addItemListener(new ItemListener()
        {
            // Select a path.
            public void itemStateChanged(ItemEvent ev)
            {
                String item = (String) ev.getItem();

                for (int i = 0; i < PATHS.length; ++i) {
                    if (item.equals(PATHS[i])) {
                        path = i;
                        break;
                    }
                }
            }
        });

        // Run button handler.
        runButton.addActionListener(new ActionListener()
        {
            // Start and stop the run thread.
            public void actionPerformed(ActionEvent ev)
            {
                if (runButton.getLabel().equals(RUN_LABEL)) {
                    runButton.setLabel(STOP_LABEL);
                    runButton.setEnabled(false);

                    draw();         // clear panel
                    plotPrimes();
                }
                else {
                    runButton.setLabel(RUN_LABEL);
                    stopFlag = true;
                }
            }
        });
    }

    /**
     * Plot the primes along the chosen path.
     */
    protected void plotPrimes()
    {
        try {
            processTextFields();
        }
        catch(Exception ex) {
            processUserError(ex.getMessage());
            resetRunButton();
            return;
        }

        setHeaderLabel(TITLE);

        // Recompute the prime sieve only for the first time
        // or if it needs to be larger.
        max = size*size + start - 1;
        if ((isPrime == null) || (isPrime.length < max+1)) {
            isPrime = PrimeFactors.primeSieve(max+1);
        }

        stopFlag = false;

        // Which path?
        switch(path) {
            case SPIRAL:      plotThread = new Spiral();      break;
            case DIAGONAL:    plotThread = new Diagonal();    break;
            case SEQUENTIAL:  plotThread = new Sequential();  break;
        }

        // Start the path thread and monitor it.
        plotThread.start();
        runButton.setEnabled(true);
        (new Monitor(plotThread)).start();
    }

    /**
     * Process the user-entered size and start textfields.
     * @throws Exception if there was an error
     */
    private void processTextFields() throws Exception
    {
        // Get the size.
        try {
            size = Integer.parseInt(sizeText.getText());
            if ((size < 1) || (size > 500)) {
                throw new Exception("Size must be at least 1 and " +
                                    "at most 500");
            }
        }
        catch(NumberFormatException ex) {
            throw new Exception("Invalid size: " + sizeText.getText());
        }

        // Get the starting integer.
        try {
            start = Integer.parseInt(startText.getText());
            if ((start < 1) || (start > 500)) {
                throw new Exception("Starting number must be at " +
                                    "least 1 and at most 500");
            }
        }
        catch(NumberFormatException ex) {
            throw new Exception("Invalid starting number: " +
                                startText.getText());
        }
    }


    /**
     * Reset the run button.
     */
    private void resetRunButton()
    {
        runButton.setEnabled(true);
        runButton.setLabel(RUN_LABEL);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the fractal.
     */
    protected void plotFunction()
    {
        // Stop the currently-running thread.
        if ((plotThread != null) && (plotThread.isAlive())) {
            stopFlag = true;
            try {
                plotThread.join();
            }
            catch(InterruptedException ignored) {}
        }

        // Start a new plot thread.
        plotPrimes();
    }

    //-------------------//
    // Animation threads //
    //-------------------//

    /**
     * Monitor for the path thread.
     */
    private class Monitor extends Thread
    {
        private Thread thread;

        /**
         * Constructor.
         * @param thread the path thread to monitor
         */
        private Monitor(Thread thread)
        {
            this.thread = thread;
        }

        /**
         * Monitor the path thread.  Upon the thread's termination,
         * set the run button's label to "Run".
         */
        public void run()
        {
            if (thread.isAlive()) {
                try {
                    thread.join();
                }
                catch(InterruptedException ex) {}
            }

            runButton.setLabel(RUN_LABEL);
        }
    }

    /**
     * Thread for the spiral path.
     */
    private class Spiral extends Thread
    {
        /**
         * Arrange the integers in a counter-clockwise outward spiral.
         */
        public void run()
        {
            int direction = 0;  // right, up, left, or down
            int steps     = 1;  // steps in a direction
            int dr        = 0;  // change in row
            int dc        = 1;  // change in column

            int k = start;
            int r = (size - 1)/2;   // middle row
            int c = r;              // middle column

            while (k <= max) {

                // Plot along the current direction.
                for (int i = steps; i > 0; --i) {
                    if (stopFlag) return;

                    // Plot a point if the integer is prime.
                    if (isPrime[k++]) plotPoint(c, r, Color.black);

                    // Move to the next position in the current direction.
                    r += dr;
                    c += dc;
                }

                // Draw the plot before changing direction.
                drawPlot();
                yield();

                // Change direction.
                direction = (direction + 1)%4;
                switch (direction) {

                    case 0: dr = 0;     // right
                            dc = 1;
                            ++steps;
                            break;

                    case 1: dr = -1;    // up
                            dc = 0;
                            break;

                    case 2: dr = 0;     // left
                            dc = -1;
                            ++steps;
                            break;

                    case 3: dr = 1;     // down
                            dc = 0;
                            break;
                }
            }
        }
    }

    /**
     * Thread for the diagonal path.
     */
    private class Diagonal extends Thread
    {
        /**
         * Arrange the integers diagonally from lower left to upper right,
         * starting in the upper left corner.
         */
        public void run()
        {
            int k = start;
            int r, c;

            // Do the first half.
            for (int i = 0; i < size; ++i) {
                r = i;
                c = 0;

                do {
                    if (stopFlag) return;

                    // Plot a point if the integer is prime.
                    if (isPrime[k++]) plotPoint(c, r, Color.black);

                    // Move to the next position along the diagonal.
                    --r;
                    ++c;
                } while (r >= 0);

                // Draw the plot after each diagonal.
                drawPlot();
                yield();
            }

            // Do the second half.
            for (int j = 1; j < size; ++j) {
                r = size - 1;
                c = j;

                do {
                    if (stopFlag) return;

                    // Plot a point if the integer is prime.
                    if (isPrime[k++]) plotPoint(c, r, Color.black);

                    // Move to the next position along the diagonal.
                    --r;
                    ++c;
                } while (c < size);

                // Draw the plot after each diagonal.
                drawPlot();
                yield();
            }
        }
    }

    /**
     * Thread for the sequential path.
     */
    private class Sequential extends Thread
    {
        /**
         * Arrange the integers left to right, row by row.
         */
        public void run()
        {
            int k = start;

            for (int r = 0; r < size; ++r) {
                for (int c = 0; c < size; ++c) {
                    if (stopFlag) return;

                    // Plot a point if the integer is prime.
                    if (isPrime[k++]) plotPoint(c, r, Color.black);
                }

                // Draw the plot after each row.
                drawPlot();
                yield();
            }
        }
    }
}
