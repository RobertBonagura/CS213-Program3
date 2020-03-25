package program2;

import javax.swing.*;

public class GUITuitionManager {
    // The use case I envisioned is that the controller will send the appropriate request from
    // the input provided in the GUI by calling the methods here. For error checking, each of the
    // request methods returns a string. If the string is empty, there is no error. If it isn't
    // equivalent to the empty string, there is an error and the returned string is the appropriate
    // error message.
    StudentList studentList;
    private final String NO_ERROR = "";
    public GUITuitionManager(){
       this.studentList = new StudentList();
    }
    public String addInstateRequest(String fName, String lName, int numCredits, int funding){
        Instate studentToAdd = new Instate(fName, lName, numCredits, funding);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return NO_ERROR;
        } else {
            return errorMsg;
        }
    }
    public String addOutstateRequest(String fName, String lName, int numCredits, boolean isTristate){
        Outstate studentToAdd = new Outstate(fName, lName, numCredits, isTristate);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return NO_ERROR;
        } else {
            return errorMsg;
        }
    }
    public String addInternationalRequest(String fName, String lName, int numCredits, boolean isExchange){
        International studentToAdd = new International(fName, lName, numCredits, isExchange);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return NO_ERROR;
        } else {
            return errorMsg;
        }
    }
    private String determineAddRequestErrorMsg(Student student, StudentList studentList){
        if(student.credit < 1){
            return "Error: Student must have a positive number of credits.";
        } else if( (student instanceof International) && student.credit< 9 ) {
            return "Error: International students must have at least 9 credits.";
        } else if (studentList.contains(student)){
            return "Error: Student could not be added because list already contains this student.";
        }
        return NO_ERROR;
    }
    public String sendDeleteRequest(String fName, String lName){
        int NULL = 0;
        Student studentToRemove = new Instate(fName, lName, NULL, NULL);
        if(studentList.contains(studentToRemove)){
            studentList.remove(studentToRemove);
            return NO_ERROR;
        } else {
            return "Error: Could not remove student because student is not present in list.";
        }
    }
    public String sendPrintRequest(){
        return studentList.toString();
    }
}
