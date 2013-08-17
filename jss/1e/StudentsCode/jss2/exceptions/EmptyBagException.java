
//********************************************************************
//  EmptyBagException.java     Authors: Lewis/Chase
//
//  Represents the situation in which a bag is empty.
//********************************************************************

package jss2.exceptions;

public class EmptyBagException extends RuntimeException
{
   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyBagException()
   {
      super ("The bag is empty.");
   }

   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyBagException (String message)
   {
      super (message);
   }
}

