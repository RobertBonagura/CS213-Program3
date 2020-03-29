package program3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static StudentList studentList;
    final static String NO_ERROR = "";
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Program 3 - Tuition Manager");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        studentList = new StudentList();
        launch(args);
    }


    public static String addInstateRequest(String fName, String lName, int numCredits, int funding){
        Instate studentToAdd = new Instate(fName, lName, numCredits, funding);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return (fName + " " + lName + " Succesfully added.\n");
        } else {
            return errorMsg;
        }
    }
    public static String addOutstateRequest(String fName, String lName, int numCredits, boolean isTristate){
        Outstate studentToAdd = new Outstate(fName, lName, numCredits, isTristate);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return (fName + " " + lName + " Succesfully added.\n");
        } else {
            return errorMsg;
        }
    }
    public static String addInternationalRequest(String fName, String lName, int numCredits, boolean isExchange){
        International studentToAdd = new International(fName, lName, numCredits, isExchange);
        String errorMsg = determineAddRequestErrorMsg(studentToAdd, studentList);
        if(errorMsg.equals(NO_ERROR)){
            studentList.add(studentToAdd);
            return (fName + " " + lName + " Succesfully added.\n");
        } else {
            return errorMsg;
        }
    }
    private static String determineAddRequestErrorMsg(Student student, StudentList studentList){
        if(student.credit < 1) {
            return "Error: Student must have a positive number of credits.\n";
        } else if( (student instanceof International) && student.credit< 9 ) {
            return "Error: International students must have at least 9 credits.\n";
        } else if (studentList.contains(student)){
            return "Error: Student could not be added because list already contains this student.\n";
        }
        return NO_ERROR;
    }
    public static String sendDeleteRequest(String fName, String lName){
        int NULL = 0;
        Student studentToRemove = new Instate(fName, lName, NULL, NULL);
        if(studentList.contains(studentToRemove)){
            studentList.remove(studentToRemove);
            return (fName + " " + lName + " Successfully deleted.\n");
        } else {
            return "Error: Could not remove student because student is not present in list.\n";
        }
    }
    public static String sendPrintRequest(){
        return studentList.toString();
    }
}
