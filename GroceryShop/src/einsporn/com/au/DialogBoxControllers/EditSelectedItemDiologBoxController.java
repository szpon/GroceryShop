package einsporn.com.au.DialogBoxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import einsporn.com.au.DataModel.CustomerBasketTable;

public class EditSelectedItemDiologBoxController {

    @FXML
    Label productDialogBoxLabel;
    @FXML
    Label codeDialogBoxLabel;
    @FXML
    Label sizeDialogBoxLabel;
    @FXML
    Label unitDialogBoxLabel;
    @FXML
    Label categoryDialogBoxLabel;
    @FXML
    TextField quantityDialogBoxTextField;
    @FXML
    Label priseDialogBoxLabel;



    public void setProductDialogBoxLabel(CustomerBasketTable customerBasketTable){

        productDialogBoxLabel.setText(customerBasketTable.getDescription());
        codeDialogBoxLabel.setText(customerBasketTable.getCode());
        sizeDialogBoxLabel.setText(String.valueOf(customerBasketTable.getSize()));
        unitDialogBoxLabel.setText(customerBasketTable.getUnit());
        categoryDialogBoxLabel.setText(customerBasketTable.getCategory());
        quantityDialogBoxTextField.setText(String.valueOf(customerBasketTable.getQuantity()));
        priseDialogBoxLabel.setText(String.valueOf(customerBasketTable.getPrice()));

    }


    public String getProductDialogQuantity(){

        return  quantityDialogBoxTextField.getText();

    }



}
