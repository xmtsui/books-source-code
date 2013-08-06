package numbercruncher.mathutils;

/**
 * Differential equation solver that implements Euler's algorithm.
 */
public class EulersDiffEqSolver extends DiffEqSolver
{
    /**
     * Constructor.
     * @param equation the differential equation to solve
     */
    public EulersDiffEqSolver(DifferentialEquation equation)
    {
        super(equation);
    }

    /**
     * Return the next data point in the
     * approximation of the solution.
     * @param h the width of the interval
     */
    public DataPoint nextPoint(float h)
    {
        y += h*equation.at(x, y);
        x += h;

        return new DataPoint(x, y);
    }
}