package program2;

/**
 *  Container class used to hold a list fo students.
 */
public class StudentList {

   private int capacity;
   private int size;
   private Student[] students;

   /**
    * Default constructor, sets StudentList to default capacity of 10.
    */
   public StudentList(){
      int INITIAL_CAPACITY = 10;
      this.capacity = INITIAL_CAPACITY;
      this.size = 0;
      this.students = new Student[INITIAL_CAPACITY];
   }

   /**
    * Adds student to list of Students.
    * @param student Student to add to list
    */
   public void add(Student student){
      this.students[this.size] = student;
      this.size++;
      if (this.size == capacity){
         this.grow();
      }
   }

   /**
    * Increases the capacity of the list of students by doubling the size
    * underlying array.
    * Calls Array constructor to create a new list and then iterate over each
    * element in the old array to copy into the new array.
    */
   private void grow() {
      int GROWTH_RATE = 2;
      int newCapacity = capacity * GROWTH_RATE;
      Student[] newStudents = new Student[newCapacity];
      for (int i = 0; i < this.size; i++){
         newStudents[i] = this.students[i];
      }
      this.students = newStudents;
   }

   /**
    * Removes student from list.
    * Iterates over list to find the specified student to remove.
    * Continues iterating through the rest of the list to move each element
    * back one index so that the underlying array's size always represents the
    * index to add a new student.
    * @param student Student to remove from list
    */
  public void remove(Student student){
      for (int i = 0; i < this.students.length; i++){
        if (this.students[i].compareTo(student) == 0){
         for (int j = i; j < this.students.length - 1; j++){
            this.students[i] = this.students[i+1];
         }
         return;
        }
      }
  }

   /**
    * Checks if student is in list.
    * Iterates through list checking each student using compareTo() method.
    * @param student Student checked to see if in list
    * @return boolean value indicating whether or list contains the student
    */
   public boolean contains(Student student) {
      for (Student curStudent : this.students) {
         if (curStudent.compareTo(student) == 0){
            return true;
         }
      }
      return false;
   }

   /**
    * Prints list of students to console.
    * Iterates through list and calls toString() method on each student.
    */
   public void show(){
      for (Student student : this.students) {
         System.out.println(student.toString());
      }
   }



}
