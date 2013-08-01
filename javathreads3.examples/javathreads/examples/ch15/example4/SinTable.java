package javathreads.examples.ch15.example4;

import javathreads.examples.ch15.*;

public class SinTable extends GuidedLoopHandler {
    private float lookupValues[];
    private float sinValue;
 
    public SinTable() {
        super(0, 360*100, 100, 12);
        lookupValues = new float [360 * 100];
    }
 
    public void loopDoRange(int start, int end) {
        float sinValue = 0;
        for (int i = start; i < end; i++) {
            sinValue = (float)Math.sin((i % 360)*Math.PI/180.0);
            lookupValues[i] = sinValue * (float)i / 180.0f;
        }
        if (end == endLoop)
            this.sinValue = sinValue;
    }
 
    public float[] getValues() {
        loopProcess();
        lookupValues[0] += sinValue;
        return lookupValues;
    }

    public static void main(String args[]) {
        System.out.println("Starting Example 4 (Storeback Variable Example)");

        SinTable st = new SinTable();
        float results[] = st.getValues();

        System.out.println("Results: "+ results[0]+ ", "+
                      results[1]+ ", "+ results[2]+ ", "+ "...");
        System.out.println("Done");
    }
}
