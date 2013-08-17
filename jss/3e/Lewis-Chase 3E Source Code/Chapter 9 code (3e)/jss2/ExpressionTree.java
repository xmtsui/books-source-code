/**
 * ExpressionTree represents an expression tree of operators and operands.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;

public class ExpressionTree extends LinkedBinaryTree<ExpressionTreeObj>
{
   /**
    * Creates an empty expression tree.
    */
   public ExpressionTree() 
   {
      super();
   }

   /**
    * Constructs a expression tree from the two specified expression 
    * trees.
    *
    * @param element       the expression tree for the center
    * @param leftSubtree   the expression tree for the left subtree
    * @param rightSubtree  the expression tree for the right subtree
    */
   public ExpressionTree (ExpressionTreeObj element,
               ExpressionTree leftSubtree, ExpressionTree rightSubtree) 
   {
      root = new BinaryTreeNode<ExpressionTreeObj> (element);
      count = 1;
      
      if (leftSubtree != null)
      {
         count = count + leftSubtree.size();
         root.left = leftSubtree.root;
      }
      else
         root.left = null;
      
      if (rightSubtree !=null)
      {
         count = count + rightSubtree.size();
         root.right = rightSubtree.root;
      }
      else
         root.right = null;
   }
   
   /**
    * Evaluates the expression tree by calling the recursive 
    * evaluateNode method.
    *
    * @return  the integer evaluation of the tree
    */
   public int evaluateTree() 
   {
      return evaluateNode(root);
   }

   /**
    * Recursively evaluates each node of the tree.
    *
    * @param root  the root of the tree to be evaluated
    * @return      the integer evaluation of the tree
    */
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

   /**
    * Evaluates a term consisting of an operator and two operands.
    *
    * @param operator  the operator for the expression
    * @param operand1  the first operand for the expression
    * @param operand2  the second operand for the expression
    */
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

