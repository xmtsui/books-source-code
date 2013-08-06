#!/bin/sh
cd classes
echo FPFormats.jar
$JAR -cf $JAR_DEST/FPFormats.jar numbercruncher/program3_1 numbercruncher/mathutils/IEEE754*.class
echo RootFinder.jar
$JAR -cf $JAR_DEST/RootFinder.jar numbercruncher/rootutils numbercruncher/mathutils/Epsilon.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/Function.class numbercruncher/mathutils/RootFinder*.class
echo BisectionAlgorithm.jar
$JAR -cf $JAR_DEST/BisectionAlgorithm.jar numbercruncher/program5_1 numbercruncher/mathutils/BisectionRootFinder.class
echo RegulaFalsiAlgorithm.jar
$JAR -cf $JAR_DEST/RegulaFalsiAlgorithm.jar numbercruncher/program5_2 numbercruncher/mathutils/RegulaFalsiRootFinder.class
echo ImprovedRegulaFalsiAlgorithm.jar
$JAR -cf $JAR_DEST/ImprovedRegulaFalsiAlgorithm.jar numbercruncher/program5_3 numbercruncher/mathutils/RegulaFalsiRootFinder.class numbercruncher/mathutils/ImprovedRegulaFalsiRootFinder.class
echo SecantAlgorithm.jar
$JAR -cf $JAR_DEST/SecantAlgorithm.jar numbercruncher/program5_4 numbercruncher/mathutils/SecantRootFinder.class
echo NewtonsAlgorithm.jar
$JAR -cf $JAR_DEST/NewtonsAlgorithm.jar numbercruncher/program5_5 numbercruncher/mathutils/NewtonsRootFinder.class
echo FixedPointIteration.jar
$JAR -cf $JAR_DEST/FixedPointIteration.jar numbercruncher/program5_6 numbercruncher/mathutils/FixedPointRootFinder.class
echo Interpolation.jar
$JAR -cf $JAR_DEST/Interpolation.jar numbercruncher/program6_1 numbercruncher/mathutils/DataPoint.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/InterpolationPolynomial.class
echo LinearRegression.jar
$JAR -cf $JAR_DEST/LinearRegression.jar numbercruncher/program6_2 numbercruncher/mathutils/DataPoint.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/RegressionLine.class
echo Integration.jar
$JAR -cf $JAR_DEST/Integration.jar numbercruncher/program7_1 numbercruncher/mathutils/DataPoint.class numbercruncher/mathutils/Epsilon.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/Integrator.class numbercruncher/mathutils/InterpolationPolynomial.class numbercruncher/mathutils/SimpsonsIntegrator.class numbercruncher/mathutils/TrapezoidalIntegrator.class
echo SolveDiffEq.jar
$JAR -cf $JAR_DEST/SolveDiffEq.jar numbercruncher/program8_1 numbercruncher/mathutils/DataPoint.class numbercruncher/mathutils/DiffEqSolver.class numbercruncher/mathutils/DifferentialEquation.class numbercruncher/mathutils/EulersDiffEqSolver.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/PredictorCorrectorDiffEqSolver.class numbercruncher/mathutils/RungeKuttaDiffEqSolver.class
echo Transformation.jar
$JAR -cf $JAR_DEST/Transformation.jar numbercruncher/program9_1 numbercruncher/graphutils/DemoApplet.class numbercruncher/graphutils/DemoPanel.class numbercruncher/mathutils/AlignRight.class numbercruncher/mathutils/Epsilon.class
echo Regression.jar
$JAR -cf $JAR_DEST/Regression.jar numbercruncher/program10_2 numbercruncher/mathutils/AlignRight.class numbercruncher/mathutils/DataPoint.class numbercruncher/mathutils/Epsilon.class numbercruncher/mathutils/Evaluatable.class numbercruncher/mathutils/IntPower.class numbercruncher/mathutils/RegressionPolynomial.class
echo PiBorwein.jar
$JAR -cf $JAR_DEST/PiBorwein.jar numbercruncher/program13_3 numbercruncher/graphutils/DemoApplet.class numbercruncher/graphutils/DemoPanel.class numbercruncher/mathutils/BigFunctions.class
echo RandomNormal.jar
$JAR -cf $JAR_DEST/RandomNormal.jar numbercruncher/program14_1 numbercruncher/mathutils/AlignRight.class numbercruncher/mathutils/RandomNormal.class numbercruncher/randomutils/Buckets.class
echo RandomExponential.jar
$JAR -cf $JAR_DEST/RandomExponential.jar numbercruncher/program14_2 numbercruncher/mathutils/AlignRight.class numbercruncher/mathutils/RandomExponential.class numbercruncher/randomutils/Buckets.class
echo Buffon.jar
$JAR -cf $JAR_DEST/Buffon.jar numbercruncher/program14_3
echo PrimePatterns.jar
$JAR -cf $JAR_DEST/PrimePatterns.jar numbercruncher/program15_5 numbercruncher/mathutils/PrimeFactors.class
echo Bifurcation.jar
$JAR -cf $JAR_DEST/Bifurcation.jar numbercruncher/program16_1
echo JuliaFractal.jar
$JAR -cf $JAR_DEST/JuliaFractal.jar numbercruncher/program16_2 numbercruncher/mathutils/Complex.class
echo NewtonsFractal.jar
$JAR -cf $JAR_DEST/NewtonsFractal.jar numbercruncher/program16_3 numbercruncher/mathutils/Complex.class
echo MandelbrotFractal.jar
$JAR -cf $JAR_DEST/MandelbrotFractal.jar numbercruncher/program16_4 numbercruncher/mathutils/Complex.class
