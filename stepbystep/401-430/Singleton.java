public class Singleton{
  public static Singleton Instance;
  private Singleton()
  {
    
  }
  public static Singleton getInstance()
  {
		if(Instance == null)
		{
			Instance = new Singleton();
 		}
  		return Instance;
  }  

  public static void main(String args[])
  {

    Singleton s1 = new Singleton().getInstance();
    Singleton s2 = new Singleton().getInstance();
    if (s1==s2)
      System.out.println("s1 is the same instance with s2");
    else
      System.out.println("s1 is not the same instance with s2");
  }
}
