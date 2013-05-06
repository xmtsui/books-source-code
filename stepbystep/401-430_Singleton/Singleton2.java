public class Singleton2{

  private static Singleton2 instance;
  private final static Object lock = new Object();

  private Singleton2(){}
  public static Singleton2(){}

  public static Singleton2 getInstance()
  {
    synchronized(lock)
    {
      if(instance == null)
      {
        instance = new Singleton2();
      }
    }
    return instance;
  }
}