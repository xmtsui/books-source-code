public class NoClassDefFoundErrorDemo {
  public static void main(String[] args) {
    System.out.println("========== Test 1 ==========");
    try {
      new Foo(); // try loading class Foo once, throws ExceptionInInitializerError
    } catch (Throwable t) {
      t.printStackTrace();
    }
    
    System.out.println("========== Test 2 ==========");
    try {
      new Foo(); // try it again
      // Because Foo was already in erroneous state,
      // so a NoClassDefFoundError is thrown instead of another ExceptionInInitializerError.
      // For more details, see JVM Spec, 2nd Edition, 2.17.5, step 5.
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}

class Foo {
  private static Bar bar = new Bar();
}

class Bar {
  static {
    try {
      Baz.test();
    } catch (Throwable t) {
      Exception e = (Exception) t;
    }
  }
}

/*
java NoClassDefFoundErrorDemo

========== Test 1 ==========
java.lang.ExceptionInInitializerError
        at Foo.<clinit>(NoClassDefFoundErrorDemo.java:23)
        at NoClassDefFoundErrorDemo.main(NoClassDefFoundErrorDemo.java:5)
Caused by: java.lang.ClassCastException: java.lang.NoSuchMethodError cannot be cast to java.lang.Exception
        at Bar.<clinit>(NoClassDefFoundErrorDemo.java:31)
        ... 2 more
========== Test 2 ==========
java.lang.NoClassDefFoundError: Could not initialize class Foo
        at NoClassDefFoundErrorDemo.main(NoClassDefFoundErrorDemo.java:12)
*/

/*
java -verbose NoClassDefFoundErrorDemo

...
[Loaded NoClassDefFoundErrorDemo from file:/D:/test/ClassLoadingSamples/4_NoClassDefFoundError/]
========== Test 1 ==========
[Loaded Foo from file:/D:/test/ClassLoadingSamples/4_NoClassDefFoundError/]
[Loaded Bar from file:/D:/test/ClassLoadingSamples/4_NoClassDefFoundError/]
[Loaded Baz from file:/D:/test/ClassLoadingSamples/4_NoClassDefFoundError/]
[Loaded java.lang.ExceptionInInitializerError from shared objects file]
java.lang.ExceptionInInitializerError
        at Foo.<clinit>(NoClassDefFoundErrorDemo.java:23)
        at NoClassDefFoundErrorDemo.main(NoClassDefFoundErrorDemo.java:5)
Caused by: java.lang.ClassCastException: java.lang.NoSuchMethodError cannot be cast to java.lang.Exception
        at Bar.<clinit>(NoClassDefFoundErrorDemo.java:31)
        ... 2 more
========== Test 2 ==========
java.lang.NoClassDefFoundError: Could not initialize class Foo
        at NoClassDefFoundErrorDemo.main(NoClassDefFoundErrorDemo.java:12)
*/
