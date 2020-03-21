package program2;

public class Instate extends Student
{

   private int funding;
   public Instate(String fName, String lName, int credit, int funding)
   {
      super(fName, lName, credit);
      this.funding = funding;
   }
   public int tuitionDue(){

      final int COST_PER_CREDIT = 433;
           
      int creditsToBill;
      if(this.credit > MAX_BILLABLE_CREDITS){
         creditsToBill = MAX_BILLABLE_CREDITS;
      } else {
         creditsToBill = this.credit;
      }
      
      int feeToPay;
      if(this.credit >= FULL_TIME_CREDIT_MIN){
         feeToPay = FULL_TIME_FEE;
      } else {
         feeToPay = PART_TIME_FEE;
      }

      if (this.credit < FULL_TIME_CREDIT_MIN) {
         this.funding = 0;
      }

      return (creditsToBill * COST_PER_CREDIT) + feeToPay - funding;
   }
}


