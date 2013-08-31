public class VerifyErrorDemoMain {
  public static void main(String[] args) {
    System.out.println("before VerifyErrorDemo.test()");
    VerifyErrorDemo.test();
    System.out.println("after VerifyErrorDemo.test()");
  }
}

/*
java VerifyErrorDemoMain
before VerifyErrorDemo.test()
Exception in thread "main" java.lang.VerifyError: (class: VerifyErrorDemo, method: test signature: ()V) Unable to pop operand off an empty stack
        at VerifyErrorDemoMain.main(VerifyErrorDemoMain.java:4)
*/
