public class LoadClassFromBytes {
  public static void main(String[] args) throws Exception {
    new MyClassLoader().loadClass("Foo");
  }
}

class MyClassLoader extends ClassLoader {
  private static byte[] makeFooClassBytes() {
    return new byte[] {
      (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE,
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x32,
      (byte) 0x00, (byte) 0x0A, (byte) 0x0A, (byte) 0x00,
      (byte) 0x03, (byte) 0x00, (byte) 0x07, (byte) 0x07,
      (byte) 0x00, (byte) 0x08, (byte) 0x07, (byte) 0x00,
      (byte) 0x09, (byte) 0x01, (byte) 0x00, (byte) 0x06,
      (byte) 0x3C, (byte) 0x69, (byte) 0x6E, (byte) 0x69,
      (byte) 0x74, (byte) 0x3E, (byte) 0x01, (byte) 0x00,
      (byte) 0x03, (byte) 0x28, (byte) 0x29, (byte) 0x56,
      (byte) 0x01, (byte) 0x00, (byte) 0x04, (byte) 0x43,
      (byte) 0x6F, (byte) 0x64, (byte) 0x65, (byte) 0x0C,
      (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x05,
      (byte) 0x01, (byte) 0x00, (byte) 0x03, (byte) 0x46,
      (byte) 0x6F, (byte) 0x6F, (byte) 0x01, (byte) 0x00,
      (byte) 0x10, (byte) 0x6A, (byte) 0x61, (byte) 0x76,
      (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61,
      (byte) 0x6E, (byte) 0x67, (byte) 0x2F, (byte) 0x4F,
      (byte) 0x62, (byte) 0x6A, (byte) 0x65, (byte) 0x63,
      (byte) 0x74, (byte) 0x00, (byte) 0x21, (byte) 0x00,
      (byte) 0x02, (byte) 0x00, (byte) 0x03, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
      (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00,
      (byte) 0x04, (byte) 0x00, (byte) 0x05, (byte) 0x00,
      (byte) 0x01, (byte) 0x00, (byte) 0x06, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x00,
      (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x2A,
      (byte) 0xB7, (byte) 0x00, (byte) 0x01, (byte) 0xB1,
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
      (byte) 0x00, (byte) 0x00
    };
  }
  
  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    if ("Foo".equals(name)) {
      byte[] bytes = makeFooClassBytes();
      return defineClass(name, bytes, 0, bytes.length);
    } else {
      throw new ClassNotFoundException(name);
    }
  }
}

/*
java -verbose LoadClassFromBytes

[Loaded LoadClassFromBytes from file:/D:/test/ClassLoadingSamples/2_LoadClassFromBytes/]
[Loaded MyClassLoader from file:/D:/test/ClassLoadingSamples/2_LoadClassFromBytes/]
[Loaded Foo from __JVM_DefineClass__]
*/
