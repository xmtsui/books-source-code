/**
 *  @author Lewis and Chase
 *
 *  Represents a linked implementation of a stack.
 */
package jss2;
import jss2.exceptions.*;
import java.util.Iterator;

public class LinkedStack<T> implements StackADT<T>
{
  /** indicates number of elements stored */
  private int count;  
  /** pointer to top of stack */
  private LinearNode<T> top; 

  /**
   * Creates an empty stack.
   */
  public LinkedStack()
  {
    count = 0;
    top = null;
  }

  /**
   * Adds the specified element to the top of this stack.
   * @param element element to be pushed on stack
   */
  public void push (T element)
  {
    LinearNode<T> temp = new LinearNode<T> (element);

    temp.setNext(top);
    top = temp;
    count++;
  }

  /**
   * Removes the element at the top of this stack and returns a
   * reference to it. Throws an EmptyCollectionException if the stack
   * is empty.
   * @return T element from top of stack
   * @throws EmptyCollectionException on pop from empty stack
   */
  public T pop() throws EmptyCollectionException
  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");

    T result = top.getElement();
    top = top.getNext();
    count--;
 
    return result;
  }
   
  /**
   * Returns a reference to the element at the top of this stack.
   * The element is not removed from the stack.  Throws an
   * EmptyCollectionException if the stack is empty.
   * @return T element on top of stack
   * @throws EmptyCollectionException on peek at empty stack  
   */
  public T peek() throws EmptyCollectionException
  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack"); 

    return top.getElement();
  }

  /**
   * Returns true if this stack is empty and false otherwise. 
   * @return boolean true if stack is empty
   */
  public boolean isEmpty()
  {
    //left as a programming project
  }
 
  /**
   * Returns the number of elements in this stack.
   * @return int number of elements in this stack
   */
  public int size()
  {
    //left as a programming project
  }

  /**
   * Returns a string representation of this stack. 
   * @return String representation of this stack
   */
  public String toString()
  {
    //left as a programming project  }
  }
