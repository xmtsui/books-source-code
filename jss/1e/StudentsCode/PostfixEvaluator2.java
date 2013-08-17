//*************************************************************************
//  PostfixEvaluator2.java				Authors:  Lewis/Chase
//
//  This modification of our stack example uses a pair of stacks to create an 
//  expression tree from a VALID postfix expression and then uses a recursive 
//  method from the ExpressionTree class to evaluate the tree.
//*************************************************************************
import jss2.*;
import jss2.exceptions.*;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.io.*;

public class PostfixEvaluator2   
{
	
	//======================================================================
	//
	// method get_operand retrieves the next operand off of the tree_stack
	// and returns it
	//
	//======================================================================
	private ExpressionTree get_operand(LinkedStack tree_stack)
	{
		ExpressionTree temp;
		temp = (ExpressionTree)tree_stack.pop();
		return temp;
	}//method get_operand;

	//======================================================================
	//
	// method get_next_token retrieves the next token, either an operator or	// operand from the user and returns it
	//
	//======================================================================

	private String get_next_token() 
	{
		String temptoken = "0", instring;
 		StringTokenizer tokenizer;
		try
		{
       		BufferedReader in = new 
                   BufferedReader( new InputStreamReader(System.in));
       		instring = in.readLine();
         		tokenizer = new StringTokenizer(instring);
         		temptoken = (tokenizer.nextToken());
		}//try
		catch (Exception IOException)
		{
			System.out.println("An input/output exception has occurred");
		}//catch
		return temptoken;
	}//method get_next_token



	//======================================================================
	//
	//method solve prompts the user for a valid post-fix expression then
	//converts it to an expression tree using a two stack method and then 
	//calls a recursive method to evaluate the expression.
	//
	//======================================================================

	public void solve () 
	{
		ExpressionTree operand1, operand2;
		char operator;
		String temptoken;
		LinkedStack tree_stack = new LinkedStack();

		System.out.println("Enter a valid post-fix expression one token at a time pressing the enter key after each token");

          System.out.println("Enter an integer, an operator (+,-,*,/) or ! to quit ");

		temptoken = get_next_token();
		operator = temptoken.charAt(0);

		while (!(operator == '!')) 
		{
			if ((operator == '+') || (operator == '-') || (operator == '*') || 
					(operator == '/'))
			{
				operand1 = get_operand(tree_stack);
				operand2 = get_operand(tree_stack);
                  	tree_stack.push(new ExpressionTree (new 					
					ExpressionTreeObj(1,operator,0), operand2, operand1));
			}//if
			
			else
			{
				tree_stack.push(new ExpressionTree (new ExpressionTreeObj(2,' ',Integer.parseInt(temptoken)), null, null));

			}//else
                	temptoken = get_next_token();
			operator = temptoken.charAt(0);
		}//while
		System.out.print("The result is ");
		System.out.println(((ExpressionTree)tree_stack.peek()).evaluate_tree());
		
	}//method main
}//class PostfixEvaluator2

