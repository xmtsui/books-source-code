package javathreads.examples.ch13;

import java.util.*;

public class TestOverrideThread implements Runnable {

    static class OverrideExceptionHandler implements Thread.UncaughtExceptionHandler {
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
        Thread t = new Thread(new TestOverrideThread());
        t.setUncaughtExceptionHandler(new OverrideExceptionHandler());
        System.out.println(t.getUncaughtExceptionHandler());
        t.start();
    }

    public void run() {
        ArrayList al = new ArrayList();
        while (true) {
            al.add(new byte[1024]);
        }
    }
}
