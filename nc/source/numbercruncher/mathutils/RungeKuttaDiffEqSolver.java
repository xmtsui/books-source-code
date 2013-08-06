package numbercruncher.mathutils;

/**
 * Differential equation solver that implements
 * a fourth-order Runge-Kutta algorithm.
 */
public class RungeKuttaDiffEqSolver extends DiffEqSolver
{
    /**
     * Constructor.
     * @param equation the differential equation to solve
     */
    public RungeKuttaDiffEqSolver(DifferentialEquation equation)
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
        float k1 = equation.at(x, y);
        float k2 = equation.at(x + h/2, y + k1*h/2);
        float k3 = equation.at(x + h/2, y + k2*h/2);
        float k4 = equation.at(x + h, y + k3*h);

        y += (k1 + 2*(k2 + k3) + k4)*h/6;
        x += h;

        return new DataPoint(x, y);
    }
}