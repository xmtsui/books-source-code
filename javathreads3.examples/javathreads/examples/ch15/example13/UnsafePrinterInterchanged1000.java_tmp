package javathreads.examples.ch15.example13;

import javathreads.examples.ch15.*;
import java.text.*;
import java.io.*;

public class UnsafePrinterInterchanged implements ScaleTester {
    private float lookupValues[][];
    private int nRows, nCols, nThreads;
    private LoopPrinterUnsafe lp;

    private class UnsafePrinterInterchangedHandler
                                    extends GuidedLoopHandler {
        UnsafePrinterInterchangedHandler(int nc, int nt) {
            super(0, nc, 10, nt);
        }

        public void loopDoRange(int start, int end) {
	    NumberFormat nf = (NumberFormat) NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(4);
            for (int i = start; i < end; i++) {
                lookupValues[0][i] = 0;
            }
            for (int i = start; i < end; i++) {
                for (int j = 1; j < nRows; j++) {
                    float sinValue =
                                (float)Math.sin((i % 360)*Math.PI/180.0);
                    lookupValues[j][i] = sinValue * (float)i / 180.0f;
                    lookupValues[j][i] +=
                                lookupValues[j-1][i]*(float)j/180.0f;
		    if ((j % 1000) == 0)
			lp.println(j * nCols + i, nf.format(lookupValues[j][i]));
                }  
            }
        }
    }

    public void init(int nRows, int nCols, int nThreads) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.nThreads = nThreads;
	lp = new LoopPrinterUnsafe(nCols*nRows);
        lookupValues = new float[nRows][];
        for (int j = 0; j < nRows; j++) {
            lookupValues[j] = new float[nCols];
        }
    }

    public float[][] doCalc() {
        UnsafePrinterInterchangedHandler loop =
                    new UnsafePrinterInterchangedHandler(nCols, nThreads);
        loop.loopProcess();
	try {
	    lp.send2stream(new PrintStream(new FileOutputStream("/dev/null")));
	} catch (IOException ioe) {
	    throw new IllegalArgumentException("Can't write to /dev/null " + ioe);
	}
        return lookupValues;
    }
}
