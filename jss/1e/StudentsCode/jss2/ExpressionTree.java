//*******************************************************************
//
// ExpressionTree.java		Authors: Lewis/Chase
//
// Represents an expression tree of operators and operands
//*******************************************************************

package jss2;

public class ExpressionTree extends BinaryTree
{

   //================================================================
   //  Creates an empty expression tree.
   //================================================================
   public ExpressionTree() 
   {
      super();
   }  // constructor ExpressionTree


   //================================================================
   //  Constructs a expression tree from the two specified expression trees.
   //================================================================
   public ExpressionTree (Object element, ExpressionTree leftSubtree,
                                      ExpressionTree rightSubtree) 
   {

	 super(element, leftSubtree, rightSubtree);
 
   }  // constructor ExpressionTree
   

   //================================================================
   //  evaluates the expression tree by calling the recursive evaluate_node 
   //  method.
   //================================================================
   public int evaluate_tree() 
   {
      return evaluate_node(root);
   }  // method evaluate_tree

   //================================================================
   //  recursively evaluates each node of the tree
   //================================================================
   public int evaluate_node(BinaryTreeNode root) 
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
				operand1 = evaluate_node(root.left);
				operand2 = evaluate_node(root.right);
				result = compute_term(temp.getOperator(), operand1, operand2);
			}//if
			else
				result = temp.getValue();
		}//else
		return result;

   }  // method evaluate_node

	//======================================================================
	//
	//method compute_term evaluates a term consisting of an operator and 
	//two operands
	//
	//======================================================================

	private static int compute_term(char operator, int operand1, int operand2)
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



}  // class ExpressionTree

