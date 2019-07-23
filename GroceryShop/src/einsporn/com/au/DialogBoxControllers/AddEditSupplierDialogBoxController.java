package einsporn.com.au.DialogBoxControllers;

import einsporn.com.au.DataModel.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddEditSupplierDialogBoxController {


    @FXML
    TextField nameDialogBoxTextField;
    @FXML
    TextField emailDialogBoxTextField;
    @FXML
    TextField phoneDialogBoxTextField;
    @FXML
    TextField addressDialogBoxTextField;
    @FXML
    TextArea notesDialogBoxTextField;
    @FXML
    Label newSupplierIDLabel;



    public Supplier getSupplierTextFields(){

        return new Supplier(nameDialogBoxTextField.getText(),emailDialogBoxTextField.getText(),phoneDialogBoxTextField.getText(), addressDialogBoxTextField.getText(),notesDialogBoxTextField.getText());

    }


    public void setNewSupplierIDLabel(Integer id){

        newSupplierIDLabel.setText(String.valueOf(id));

    }

    public void setSupplierTextFields(Supplier textFields){

        nameDialogBoxTextField.setText(textFields.getName());
        emailDialogBoxTextField.setText(textFields.getEmail());
        phoneDialogBoxTextField.setText(textFields.getPhone());
        addressDialogBoxTextField.setText(textFields.getAddress());
        notesDialogBoxTextField.setText(textFields.getNotes());


    }

}




