
//********************************************************************
//  DoubleNode.java       Authors: Lewis/Chase
//                          Mods : Davis
//
//  Represents a node in a doubly linked list.
//********************************************************************
package jss2;

public class DoubleNode
{
    private DoubleNode next;
    private Object element;
    private DoubleNode previous;

    //-----------------------------------------------------------------
    //  Creates an empty node.
    //-----------------------------------------------------------------
    public DoubleNode()
    {
       next = null;
       element = null;
       previous = null;
    }

    //-----------------------------------------------------------------
    //  Creates a node storing the specified element.
    //-----------------------------------------------------------------
    public DoubleNode (Object elem)
    {
       next = null;
       element = elem;
       previous = null;
    }

    //-----------------------------------------------------------------
    //  Returns the node that follows this one.
    //-----------------------------------------------------------------
    public DoubleNode getNext()
    {
       return next;
    }

    //-----------------------------------------------------------------
    //  Returns the node that precedes this one.
    //-----------------------------------------------------------------
    public DoubleNode getPrevious()
    {
       return previous;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setNext (DoubleNode dnode)
    {
       next = dnode;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setPrevious (DoubleNode dnode)
    {
       previous = dnode;
    }


    //-----------------------------------------------------------------
    //  Returns the element stored in this node.
    //-----------------------------------------------------------------
    public Object getElement()
    {
       return element;
    }

    //-----------------------------------------------------------------
    //  Sets the element stored in this node.
    //-----------------------------------------------------------------
    public void setElement (Object elem)
    {
       element = elem;
    }


}



