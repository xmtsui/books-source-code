//************************************************************
//  InfixToPostfix.Java      Authors:  Lewis/Chase
//  
//  Provides an implementation of an infix to postfix
//  converter for expressions
//************************************************************
/**
Infix to Postfix Conversion:

* Scan the input String using Scanner
* While there are more tokens
   o If the next token is of length greater than 1, it is a 
     multiple digit number and is added to the result
   o Else if the next token is 1 digit number, it is added 
     to the result
   o Else if the next token is a right parenthesis,
      * Pop elements off of the stack, adding them to the 
        result until the top element of the stack is a matching 
        left parenthesis
      * Pop the left parenthesis off of the stack
   o Else if the next token is an operator (+,-,*,/)
      * Then compare the token to the top of the stack to 
        determine precedence
      * While the operator on the stack has precedence
      * Pop the top element off of the stack and add it to the 
        result
      * Push the current operator on the stack
* While there are elements remaining on the stack
   o Pop the top element off of the stack and add it to the result
* The result is a postfix expression in reverse order so reverse 
  it and return it

*/

import jss2.*;
import java.util.Scanner; 

public class InfixToPostfix 
{

  /**********************************************************
     Constructor
  **********************************************************/
  public InfixToPostfix()
  {
     
  }

  /**********************************************************
     Returns a postfix expression of this infix string
      as a list.
  **********************************************************/
  public ArrayUnorderedList convert(String infix)
  {
    ArrayUnorderedList<String> tokenList = 
                        new ArrayUnorderedList<String>();
    ArrayStack<String> postStack = new ArrayStack<String>();

    int toPush=0,operand1=0, operand2=0;
    boolean precedence=true;
    char tempChar;
    String tempToken;
    String input;
    input = infix;
    Scanner s = new Scanner(input);

    for(int scan = 0; scan < input.length(); scan++)
    {
      while(s.hasNext())
      {
        tempToken = s.next();
        tempToken = tempToken.toString();
        
        if(tempToken.length()>1)
        {
          tokenList.addToFront(tempToken);
        }
        else if(tempToken.length() ==1)
        {
          tempChar = tempToken.charAt(0);
          
          if(tempChar >= '0' && tempChar <= '9')
            tokenList.addToFront(tempToken);

          else if(tempToken.equals("(") )
            postStack.push(tempToken);
           
          else if(tempToken.equals(")") )
          {
            while(!postStack.isEmpty() && 
                   postStack.peek() != "("  )
            {                
              tokenList.addToFront(postStack.pop()); 
            }
            if (!postStack.isEmpty())
              postStack.pop();
          }
          
          else if( tempToken.equals("*") || tempToken.equals("/") || 
                   tempToken.equals("+") || tempToken.equals("-") )
          {
            if(!postStack.isEmpty())
            {
              String top = postStack.peek()+""; 
              
              if((top.equals("+") || top.equals("-")) &&
                 (tempToken.equals("*") || tempToken.equals("/")))       
                precedence = false;
                else if (top.equals("("))
                  precedence = false;
                else
                  precedence = true;
            }
            while( !postStack.isEmpty() && 
                    postStack.peek() !="("&& precedence )
            { 
              String top = postStack.peek()+""; 
              
              if((tempToken.equals("+") || tempToken.equals("-")) &&
                 (top.equals("*") || top.equals("/")))       
                precedence = true;
                else if (top.equals("("))
                  precedence = true;
                else
                  precedence = false;
                
                tokenList.addToFront(postStack.pop());
            }
            
            postStack.push(tempToken);     
          }
          
          else   //error handling
          {
            System.out.println( tempToken + " is illegal");
            System.exit(1);
          }
        }
      }
    }
    
    while( !postStack.isEmpty() )
    {
      tokenList.addToFront(postStack.pop());
    }
    
    s.close();

    int counter = tokenList.size();
    ArrayUnorderedList<String> reverseorder = 
             new ArrayUnorderedList<String>();
    for (int i=0; i<counter; i++)
      reverseorder.addToRear(tokenList.removeLast());

    return reverseorder;
  }
}
