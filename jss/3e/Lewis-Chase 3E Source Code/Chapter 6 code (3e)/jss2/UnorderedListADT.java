/** * UnorderedListADT defines the interface to an unordered list collection. 
 * Elements are stored in any order the user desires.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */package jss2;public interface UnorderedListADT<T> extends ListADT<T>{   /**  
    * Adds the specified element to the front of this list. 
    *
    * @param element  the element to be added to the front of this list    
    */   public void addToFront (T element);   /**  
    * Adds the specified element to the rear of this list. 
    *
    * @param element  the element to be added to the rear of this list    
    */   public void addToRear (T element);   /**  
    * Adds the specified element after the specified target. 
    *
    * @param element  the element to be added after the target
    * @param target   the target is the item that the element will be added
    *                 after    
    */   public void addAfter (T element, T target);}
