//*************************************************************************
//  PostfixEvaluator2.java              Authors:  Lewis/Chase
//
//  This modification of our stack example uses a pair of stacks to 
//  create an expression tree from a VALID postfix integer expression 
//  and then uses a recursive method from the ExpressionTree class to 
//  evaluate the tree.
//*************************************************************************
import jss2.*;
import jss2.exceptions.*;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.io.*;

public class PostfixEvaluator2   
{
   /*******************************************************************
     Retrieves and returns the next operand off of this tree stack.
   *******************************************************************/
   private ExpressionTree getOperand(LinkedStack<ExpressionTree> treeStack)
   {
      ExpressionTree temp;
      temp = treeStack.pop();
      
      return temp;
   }
    
   /*******************************************************************
     Retrieves and returns the next token, either an operator or 
     operand from the user.
   *******************************************************************/
   private String getNextToken() 
   {
      String tempToken = "0", inString;
      StringTokenizer tokenizer;
      
      try
      {
         BufferedReader in =
               new BufferedReader( new InputStreamReader(System.in));
         inString = in.readLine();
         tokenizer = new StringTokenizer(inString);
         tempToken = (tokenizer.nextToken());
      }
      catch (Exception IOException)
      {
         System.out.println("An input/output exception has occurred");
      }
      
      return tempToken;
   }
   
   /*******************************************************************
     Prompts the user for a valid post-fix expression, converts it to 
     an expression tree using a two stack method, then calls a 
     recursive method to evaluate the expression.
   *******************************************************************/
   public void solve ()
   {
      ExpressionTree operand1, operand2;
      char operator;
      String tempToken;
      LinkedStack<ExpressionTree> treeStack = new LinkedStack<ExpressionTree>();

      System.out.println("Enter a valid post-fix expression one token " +
                         "at a time pressing the enter key after each token");
      System.out.println("Enter an integer, an operator (+,-,*,/) then ! to evaluate ");
      
      tempToken = getNextToken();
      operator = tempToken.charAt(0);
      
      while (!(operator == '!')) 
      {
         if ((operator == '+') || (operator == '-') || (operator == '*') || 
             (operator == '/'))
         {
            operand1 = getOperand(treeStack);
            operand2 = getOperand(treeStack);
            treeStack.push(new ExpressionTree 
                          (new ExpressionTreeObj(1,operator,0), operand2, operand1));
         }
         else
         {
            treeStack.push(new ExpressionTree (new ExpressionTreeObj
                           (2,' ',Integer.parseInt(tempToken)), null, null));
         }
         
         tempToken = getNextToken();
         operator = tempToken.charAt(0);
      }
      
      System.out.print("The result is ");
      System.out.println(((ExpressionTree)treeStack.peek()).evaluateTree());
   }
}
