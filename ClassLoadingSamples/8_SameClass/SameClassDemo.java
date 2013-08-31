import java.io.*;
import java.net.*;

public class SameClassDemo {
  private static Object getFooInstanceFromAnotherClassLoader() throws Exception {
    ClassLoader loader = new URLClassLoader(new URL[] {
      new File(".").toURI().toURL()
    }, null);
    return Class.forName("Foo", true, loader).newInstance();
  }
  
  public static void test(Foo foo) {
  }
  
  private static void reflectInvokeTest(Object foo) throws Exception {
    SameClassDemo.class.getMethod("test", new Class<?>[] { Foo.class }).invoke(null, foo);
  }
  
  public static void main(String[] args) throws Exception {
    Object foo = getFooInstanceFromAnotherClassLoader();
    reflectInvokeTest(foo);
  }
}

/*
java SameClassDemo
Exception in thread "main" java.lang.IllegalArgumentException: argument type mismatch
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.lang.reflect.Method.invoke(Unknown Source)
        at SameClassDemo.reflectInvokeTest(SameClassDemo.java:16)
        at SameClassDemo.main(SameClassDemo.java:21)
*/

