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
    */
   public void add(){
      System.out.println("Add");
      if (isInstate) {
         tuitionManager.addInstateRequest(fName.getText(), lName.getText(),
                 Integer.parseInt(credits.getText()),
                 Integer.parseInt(fundingAmt.getText()));
      } else if (isOutstate) {
         tuitionManager.addOutstateRequest(fName.getText(), lName.getText(),
                 Integer.parseInt(credits.getText()), tristate.isSelected());

      } else if (isInternat) {
         tuitionManager.addInternationalRequest(fName.getText(),
                 lName.getText(), Integer.parseInt(credits.getText()),
                 exchange.isSelected());
      }
   }

   /**
    * Remove student based on First and Last Name provided.
    */
   public void remove(){
      System.out.println("Remove");
      tuitionManager.sendDeleteRequest(fName.getText(), lName.getText());
   }

   /**
    * Print all student list information to TextArea.
    */
   public void print(){
      System.out.println("Print");
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
