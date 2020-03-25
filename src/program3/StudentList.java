package program3;

/**
 *  Container class used to hold a list fo students.
 * @author Robert Bonagura
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
    * Gets size of List.
    * @return Size of List
    */
   public int getSize() {
      return size;
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
            this.students[j] = this.students[j+1];
         }
         this.size--;
         return;
        }
      }
  }

   /**
    * Removes first student from list.
    */
   public Student remove(){
     Student student = this.students[0];
     for (int i = 0; i < this.students.length - 1; i++){
        this.students[i] = this.students[i+1];
     }
     this.size--;
     return student;
   }

   /**
    * Checks if student is in list.
    * Iterates through list checking each student using compareTo() method.
    * @param student Student checked to see if in list
    * @return boolean value indicating whether or list contains the student
    */
   public boolean contains(Student student) {
      if (this.size == 0){
         return false;
      }
      for (Student curStudent : this.students) {
         if (student.compareTo(curStudent) == 0){
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
      if (this.size == 0){
         System.out.println("The list is empty");
      }
      for (int i = 0; i < this.getSize(); i++) {
         Student student = this.students[i];
         System.out.println(student.toString());
      }
   }

   /**
    * Returns a String representation of the show() method.
    * @return
    */
   public String toString(){
      StringBuilder sb = new StringBuilder();
      String result;
      if (this.size == 0){
         result = "The list is empty\n";
         return result;
      }
      for (int i = 0; i < this.getSize(); i++) {
         Student student = this.students[i];
         sb.append(student.toString());
      }
      result = sb.toString();
      return result;
   }



}
