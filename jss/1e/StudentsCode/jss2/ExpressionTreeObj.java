//*******************************************************************
//
// ExpressionTreeObj.java			Authors: Lewis/Chase
//
// Represents an element in an expression tree
//*******************************************************************
package jss2;

public class ExpressionTreeObj {

   private int termtype;
   private char operator;
   private int value;

   //================================================================
   //  Creates a new expression tree object with the specified data.
   //================================================================
   public ExpressionTreeObj (int type,char op, int val) 
   {
      termtype = type;
      operator = op;
      value = val;
   }  // constructor BinaryTreeNode

   //================================================================
   //  Boolean method isOperator() returns true if this object is
   //  an operator and false otherwise
   //================================================================
   public boolean isOperator() 
   {

 	return (termtype == 1);

   }  // method isOperator
   
   //================================================================
   // method getOperator() returns the operator
   //================================================================
   public char getOperator() 
   {

 	return operator;

   }  // method getOperator

   //================================================================
   // method getValue() returns the value
   //  
   //================================================================
   public int getValue() 
   {

 	return value;

   }  // method getValue
   

}  // class ExpressionTreeObj

