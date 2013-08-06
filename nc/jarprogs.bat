cd classes
%jar% -cf %jardest%\FPFormats.jar numbercruncher\program3_1 numbercruncher\mathutils\IEEE754*.class
%jar% -cf %jardest%\RootFinder.jar numbercruncher\rootutils numbercruncher\mathutils\Epsilon.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\Function.class numbercruncher\mathutils\RootFinder*.class
%jar% -cf %jardest%\BisectionAlgorithm.jar numbercruncher\program5_1 numbercruncher\mathutils\BisectionRootFinder.class
%jar% -cf %jardest%\RegulaFalsiAlgorithm.jar numbercruncher\program5_2 numbercruncher\mathutils\RegulaFalsiRootFinder.class
%jar% -cf %jardest%\ImprovedRegulaFalsiAlgorithm.jar numbercruncher\program5_3 numbercruncher\mathutils\RegulaFalsiRootFinder.class numbercruncher\mathutils\ImprovedRegulaFalsiRootFinder.class
%jar% -cf %jardest%\SecantAlgorithm.jar numbercruncher\program5_4 numbercruncher\mathutils\SecantRootFinder.class
%jar% -cf %jardest%\NewtonsAlgorithm.jar numbercruncher\program5_5 numbercruncher\mathutils\NewtonsRootFinder.class
%jar% -cf %jardest%\FixedPointIteration.jar numbercruncher\program5_6 numbercruncher\mathutils\FixedPointRootFinder.class
%jar% -cf %jardest%\Interpolation.jar numbercruncher\program6_1 numbercruncher\mathutils\DataPoint.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\InterpolationPolynomial.class
%jar% -cf %jardest%\LinearRegression.jar numbercruncher\program6_2 numbercruncher\mathutils\DataPoint.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\RegressionLine.class
%jar% -cf %jardest%\Integration.jar numbercruncher\program7_1 numbercruncher\mathutils\DataPoint.class numbercruncher\mathutils\Epsilon.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\Integrator.class numbercruncher\mathutils\InterpolationPolynomial.class numbercruncher\mathutils\SimpsonsIntegrator.class numbercruncher\mathutils\TrapezoidalIntegrator.class
%jar% -cf %jardest%\SolveDiffEq.jar numbercruncher\program8_1 numbercruncher\mathutils\DataPoint.class numbercruncher\mathutils\DiffEqSolver.class numbercruncher\mathutils\DifferentialEquation.class numbercruncher\mathutils\EulersDiffEqSolver.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\PredictorCorrectorDiffEqSolver.class numbercruncher\mathutils\RungeKuttaDiffEqSolver.class
%jar% -cf %jardest%\Transformation.jar numbercruncher\program9_1 numbercruncher\graphutils\DemoApplet.class numbercruncher\graphutils\DemoPanel.class numbercruncher\mathutils\AlignRight.class numbercruncher\mathutils\Epsilon.class
%jar% -cf %jardest%\Regression.jar numbercruncher\program10_2 numbercruncher\mathutils\AlignRight.class numbercruncher\mathutils\DataPoint.class numbercruncher\mathutils\Epsilon.class numbercruncher\mathutils\Evaluatable.class numbercruncher\mathutils\IntPower.class numbercruncher\mathutils\RegressionPolynomial.class
%jar% -cf %jardest%\PiBorwein.jar numbercruncher\program13_3 numbercruncher\graphutils\DemoApplet.class numbercruncher\graphutils\DemoPanel.class numbercruncher\mathutils\BigFunctions.class
%jar% -cf %jardest%\RandomNormal.jar numbercruncher\program14_1 numbercruncher\mathutils\AlignRight.class numbercruncher\mathutils\RandomNormal.class numbercruncher\randomutils\Buckets.class
%jar% -cf %jardest%\RandomExponential.jar numbercruncher\program14_2 numbercruncher\mathutils\AlignRight.class numbercruncher\mathutils\RandomExponential.class numbercruncher\randomutils\Buckets.class
%jar% -cf %jardest%\Buffon.jar numbercruncher\program14_3
%jar% -cf %jardest%\PrimePatterns.jar numbercruncher\program15_5 numbercruncher\mathutils\PrimeFactors.class
%jar% -cf %jardest%\Bifurcation.jar numbercruncher\program16_1
%jar% -cf %jardest%\JuliaFractal.jar numbercruncher\program16_2 numbercruncher\mathutils\Complex.class
%jar% -cf %jardest%\NewtonsFractal.jar numbercruncher\program16_3 numbercruncher\mathutils\Complex.class
%jar% -cf %jardest%\MandelbrotFractal.jar numbercruncher\program16_4 numbercruncher\mathutils\Complex.class
cd ..