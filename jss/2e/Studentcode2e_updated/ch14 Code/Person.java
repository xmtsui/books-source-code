//********************************************************************
//  Person.java       Authors: Lewis/Chase
//
//  Represents one person in the ancestor tree.
//********************************************************************

public class Person implements Comparable, java.io.Serializable
{
   String fName, lName, dob, dod, occupation, address;
   
   /******************************************************************
     Sets up this person with the specified information.
   ******************************************************************/
   public Person(String firstName, String lastName, String bDay,
                 String dDay, String occ, String add)
   {
      fName = firstName;
      lName = lastName;
      dob = bDay;
      dod = dDay;
      occupation = occ;
      address = add;
   }
   
   /******************************************************************
     Compares the date of birth of this person to another. Returns
     -1, 0, or 1 for less than, equal to, or greater than.
   ******************************************************************/
   public int compareTo (Object other)
   {
      int result;
      
      result = dob.compareTo(((Person)other).dob);

      return result;
   }
   
   /******************************************************************
     Returns a string representation of this person.
   ******************************************************************/
   public String toString()
   {
      return fName +" "+ lName +"\n DOB: "+dob +
             "\n DOD: " + dod +"\n Occupation: " + occupation
             +"\n Address: " + address +"\n";
   }
}
