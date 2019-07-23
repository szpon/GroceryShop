package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDialogBoxData {

    private ObservableList<Customer> customerDialogBoxDataTableView;

    public CustomerDialogBoxData() {

        customerDialogBoxDataTableView = FXCollections.observableArrayList();

    }


    private void addCustomerDialogBoxDataTableView(Customer customer){

        customerDialogBoxDataTableView.addAll(customer);

    }


    public ObservableList<Customer> getCustomerDialogBoxDataTableView(){

        return customerDialogBoxDataTableView;

    }


    public void retriveSearchSaleProductTableItemsFromDB(String text, String selectedRadioBtn) {


        Character x = '\'';
        String y = "%";
        ResultSet rs=null;

        String text1 = x + y + text+ y +x;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {

            // selction of the SQL statment depending on RadioButton clicked by the user
            if (selectedRadioBtn.equals("firstName")) {

                String SQL = "SELECT * FROM Customers where Customers.FirstName like " + text1; // Returns all rows where  first name contains the letters in Text1.
                rs = stmt.executeQuery(SQL);
            } else if(selectedRadioBtn.equals("lastName")) {

                String SQL = "SELECT * FROM Customers where Customers.LastName like " + text1; // Returns all rows where last name contains the letters in Text1.
                rs = stmt.executeQuery(SQL);
            }
            while (rs.next()) {
                addCustomerDialogBoxDataTableView(new Customer(rs.getInt("CustomerNumber"), rs.getString("FirstName"), rs.getString("LastName")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
