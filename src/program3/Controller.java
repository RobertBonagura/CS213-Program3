package program3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import program2.GUITuitionManager;

/**
 * Controller class to handle all action events.
 */
public class Controller {

   private boolean isInstate, isOutstate, isInternat;

   @FXML
   private TextField fName, lName, credits, fundingAmt;

   @FXML
   private RadioButton instate, outstate, internat;

   @FXML
   private CheckBox funding, tristate, exchange;

   @FXML
   TextArea textArea;

   GUITuitionManager tuitionManager;

   /**
    * Constructor to bind an instance of the TuitionManager to GUI.
    * Instate Student is selected by default.
    */
   public Controller(){
      this.tuitionManager = new GUITuitionManager();
      this.isInstate = true;
   }

   /**
    * Check selected Student type and add to list.
    * Appends confirmation message or error message to textArea.
    */
   public void add(){
      String message;
      if (isInstate) {
         message = tuitionManager.addInstateRequest(fName.getText(),
                 lName.getText(), Integer.parseInt(credits.getText()),
                 Integer.parseInt(fundingAmt.getText()));
      } else if (isOutstate) {
         message = tuitionManager.addOutstateRequest(fName.getText(),
                 lName.getText(), Integer.parseInt(credits.getText()),
                 tristate.isSelected());

      } else if (isInternat) {
         message = tuitionManager.addInternationalRequest(fName.getText(),
                 lName.getText(), Integer.parseInt(credits.getText()),
                 exchange.isSelected());
      } else {
         message = "Error: A student type could not be determined.\n";
      }
      textArea.appendText(message);
   }

   /**
    * Remove student based on First and Last Name provided.
    * Appends confirmation message or error message to textArea
    */
   public void remove(){
      String message;
      message = tuitionManager.sendDeleteRequest(fName.getText(),
              lName.getText());
      textArea.appendText(message);
   }

   /**
    * Print all student list information to TextArea.
    */
   public void print(){
      textArea.appendText(tuitionManager.sendPrintRequest());
   }

   /**
    * Based on selected radio button, enable the corresponding checkbox and
    * disable all other checkboxes.
    */
   public void radioAction(){
      if (instate.selectedProperty().get()) {
         isInstate = true;
         isOutstate = false;
         isInternat = false;
         funding.setDisable(false);
         fundingAmt.setDisable(false);
         tristate.setSelected(false);
         tristate.setDisable(true);
         exchange.setSelected(false);
         exchange.setDisable(true);
      } else if (outstate.selectedProperty().get()) {
         isInstate = false;
         isOutstate = true;
         isInternat = false;
         funding.setSelected(false);
         funding.setDisable(true);
         fundingAmt.setDisable(true);
         tristate.setDisable(false);
         exchange.setSelected(false);
         exchange.setDisable(true);
      } else if (internat.selectedProperty().get()) {
         isInstate = false;
         isOutstate = false;
         isInternat = true;
         funding.setSelected(false);
         funding.setDisable(true);
         fundingAmt.setDisable(true);
         tristate.setSelected(false);
         tristate.setDisable(true);
         exchange.setDisable(false);
      }
   }
}
