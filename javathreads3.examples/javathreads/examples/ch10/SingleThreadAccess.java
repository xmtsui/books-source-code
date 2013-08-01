package javathreads.examples.ch10;

import java.util.concurrent.*;
import java.io.*;

public class SingleThreadAccess {

    private ThreadPoolExecutor tpe;

    public SingleThreadAccess() {
        tpe = new ThreadPoolExecutor(
        	1, 1, 50000L, TimeUnit.SECONDS,
        	new LinkedBlockingQueue<Runnable>());
    }

    public void invokeLater(Runnable r) {
        tpe.execute(r);
    }

    public void invokeAneWait(Runnable r) throws InterruptedException, ExecutionException {
        FutureTask task = new FutureTask(r, null);
        tpe.execute(task);
        task.get();
    }

    public void shutdown() {
        tpe.shutdown();
    }
}
