public class LoadedVersusInitialized {
  private static void loadWithoutInit(ClassLoader loader)
       throws Exception {
    System.out.println("Class.forName(\"Foo\", false, currentLoader) invoked");
    Class<?> clazz = Class.forName("Foo", false, loader);
    System.out.println("class Foo loaded but not initialized");
    clazz.newInstance();
    System.out.println("class Foo initialized");
  }
  
  private static void loadWithInit()
       throws Exception {
    System.out.println("Class.forName(\"Bar\") invoked");
    Class<?> clazz = Class.forName("Bar"); // Class.forName("Bar", true, currentLoader);
    System.out.println("class Bar initialized");
  }
  
  private static void loadInterfaceWithInit()
       throws Exception {
    System.out.println("Class.forName(\"IBaz\") invoked");
    Class<?> clazz = Class.forName("IBaz"); // Class.forName("IBaz", true, currentLoader);
    System.out.println("interface IBaz initialized");
  }
  
  public static int dummyValue() {
    System.out.println("dummyValue() invoked from IBaz.<clinit>()");
    return 42;
  }
  
  public static void main(String[] args) throws Exception {
    ClassLoader currentLoader = LoadedVersusInitialized.class.getClassLoader();
    loadWithoutInit(currentLoader);
    System.out.println();
    loadWithInit();
    System.out.println();
    loadInterfaceWithInit();
  }
}

class Foo {
  static {
    System.out.println("Foo.<clinit>() invoked");
  }
}

class Bar {
  static {
    System.out.println("Bar.<clinit>() invoked");
  }
}

interface IBaz {
  int value = LoadedVersusInitialized.dummyValue();
}

/*
java -verbose LoadedVersusInitialized

Class.forName("Foo", false, currentLoader) invoked
[Loaded Foo from file:/D:/test/ClassLoadingSamples/3_LoadedVersusInitialized/]
class Foo loaded but not initialized
Foo.<clinit>() invoked
class Foo initialized

Class.forName("Bar") invoked
[Loaded Bar from file:/D:/test/ClassLoadingSamples/3_LoadedVersusInitialized/]
Bar.<clinit>() invoked
class Bar initialized

Class.forName("IBaz") invoked
[Loaded IBaz from file:/D:/test/ClassLoadingSamples/3_LoadedVersusInitialized/]
dummyValue() invoked from IBaz.<clinit>()
interface IBaz initialized
*/
