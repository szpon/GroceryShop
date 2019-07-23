package einsporn.com.au.DialogBoxControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class UnitsDialogBoxController {


    @FXML
    ListView<String> unitsListView = new ListView<>();
    @FXML
    TextField newUnitsTextField;
    @FXML
    Button unitsBtnHandler;
    @FXML
    Button deleteUnitsBtnHandler;


    public ListView<String> getUnitsListView() {

        return unitsListView;

    }

    public void setUnitsListView(ObservableList<String> add) {
        unitsListView.setItems(add);
    }


    public String getNewUnitsTextField() {

     return newUnitsTextField.getText();
    }


    public Button getUnitsBtnHandler(){

        return unitsBtnHandler;

    }

    public Button getDeleteSelectedUnitsBtnHandler() {
        return deleteUnitsBtnHandler;
    }

    public void setText(String text){

        newUnitsTextField.setText(text);

    }



}
