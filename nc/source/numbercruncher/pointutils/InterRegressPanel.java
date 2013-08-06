package numbercruncher.pointutils;

import java.awt.*;
import java.awt.event.*;

/**
 * The base panel for the interpolation and regression panels.
 */
public abstract class InterRegressPanel extends UserPointPanel
{
    /** true if OK to plot function */  protected boolean plotOK = false;

    /**
     * Constructor.
     * @param maxPoints the maximum number of data points
     * @param actionButton1Label the label for action button 1
     * @param actionButton2Label the label for action button 2
     */
    protected InterRegressPanel(int maxPoints,
                                String actionButton1Label,
                                String actionButton2Label)
    {
        this(maxPoints, actionButton1Label, actionButton2Label, false);
    }

    /**
     * Constructor.
     * @param maxPoints the maximum number of data points
     * @param actionButton1Label the label for action button 1
     * @param actionButton2Label the label for action button 2
     * @param enableDegree true to enable the degree choice, false to disable
     */
    protected InterRegressPanel(int maxPoints,
                                String actionButton1Label,
                                String actionButton2Label,
                                boolean showDegree)
    {
        super(maxPoints, "# points:", actionButton1Label, actionButton2Label);

        Label  degreeLabel  = new Label();
        Choice degreeChoice = new Choice();

        // Control panel.
        controlPanel.setLayout(new GridLayout(0, 3, 5, 2));
        controlPanel.add(actionButton1);
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        controlPanel.add(actionButton2);

        // Degree control.
        if (showDegree) {
            Font labelFont = getLabelFont();
            Font textFont  = getTextFont();

            degreeLabel.setFont(labelFont);
            degreeLabel.setAlignment(Label.RIGHT);
            degreeLabel.setText("Degree:");

            for (int i = 1; i <= 9; ++i) degreeChoice.add(Integer.toString(i));

            controlPanel.add(degreeLabel);
            controlPanel.add(degreeChoice);
        }
        addDemoControls(controlPanel);

        actionButton1.setEnabled(false);

        // Degree choice handler.
        degreeChoice.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ev)
            {
                Choice choice = (Choice) ev.getItemSelectable();
                int    degree  = choice.getSelectedIndex() + 1;
                degreeChanged(degree);
                draw();
            }
        });
    }

    /**
     * Notify that the degree has changed.  (Do nothing here.)
     * @param degree the new degree
     */
    protected void degreeChanged(int degree) {}

    /**
     * Return whether or not it's OK to plot the function.
     * @return true if OK, otherwise false
     */
    protected boolean plotOK() { return plotOK; }
}