package javathreads.examples.ch15.example12;

import javathreads.examples.ch15.*;

public class GuidedLoopInterchanged implements ScaleTester {
    private float lookupValues[][];
    private int nRows, nCols, nThreads;
    private int j;        // Now global for inner loop

    private class GuidedLoopInterchangedHandler
                                    extends GuidedLoopHandler {
        GuidedLoopInterchangedHandler(int nc, int nt) {
            super(0, nc, 10, nt);
        }

        public void loopDoRange(int start, int end) {
	    float sinValue = 0.0f;
            for (int i = start; i < end; i++) {
                    sinValue = (float)Math.sin((i % 360)*Math.PI/180.0);
                    lookupValues[j][i] = sinValue * (float)i / 180.0f;
                    lookupValues[j][i] +=
                                lookupValues[j-1][i]*(float)j/180.0f;
            }
        }
    }

    public void init(int nRows, int nCols, int nThreads) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.nThreads = nThreads;
        lookupValues = new float[nRows][];
        for (int j = 0; j < nRows; j++) {
            lookupValues[j] = new float[nCols];
        }
    }

    public float[][] doCalc() {
	for (int i = 0; i < nCols; i++)
	    lookupValues[0][i] = 0;
        GuidedLoopInterchangedHandler loop =
                    new GuidedLoopInterchangedHandler(nCols, nThreads);
	for (j = 1; j < nRows; j++)
	    loop.loopProcess();
        return lookupValues;
    }
}
