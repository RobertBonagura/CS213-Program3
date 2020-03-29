package program3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of the program and processor of requests from the Controller.
 * Extends the JavaFX class "Application"
 * @author Ezra Haleva
 */
public class Main extends Application {

    static StudentList studentList;
    final static String NO_ERROR = "";

    /**
     * Initializes the JavaFX GUI
     * Loads info from the fxml file, set's the title, and makes GUI visible
     * @param primaryStage The starting stage. Concept specific to JavaFX innards.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Project3GUI.fxml"));
            primaryStage.setTitle("Program 3 - Tuition Manager");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Entry point.
     * Initializes student list and calls Launch() from JavaFX library.
     * @param args Entry point arguments used by JavaFX innards.
     */
    public static void main(String[] args) {
        studentList = new StudentList();
        launch(args);
    }

    /**
     * Sends/processes a request to add an Instate Student.
     * "Processing" a request includes checking that it's entries are valid,
     * according to our defined conditions, and then executing the request
     * if it checks out.
     * @param fName First name of student
     * @param lName Last name of student
     * @param numCredits Number of credits student is taking
     * @param funding Funding student has
     * @return A string of the requests output message, whether an error or a
     * success confirmation.
     */
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

    /**
     * Sends/processes a request to add an Out of state student.
     * "Processing" a request includes checking that it's entries are valid,
     * according to our defined conditions, and then executing the request
     * if it checks out.
     * @param fName First name of student
     * @param lName Last name of student
     * @param numCredits Number of credits student is taking
     * @param isTristate whether or not the student is from the tristate area.
     * @return A string of the requests output message, whether an error or a
     * success confirmation.
     */
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
    /**
     * Sends/processes a request to add an International Student.
     * "Processing" a request includes checking that it's entries are valid,
     * according to our defined conditions, and then executing the request
     * if it checks out.
     * @param fName First name of student
     * @param lName Last name of student
     * @param numCredits Number of credits student is taking
     * @param isExchange Whether or not the student is an exchange student.
     * @return A string of the requests output message, whether an error or a
     * success confirmation.
     */
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

    /**
     * Determines the appropriate error message of a request to add a student.
     * @param student Student requested to be added.
     * @param studentList The student list to which the student would be added.
     * @return If there is an error, a string elaborating on that error. An
     * empty string if there is no error.
     */
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

    /**
     * Sends/processes a request to delete a student from studentList
     * "Processing" a request includes checking that it's entries are valid,
     * according to our defined conditions, and then executing the request
     * if it checks out.
     * @param fName First name of student
     * @param lName Last name of student
     * @return A string of the requests output message, whether an error or a
     * success confirmation.
     */
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

    /**
     * Sends a request to print the members of studentList
     * This request currently doesn't require any checks for validity.
     * Processing then only involves execution.
     * @return A string representation of studentList, the output of
     * this request.
     */
    public static String sendPrintRequest(){
        return studentList.toString();
    }
}
