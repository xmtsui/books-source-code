//********************************************************************
//  AncestorTree.java       Authors: Lewis/Chase
//
//  A tree representation of an ancestor tree using an 
//  ArrayBinaryTree.
//********************************************************************
import jss2.*;
import jss2.exceptions.*;
import java.util.Iterator;

public class AncestorTree extends ArrayBinaryTree 
                          implements java.io.Serializable
{
  private final int capacity = 50;  
  protected int height;
  protected int maxIndex;

  /******************************************************************
    Creates an empty binary tree.
  ******************************************************************/
  public AncestorTree() 
  {
    super();
    height = 0;
    maxIndex = -1;
  }

  /******************************************************************
    Creates a tree with the specified element as its root.
  ******************************************************************/
  public AncestorTree (Object element) 
  {
    super(element);
    height = 1;
    maxIndex = 0;
  }

  /******************************************************************
    Adds the specified object to this tree. Note that the
    index of the left child of the current index can be found by
    doubling the current index and adding 1.  Finding the index
    of the right child can be calculated by doubling the current
    index and adding 2.
  ******************************************************************/
  public void addElement (Person element,Person offspring) 
  {
    AncestorNode temp = new AncestorNode (element);

    if (tree.length < maxIndex*2+3)
      expandCapacity();

    if (isEmpty()) 
    {
      tree[0] = temp;
      maxIndex = 0;
    }
    else 
    {
      int currentIndex = findIndex(offspring);

      /** make left child */
      if (tree[currentIndex*2+1] == null) 
      {
        tree[currentIndex*2+1] = temp;
        ((AncestorNode)(tree[currentIndex])).setLeft(temp);
        if (currentIndex*2+1 > maxIndex)
          maxIndex = currentIndex*2+1;
      }
           
      /** make right child */
      else if(tree[currentIndex*2+2] == null)
      {
        tree[currentIndex*2+2] = temp;
        ((AncestorNode)(tree[currentIndex])).setRight(temp);
        if (currentIndex*2+2 > maxIndex)
          maxIndex = currentIndex*2+2;
      }             
      else
        System.out.println("Parents Full - Try Again");
    }  

    height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
    count++;
  }

  /******************************************************************
    Removes the first element that matches the specified target
    element from this tree and returns a reference to it. 
    Throws a ElementNotFoundException if the specified target
    element is not found in the tree.
  ******************************************************************/
  public Person removeElement (Person targetElement)
                               throws ElementNotFoundException 
  {
    Person result = null;
    boolean found = false;

    if (isEmpty())
      return result;

    for (int i = 0; (i <= maxIndex) && !found; i++)
    {
      if ((tree[i] != null) && targetElement.equals(((AncestorNode)tree[i]).element))
      {
        found = true;
        result = (Person)((AncestorNode)tree[i]).element;
        count--;
        tree[i]=null;
            
        /** recursive calls to delete the entire line of ancesters */
        if(tree[i*2+1] != null)
          removeLeftLine(i*2+1);
        if(tree[i*2+2] != null)
          removeRightLine(i*2+2);
      }
    } 

    if (!found)
      throw new ElementNotFoundException("element not found in the tree");
      
    int temp = maxIndex;
    maxIndex = -1;
    
    for (int i = 0; i <= temp; i++)
      if (tree[i] != null)
        maxIndex = i;
      
    height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;

    return result;
  } 

  /******************************************************************
    Deletes the left line for the removeElement method.
  ******************************************************************/
  protected void removeLeftLine(int i)
  {
    boolean done = false;

    if(tree[i] != null)
    {
      count--;
      tree[i] = null;
    }
    else
      done=true;

    if(!done)
    {
      removeRightLine(i*2+2);
      removeLeftLine(i*2+1);
    }
  }

  /******************************************************************
    Deletes the right line for the removeElement method.
  ******************************************************************/
  protected void removeRightLine(int i)
  {
    boolean done = false;

    if(tree[i] != null)
    {
      count--;
      tree[i] = null;
    }

    else
      done=true;

    if(!done)
    {
      removeLeftLine(i*2+1);
      removeRightLine(i*2+2);
    }
  }

  /******************************************************************
    Returns a copy of the array containing the values in this tree.
  ******************************************************************/
  public Object[] getArray()
  {
    Object[] temp;
    if (size() == 0)
    {
      temp = new Object[0];
      return temp;
    }

    temp = new Object[tree.length];

    for (int i = 0; i < tree.length; i++)
    {
      if (tree[i] != null)
        temp[i] = ((AncestorNode)tree[i]).element;
      else
        temp[i] = null;
    }
      
    return temp;
  }
   
  /******************************************************************
    Returns a copy of the array containing the values in this tree
    represented as a string. (for writing to a file)
  ******************************************************************/
  public String levelOrderToString()
  {
    String temp = "";

    if (size() == 0)
    {
      temp = null;
      return temp;
    }

    for (int i = 0; i < tree.length; i++)
    {
      if (tree[i] != null)
      {
        temp += ((AncestorNode)tree[i]).element.fName + " ";
        temp += ((AncestorNode)tree[i]).element.lName + " ";
        temp += ((AncestorNode)tree[i]).element.dob + " ";
        temp += ((AncestorNode)tree[i]).element.dod + " ";
        temp += ((AncestorNode)tree[i]).element.occupation + " ";
        temp += ((AncestorNode)tree[i]).element.address + "\n";
      }
    }

    return temp;
  }

  /******************************************************************
    Returns the height of this tree.
  ******************************************************************/
  public int getHeight()
  {
    return height;
  }

  /******************************************************************
    Returns the max index of this tree.
  ******************************************************************/
  public int getMaxIndex()
  {
    return maxIndex;
  }

  /******************************************************************
    Deletes all nodes from this tree.
  ******************************************************************/
  public void removeAllElements() 
  {
    count = 0;
      
    for (int ct=0; ct<tree.length; ct++)      
      tree[ct] = null;
    
    height = 0;
    maxIndex = -1;
  }

  /******************************************************************
    Returns a reference to the specified target element if it is
    found in the tree based on First and Last name only.
    Throws a NoSuchElementException if the specified target  
    element is not found in the tree.
  ******************************************************************/
  public Object find (Person targetElement) throws ElementNotFoundException 
  {
    Object result = null;
    boolean found = false;

    if (isEmpty())
      throw new EmptyCollectionException ("Ancestor tree");
    else 
    {
      int currI = 0;

      while ((currI <= maxIndex) && !found)
      {
        if ((tree[currI] != null) &&
           (targetElement.fName).equals((((AncestorNode)(tree[currI])).element).fName) &&
           (targetElement.lName).equals((((AncestorNode)(tree[currI])).element).lName))
        {
          found = true;
          result = tree[currI];
        }
            
        currI++;
      }
    }
    
    if (!found)
      throw new ElementNotFoundException("Ancestor tree");

    return result;
  }

  /******************************************************************
    Returns a index of the specified target element if it is
    found in this tree.  Throws a NoSuchElementException if
    the specified target element is not found in the tree.
  ******************************************************************/
  private int findIndex(Person targetElement) throws ElementNotFoundException
  {
    int currI = 0;
    int result =0;
    boolean found = false;
  
    while ((currI <= maxIndex) && (!found))
    {
      if ((tree[currI] != null) &&
         targetElement==(((AncestorNode)tree[currI]).element))
      {
        found = true;
        result = currI;
      }
 
      currI++;
    }
           
    if (!found)
      throw new ElementNotFoundException("Ancestor tree");

    return result;
  }

  /******************************************************************
    Returns true if this tree contains an element that matches the
    specified target element and false otherwise.
  ******************************************************************/
  public boolean contains (Object targetElement) 
  {
    boolean found = false;

    for (int ct=0; ct<maxIndex && !found; ct++)
      if ((targetElement !=null) && (targetElement.equals(tree[ct])))
	    found = true;

    return found;
  }

  /******************************************************************
    Performs a levelorder traversal on this ancestor tree, using a
    temp list.
  ******************************************************************/
  public Iterator iteratorLevelOrder() 
  {
    ArrayUnorderedList templist = new ArrayUnorderedList();

    for (int ct=0; ct<=maxIndex; ct++)
      templist.addToRear(tree[ct]);

    return templist.iterator();
  }
}
