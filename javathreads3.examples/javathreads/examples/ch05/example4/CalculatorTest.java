package javathreads.examples.ch05.example4;

public class CalculatorTest extends Calculator implements Runnable {

    public static void main(String[] args) {
        int nThreads = Integer.parseInt(args[0]);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(new CalculatorTest());
            t.start();
        }
    }

    public void run() {
        for (int i = 0; i < 30; i++) {
            Integer p = new Integer(i % 5);
            calculate(p);
        }
    }

    protected Object doLocalCalculate(Object p) {
        System.out.println("Doing calculation of " + p + " in thread " + Thread.currentThread());
        return p;
    }
}
