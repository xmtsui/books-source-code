import java.util.Scanner;
import java.math.BigInteger;
public class _t1_add
{
    public static void main(String args[])
    {
      /*
      Scanner s = new Scanner(System.in);
      int a = s.nextInt();
      int b = s.nextInt();
      System.out.println(a+b);
      */

      //Big integer
      Scanner s = new Scanner(System.in);
      BigInteger a = s.nextBigInteger();
      BigInteger b = s.nextBigInteger();
      System.out.println(a.add(b));
    }
}
