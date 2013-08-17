/** * DoubleNode represents a node in a doubly linked list.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @author Davis
 * @version 1.0, 08/13/08
 */
package jss2;public class DoubleNode<E>{    private DoubleNode<E> next;    private E element;    private DoubleNode<E> previous;    /**     * Creates an empty node.     */    public DoubleNode()    {       next = null;       element = null;       previous = null;    }    /**     * Creates a node storing the specified element.     * 
     * @param elem  the element to be stored into the new node
     */    public DoubleNode (E elem)    {       next = null;       element = elem;       previous = null;    }    /**     * Returns the node that follows this one.     *
     * @return  the node that follows the current one
     */    public DoubleNode<E> getNext()    {       return next;    }    /**     * Returns the node that precedes this one.     *
     * @return  the node that precedes the current one
     */    public DoubleNode<E> getPrevious()    {       return previous;    }    /**     * Sets the node that follows this one.     *
     * @param dnode  the node to be set as the one to follow the current one
     */    public void setNext (DoubleNode<E> dnode)    {       next = dnode;    }    /**     * Sets the node that precedes this one.     *
     * @param dnode  the node to be set as the one to precede the current one
     */    public void setPrevious (DoubleNode<E> dnode)    {       previous = dnode;    }    /**     * Returns the element stored in this node.     *
     * @return  the element stored in this node
     */    public E getElement()    {       return element;    }    /**     * Sets the element stored in this node.     *
     * @param elem  the element to be stored in this node
     */    public void setElement (E elem)    {       element = elem;    }}
