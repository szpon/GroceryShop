package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UnitsDialogBoxData {


    private ObservableList<String> addNewUnitsListView;


    public UnitsDialogBoxData() {

        addNewUnitsListView = FXCollections.observableArrayList();

    }

    private void addUnitsToListView(String add) {

        addNewUnitsListView.addAll(add);


    }

    public ObservableList<String> getAddNewUnitsListView() {

        return addNewUnitsListView;
    }


    public void retriveUnitsFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Units";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addUnitsToListView(rs.getString("Units"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUnitsToDB(String units) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("INSERT Units (Units) VALUES (?)");
            stm.setString(1, units);

            stm.execute();


        } finally {

            if (stm != null) {

                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }


    public void deleteUnitsFromDB(String units) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("DELETE Units where Units=?");
            stm.setString(1, units);

            stm.execute();


        } finally {

            if (stm != null) {

                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }


    }




}

    
