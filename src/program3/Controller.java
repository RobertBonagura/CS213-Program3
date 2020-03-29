package program3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

   /**
    * Constructor to bind an instance of the TuitionManager to GUI.
    * Instate Student is selected by default.
    */
   public Controller(){
      this.isInstate = true;
   }

   /**
    * Check selected Student type and add to list.
    * Appends confirmation message or error message to textArea.
    */
   public void add(){
      String message;
      if (fName.getText().isBlank() || lName.getText().isBlank()) {
         message = "Error: Student name fields can not be left blank" +
                    " or contain only white spaces.\n";
         textArea.appendText(message);
         return;
      }
      if (credits.getText().isBlank()) {
         message = "Error: Number of Credits field can not be left blank.\n";
         textArea.appendText(message);
         return;
      }

      try {
         if (isInstate) {
            if (funding.isSelected() && fundingAmt.getText().isBlank()) {
               message = "Error: Funding amount can not be left blank.\n";
            } else if (funding.isSelected() &&
                    Integer.parseInt(fundingAmt.getText()) < 0) {
               message = "Error: Funding needs to be a positive value.\n";
            } else if (funding.isSelected() &&
                    Integer.parseInt(credits.getText()) < 9 &&
                    Integer.parseInt(fundingAmt.getText()) > 0) {
               message = "Error: Student needs to take at least 9 credits" +
                       " in order to receive funding.\n";
            } else {
               if (!funding.isSelected()){
                  int FUNDING = 0;
                  message = Main.addInstateRequest(fName.getText(),
                          lName.getText(), Integer.parseInt(credits.getText()),
                          FUNDING);
               } else {
                  message = Main.addInstateRequest(fName.getText(),
                          lName.getText(), Integer.parseInt(credits.getText()),
                          Integer.parseInt(fundingAmt.getText()));
               }
            }
         } else if (isOutstate) {
            message = Main.addOutstateRequest(fName.getText(),
                    lName.getText(), Integer.parseInt(credits.getText()),
                    tristate.isSelected());

         } else if (isInternat) {
            message = Main.addInternationalRequest(fName.getText(),
                    lName.getText(), Integer.parseInt(credits.getText()),
                    exchange.isSelected());
         } else {
            message = "Error: A student type could not be determined.\n";
         }
      } catch (NumberFormatException e) {
         message = "Error: Fields requiring a number value can not be given" +
                 " a character value.\n";
      } catch (Exception e){
         message = e.toString() + "\n";
      }
      textArea.appendText(message);
   }

   /**
    * Remove student based on First and Last Name provided.
    * Appends confirmation message or error message to textArea
    */
   public void remove(){
      String message;
      if (fName.getText().isBlank() || lName.getText().isBlank()) {
         message = "Error: Student name fields can not be left blank" +
                 " or contain only white spaces.\n";
      } else {
         message = Main.sendDeleteRequest(fName.getText(),
                 lName.getText());
      }
      textArea.appendText(message);
   }

   /**
    * Print all student list information to TextArea.
    */
   public void print(){
      textArea.appendText(Main.sendPrintRequest());
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
