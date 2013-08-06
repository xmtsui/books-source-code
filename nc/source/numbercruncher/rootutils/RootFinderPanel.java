package numbercruncher.rootutils;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.GraphPanel;
import numbercruncher.graphutils.FunctionFrame;
import numbercruncher.mathutils.Function;

/**
 * The base panel for all root finder demo panels.
 */
public abstract class RootFinderPanel extends GraphPanel
{
    /** control panel */    protected Panel controlPanel = new Panel();
    /** n label */          protected Label nLabel       = new Label();
    /** n text */           protected Label nText        = new Label();
    /** run button */       protected Button runButton   = new Button("Run");
    /** step button */      protected Button stepButton  = new Button("Step");

    /** function image file name */ private String        functionImageFileName;
    /** function frame */           private FunctionFrame functionFrame;
    /** function frame title */     private String        functionFrameTitle;

    /** array of functions
        to find roots for */        private PlotFunction  plotFunctions[];
    /** selected function */        private PlotFunction  plotFunction;

    /** thread for automatic stepping */    private Thread runThread = null;

    /** true if pause button was pressed */ private boolean paused    = false;
    /** true if algorithm converged */      private boolean converged = false;

    /**
     * Constructor.
     * @param plotFunctions the array of functions to plot
     * @param functionImageFileName the function image file name
     * @param functionFrameTitle the function frame title
     */
    protected RootFinderPanel(PlotFunction plotFunctions[],
                              String functionImageFileName,
                              String functionFrameTitle)
    {
        this(plotFunctions, true, false,
             functionImageFileName, functionFrameTitle);
    }

    /**
     * Constructor.
     * @param plotFunctions the array of functions to plot
     * @param xorMode true to set XORMode
     * @param functionImageFileName the function image file name
     * @param functionFrameTitle the function frame title
     */
    protected RootFinderPanel(PlotFunction plotFunctions[],
                              boolean xorMode,
                              String functionImageFileName,
                              String functionFrameTitle)
    {
        this(plotFunctions, xorMode, false,
             functionImageFileName, functionFrameTitle);
    }

    /**
     * Constructor.
     * @param plotFunctions the array of functions to plot
     * @param xorMode true to set XORMode
     * @param drawXequalsY true to draw X=Y line
     * @param functionImageFileName the function image file name
     * @param functionFrameTitle the function frame title
     */
    protected RootFinderPanel(PlotFunction plotFunctions[],
                              boolean xorMode,
                              boolean drawXequalsY,
                              String functionImageFileName,
                              String functionFrameTitle)
    {
        super(plotFunctions, plotFunctions[0].getPlotProperties(),
              xorMode, drawXequalsY);

        this.plotFunctions         = plotFunctions;
        this.plotFunction          = plotFunctions[0];
        this.functionImageFileName = functionImageFileName;
        this.functionFrameTitle    = functionFrameTitle;

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        nLabel.setFont(labelFont);
        nLabel.setAlignment(Label.RIGHT);
        nLabel.setText("n:");
        nText.setFont(textFont);
        nText.setAlignment(Label.LEFT);
        nText.setText(" ");

        runButton.setEnabled(true);
        stepButton.setEnabled(true);

        // Step button handler.
        stepButton.addActionListener(new ActionListener()
        {
            // If the run thread is inactive, call step().
            // If the thread is active, stop it and revert the
            // button label to "Step".
            public void actionPerformed(ActionEvent ev)
            {
                if ((runThread != null) && (runThread.isAlive())) {
                    paused = true;
                    runButton.setEnabled(true);
                    stepButton.setLabel("Step");
                    }
                else {
                    step();
                }
            }
        });

        // Run button handler.
        runButton.addActionListener(new ActionListener()
        {
            // Start the run thread and change the step button
            // label to "Pause".
            public void actionPerformed(ActionEvent ev)
            {
                runButton.setEnabled(false);
                stepButton.setLabel("Pause");
                paused = false;

                runThread = new RunThread();
                runThread.start();
            }
        });
    }

    /**
     * Set the converged flag.
     * @param converged true if algorithm converged
     */
    public void setConverged(boolean converged) { this.converged = converged; }

    /**
     * Open the function frame.
     */
    private void openFunctionFrame()
    {
        functionFrame = new FunctionFrame(plotFunctions, functionImageFileName,
                                          functionFrameTitle, this);
        functionFrame.setVisible(true);

        setHeaderImage(functionFrame.getImage());
        setFunction(plotFunctions[0]);
    }

    /**
     * Choose a function. (Callback from the function frame.)
     */
    public void chooseFunction(int index)
    {
        plotFunction = plotFunctions[index];
        setFunction(plotFunction);
        draw();
    }

    /**
     * Return the selected function to find roots for.
     * @return the selected function
     */
    protected PlotFunction getSelectedPlotFunction() { return plotFunction; }

    //-----------------------------//
    // GraphPanel method overrides //
    //-----------------------------//

    /**
     * Create the function frame or bring it to the front.
     * (Callback from header panel.)
     */
    public void doHeaderAction()
    {
        if (functionFrame != null) {
            functionFrame.toFront();
        }
        else {
            openFunctionFrame();
        }
    }

    /**
     * Return the value of the selected function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return plotFunction.at(x); }

    /**
     * Notification that the plot bounds changed.
     * Redraw the panel.
     */
    public void plotBoundsChanged()
    {
        draw();
    }

    /**
     * Notification that a user input error occurred.
     * Disable the run and step buttons.
     */
    protected void userErrorOccurred()
    {
        runButton.setEnabled(false);
        stepButton.setEnabled(false);
    }

    /**
     * Draw the contents the panel.
     */
    public void draw()
    {
        // Stop the run thread.
        paused    = true;
        converged = false;

        // Reinitialize the run and step buttons.
        runButton.setEnabled(true);
        stepButton.setEnabled(true);
        stepButton.setLabel("Step");

        setHeaderDisplay(plotFunction);
        super.draw();
    }

    //--------------------------//
    // DemoPanel implementation //
    //--------------------------//

    /**
     * Initialize the demo. (Callback from applet.)
     */
    public void initializeDemo()
    {
        openFunctionFrame();
    }

    /**
     * Close the demo. (Callback from applet.)
     */
    public void closeDemo()
    {
        if (functionFrame != null) {
            functionFrame.setVisible(false);
            functionFrame.dispose();
            functionFrame = null;
        }
    }

    //---------------------//
    // Algorithm animation //
    //---------------------//

    /**
     * One iteration step.  Do nothing here.
     */
    protected void step() {}

    /**
     * The thread that automatically steps once per half second.
     */
    private class RunThread extends Thread
    {
        /**
         * Run the thread.
         */
        public void run()
        {
            // Loop until the iteration count stops.
            while ((!paused) && (!converged)) {
                step();

                try {
                    sleep(500);  // half second
                }
                catch(Exception ex) {}
            }

            // Unless it's only paused, disable this function.
            if (!paused) {
                runButton.setEnabled(false);
                stepButton.setEnabled(false);
                stepButton.setLabel("Step");
            }
        }
    }

    /**
     * The algorithm successfully converged.
     */
    protected void successfullyConverged()
    {
        converged = true;

        runButton.setEnabled(false);
        stepButton.setEnabled(false);
    }

    /**
     * The algorithm has exceeded the maximum number of iterations.
     * @param maxIters the maximum number of iterations
     * @param text the text to star out
     */
    protected void iterationLimitExceeded(int maxIters, Label text)
    {
        converged = false;
        paused    = true;   // stop the run thread

        nText.setText(">" + maxIters + " ITERS");
        text.setText("***");

        runButton.setEnabled(false);
        stepButton.setEnabled(false);
    }
}
