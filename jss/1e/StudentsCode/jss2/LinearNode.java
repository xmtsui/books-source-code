    //************************************************************
    //  LinearNode.java       Authors: Lewis/Chase
    //
    //  Represents a node in a linked list.
    //************************************************************
    
    package jss2;   


    public class LinearNode
    {
       private LinearNode next;
       private Object element;
    
       //---------------------------------------------------------
       //  Creates an empty node.
       //---------------------------------------------------------
       public LinearNode()
       {
          next = null;
          element = null;
       }
    
       //---------------------------------------------------------
       //  Creates a node storing the specified element.
       //---------------------------------------------------------
       public LinearNode (Object elem)
       {
          next = null;
          element = elem;
       }
    
       //---------------------------------------------------------
       //  Returns the node that follows this one.
       //---------------------------------------------------------
       public LinearNode getNext()
       {
          return next;
       }
    
       //---------------------------------------------------------
       //  Sets the node that follows this one.
       //---------------------------------------------------------
       public void setNext (LinearNode node)
       {
          next = node;
       }
    
       //---------------------------------------------------------
       //  Returns the element stored in this node.
       //---------------------------------------------------------
       public Object getElement()
       {
          return element;
       }
    
       //---------------------------------------------------------
       //  Sets the element stored in this node.
       //---------------------------------------------------------
       public void setElement (Object elem)
       {
          element = elem;
       }
    }

