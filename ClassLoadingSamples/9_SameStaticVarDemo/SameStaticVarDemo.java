import java.io.*;
import java.util.*;
import java.net.*;

public class SameStaticVarDemo {
  private static Class<?> getFooClassFromAnotherClassLoader() throws Exception {
    ClassLoader loader = new URLClassLoader(new URL[] {
      new File(".").toURI().toURL()
    }, /* parent */ null);
    return Class.forName("Foo", true, loader);
  }
  
  @SuppressWarnings("unchecked")
  private static List<String> getList(Class<?> clazz) throws Exception {
    return (List) clazz.getField("list").get(null);
  }
  
  public static void main(String[] args) throws Exception {
    Foo.list.add("alpha");
    
    Class<?> anotherFooClass = getFooClassFromAnotherClassLoader();
    List<String> anotherList = getList(anotherFooClass);
    anotherList.add("beta");
    
    System.out.printf("Foo.list: %s\n", Foo.list.toString());
    System.out.printf("anotherFoo.list: %s\n", anotherList.toString());
    System.out.printf("Foo.class == anotherFooClass: %b\n", Foo.class == anotherFooClass);
  }
}

/*
java SameStaticVarDemo

Foo.list: [alpha]
anotherFoo.list: [beta]
Foo.class == anotherFooClass: false
*/

