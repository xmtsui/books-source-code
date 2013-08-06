package numbercruncher.mathutils;

/**
 * The base class for differential equation solvers.
 */
public abstract class DiffEqSolver
{
    /** the differential equation to solve */
    protected DifferentialEquation equation;

    /** the initial condition data point */
    protected DataPoint initialCondition;

    /** current x value */      protected float x;
    /** current y value */      protected float y;

    /**
     * Constructor.
     * @param equation the differential equation to solve
     */
    public DiffEqSolver(DifferentialEquation equation)
    {
        this.equation         = equation;
        this.initialCondition = equation.getInitialCondition();

        reset();
    }

    /**
     * Reset x and y to the initial condition data point.
     */
    public void reset()
    {
        this.x = initialCondition.x;
        this.y = initialCondition.y;
    }

    /**
     * Return the next data point in the
     * approximation of the solution.
     * @param h the width of the interval
     */
    public abstract DataPoint nextPoint(float h);
}