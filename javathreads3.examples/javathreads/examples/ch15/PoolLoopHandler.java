package javathreads.examples.ch15;

import java.util.concurrent.*;

public class PoolLoopHandler implements Runnable {
    protected static class LoopRange {
        public int start, end;
    }

    protected static class PoolHandlerFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
	    Thread t = new Thread(r);
	    t.setDaemon(true);
	    return t;
	}
    }

    static protected ThreadPoolExecutor threadpool;
    static protected int maxThreads = 1;
    protected int startLoop, endLoop, curLoop, numThreads;

    synchronized static void getThreadPool(int threads) {
        if (threadpool == null)
	    threadpool = new ThreadPoolExecutor(
				1, 1,
				50000L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				new PoolHandlerFactory());
	if (threads > maxThreads) {
	    maxThreads = threads;
	    threadpool.setMaximumPoolSize(maxThreads);
	    threadpool.setCorePoolSize(maxThreads);
	}
    }

    public PoolLoopHandler(int start, int end, int threads) {
        numThreads = threads;
	getThreadPool(numThreads);
        setRange(start, end);
    }

    public synchronized void setRange(int start, int end) {
        startLoop = start;
        endLoop = end;
        reset();
    }

    public synchronized void reset() {
        curLoop = startLoop;
    }

    protected synchronized LoopRange loopGetRange() {
        if (curLoop >= endLoop)
            return null;
        LoopRange ret = new LoopRange();
        ret.start = curLoop;
        curLoop += (endLoop-startLoop)/numThreads+1;
        ret.end = (curLoop<endLoop)?curLoop:endLoop;
        return ret;
    }
 
    public void loopDoRange(int start, int end) {
    }
 
    public void loopProcess() {
        reset();
        FutureTask t[] = new FutureTask[numThreads];
        for (int i = 0; i < numThreads; i++) {
            t[i] = new FutureTask(this, null);
            threadpool.execute(t[i]);
        }
        for (int i = 0; i < numThreads; i++) {
	    try {
                t[i].get();
	    } catch (ExecutionException ee) {
	    } catch (InterruptedException ie) {
	    }
        }
    }
 
    public void run() {
        LoopRange str;
        while ((str = loopGetRange()) != null) {
            loopDoRange(str.start, str.end);
        }
    }
}
