package numbercruncher.program8_1;

import numbercruncher.mathutils.DifferentialEquation;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.DiffEqSolver;
import numbercruncher.mathutils.EulersDiffEqSolver;
import numbercruncher.mathutils.PredictorCorrectorDiffEqSolver;
import numbercruncher.mathutils.RungeKuttaDiffEqSolver;
import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 8-1: Solve Differential Equations
 *
 * Demonstrate algorithms for solving differential equations.
 */
public class SolveDiffEq
{
    private static final int MAX_INTERVALS = Integer.MAX_VALUE/2;

    private static final int EULER               = 0;
    private static final int PREDICTOR_CORRECTOR = 1;
    private static final int RUNGE_KUTTA         = 2;

    private static final String ALGORITHMS[] = {
        "Euler's", "Predictor-Corrector", "Runge-Kutta"
    };

    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        if (args.length < 1) {
            System.out.println("Specify either Euler's, " +
                               "Predictor-Corrector, or " +
                               "Runge-Kutta on the command line.");
            return;
        }

        String arg = args[0].toLowerCase();
        int algorithm =   arg.startsWith("eul") ? EULER
                        : arg.startsWith("pre") ? PREDICTOR_CORRECTOR
                        :                         RUNGE_KUTTA;

        System.out.println("ALGORITHM: " + ALGORITHMS[algorithm]);

        solve(algorithm, "6x^2 - 20x + 11", 6);
        solve(algorithm, "2xe^2x + y", 1);
        solve(algorithm, "xe^-2x - 2y", 1);
    }

    private static void solve(int algorithm, String key, float x)
    {
        DifferentialEquation equation = DiffEqsToSolve.equation(key);
        DataPoint initialCondition = equation.getInitialCondition();
        String    solutionLabel    = equation.getSolutionLabel();

        float    initX     = initialCondition.x;
        float    initY     = initialCondition.y;
        float    trueValue = equation.solutionAt(x);

        System.out.println();
        System.out.println("Differential equation: f(x,y) = " + key);
        System.out.println("    Initial condition: y(" +
                           initX + ") = " + initY);
        System.out.println("  Analytical solution: y = " +
                           solutionLabel);
        System.out.println("           True value: y(" +
                           x + ") = " + trueValue);
        System.out.println();

        DiffEqSolver solver;

        switch (algorithm) {

            case EULER: {
                solver = new EulersDiffEqSolver(equation);
                break;
            }

            case PREDICTOR_CORRECTOR: {
                solver = new PredictorCorrectorDiffEqSolver(equation);
                break;
            }

            default: {
                solver = new RungeKuttaDiffEqSolver(equation);
                break;
            }
        }

        AlignRight ar = new AlignRight();

        ar.print("n", 8);             ar.print("h", 15);
        ar.print("y(" + x + ")", 15); ar.print("Error", 15);
        ar.underline();

        int intervals = 2;
        float h = Math.abs(x - initX)/intervals;

        float error = Float.MAX_VALUE;
        float prevError;

        do {
            DataPoint nextPoint = null;

            for (int i = 0; i < intervals; ++i) {
                nextPoint = solver.nextPoint(h);
            }

            prevError = error;
            error     = nextPoint.y - trueValue;

            ar.print(intervals, 8);    ar.print(h, 15);
            ar.print(nextPoint.y, 15); ar.print(error, 15);
            ar.println();

            intervals *= 2;
            h /= 2;
            solver.reset();
        } while ((intervals < MAX_INTERVALS) &&
                 (Math.abs(prevError) > Math.abs(error)));
    }
}
/*
Output:
ALGORITHM: Euler's

Differential equation: f(x,y) = 6x^2 - 20x + 11
    Initial condition: y(0.0) = -5.0
  Analytical solution: y = 2x^3 - 10x^2 + 11x - 5
           True value: y(6.0) = 133.0

       n              h         y(6.0)          Error
-----------------------------------------------------
       2            3.0           43.0          -90.0
       4            1.5           74.5          -58.5
       8           0.75        100.375        -32.625
      16          0.375      115.84375      -17.15625
      32         0.1875      124.21094     -8.7890625
      64        0.09375      128.55273     -4.4472656
     128       0.046875      130.76318     -2.2368164
     256      0.0234375      131.87823     -1.1217651
     512     0.01171875      132.43834    -0.56166077
    1024    0.005859375        132.719    -0.28100586
    2048   0.0029296875      132.85947    -0.14053345
    4096   0.0014648438      132.92973    -0.07026672
    8192    7.324219E-4      132.96489   -0.035110474
   16384   3.6621094E-4      132.98236    -0.01763916
   32768   1.8310547E-4      132.99138   -0.008621216
   65536   9.1552734E-5      132.99562  -0.0043792725
  131072   4.5776367E-5      132.99786  -0.0021362305
  262144   2.2888184E-5      132.99792  -0.0020751953
  524288   1.1444092E-5       133.0011   0.0010986328
 1048576    5.722046E-6      133.00006   6.1035156E-5
 2097152    2.861023E-6      133.03824    0.038238525

Differential equation: f(x,y) = 2xe^2x + y
    Initial condition: y(0.0) = 1.0
  Analytical solution: y = 3e^x - 2e^2x + 2xe^2x
           True value: y(1.0) = 8.154845

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5      3.6091409     -4.5457044
       4           0.25       5.293519     -2.8613262
       8          0.125      6.5289307     -1.6259146
      16         0.0625       7.284655    -0.87019014
      32        0.03125      7.7041717    -0.45067358
      64       0.015625      7.9254375    -0.22940779
     128      0.0078125       8.039102    -0.11574364
     256     0.00390625        8.09671   -0.058135033
     512    0.001953125      8.1257105    -0.02913475
    1024    9.765625E-4       8.140253   -0.014592171
    2048   4.8828125E-4       8.147548   -0.007297516
    4096   2.4414062E-4       8.151206  -0.0036392212
    8192   1.2207031E-4       8.153012   -0.001832962
   16384   6.1035156E-5        8.15396  -8.8500977E-4
   32768   3.0517578E-5       8.154387  -4.5776367E-4
   65536   1.5258789E-5       8.154611  -2.3460388E-4
  131072   7.6293945E-6       8.154683  -1.6212463E-4
  262144   3.8146973E-6       8.154706  -1.3923645E-4
  524288   1.9073486E-6       8.154436  -4.0912628E-4

Differential equation: f(x,y) = xe^-2x - 2y
    Initial condition: y(0.0) = -0.5
  Analytical solution: y = (x^2e^-2x - e^-2x)/2
           True value: y(1.0) = 0.0

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5     0.09196986     0.09196986
       4           0.25    0.043056414    0.043056414
       8          0.125     0.02059899     0.02059899
      16         0.0625    0.010078897    0.010078897
      32        0.03125     0.00498614     0.00498614
      64       0.015625   0.0024799863   0.0024799863
     128      0.0078125   0.0012367641   0.0012367641
     256     0.00390625    6.176049E-4    6.176049E-4
     512    0.001953125   3.0862397E-4   3.0862397E-4
    1024    9.765625E-4   1.5423674E-4   1.5423674E-4
    2048   4.8828125E-4    7.712061E-5    7.712061E-5
    4096   2.4414062E-4   3.8514103E-5   3.8514103E-5
    8192   1.2207031E-4   1.9193667E-5   1.9193667E-5
   16384   6.1035156E-5   9.6256945E-6   9.6256945E-6
   32768   3.0517578E-5   4.7721633E-6   4.7721633E-6
   65536   1.5258789E-5   2.3496477E-6   2.3496477E-6
  131072   7.6293945E-6   9.0182266E-7   9.0182266E-7
  262144   3.8146973E-6     -3.8416E-7     -3.8416E-7
  524288   1.9073486E-6   4.7914605E-6   4.7914605E-6
*/
/*
ALGORITHM: Predictor-Corrector

Differential equation: f(x,y) = 6x^2 - 20x + 11
    Initial condition: y(0.0) = -5.0
  Analytical solution: y = 2x^3 - 10x^2 + 11x - 5
           True value: y(6.0) = 133.0

       n              h         y(6.0)          Error
-----------------------------------------------------
       2            3.0          187.0           54.0
       4            1.5          146.5           13.5
       8           0.75        136.375          3.375
      16          0.375      133.84375        0.84375
      32         0.1875      133.21094      0.2109375
      64        0.09375      133.05273    0.052734375
     128       0.046875      133.01312    0.013122559
     256      0.0234375      133.00331   0.0033111572
     512     0.01171875      133.00082    8.239746E-4
    1024    0.005859375      133.00023   2.2888184E-4
    2048   0.0029296875      133.00005   4.5776367E-5
    4096   0.0014648438      133.00005   4.5776367E-5

Differential equation: f(x,y) = 2xe^2x + y
    Initial condition: y(0.0) = 1.0
  Analytical solution: y = 3e^x - 2e^2x + 2xe^2x
           True value: y(1.0) = 8.154845

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5       8.449224     0.29437923
       4           0.25       8.197649    0.042803764
       8          0.125       8.160373   0.0055274963
      16         0.0625        8.15547    6.246567E-4
      32        0.03125       8.154899    5.340576E-5
      64       0.015625       8.154848    2.861023E-6
     128      0.0078125       8.154843  -1.9073486E-6
     256     0.00390625       8.154846    9.536743E-7
     512    0.001953125       8.154843  -1.9073486E-6

Differential equation: f(x,y) = xe^-2x - 2y
    Initial condition: y(0.0) = -0.5
  Analytical solution: y = (x^2e^-2x - e^-2x)/2
           True value: y(1.0) = 0.0

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5   -0.035143584   -0.035143584
       4           0.25  -0.0086004995  -0.0086004995
       8          0.125  -0.0020729005  -0.0020729005
      16         0.0625  -5.0861575E-4  -5.0861575E-4
      32        0.03125     -1.2598E-4     -1.2598E-4
      64       0.015625   -3.135181E-5   -3.135181E-5
     128      0.0078125   -7.822178E-6   -7.822178E-6
     256     0.00390625  -1.9620056E-6  -1.9620056E-6
     512    0.001953125   -4.900794E-7   -4.900794E-7
    1024    9.765625E-4  -1.1298107E-7  -1.1298107E-7
    2048   4.8828125E-4  -3.3578544E-8  -3.3578544E-8
    4096   2.4414062E-4   -7.399285E-8   -7.399285E-8
*/
/*
ALGORITHM: Runge-Kutta

Differential equation: f(x,y) = 6x^2 - 20x + 11
    Initial condition: y(0.0) = -5.0
  Analytical solution: y = 2x^3 - 10x^2 + 11x - 5
           True value: y(6.0) = 133.0

       n              h         y(6.0)          Error
-----------------------------------------------------
       2            3.0          133.0            0.0
       4            1.5          133.0            0.0

Differential equation: f(x,y) = 2xe^2x + y
    Initial condition: y(0.0) = 1.0
  Analytical solution: y = 3e^x - 2e^2x + 2xe^2x
           True value: y(1.0) = 8.154845

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5       8.148893  -0.0059518814
       4           0.25       8.154289   -5.559921E-4
       8          0.125       8.154804  -4.1007996E-5
      16         0.0625       8.154843  -1.9073486E-6
      32        0.03125       8.154845            0.0
      64       0.015625       8.154845            0.0

Differential equation: f(x,y) = xe^-2x - 2y
    Initial condition: y(0.0) = -0.5
  Analytical solution: y = (x^2e^-2x - e^-2x)/2
           True value: y(1.0) = 0.0

       n              h         y(1.0)          Error
-----------------------------------------------------
       2            0.5  -0.0027439892  -0.0027439892
       4           0.25  -1.2015179E-4  -1.2015179E-4
       8          0.125   -6.262213E-6   -6.262213E-6
      16         0.0625  -3.6507845E-7  -3.6507845E-7
      32        0.03125  -3.4924597E-8  -3.4924597E-8
      64       0.015625   1.7695129E-8   1.7695129E-8
     128      0.0078125   1.2805685E-9   1.2805685E-9
     256     0.00390625   6.9849193E-9   6.9849193E-9
*/