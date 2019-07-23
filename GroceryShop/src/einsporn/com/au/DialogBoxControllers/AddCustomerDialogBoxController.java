package einsporn.com.au.DialogBoxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import einsporn.com.au.DataModel.Customer;


public class AddCustomerDialogBoxController {


    @FXML
    private TableView<Customer> SelectCustomerTableView;
    @FXML
    private Label customerNoDialogBoxLabel;
    @FXML
    private Label customerNameDialogBoxLabel;


    public TableView<Customer> getSelectCustomerTableView() {
        return SelectCustomerTableView;
    }


    public void setCustomerNoDialogBoxLabel(String text){

        customerNoDialogBoxLabel.setText(text);

    }

    public void setCustomerNameDialogBoxLabel(String text) {
        customerNameDialogBoxLabel.setText(text);
    }

}
