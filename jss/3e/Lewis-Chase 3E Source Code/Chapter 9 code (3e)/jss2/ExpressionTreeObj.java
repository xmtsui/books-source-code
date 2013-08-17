/**
 * ExpressionTreeObj represents an element in an expression tree.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;

public class ExpressionTreeObj 
{
   private int termType;
   private char operator;
   private int value;

   /**
    * Creates a new expression tree object with the specified data.
    *
    * @param type  the integer type of the expression
    * @param op    the operand for the expression
    * @param val   the value for the expression
    */
   public ExpressionTreeObj (int type,char op, int val) 
   {
      termType = type;
      operator = op;
      value = val;
   }

   /**
    * Returns true if this object is an operator and false otherwise.
    *
    * @return  true if this object is an operator
    */
   public boolean isOperator() 
   {
      return (termType == 1);
   }
   
   /**
    *Returns the operator of this expression tree object.
    *
    * @return  the character representation of the operator
    */
   public char getOperator() 
   {
      return operator;
   }

   /**
    * Returns the value of this expression tree object.
    *
    * @return  the value of this expression tree object
    */
   public int getValue() 
   {
      return value;
   }
}

