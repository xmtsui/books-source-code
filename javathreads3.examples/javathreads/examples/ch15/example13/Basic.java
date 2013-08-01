package javathreads.examples.ch15.example13;

import javathreads.examples.ch15.*;
import java.text.*;
import java.io.*;

public class Basic implements ScaleTester {
    private float lookupValues[][];
    int nCols, nRows;
    PrintStream devnull;

    public void init(int nRows, int nCols, int nThreads) {
        this.nCols = nCols;
        this.nRows = nRows;
        lookupValues = new float[nRows][];
        for (int j = 0; j < nRows; j++) {
            lookupValues[j] = new float[nCols];
        }
	try {
	    devnull = new PrintStream(new FileOutputStream("/dev/null"));
	} catch (IOException e) {
	    throw new IllegalArgumentException("Can't open /dev/null " + e);
	}
    }

    public float[][] doCalc() {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(4);
        for (int i = 0; i < nCols; i++) {
            lookupValues[0][i] = 0;
        }
        for (int j = 1; j < nRows; j++) {
            for (int i = 0; i < nCols; i++) {
                float sinValue =
                                (float)Math.sin((i % 360)*Math.PI/180.0);
                lookupValues[j][i] = sinValue * (float)i / 180.0f;
                lookupValues[j][i] +=
                                lookupValues[j-1][i]*(float)j/180.0f;
		if ((j % 20) == 0)
		    devnull.println(nf.format(lookupValues[j][i]));
            }
        }
        return lookupValues;
    }
}
