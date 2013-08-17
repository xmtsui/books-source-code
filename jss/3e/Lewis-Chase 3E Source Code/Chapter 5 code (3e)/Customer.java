/**
 * Customer represents a waiting customer.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

public class Customer
{
   private int arrivalTime, departureTime;

   /**
    * Creates a new customer with the specified arrival time.
    *
    * @param arrives  the integer representation of the arrival time
    */
   public Customer (int arrives)
   {
      arrivalTime = arrives;
      departureTime = 0;
   }

   /**
    * Returns the arrival time of this customer.
    *
    * @return  the integer representation of the arrival time
    */
   public int getArrivalTime()
   {
      return arrivalTime;
   }

   /**
    * Sets the departure time for this customer.
    *
    * @param departs  the integer representation of the departure time
    **/
   public void setDepartureTime (int departs)
   {
      departureTime = departs;
   }
   
   /**
    * Returns the departure time of this customer.
    *
    * @return  the integer representation of the departure time
    */
   public int getDepartureTime()
   {
      return departureTime;
   }

   /**
    * Computes and returns the total time spent by this customer.
    *
    * @return  the integer representation of the total customer time
    */
   public int totalTime()
   {
      return departureTime - arrivalTime;
   }
}
