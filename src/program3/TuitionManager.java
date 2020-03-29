package program3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Takes input from stdin and manages student list and validity logic to determine
 * appropriate output
 * @author Ezra Haleva
 */
public class TuitionManager
{
   /**
   Abstracted entry point of program.
   Main should call this method once.
   */
   public void run(){                   
      Scanner sc = new Scanner(System.in);
      StudentList studentList = new StudentList();

      boolean exit = false;
      while(!exit){

         try {
            char command = sc.next().charAt(0);
            switch (command) {
               case 'I': {
                  Instate studentToAdd = createInstateFromInput(sc);
                  if (!studentToAdd.isValid()) {
                     System.out.println("Error: invalid student entry");
                     break;
                  }
                  if (studentList.contains(studentToAdd)) {
                     System.out.println("Error: list already contains this student");
                     break;
                  }
                  studentList.add(studentToAdd);
                  break;
               }
               case 'O': {
                  Outstate studentToAdd = createOutstateFromInput(sc);
                  if (!studentToAdd.isValid()) {
                     System.out.println("Error: invalid student entry");
                     break;
                  }
                  if (studentList.contains(studentToAdd)) {
                     System.out.println("Error: list already contains this student");
                     break;
                  }
                  studentList.add(studentToAdd);
                  break;
               }
               case 'N': {
                  International studentToAdd = createInternationalFromInput(sc);
                  if (!studentToAdd.isValid()) {
                     System.out.println("Error: invalid student entry");
                     break;
                  }
                  if (studentList.contains(studentToAdd)) {
                     System.out.println("Error: list already contains this student");
                     break;
                  }
                  studentList.add(studentToAdd);
                  break;
               }
               case 'R': {
                  String fName = sc.next();
                  String lName = sc.next();
                  int NULL = 0;
                  Student studentToRemove = new Instate(fName, lName, NULL, NULL);
                  if (studentList.contains(studentToRemove)) {
                     studentList.remove(studentToRemove);
                  } else {
                     System.out.println("Error: list does not contain this student");
                  }
                  break;
               }
               case 'P': {
                  studentList.show();
                  break;
               }
               case 'Q': {
                  exit = true;
                  break;
               }
               default: {
                  System.out.println("Error, did not recognize input command");
                  break;
               }
            }
         } catch (InputMismatchException e){
            System.out.println("Error, bad input. Please Try again");
         }

      }
      System.out.println("Program Terminated");
   }
   /**
   Reads data from standard input and creates with it an instance of Instate.
   Types of data read are specific to Instate. 
   @param sc A scanner through which to read input
   @return the instance of Instate created with read data.
   */
   private Instate createInstateFromInput(Scanner sc){
      String fName = sc.next();
      String lName = sc.next();
      int credit = sc.nextInt();
      int funding = sc.nextInt();
      return new Instate(fName, lName, credit, funding);
   }
   /**
   Reads data from standard input and creates with it an instance of Outstate.
   Types of data read are specific to Outstate. 
   @param sc A scanner through which to read input
   @return the instance of Outstate created with read data.
   */
   private Outstate createOutstateFromInput(Scanner sc){
      String fName = sc.next();
      String lName = sc.next();
      int credit = sc.nextInt();
      String tristateStr = sc.next();
      boolean isTristate;
      if(tristateStr.charAt(0) == 'T'){
         isTristate = true;
      }else{
         isTristate = false;
      }
      return new Outstate(fName, lName, credit, isTristate);
   }
   /**
   Reads data from standard input and creates with it an instance of International.
   Types of data read are specific to International. 
   @param sc A scanner through which to read input
   @return the instance of International created with read data.
   */
   private International createInternationalFromInput(Scanner sc){
      String fName = sc.next();
      String lName = sc.next();
      int credit = sc.nextInt();
      String exchangeStr = sc.next();
      boolean isExchange;
      if(exchangeStr.charAt(0) == 'T'){
         isExchange = true;
      } else{
         isExchange = false;
      }
      return new International(fName, lName, credit, isExchange);
   }

}


