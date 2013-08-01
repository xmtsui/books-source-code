package javathreads.examples.ch15.example11;

import javathreads.examples.ch15.*;

public class GuidedAtomicLoopInterchanged implements ScaleTester {
    private float lookupValues[][];
    private int nRows, nCols, nThreads;
    AtomicDouble sumValue = new AtomicDouble();

    private class GuidedLoopInterchangedHandler
                                    extends GuidedLoopHandler {
        GuidedLoopInterchangedHandler(int nc, int nt) {
            super(0, nc, 10, nt);
        }

        public void loopDoRange(int start, int end) {
            float sum = 0.0f;
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
		    sum += lookupValues[j][i];
                }  
            }
	    sumValue.addAndGet(sum);
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
        GuidedLoopInterchangedHandler loop =
                    new GuidedLoopInterchangedHandler(nCols, nThreads);
        loop.loopProcess();
	lookupValues[0][0] += sumValue.get();
        return lookupValues;
    }
}
