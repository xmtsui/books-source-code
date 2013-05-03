public class Singleton2{
  private static Singleton2 Instance;

  public static Singleton2 getInstance()
  {
		//if(Instance == null)
		//{
			Instance = new Singleton2();
 		//}
  		return Instance;
  }  

  public static void main(String args[])
  {

    Singleton2 s21 = new Singleton2().getInstance();
    Singleton2 s22 = new Singleton2().getInstance();
    if (s21==s22)
      System.out.println("s1 is the same instance with s2");
    else
      System.out.println("s1 is not the same instance with s2" + Singleton.getInstance());

    Singleton s11 = new Singleton().getInstance();
    Singleton s12 = new Singleton().getInstance();
    if (s11==s12)
      System.out.println("s1 is the same instance with s2");
    else
      System.out.println("s1 is not the same instance with s2");
  }
}
