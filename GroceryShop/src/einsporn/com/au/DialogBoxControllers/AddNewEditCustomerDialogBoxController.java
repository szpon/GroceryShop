package einsporn.com.au.DialogBoxControllers;

import einsporn.com.au.DataModel.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddNewEditCustomerDialogBoxController {


    @FXML
    TextField firstNameDialogBoxTextField;
    @FXML
    TextField lastNameDialogBoxTextField;
    @FXML
    TextField addressDialogBoxTextField;
    @FXML
    TextField suburbDialogBoxTextField;
    @FXML
    TextField stateDialogBoxTextField;
    @FXML
    TextField postCodeDialogBoxTextField;
    @FXML
    Label customerNumber;



    public void setCustomerNumber(String text){
    customerNumber.setText(text);
    }


    public Customer getCutomerDialogBoxData(){

        String firstName =firstNameDialogBoxTextField.getText().trim();
        String lastName = lastNameDialogBoxTextField.getText().trim();
        String address = addressDialogBoxTextField.getText().trim();
        String suburb = suburbDialogBoxTextField.getText().trim();
        String state = stateDialogBoxTextField.getText().trim();
        String postCode = postCodeDialogBoxTextField.getText().trim();

        return new Customer(firstName,lastName,address,suburb,state,postCode);
    }

    public void setCustomerDialogBox(Customer customers, String customerNumber){
        setCustomerNumber(customerNumber);
        firstNameDialogBoxTextField.setText(customers.getFirstName());
        lastNameDialogBoxTextField.setText(customers.getLastName());
        addressDialogBoxTextField.setText(customers.getAddress());
        suburbDialogBoxTextField.setText(customers.getSuburb());
        stateDialogBoxTextField.setText(customers.getState());
        postCodeDialogBoxTextField.setText(customers.getPostCode());

    }

}
