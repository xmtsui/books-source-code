import java.io.*;
import java.net.*;

public class DeadlockClassLoader extends URLClassLoader {
  private final Object LOCK_OBJECT = new Object();
  
  public DeadlockClassLoader() {
    super(makeClassPath(), null);
  }
  
  private static URL[] makeClassPath() {
    try {
      return new URL[] {
        new File(".").toURI().toURL()
      };
    } catch (Exception _) {
      return new URL[] { };
    }
  }
  
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.printf("loading %s from thread %d\n", name, Thread.currentThread().getId());
    synchronized (LOCK_OBJECT) {
      // simulate some heavy duty task...
      try {
        Thread.sleep(400);
      } catch (Exception _) { }
      
      Class<?> clazz = super.loadClass(name);
      System.out.printf("loaded %s from thread %d\n", name, Thread.currentThread().getId());
      return clazz;
    }
  }
}
