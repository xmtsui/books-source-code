//********************************************************************
//  ExpressionTreeObj.java			Authors: Lewis/Chase
//
//  Represents an element in an expression tree.
//********************************************************************
package jss2;

public class ExpressionTreeObj 
{
   private int termType;
   private char operator;
   private int value;

   /******************************************************************
     Creates a new expression tree object with the specified data.
   ******************************************************************/
   public ExpressionTreeObj (int type,char op, int val) 
   {
      termType = type;
      operator = op;
      value = val;
   }

   /******************************************************************
     Returns true if this object is an operator and false otherwise.
   ******************************************************************/
   public boolean isOperator() 
   {
      return (termType == 1);
   }
   
   /******************************************************************
    Returns the operator of this expression tree object.
   ******************************************************************/
   public char getOperator() 
   {
      return operator;
   }

   /******************************************************************
     Returns the value of this expression tree object.
   ******************************************************************/
   public int getValue() 
   {
      return value;
   }
}