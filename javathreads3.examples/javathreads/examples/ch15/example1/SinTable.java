package javathreads.examples.ch15.example1;

public class SinTable {
    private float lookupValues[] = null;

    public synchronized float[] getValues() {
        if (lookupValues == null) {
            lookupValues = new float [360 * 100];
            for (int i = 0; i < (360*100); i++) {
                float sinValue = (float)Math.sin(
                                    (i % 360)*Math.PI/180.0);
                lookupValues[i] = sinValue * (float)i / 180.0f;
            }    
        }
        return lookupValues;
    }

    public static void main(String args[]) {
        System.out.println("Starting Example 1 (Control Example)");

        SinTable st = new SinTable();
        float results[] = st.getValues();

        System.out.println("Results: "+ results[0]+ ", "+
                      results[1]+ ", "+ results[2]+ ", "+ "...");
        System.out.println("Done");
    }
}
