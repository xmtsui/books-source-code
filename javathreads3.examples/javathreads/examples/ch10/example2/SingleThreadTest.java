package javathreads.examples.ch10.example2;

import javathreads.examples.ch10.*;

public class SingleThreadTest {

    public static void main(String[] args) {
        int nTasks = Integer.parseInt(args[0]);
	int fib = Integer.parseInt(args[1]);
	SingleThreadAccess sta = new SingleThreadAccess();
	for (int i = 0; i < nTasks; i++)
	    sta.invokeLater(new Task(fib, "Task " + i));
	sta.shutdown();
    }
}
