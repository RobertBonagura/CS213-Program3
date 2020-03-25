package program2;

/**
Subclass of Student, factors funding into tuiton cost.
@author Ezra Haleva
*/
public class Instate extends Student
{

   private int funding;
   /**
   Constructor.
   Calls Student constructor then initializes funding variable.
   @param fName first name of student
   @param lName last name of student
   @param credit number of credits student is taking
   @param funding dollar amount of funding received from university
   */
   public Instate(String fName, String lName, int credit, int funding)
   {
      super(fName, lName, credit);
      this.funding = funding;
   }
   /**
   Checks whether the student as described is valid.
   Ensures student is signed up for an appropriate amount of credits.
   */
   public boolean isValid(){
      if(super.credit > 0 && this.funding > 0){
         return true;
      } else {
         return false;
      }
   }
   /**
   Calculates the tuition owed by the student.
   Takes into account amount of credits and funding. 
   */
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
   /**
   Returns a string representation of student and info.
   Data members delimited with space
   */
   @Override
   public String toString(){
      return (super.toString() + "\nFunding: " + funding + "\nTuition Due: " + this.tuitionDue() + "\n");
   }

   public static void main(String[] args){
      int testCounter = 1;
      Instate instate0 = new Instate("Ezra", "Haleva", 0, 0);//for access of constants
      //Constructor Tests
      Instate instate1 = new Instate("Ezra", "Haleva", 15, 100);
      if(instate1.credit == 15){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
      if(instate1.funding == 100){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
      
      //isValid() tests
      Instate validInstate = new Instate("Ezra", "Haleva", 15, 100);
      Instate invalidInstate = new Instate("Ezra", "Haleva", 0, 100);
      if(validInstate.isValid()){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
      if(!invalidInstate.isValid()) {
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
   
      //tuitionDue() tests
      final int COST_PER_CREDIT = 433;
      Instate instate2 = new Instate("Ezra", "Haleva", 15, 0);
      if(instate2.tuitionDue()==( COST_PER_CREDIT * 15 + instate0.FULL_TIME_FEE) ){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;

      Instate instate3 = new Instate("Ezra", "Haleva", 18, 0);
      if(instate3.tuitionDue()==( COST_PER_CREDIT * 15 + instate0.FULL_TIME_FEE) ){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
      
      Instate instate4 = new Instate("Ezra", "Haleva", 11, 0);
      if(instate4.tuitionDue()==( COST_PER_CREDIT * 11 + instate0.PART_TIME_FEE) ){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;

      Instate instate5 = new Instate("Ezra", "Haleva", 15, 1000);
      if(instate5.tuitionDue()==( COST_PER_CREDIT * 15 + instate0.FULL_TIME_FEE - 1000) ){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;

      Instate instate6 = new Instate("Ezra", "Haleva", 11, 1000);
      if(instate6.tuitionDue()==( COST_PER_CREDIT * 11 + instate0.PART_TIME_FEE) ){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
      
      //ToString() tests
      Instate instate7 = new Instate("Ezra", "Haleva", 11, 100);
      if(instate7.toString().equals("Name: Ezra Haleva\nCredits: 11\nFunding: 100\nTuition Due: " + instate7.tuitionDue())){
         System.out.println("Passed test #" + testCounter);
      } else {
         System.out.println("failed test #" + testCounter);
      }
      testCounter++;
   }

}



