import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClasspathDemo {
  private static void loadWithCurrentLoader() throws Exception {
    // ClassLoader currentLoader = ClasspathDemo.class.getClassLoader();
    Class.forName("Alice"); // Class.forName("Alice", true, currentLoader)
    try {
      Class.forName("Bob"); // Class.forName("Bob", true, currentLoader)
    } catch (ClassNotFoundException e) {
      System.out.println("class Bob cannot be found by the current loader");
    }
  }
  
  private static void loadWithCustomLoader() throws Exception {
    ClassLoader loader = new URLClassLoader(new URL[] {
      new File("bob").toURI().toURL()
    });
    try {
      Class.forName("Bob", true, loader);
    } catch (ClassNotFoundException e) {
      System.out.println("class Bob cannot be found by the custom loader");
    }
  }
  
  public static void main(String[] args) throws Exception {
    loadWithCurrentLoader();
    loadWithCustomLoader();
    System.in.read();
  }
}

/*
java -classpath ".;alice" -verbose ClasspathDemo

[Loaded ClasspathDemo from file:/D:/test/ClassLoadingSamples/1_Classpath/]
[Loaded Alice from file:/D:/test/ClassLoadingSamples/1_Classpath/alice/]
class Bob cannot be found by the current loader
...
[Loaded Bob from file:/D:/test/ClassLoadingSamples/1_Classpath/bob/]
*/
