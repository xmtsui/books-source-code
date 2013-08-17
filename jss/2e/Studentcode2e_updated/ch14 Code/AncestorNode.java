//********************************************************************
//  AncestorNode.java       Authors: Lewis/Chase
//
//  Represents a node in a binary tree with a left and right child.
//********************************************************************
import jss2.*;
import jss2.exceptions.*;

public class AncestorNode implements java.io.Serializable
{
   DoubleOrderedList siblings;
   Person element;
   AncestorNode left, right;
   
   /******************************************************************
     Sets up this node with the specified person.
   ******************************************************************/
   public AncestorNode(Person obj)
   {
      siblings = new DoubleOrderedList();
      element = obj;
      left = null;
      right = null;
   }
   
   /******************************************************************
     Adds the specified sibling to this node.
   ******************************************************************/
   public void addSibling(Person sib)
   {
      siblings.add(sib);
   }
   
   /******************************************************************
     Returns the number of children for this node.
   ******************************************************************/
   public int numChildren()
   {
      int children = 0;
      
      if (left != null)
         children = 1 + left.numChildren();
      
      if (right != null)
         children = children + 1 + right.numChildren();
      
      return children;
   }
   
   /******************************************************************
     Returns the node that is the left child of this one.
   ******************************************************************/
   public AncestorNode getLeft()
   {
      return left;
   }

   /******************************************************************
     Returns the node that is the right child of this one.
   ******************************************************************/ 
   public AncestorNode getRight()
   {
      return right;
   }
   
   /******************************************************************
     Sets the left child of this node to the specified node.
   ******************************************************************/ 
   public void setLeft(AncestorNode node)
   {
      left = node;
   }
   
   /******************************************************************
     Sets the right child of this node to the specified node.
   ******************************************************************/
   public void setRight(AncestorNode node)
   {
      right = node;
   }
   
   /******************************************************************
     Returns a string representation of this node.
   ******************************************************************/
   public String toString()
   {
      return element.toString() + "Siblings: \n"+siblings.toString()+"\n";
   }
}
