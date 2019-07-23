package einsporn.com.au.DataModel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerPageData {


    private ObservableList<Customer> customersData;
    private ObservableList<Customer> selectedCustomer;

    public CustomerPageData() {

        customersData = FXCollections.observableArrayList();
        selectedCustomer = FXCollections.observableArrayList();


    }

    private void addDataToCustomersList(Customer customers) {

        customersData.addAll(customers);

    }

    public ObservableList<Customer> getCustomersDataList() {
        return customersData;
    }


    private void addDataToSelectedCustomerList(Customer customer) {

        selectedCustomer.addAll(customer);

    }

    public ObservableList<Customer> getSelectedCustomerList()
    {

        return selectedCustomer;
    }



    public void retriveCustomersFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Customers";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addDataToCustomersList(new Customer(rs.getInt("CustomerNumber"),rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("Suburb"), rs.getString("State"),rs.getString("PostCode")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void retriveSearchSaleProductTableItemsFromDB(String text, String radioBtnSelected) {


        Character x = '\'';
        String y = "%";
        ResultSet rs=null;

        String text1 = x + y + text+ y +x;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {

            // selction of the SQL statment depending on RadioButton clicked by the user
            if (radioBtnSelected.equals("firstName")) {

                String SQL = "SELECT * FROM Customers where Customers.FirstName like " + text1; // Returns all rows where  first name contains the letters as in Text1.
                rs = stmt.executeQuery(SQL);
            } else if(radioBtnSelected.equals("lastName")) {

                String SQL = "SELECT * FROM Customers where Customers.LastName like " + text1; // Returns all rows where last name contains the letters as in Text1.
                rs = stmt.executeQuery(SQL);

            }else if(radioBtnSelected.equals("address")) {

                String SQL = "SELECT * FROM Customers where Customers.Address like " + text1; // Returns all rows where address contains the letters as in Text1.
                rs = stmt.executeQuery(SQL);

            }


            while (rs.next()) {
                addDataToCustomersList(new Customer(rs.getInt("CustomerNumber"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("Suburb"), rs.getString("State"),rs.getString("PostCode")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Integer retriveLastCustomerNumberFromDB() throws SQLException {

        Integer lastCutomerNumber=null;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT MAX(Customers.CustomerNumber) as CustomerNumber FROM Customers";
          ResultSet  rs = stmt.executeQuery(SQL);

            if(rs.next()) {
                lastCutomerNumber = rs.getInt("CustomerNumber");
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


        return lastCutomerNumber;
       }


    public void addEditCustomerToDB(Integer customerNumber, Customer customers, String addEdit) throws Exception {


        String firstName = customers.getFirstName();
        String lastName = customers.getLastName();
        String addreess = customers.getAddress();
        String suburb = customers.getSuburb();
        String state = customers.getState();
        String postCode = customers.getPostCode();


        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();


            if(addEdit.equals("add")) {
                stm = con.prepareStatement("INSERT INTO Customers(CustomerNumber,FirstName,LastName,Address,Suburb,State,PostCode) VALUES (?,?,?,?,?,?,?)");
                stm.setInt(1, customerNumber);
                stm.setString(2, firstName);
                stm.setString(3, lastName);
                stm.setString(4, addreess);
                stm.setString(5, suburb);
                stm.setString(6, state);
                stm.setString(7, postCode);
                stm.execute();

            } else {

                stm = con.prepareStatement("UPDATE Customers SET FirstName=?,LastName=?,Address=?,Suburb=?,State=?,PostCode=? where CustomerNumber=?");
                stm.setString(1, firstName);
                stm.setString(2, lastName);
                stm.setString(3, addreess);
                stm.setString(4, suburb);
                stm.setString(5, state);
                stm.setString(6, postCode);
                stm.setInt(7, customerNumber);
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



    public void deleteCustomerFromDB(Integer customerNumber) throws Exception {


        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();
            stm = con.prepareStatement("DELETE FROM Customers where CustomerNumber=?");

            stm.setInt(1, customerNumber);

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


    public void updateSelectedCutomerInDB(Integer customerNumber, String customerFirsName, String customerLastName) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("UPDATE SelectedCustomer SET CustomerNumber=?,CustomerFirstName=?,CustomerLastName=?");
            stm.setInt(1, customerNumber);
            stm.setString(2,customerFirsName);
            stm.setString(3,customerLastName);

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

    public void retriveSelectedCustomer() {


        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            //String SQL = "SELECT (Customers.FirstName + Customers.LastName) as CustomerName FROM Customers where CustomerNumber="+ customerNumber;
            String SQL = "SELECT * FROM SelectedCustomer";
            ResultSet  rs = stmt.executeQuery(SQL);

            if(rs.next()) {
                addDataToSelectedCustomerList(new Customer(rs.getInt("CustomerNumber"),rs.getString("CustomerFirstName"),rs.getString("CustomerLastName")));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }





}
