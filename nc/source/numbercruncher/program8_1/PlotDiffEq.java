package numbercruncher.program8_1;

import java.util.*;
import java.awt.*;

import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.DifferentialEquation;
import numbercruncher.mathutils.Evaluatable;
import numbercruncher.graphutils.Plottable;
import numbercruncher.graphutils.PlotProperties;

/**
 * Wrapper class that makes differential equations plottable.
 */
public class PlotDiffEq implements Plottable
{
    private static final int X1  =   1;
    private static final int X2  = 145;

    private static final int Y11 =  25;
    private static final int Y12 =  52;
    private static final int Y21 = Y12 + 2;
    private static final int Y22 =  82;
    private static final int Y31 = Y22 + 2;
    private static final int Y32 = 111;
    private static final int Y41 = Y32 + 2;
    private static final int Y42 = 139;
    private static final int Y51 = Y42 + 2;
    private static final int Y52 = 169;
    private static final int Y61 = Y52 + 2;
    private static final int Y62 = 219;

    /** differential equation */    private DifferentialEquation equation;
    /** image region */             private Rectangle            rectangle;
    /** plot properties */          private PlotProperties       properties;

    /** wrapped differential equations table */
    public static Hashtable TABLE = new Hashtable(32);

    // Enter the wrapped differential equations into the table.
    static {
        enter("2x",                 X1, Y11, X2, Y12);
        enter("3x^2 + 6x - 9",      X1, Y21, X2, Y22);
        enter("6x^2 - 20x + 11",    X1, Y31, X2, Y32);
        enter("2xe^2x + y",         X1, Y41, X2, Y42);
        enter("8x - 2y + 8",        X1, Y51, X2, Y52);
        enter("xe^-2x - 2y",        X1, Y61, X2, Y62);
    };

    /**
     * Create a wrapped diffential equation and enter it into the table.
     * @param key  the hash key
     * @param xMin the minimum x value of the plot bounds
     * @param xMax the maximum x value of the plot bounds
     * @param xMin the minimum y value of the plot bounds
     * @param yMax the maximum y value of the plot bounds
     */
    private static void enter(String key, int x1, int y1, int x2, int y2)
    {
        PlotDiffEq plotDiffEq = new PlotDiffEq(x1, y1, x2, y2);
        plotDiffEq.equation   = DiffEqsToSolve.equation(key);

        TABLE.put(key, plotDiffEq);
    }

    /**
     * Constructor.
     * @param key the function key
     * @param x1 the x-coordinate of uppper left corner of image region
     * @param y1 the y-coordinate of uppper left corner of image region
     * @param x1 the x-coordinate of lower left corner of image region
     * @param y1 the y-coordinate of lower left corner of image region
     */
    public PlotDiffEq(String key,
                        float xMin, float xMax, float yMin, float yMax)
    {
        PlotDiffEq solvedEquation = (PlotDiffEq) TABLE.get(key);

        if (solvedEquation != null) {
            this.equation   = solvedEquation.equation;
            this.rectangle  = solvedEquation.rectangle;
            this.properties = new PlotProperties(xMin, xMax, yMin, yMax);
        }
    }

    /**
     * Constructor.
     * @param equation the differential equation
     * @param x1 the x-coordinate of uppper left corner of image region
     * @param y1 the y-coordinate of uppper left corner of image region
     * @param x1 the x-coordinate of lower left corner of image region
     * @param y1 the y-coordinate of lower left corner of image region
     */
    public PlotDiffEq(DifferentialEquation equation,
                        float xMin, float xMax, float yMin, float yMax)
    {
        this.equation   = equation;
        this.rectangle  = null;
        this.properties = new PlotProperties(xMin, xMax, yMin, yMax);
    }

    /**
     * Constructor.
     * @param x1 the x-coordinate of uppper left corner of image region
     * @param y1 the y-coordinate of uppper left corner of image region
     * @param x1 the x-coordinate of lower left corner of image region
     * @param y1 the y-coordinate of lower left corner of image region
     */
    private PlotDiffEq(int x1, int y1, int x2, int y2)
    {
        this.rectangle = new Rectangle(x1, y1, x2-x1+1, y2-y1+1);
    }

    /**
     * Return the unwrapped differential equation.
     * @return the differential equation
     */
    public Evaluatable getFunction() { return equation; }

    /**
     * Return the value of the differential equation at x.
     * @return the differential equation value
     */
    public float at(float x) { return equation.at(x); }

    /**
     * Return the value of the solution at x.
     * @return the solution value
     */
    public float solutionAt(float x) { return equation.solutionAt(x); }

    /**
     * Return the initial condition data point.
     * @return the initial condition
     */
    public DataPoint getInitialCondition()
    {
        return equation.getInitialCondition();
    }

    //--------------------------//
    // Plottable implementation //
    //--------------------------//

    /**
     * Get the root function's image rectangle
     * @return the rectangle
     */
    public Rectangle getRectangle() { return rectangle;  }

    /**
     * Get the root function's properties
     * @return the properties
     */
    public PlotProperties getPlotProperties() { return properties; }
}