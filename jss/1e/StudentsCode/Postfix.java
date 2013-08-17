//********************************************************************
//  Postfix.java       Authors: Lewis/Chase
//
//  Demonstrates the use of a stack to evaluate postfix expressions.
//********************************************************************

import java.util.StringTokenizer;
import java.io.*;

public class Postfix    
{
   //-----------------------------------------------------------------
   //  Reads and evaluates multiple postfix expressions.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      String expression, again;
      int result;

      try
      {
         BufferedReader in = new 
             BufferedReader( new InputStreamReader(System.in));

         PostfixEvaluator evaluator = new PostfixEvaluator();

         do
         {
            System.out.println ("Enter a valid postfix expression: ");
            expression = in.readLine();

            result = evaluator.evaluate (expression);
            System.out.println();
            System.out.println ("That expression equals " + result);

            System.out.print ("Evaluate another expression [Y/N]? ");
            again = in.readLine();
            System.out.println();
         }
         while (again.equalsIgnoreCase("y"));
      }
      catch (Exception IOException)
	 {
         System.out.println("Input exception reported");
      }
   }
}
