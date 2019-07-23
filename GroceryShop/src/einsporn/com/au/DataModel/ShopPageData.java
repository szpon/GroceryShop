package einsporn.com.au.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class ShopPageData {

    private ObservableList<Product> selectProductTableList;
    private ObservableList<CustomerBasketTable> customerBasketTableList;
    DBConnection dbConnection;




    public ShopPageData() {

        selectProductTableList = FXCollections.observableArrayList();
        customerBasketTableList = FXCollections.observableArrayList();

    }


    public ObservableList<Product> getSelectProductTableList() {
        return selectProductTableList;
    }

    public ObservableList<CustomerBasketTable> getCustomerBasketTableList() {
        return customerBasketTableList;
    }


    public void addCustomerBasketTableList(CustomerBasketTable customerBasketTable) {

        this.customerBasketTableList.addAll(customerBasketTable);

    }

    private void addToSelectProductTableList(Product selectProductTable) {

        this.selectProductTableList.addAll(selectProductTable);

    }

    public void removeProductFromCustomerBasketTableList(CustomerBasketTable customerBasketTable){
        this.customerBasketTableList.remove(customerBasketTable);

    }








    public void retriveCustomerBasketItemsFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM CustomerBasket";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addCustomerBasketTableList(new CustomerBasketTable(rs.getInt("Id"),rs.getString("ProductName"), rs.getString("ProductCode"),
                        rs.getString("Size"), rs.getString("Units"), rs.getString("Category"),
                        rs.getString("Quantity"),rs.getString("Prise")));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void retriveSearchSelectProductTableItemsFromDB(String text, String radioBtnSelection) {


        Character x = '\'';
        String y = "%";
        ResultSet rs = null;

        String text1 = x + y + text+ y +x;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement();) {


            if (radioBtnSelection.equals("description")) {


                String SQL = "SELECT * FROM Products where Products.ProductName LIKE " + text1; // Returns all rows where the product name contains the letters as in Text1.

                rs = stmt.executeQuery(SQL);

            } else if (radioBtnSelection.equals("category")) {

                String SQL = "SELECT * FROM Products where Products.Category LIKE " + text1; // Returns all rows where the product name contains the letters as in Text1.

                rs = stmt.executeQuery(SQL);

            }


            while (rs.next()) {
                addToSelectProductTableList(new Product(rs.getString("ProductName"),
                        rs.getString("ProductCode"), rs.getString("Size"), rs.getString("Units"),
                        rs.getString("Category"), rs.getString("StockLevel"),rs.getString("Price")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void addCustomerBasketItemToDB(Integer id, String productName, String productCode, String size, String unit, String category, String quantity, String price) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();
            stm = con.prepareStatement("INSERT INTO CustomerBasket(Id,ProductName,ProductCode,Size,Units,Category,Quantity,Prise) VALUES (?,?,?,?,?,?,?,?)");

            stm.setInt(1, id);
            stm.setString(2, productName);
            stm.setString(3, productCode);
            stm.setString(4, size);
            stm.setString(5, unit);
            stm.setString(6, category);
            stm.setString(7, quantity);
            stm.setString(8, price);
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



    public void editSelectedCustomerBasketIteminInDB(Integer id,Integer quantity, Float prise) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;


        try {

            con = DBConnection.getconnection();
            stm = con.prepareStatement("UPDATE CustomerBasket set Quantity=?, Prise=? where Id=?");


            stm.setInt(1, quantity);
            stm.setFloat(2,prise);
            stm.setInt(3, id);

            stm.execute();

        } finally {

            if (stm != null) {

                stm.close();
            }

            if (con != null) {
                con.close();

                System.out.println("id: "+id);
                System.out.println("quantity: " + quantity);

            }
        }
    }



    public void removeSelectedCustomerBasketItemFromDB(Integer id) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();
            stm = con.prepareStatement("DELETE FROM CustomerBasket where id=?");

            stm.setInt(1, id);

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


    public void clearCustomerBasketItemsFromDB() throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {


            con = DBConnection.getconnection();
            stm = con.prepareStatement("DELETE FROM CustomerBasket");

            //stm.setString(1, productName);

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









