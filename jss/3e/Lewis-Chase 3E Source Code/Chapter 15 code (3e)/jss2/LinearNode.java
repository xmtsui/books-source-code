/**
 * LinearNode represents a node in a linked list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

package jss2;

public class LinearNode<T>
{
  private LinearNode<T> next;
  private T element;
 
  /**
   * Creates an empty node.
   */
  public LinearNode()
  {
    next = null;
    element = null;
  }
 
  /**
   * Creates a node storing the specified element.
   *
   * @param elem  the element to be stored within the new node
   */
  public LinearNode (T elem)
  {
    next = null;
    element = elem;
  }
 
  /**
   * Returns the node that follows this one.
   *
   * @return  the node that follows this node
   */
  public LinearNode<T> getNext()
  {
    return next;
  }
 
  /**
   * Sets the node that follows this one.
   *
   * @param node  the node to be set as the one to follow this node
   */
  public void setNext (LinearNode<T> node)
  {
    next = node;
  }
 
  /**
   * Returns the element stored in this node.
   *
   * @return  the element stored in this node
   */
  public T getElement()
  {
    return element;
  }
 
  /**
   * Sets the element stored in this node.
   *
   * @param elem  the element to be stored in this node
   */
  public void setElement (T elem)
  {
    element = elem;
  }
}
