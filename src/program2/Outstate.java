package program2;

/**
 * Class used to represent an Out-Of-State student.
 * @author Robert Bonagura
 */
public class Outstate extends Student {

   private boolean tristate;

   /**
    * Default constructor.
    * @param fName First Name
    * @param lName Last Name
    * @param credit Credits taken
    * @param tristate Inidcates whether student lives in tri-state area
    */
   public Outstate(String fName, String lName, int credit, boolean tristate) {
      super(fName, lName, credit);
      this.tristate = tristate;
   }

   /**
    * Checks that a student is taking at least 1 credit.
    * @return true or false
    */
   public boolean isValid(){
      if(super.credit >= 1) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Calculates Tutition due by Student.
    * @return Tuition due
    */
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

   /**
    * Converts all relevant information about student into a string.
    * @return String representation of Student info
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(super.toString());
      String tristateStr = "\nLives in Tristate: " + tristate + "\n";
      String tuitionStr = "Tuition Due: " + tuitionDue() + "\n";
      sb.append(tristateStr);
      sb.append(tuitionStr);
      return sb.toString();
   }

   /**
    * Test bed main function for all methods of this class.
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Test Case 1:");
      System.out.println("Checks default constructor and TuitionDue() " +
              "based on students full-time/part-time status:");

      int FULL_TIME_CREDIT_MIN = 12;
      StudentList list = new StudentList();
      Outstate outstate1 = new Outstate("Robert", "Bonagura",
              FULL_TIME_CREDIT_MIN, false);
      Outstate outstate2 = new Outstate("Bob", "Bonagura",
              FULL_TIME_CREDIT_MIN -1, false);

      list.add(outstate1);
      list.add(outstate2);

      int size = list.getSize();
      for (int i = 0; i < size; i++){
         Outstate student = (Outstate) list.remove();
         System.out.printf("Student %s:\n%s\n", i, student.toString());
      }
      System.out.println();

      System.out.println("Test Case 2:");
      System.out.println("Checks TuitionDue() on full-time students for " +
              "both tristate values:");

      outstate1 = new Outstate("Robert", "Bonagura",
              FULL_TIME_CREDIT_MIN, true);
      outstate2 = new Outstate("Greg", "Bonagura",
              FULL_TIME_CREDIT_MIN, false);

      list.add(outstate1);
      list.add(outstate2);

      size = list.getSize();
      for (int i = 0; i < size; i++){
         Outstate student = (Outstate) list.remove();
         System.out.printf("Student %s:\n%s\n", i, student.toString());
      }
      System.out.println();

      System.out.println("Test Case 3:");
      System.out.println("Tests if student is taking valid number of " +
              "credits:");
      outstate1 = new Outstate("Bobby", "Bonagura",
              FULL_TIME_CREDIT_MIN - FULL_TIME_CREDIT_MIN, false);
      outstate2 = new Outstate("Greg", "Bonagura",
              FULL_TIME_CREDIT_MIN, false);

      System.out.printf("Student is taking %s credits:\nThis student is " +
              "taking a valid number of credits: %s\n",
              FULL_TIME_CREDIT_MIN - FULL_TIME_CREDIT_MIN,
              outstate1.isValid());
      System.out.printf("Student is taking %s credits:\nThis student is " +
                      "taking a valid number of credits: %s\n",
              FULL_TIME_CREDIT_MIN, outstate2.isValid());
   }
}
