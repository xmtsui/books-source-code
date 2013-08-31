public class DeadlockDemo {
  public static ClassLoader loader = new DeadlockClassLoader();
  
  public static void main(String[] args) {
    Thread thread1 = new MyThread1(loader);
    Thread thread2 = new MyThread2(loader);
    thread1.start();
    thread2.start();
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("done class loading, shutting down");
      }
    }));
  }
}

class MyThread1 extends Thread {
  private ClassLoader loader;
  
  public MyThread1(ClassLoader loader) {
    this.loader = loader;
    setDaemon(false);
  }
  
  @Override
  public void run() {
    try {
      Thread.sleep(100);
      System.out.println("running thread " + getId());
      loader.loadClass("Foo");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class MyThread2 extends Thread {
  private ClassLoader loader;
  
  public MyThread2(ClassLoader loader) {
    this.loader = loader;
    setDaemon(false);
  }
  
  @Override
  public void run() {
    System.out.println("running thread " + getId());
    synchronized (loader) {
      System.out.printf("thread %d locked loader\n", getId());
      try {
        Thread.sleep(1000);
        loader.loadClass("Bar");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

class Foo {
}

class Bar {
}

/*
java DeadlockDemo

running thread 9
thread 9 locked loader
running thread 8
loading Foo from thread 8
loading Bar from thread 9

... and there goes dead lock
*/
