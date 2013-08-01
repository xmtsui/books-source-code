package javathreads.examples.ch14;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class HashTest implements Runnable {
    static int nLoops;
    static int nThreads;

    public static void main(String[] args) {

	nLoops = 1000000;
        nThreads = 1;
        doTest(new Hashtable());
        doTest(new HashMap());
        doTest(new ConcurrentHashMap());
	nLoops = Integer.parseInt(args[0]);

        System.out.println("Starting standard hashmap test");
	cleanGC();
	Timestamp hashTS = new Timestamp();
        doTest(new HashMap());
	hashTS.stop();
        System.out.println("Standard hashmap (1 thread) took " + hashTS);

        System.out.println("Starting standard hashtable test");
	cleanGC();
	Timestamp hashtableTS = new Timestamp();
        doTest(new Hashtable());
	hashtableTS.stop();
        System.out.println("Standard hashtable (1 thread) took " + hashtableTS);

        System.out.println("Starting concurrent hashmap test");
	cleanGC();
	Timestamp concTS = new Timestamp();
        doTest(new ConcurrentHashMap());
	concTS.stop();
        System.out.println("Concurrent hashmap (1 thread) took " + concTS);

	nThreads = Integer.parseInt(args[1]);

        System.out.println("Starting standard hashtable test");
	cleanGC();
	Timestamp hashNTS = new Timestamp();
        doTest(new Hashtable());
	hashNTS.stop();
        System.out.println("Standard hashtable (" + nThreads + " threads) took " + hashNTS);

        System.out.println("Starting concurrent hashmap test");
	cleanGC();
	Timestamp concNTS = new Timestamp();
        doTest(new ConcurrentHashMap());
	concNTS.stop();
        System.out.println("Concurrent hashmap (" + nThreads + " threads) took " + concNTS);

	double d = ((double) (hashtableTS.elapsedTime() - hashTS.elapsedTime())) /
		nLoops;
	System.out.println("Hashmap saves " + d + " " + hashTS.units() + " per call vs. hashtable");

	d = ((double) (hashTS.elapsedTime() - concTS.elapsedTime())) /
		nLoops;
	System.out.println("Unsynchronized operation (1 thread) saves " + d + " " + concTS.units() + " per call");

	d = ((double) (hashNTS.elapsedTime() - concNTS.elapsedTime())) /
		(nLoops * nThreads);
	System.out.println("Unsynchronized operation (" + nThreads + " threads) saves " + d + " " + concNTS.units() + " per call");
    }

    static void cleanGC() {
        System.gc();
        System.runFinalization();
        System.gc();
    }

    static void doTest(Map m) {
	targetMap = m;
	Thread[] t = new Thread[nThreads];
	Runnable r = new HashTest();
	for (int i = 0; i < nThreads; i++)
	    t[i] = new Thread(r);
	for (int i = 0; i < nThreads; i++)
	    t[i].start();
	for (int i = 0; i < nThreads; i++)
	    try { t[i].join(); } catch (Exception e) {}
    }

    static Map targetMap;
    public void run() {
	for (int i = 0; i < nLoops; i++) {
	    Integer I = new Integer(i);
	    targetMap.put(I, I);
	    targetMap.get(I);
	}
    }    
}
