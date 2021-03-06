//**********************************************************
//  Codes.java       Authors: Lewis/Chase
//
//  Demonstrates the use of queues to encrypt and decrypt messages.
//**********************************************************
import jss2.LinkedQueue;
public class Codes
{
   //-----------------------------------------------------------------
   //  Encode and decode a message using a key of values stored in
   //  a queue.
   //-----------------------------------------------------------------
   public static void main ( String[] args)
   { 
      int[] key = {5, 12, -3, 8, -9, 4, 10};
      int keyValue;

      String encoded = "", decoded = ""; 

      String message = "All programmers are playwrights and all " +
                       "computers are lousy actors.";

      LinkedQueue keyQueue1 = new LinkedQueue();
      LinkedQueue keyQueue2 = new LinkedQueue();

      // load key queue
      for (int scan=0; scan < key.length; scan++)
      {
         keyQueue1.enqueue (new Integer(key[scan]));
         keyQueue2.enqueue (new Integer(key[scan]));
      }

      // encode message
      for (int scan=0; scan < message.length(); scan++)
      {
         keyValue = ((Integer) keyQueue1.dequeue()).intValue();
         encoded += (char) ((int)message.charAt(scan) + keyValue);
         keyQueue1.enqueue (new Integer(keyValue));
      }

      System.out.println ("Encoded Message:\n" + encoded + "\n");

      // decode message
      for (int scan=0; scan < encoded.length(); scan++)
      {
         keyValue = ((Integer) keyQueue2.dequeue()).intValue();
         decoded += (char) ((int)encoded.charAt(scan) - keyValue);
         keyQueue2.enqueue (new Integer(keyValue));
      }

      System.out.println ("Decoded Message:\n" + decoded);
   }
}


