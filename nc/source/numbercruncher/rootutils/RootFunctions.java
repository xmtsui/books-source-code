package numbercruncher.rootutils;

import java.util.Hashtable;
import numbercruncher.mathutils.Function;

/**
 * Load into a global table the functions whose roots we want to find.
 */
public class RootFunctions
{
    /** global function table */
    private static Hashtable TABLE = new Hashtable(32);

    // Enter the functions into the global function table.
    static {
        enterFunctions();
    }

    /**
     * Return the function with the given hash key
     * @param key the hash key
     * @return the function
     */
    public static Function function(String key)
    {
        return (Function) TABLE.get(key);
    }

    /**
     * Enter all the functions into the global function table.
     */
    private static void enterFunctions()
    {
        // Function f(x)  = x^2 - 4
        //          f'(x) = 2x
        TABLE.put(
            "x^2 - 4",
            new Function()
            {
                public float at(float x)
                {
                    return x*x - 4;
                }

                public float derivativeAt(float x)
                {
                    return 2*x;
                }
            });

        // Function f(x)  = -x^2 + 4x + 5
        //          f'(x) = -2x + 4
        TABLE.put(
            "-x^2 + 4x + 5",
            new Function()
            {
                public float at(float x)
                {
                    return -x*x + 4*x + 5;
                }

                public float derivativeAt(float x)
                {
                    return -2*x + 4;
                }
            });

        // Function f(x)  = x^3 + 3x^2 - 9x - 10
        //          f'(x) = 3x^2 + 6x - 9
        TABLE.put(
            "x^3 + 3x^2 - 9x - 10",
            new Function()
            {
                public float at(float x)
                {
                    return x*x*x + 3*x*x - 9*x - 10;
                }

                public float derivativeAt(float x)
                {
                    return 3*x*x + 6*x - 9;
                }
            });

        // Function f(x)  = x^2 - 2x + 3
        //          f'(x) = 2x - 2
        TABLE.put(
            "x^2 - 2x + 3",
            new Function()
            {
                public float at(float x)
                {
                    return x*x - 2*x + 3;
                }

                public float derivativeAt(float x)
                {
                    return 2*x - 2;
                }
            });

        // Function f(x)  = 2x^3 - 10x^2 + 11x - 5
        //          f'(x) = 6x^2 - 20x + 11
        TABLE.put(
            "2x^3 - 10x^2 + 11x - 5",
            new Function()
            {
                public float at(float x)
                {
                    return 2*x*x*x - 10*x*x + 11*x - 5;
                }

                public float derivativeAt(float x)
                {
                    return 6*x*x - 20*x + 11;
                }
            });

        // Function f(x)  = e^-x - x
        //          f'(x) = -e^-x - 1
        TABLE.put(
            "e^-x - x",
            new Function()
            {
                public float at(float x)
                {
                    return ((float) Math.exp(-x)) - x;
                }

                public float derivativeAt(float x)
                {
                    return (float) -Math.exp(-x) - 1;
                }
            });

        // Function f(x)  = x - e^(1/x)
        //          f'(x) = 1 + (1/x^2)e^(1/x)
        TABLE.put(
            "x - e^(1/x)",
            new Function()
            {
                public float at(float x)
                {
                    return x - ((float) Math.exp(1/x));
                }

                public float derivativeAt(float x)
                {
                    return 1 + (1/(x*x))*((float) Math.exp(1/x));
                }
            });

        // Function g(x) = (x + 4/x)/2
        TABLE.put(
            "(x + 4/x)/2",
            new Function()
            {
                public float at(float x)
                {
                    return (x + 4/x)/2;
                }
            });

        // Function g(x) = 4/x
        TABLE.put(
            "4/x",
            new Function()
            {
                public float at(float x)
                {
                    return 4/x;
                }
            });

        // Function g(x) = sqrt(x + 2)
        TABLE.put(
            "sqrt(x + 2)",
            new Function()
            {
                public float at(float x)
                {
                    return (float) Math.sqrt(x + 2);
                }
            });

        // Function g(x) = 2/x + 1
        TABLE.put(
            "2/x + 1",
            new Function()
            {
                public float at(float x)
                {
                    return 2/x + 1;
                }
            });

        // Function g(x) = x^2 - 2
        TABLE.put(
            "x*x - 2",
            new Function()
            {
                public float at(float x)
                {
                    return x*x - 2;
                }
            });

        // Function g(x) = e^-x
        TABLE.put(
            "exp(-x)",
            new Function()
            {
                public float at(float x)
                {
                    return (float) Math.exp(-x);
                }
            });

        // Function g(x) = -ln(x)
        TABLE.put(
            "-log(x)",
            new Function()
            {
                public float at(float x)
                {
                    return (float) -Math.log(x);
                }
            });

        // Function g(x) = e^(1/x)
        TABLE.put(
            "exp(1/x)",
            new Function()
            {
                public float at(float x)
                {
                    return (float) Math.exp(1/x);
                }
            });

        // Function g(x) = (x + e^(1/x))/2
        TABLE.put(
            "(x + exp(1/x))/2",
            new Function()
            {
                public float at(float x)
                {
                    return (x + ((float) Math.exp(1/x)))/2;
                }
            });

        // Function g(x) = 1/ln(x)
        TABLE.put(
            "1/log(x)",
            new Function()
            {
                public float at(float x)
                {
                    return 1/((float) Math.log(x));
                }
            });

        // Function g(x) = sin(x)/2 + 1
        TABLE.put(
            "sin(x)/2 + 1",
            new Function()
            {
                public float at(float x)
                {
                    return ((float) Math.sin(x)/2) + 1;
                }
            });

        // Function g(x) = 1 + 1/x + 1/x^2
        TABLE.put(
            "1 + 1/x + 1/(x*x)",
            new Function()
            {
                public float at(float x)
                {
                    return 1 + 1/x + 1/(x*x);
                }
            });

        // Function g(x) = 20/(x^2 + 2x + 10)
        TABLE.put(
            "20/(x*x + 2*x + 10)",
            new Function()
            {
                public float at(float x)
                {
                    return 20/(x*x + 2*x + 10);
                }
            });
    }
}