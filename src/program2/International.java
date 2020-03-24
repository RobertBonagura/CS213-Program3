/**
 * Class used to represent International Student.
 * @author Robert Bonagura
 */
public class International extends Student {

   private boolean exchange;
   private static final int CREDIT_MIN = 9;

   /**
    * Default constructor
    * @param fName First name of student
    * @param lName Last name of student
    * @param credit Number of credits taken
    * @param exchange Whether or not student is an exchange student or not
    */
   public International(String fName, String lName, int credit,
                        boolean exchange) {
      super(fName, lName, credit);
      this.exchange = exchange;
   }

   public boolean isValid(){
      if(super.credit >= CREDIT_MIN ) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Calculattes tuition due by student.
    * @return
    */
   @Override
   public int tuitionDue() {
      final int COST_PER_CREDIT = 945;
      final int INTERNATIONAL_STUDENT_FEE = 350;

      if (this.exchange){
         return FULL_TIME_FEE + INTERNATIONAL_STUDENT_FEE;
      }

      int creditsToBill;
      if(this.credit > MAX_BILLABLE_CREDITS){
         creditsToBill = MAX_BILLABLE_CREDITS;
      } else {
         creditsToBill = this.credit;
      }

      int feeToPay;
      if(this.credit >= FULL_TIME_CREDIT_MIN) {
         feeToPay = FULL_TIME_FEE + INTERNATIONAL_STUDENT_FEE;
      } else {
         feeToPay = PART_TIME_FEE + INTERNATIONAL_STUDENT_FEE;
      }

      return (creditsToBill * COST_PER_CREDIT) + feeToPay;
   }

   /**
    * Creates String representation of all relevant information about student.
    * @return
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(super.toString());
      String exchangeStr = "\nIs exchange student: " + exchange + "\n";
      String tuitionStr = "Tuition Due: " + tuitionDue();
      sb.append(exchangeStr);
      sb.append(tuitionStr);
      return sb.toString();
   }

   /**
    * Test bed main function for all constructors and methods of this class.
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Test Case 1:");
      System.out.println("Checks default constructor and TuitionDue() " +
              "based on students full-time/part-time status:");

      int FULL_TIME_CREDIT_MIN = 12;
      StudentList list = new StudentList();
      International internat1 = new International("Robert", "Bonagura",
              FULL_TIME_CREDIT_MIN, false);
      International internat2 = new International("Bob", "Bonagura",
              FULL_TIME_CREDIT_MIN -1, false);

      list.add(internat1);
      list.add(internat2);

      int size = list.getSize();
      for (int i = 0; i < size; i++){
         International student = (International) list.remove();
         System.out.printf("Student %s:\n%s\n", i, student.toString());
      }
      System.out.println();

      System.out.println("Test Case 2:");
      System.out.println("Checks TuitionDue() on full-time students for " +
              "both exchange values:");

      internat1 = new International("Robert", "Bonagura",
              FULL_TIME_CREDIT_MIN + 1, true);
      internat2 = new International("Greg", "Bonagura",
              FULL_TIME_CREDIT_MIN + 3, false);

      list.add(internat1);
      list.add(internat2);

      size = list.getSize();
      for (int i = 0; i < size; i++){
         International student = (International) list.remove();
         System.out.printf("Student %s:\n%s\n", i, student.toString());
      }
      System.out.println();

      System.out.println("Test Case 3:");
      System.out.println("Tests if student is taking valid number of " +
              "credits:");
      internat1 = new International("Bobby", "Bonagura",
              FULL_TIME_CREDIT_MIN - FULL_TIME_CREDIT_MIN, false);
      internat2 = new International("Greg", "Bonagura",
              FULL_TIME_CREDIT_MIN, false);

      System.out.printf("Student is taking %s credits:\nThis student is " +
                      "taking a valid number of credits: %s\n", CREDIT_MIN -1,
              internat1.isValid());
      System.out.printf("Student is taking %s credits:\nThis student is " +
                      "taking a valid number of credits: %s\n", CREDIT_MIN,
              internat2.isValid());
   }
}
