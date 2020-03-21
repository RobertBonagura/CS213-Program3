package program2;

abstract class Student implements Comparable {

   protected final int FULL_TIME_CREDIT_MIN = 12;
   protected final int MAX_BILLABLE_CREDITS = 15;
   protected final int PART_TIME_FEE = 846;
   protected final int FULL_TIME_FEE = 1441;

   private String fName;
   private String lName;
   protected int credit;
   
   public Student(String fName, String lName, int credit) {
      this.fName = fName;
      this.lName = lName;
      this.credit = credit;
   }

   public int compareTo(Object obj){
      if(obj instanceof Student){
         Student comparedStudent = (Student)(obj);
         if(fName.compareTo(comparedStudent.fName) != 0)
            return fName.compareTo(comparedStudent.fName);
         else{
            return lName.compareTo(comparedStudent.lName);
         }
      }
      return 1;//Only called if obj isn't a student
   }

   public String toString() {
      return ("Name: " + fName + " " + lName + "\nCredits: " + credit + "\n");
   }

   public abstract int tuitionDue();
}
