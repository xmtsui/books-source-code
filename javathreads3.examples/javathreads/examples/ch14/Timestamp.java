package javathreads.examples.ch14;

import java.util.concurrent.*;

public class Timestamp {

    private long startTime;
    private long stopTime;
    private boolean stopped = false;
    private TimeUnit ts;

    public Timestamp() {
	this(TimeUnit.NANOSECONDS);
    }

    public Timestamp(TimeUnit ts) {
	this.ts = ts;
        start();
    }

    public void start() {
        startTime = System.nanoTime();
	stopped = false;
    }

    public void stop() {
        stopTime = System.nanoTime();
	stopped = true;
    }

    public long elapsedTime() {
	if (!stopped)
	    throw new IllegalStateException("Timestamp not stopped");
	return ts.convert(stopTime - startTime, TimeUnit.NANOSECONDS);
    }

    public String toString() {
	try {
	    return elapsedTime() + " " + ts;
	} catch (IllegalStateException ise) {
	    return "Timestamp (not stopped)";
	}
    }

    public String units() {
	return ts.toString();
    }
}
