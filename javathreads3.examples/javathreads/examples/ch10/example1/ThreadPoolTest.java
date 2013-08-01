package javathreads.examples.ch10.example1;

import java.util.concurrent.*;
import javathreads.examples.ch10.*;

public class ThreadPoolTest {

    public static void main(String[] args) {
        int nTasks = Integer.parseInt(args[0]);
        long n = Long.parseLong(args[1]);
        int tpSize = Integer.parseInt(args[2]);

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
            tpSize, tpSize, 50000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        Task[] tasks = new Task[nTasks];
        for (int i = 0; i < nTasks; i++) {
            tasks[i] = new Task(n, "Task " + i);
            tpe.execute(tasks[i]);
        }
        tpe.shutdown();
    }
}
