package classloader;

import java.lang.reflect.Method;

/**
 * eclipse运行看不到效果 需要去命令行运行如下命令
 * javac -cp ./classloader classloader/FileSystemClassLoader.java classloader/ClassIdentity.java
 * java -cp .:./classloader classloader/ClassIdentity classloader/FileSystemClassLoader
 */
public class ClassIdentity {

	public static void main(String[] args) {
		new ClassIdentity().testClassIdentity();
	}
	
	public void testClassIdentity() {
		String classDataRootPath = "/Users/saixiaomin/Documents/git_workspace/books-sorce-code/Classloader/bin";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		System.out.println("initial fscl1: " + fscl1);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
		System.out.println("initial fscl2: " + fscl2);
		String className = "com.example.Sample";
		
		ClassLoader loader = fscl1;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		System.out.println("------");
		
		try {
			ClassLoader clold = Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(fscl1);
			System.out.println("old cl before class1: " + clold);
			
			Class<?> class1 = fscl1.loadClass(className);
			Object obj1 = class1.newInstance();
			System.out.println("now obj1 is : " + obj1);
			System.out.println("now class1 cl is : " + class1.getClassLoader());
			
			Thread.currentThread().setContextClassLoader(fscl2);
			Class<?> class2 = fscl2.loadClass(className);
			Object obj2 = class2.newInstance();
			System.out.println("now obj2 is : " + obj2);
			System.out.println("now class2 cl is : " + class2.getClassLoader());
			
			Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
//			setSampleMethod.invoke(obj1, obj2);
			
			Method testInstanceOfMethod = class1.getMethod("testInstanceOf", java.lang.Object.class);
			Method testInstanceOfMethod1 = class2.getMethod("testInstanceOf", java.lang.Object.class);
			System.out.println(testInstanceOfMethod.invoke(obj1, obj1));
			System.out.println(testInstanceOfMethod.invoke(obj1, obj2));
			System.out.println(testInstanceOfMethod1.invoke(obj2, obj1));
			System.out.println(testInstanceOfMethod1.invoke(obj2, obj2));
			
			System.out.println("+++++++++");
			System.out.println(class1.isInstance(obj1));
			System.out.println(class1.isInstance(obj2));
			System.out.println(class2.isInstance(obj1));
			System.out.println(class2.isInstance(obj2));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
