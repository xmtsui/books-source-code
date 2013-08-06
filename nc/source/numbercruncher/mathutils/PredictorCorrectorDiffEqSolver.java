package numbercruncher.mathutils;

/**
 * Differential equation solver that implements
 * a predictor-corrector algorithm.
 */
public class PredictorCorrectorDiffEqSolver extends DiffEqSolver
{
    /**
     * Constructor.
     * @param equation the differential equation to solve
     */
    public PredictorCorrectorDiffEqSolver(DifferentialEquation equation)
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
        float predictor = y + Math.abs(h)*equation.at(x);
        float avgSlope  = (equation.at(x, y)
                                + equation.at(x+h, predictor))/2;

        y += h*avgSlope;    // corrector
        x += h;

        return new DataPoint(x, y);
    }
}