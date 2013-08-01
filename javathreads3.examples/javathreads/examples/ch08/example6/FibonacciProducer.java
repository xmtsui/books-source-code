package javathreads.examples.ch08.example6;

import java.util.*;
import java.util.concurrent.*;

public class FibonacciProducer implements Runnable {
    private Thread thr;
    private BlockingQueue<Integer> queue;

    public FibonacciProducer(BlockingQueue<Integer> q) {
        queue = q;
        thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            for(int x=0;;x++) {
                Thread.sleep(1000);
                queue.put(new Integer(x));
                System.out.println("Produced request " + x);
            }
        } catch (InterruptedException ex) {
        }
    }
}
