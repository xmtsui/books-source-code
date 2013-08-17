//********************************************************************
//  ExpressionTree.java                Authors: Lewis/Chase
//
//  Represents an expression tree of operators and operands.
//********************************************************************
package jss2;

public class ExpressionTree extends LinkedBinaryTree<ExpressionTreeObj>
{
   /******************************************************************
     Creates an empty expression tree.
   ******************************************************************/
   public ExpressionTree() 
   {
      super();
   }

   /******************************************************************
     Constructs a expression tree from the two specified expression 
     trees.
   ******************************************************************/
   public ExpressionTree (ExpressionTreeObj element,
               ExpressionTree leftSubtree, ExpressionTree rightSubtree) 
   {
      super(element, leftSubtree, rightSubtree);
   }
   
   /******************************************************************
     Evaluates the expression tree by calling the recursive 
     evaluateNode method.
   ******************************************************************/
   public int evaluateTree() 
   {
      return evaluateNode(root);
   }

   /******************************************************************
     Recursively evaluates each node of the tree.
   ******************************************************************/
   public int evaluateNode(BinaryTreeNode root) 
   {
      int result, operand1, operand2;
      ExpressionTreeObj temp;
      
      if (root==null)
         result = 0;
      else
      {
         temp = (ExpressionTreeObj)root.element;
         
         if (temp.isOperator())
         {
            operand1 = evaluateNode(root.left);
            operand2 = evaluateNode(root.right);
            result = computeTerm(temp.getOperator(), operand1, operand2);
         }
         else
            result = temp.getValue();
      }
      
      return result;
   }

   /******************************************************************
     Evaluates a term consisting of an operator and two operands.
   ******************************************************************/
   private static int computeTerm(char operator, int operand1, int operand2)
   {
      int result=0;
      
      if (operator == '+')
         result = operand1 + operand2;
      
      else if (operator == '-')
         result = operand1 - operand2;
      else if (operator == '*')
         result = operand1 * operand2;
      else 
         result = operand1 / operand2;

      return result;
   }
}