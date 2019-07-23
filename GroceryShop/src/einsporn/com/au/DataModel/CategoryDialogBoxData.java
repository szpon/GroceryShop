package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CategoryDialogBoxData {


    private ObservableList<String> addNewCategoryList;


    public CategoryDialogBoxData() {

        addNewCategoryList = FXCollections.observableArrayList();

    }

    private void addCategoryToList(String add) {

        addNewCategoryList.addAll(add);

    }

    public ObservableList<String> getAddNewCategoryList() {

        return addNewCategoryList;
    }



    public void retriveCategoryFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Category";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addCategoryToList(rs.getString("Category"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCategoryToDB(String category) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("INSERT Category (Category) VALUES (?)");
            stm.setString(1, category);

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


    public void deleteCategoryFromDB(String category) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("DELETE Category where Category=?");
            stm.setString(1, category);

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

    
