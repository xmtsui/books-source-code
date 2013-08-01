package javathreads.examples.ch15.example3;

import javathreads.examples.ch15.*;

public class SinTable extends LoopHandler {
    private float lookupValues[];

    public SinTable() {
        super(0, 360*100, 12);
        lookupValues = new float [360 * 100];
    }

    public void loopDoRange(int start, int end) {
        for (int i = start; i < end; i++) {
            float sinValue = (float)Math.sin((i % 360)*Math.PI/180.0);
            lookupValues[i] = sinValue * (float)i / 180.0f;
        }
    }    

    public float[] getValues() {
        loopProcess();
        return lookupValues;
    }

    public static void main(String args[]) {
        System.out.println("Starting Example 3 (Loop Handler Example)");

        SinTable st = new SinTable();
        float results[] = st.getValues();

        System.out.println("Results: "+ results[0]+ ", "+
                      results[1]+ ", "+ results[2]+ ", "+ "...");
        System.out.println("Done");
    }
}
