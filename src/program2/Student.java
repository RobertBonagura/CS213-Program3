/**
Abstract class for shared functionality between student types.
Includes compareTo implementation, toString of shared variables,
and declares an abstract tuitionDue() method.
@author Ezra Haleva
*/
abstract class Student implements Comparable {

   protected final int FULL_TIME_CREDIT_MIN = 12;
   protected final int MAX_BILLABLE_CREDITS = 15;
   protected final int PART_TIME_FEE = 846;
   protected final int FULL_TIME_FEE = 1441;

   private String fName;
   private String lName;
   protected int credit;
   /**
   Constructor.
   initializes fields with provided values. 
   @param fName first name of the student.
   @param lName last name of the student. 
   @param credit number of credits student is taking
   */
   public Student(String fName, String lName, int credit) {
      this.fName = fName;
      this.lName = lName;
      this.credit = credit;
   }

   /** 
   Uses first and last name to determine comparison values.
   Defaults to first name comparison if not equal. Goes to last name comparison 
   if first names are equal.
   @param obj The student that is compared to this one. 
   @return 0 if first and last name are equal, comparison of first and last names
   otherwise
   */
   public int compareTo(Object obj){
      if(obj instanceof Student){
         Student comparedStudent = (Student)(obj);
         if(fName.compareTo(comparedStudent.fName) != 0)
            return fName.compareToIgnoreCase(comparedStudent.fName);
         else{
            return lName.compareToIgnoreCase(comparedStudent.lName);
         }
      }
      return 1;//Only called if obj isn't a student
   }

   /**
   Returns string representation of fields. 
   Only includes fields common to all student types, should be used alongside
   a lower level implementation. 
   @return string representation of fields.
   */
   public String toString() {
      return ("Name: " + fName + " " + lName + "\nCredits: " + credit);
   }

   /**
   Calculates tuition student owes.
   Calculation is specific to student type, is implemented by each type.
   @return Dollar amount of tuition owed by student. 
   */
   public abstract int tuitionDue();
}
