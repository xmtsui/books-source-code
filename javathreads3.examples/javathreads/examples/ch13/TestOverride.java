package javathreads.examples.ch13;

import java.util.*;

public class TestOverride implements Runnable {

    static class OverrideThreadGroup extends ThreadGroup {
        public OverrideThreadGroup() {
            super("Administrator Alert Group");
        }
        public void uncaughtException(Thread t, Throwable e) {
            alertAdministrator(e);
        }
    }

    public static void alertAdministrator(Throwable e) {
        // Use Java Mail to send the administrator's pager an email
        System.out.println("Adminstrator alert!");
        e.printStackTrace();
    }

    public static void main(String[] args) {
        ThreadGroup tg = new OverrideThreadGroup();

        Thread t = new Thread(tg, new TestOverride());
        t.start();
    }

    public void run() {
        ArrayList al = new ArrayList();
        while (true) {
            al.add(new byte[1024]);
        }
    }
}
