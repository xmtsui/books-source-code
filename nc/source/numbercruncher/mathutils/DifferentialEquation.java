package numbercruncher.mathutils;

import java.util.Hashtable;

/**
 * The base class for functions that can have derivatives.
 * Initialize the static function table with some sample functions.
 */
public abstract class DifferentialEquation implements Evaluatable
{
    /** initial condition */        private DataPoint initialCondition;
    /** solution function label */  private String    solutionLabel;

    /**
     * Constructor.
     * @param initialCondition the initial condition data point
     * @param solutionLabel the solution function label
     */
    public DifferentialEquation(DataPoint initialCondition,
                                String solutionLabel)
    {
        this.initialCondition = initialCondition;
        this.solutionLabel    = solutionLabel;
    }

    /**
     * Return the initial condition data point.
     * @return the initial condition
     */
    public DataPoint getInitialCondition() { return initialCondition; }

    /**
     * Return the solution label.
     * @return the label
     */
    public String getSolutionLabel() { return solutionLabel; }

    /**
     * Return the value of the differential equation at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the solution value
     */
    public abstract float at(float x);

    /**
     * Return the value of the differential equation at (x, y).
     * @return the solution value
     */
    public float at(float x, float y) { return at(x); }

    /**
     * Return the value of the solution at x.
     * @return the solution value
     */
    public abstract float solutionAt(float x);
}