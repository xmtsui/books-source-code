#!/bin/sh
cd classes
echo GraphUtils.jar
$JAR -cf $JAR_DEST/GraphUtils.jar  numbercruncher/graphutils
echo MathUtils.jar
$JAR -cf $JAR_DEST/MathUtils.jar   numbercruncher/mathutils
echo Matrix.jar
$JAR -cf $JAR_DEST/Matrix.jar      numbercruncher/matrix
echo PiUtils.jar
$JAR -cf $JAR_DEST/PiUtils.jar     numbercruncher/piutils
echo PointUtils.jar
$JAR -cf $JAR_DEST/PointUtils.jar  numbercruncher/pointutils
echo PrimeUtils.jar
$JAR -cf $JAR_DEST/PrimeUtils.jar  numbercruncher/primeutils
echo RandomUtils.jar
$JAR -cf $JAR_DEST/RandomUtils.jar numbercruncher/randomutils
echo RootUtils.jar
$JAR -cf $JAR_DEST/RootUtils.jar   numbercruncher/rootutils
