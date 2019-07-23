package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SupplierPageData {

    private ObservableList<Supplier> suppliersDataList;


    public SupplierPageData() {

        suppliersDataList = FXCollections.observableArrayList();
    }


    public ObservableList<Supplier> getSuppliersDataList(){

        return suppliersDataList;

    }


    private void addToSuppliersDataList(Supplier suppliers){

        suppliersDataList.addAll(suppliers);

    }


    public void retriveSupplierFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Supplier";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addToSuppliersDataList(new Supplier(rs.getInt("SupplierID"),
                        rs.getString("SupplierName"), rs.getString("Email"), rs.getString("Phone"),
                        rs.getString("Address"),rs.getString("Notes")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retriveSearchSupplierFromDB(String searchWord, String radioBtnSelected) {

        Character x = '\'';
        String y = "%";

        String text = x + y + searchWord+ y +x;


        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
           String SQL="";

            if (radioBtnSelected.equals("searchByID")) {
                SQL = "SELECT * FROM Supplier where Supplier.SupplierID LIKE " + text; // Returns all rows where last name contains the letters as in Text1.

            }else if(radioBtnSelected.equals("searchByName")) {

                SQL = "SELECT * FROM Supplier where Supplier.SupplierName LIKE " + text; // Returns all rows where last name contains the letters as in Text1.

        }else if(radioBtnSelected.equals("searchByAddress")) {

            SQL = "SELECT * FROM Supplier where Supplier.Address LIKE " + text; // Returns all rows where address contains the letters as in Text1.

        }

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addToSuppliersDataList(new Supplier(rs.getInt("SupplierID"),
                        rs.getString("SupplierName"), rs.getString("Email"), rs.getString("Phone"),
                        rs.getString("Address"), rs.getString("Notes")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEditSupplierToDB(Supplier supplier, String addOrEddit) throws Exception {


        Integer id = supplier.getSupplierId();
        String name = supplier.getName();
        String email = supplier.getEmail();
        String phone = supplier.getPhone();
        String address = supplier.getAddress();
        String notes = supplier.getNotes();

        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();

            if(addOrEddit.equals("addSupplier")) {
                stm = con.prepareStatement("INSERT INTO Supplier(SupplierID,SupplierName,Email,Phone,Address,Notes) VALUES (?,?,?,?,?,?)");
                stm.setInt(1, id);
                stm.setString(2,name);
                stm.setString(3, email);
                stm.setString(4, phone);
                stm.setString(5, address);
                stm.setString(6, notes);

                stm.execute();


            } else if(addOrEddit.equals("editSupplier")) {

                stm = con.prepareStatement("UPDATE Supplier SET SupplierName=?,Email=?,Phone=?,Address=?, Notes=? where SupplierID=?");
                stm.setString(1,name);
                stm.setString(2, email);
                stm.setString(3, phone);
                stm.setString(4, address);
                stm.setString(5, notes);
                stm.setInt(6, id);
                stm.execute();
            }

        } finally {

            if (stm != null) {

                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }


    public Integer retriveLastSupplierNumberFromDB() throws SQLException {

        Integer lastCutomerNumber=null;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT MAX(Supplier.SupplierID) as SupplierNumber FROM Supplier";
            ResultSet  rs = stmt.executeQuery(SQL);

            if(rs.next()) {
                lastCutomerNumber = rs.getInt("SupplierNumber");
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return lastCutomerNumber;
    }


    public void deleteSupplierFromDB(Integer supplierId) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("DELETE FROM Supplier WHERE SupplierID=? ");
            stm.setInt(1, supplierId);

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
