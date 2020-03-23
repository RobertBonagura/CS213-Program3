package program2;

import program2.Instate;
import program2.International;
import program2.Outstate;
import program2.Student;
import program2.StudentList;

public class GUITuitionManager {
    StudentList studentList;
    public GUITuitionManager(){
       this.studentList = new StudentList();
    }
    public void addInstateRequest(String fName, String lName, int numCredits, int funding){
        Instate studentToAdd = new Instate(fName, lName, numCredits, funding);
        if(addRequestIsValid(studentToAdd, studentList)){
            studentList.add(studentToAdd);
        }
    }
    public void addOutstateRequest(String fName, String lName, int numCredits, boolean isTristate){
        Outstate studentToAdd = new Outstate(fName, lName, numCredits, isTristate);
        if(addRequestIsValid(studentToAdd, studentList)){
            studentList.add(studentToAdd);
        }
    }
    public void addInternationalRequest(String fName, String lName, int numCredits, boolean isExchange){
        International studentToAdd = new International(fName, lName, numCredits, isExchange);
        if(addRequestIsValid(studentToAdd, studentList)){
            studentList.add(studentToAdd);
        }
    }
    private boolean addRequestIsValid(Student student, StudentList studentList){
        if(student.credit < 1){
            return false;
        } else if( (student instanceof International) && student.credit< 9 ) {
            return false;
        } else if (studentList.contains(student)){
            return false;
        }
        return true;
    }
    public void sendDeleteRequest(String fName, String lName){
        int NULL = 0;
        Student studentToRemove = new Instate(fName, lName, NULL, NULL);
        if(studentList.contains(studentToRemove)){
            studentList.remove(studentToRemove);
        }
    }
    public void sendPrintRequest(){
        studentList.show();
    }
}
