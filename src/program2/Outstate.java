package program2;

public class Outstate extends Student {

   private boolean tristate;

   public Outstate(String fName, String lName, int credit, boolean tristate) {
      super(fName, lName, credit);
      this.tristate = tristate;
   }

   @Override
   public int tuitionDue() {

      int costPerCredit = 756;
      final int DISCOUNT = 200;

      int creditsToBill;
      if(this.credit > MAX_BILLABLE_CREDITS){
         creditsToBill = MAX_BILLABLE_CREDITS;
      } else {
         creditsToBill = this.credit;
      }

      int feeToPay;
      if(this.credit >= FULL_TIME_CREDIT_MIN){
         feeToPay = FULL_TIME_FEE;
         if (this.tristate){
            costPerCredit = costPerCredit - DISCOUNT;
         }
      } else {
         feeToPay = PART_TIME_FEE;
      }

      return (creditsToBill * costPerCredit) + feeToPay;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(super.toString());
      String tristateStr = "Lives in Tristate: " + tristate + "\n";
      String tuitionStr = "Tuition Due: " + tuitionDue();
      sb.append(tristateStr);
      sb.append(tuitionStr);
      return sb.toString();
   }
}
