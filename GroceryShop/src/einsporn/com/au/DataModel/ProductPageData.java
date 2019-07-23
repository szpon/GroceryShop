package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class ProductPageData {


    private ObservableList<Product> productDataList;
    private ObservableList<String> comboBoxListCategory;
    private ObservableList<String> comboBoxListSupplier;
    private ObservableList<String> comboBoxListUnits;


    public ProductPageData() {

        productDataList = FXCollections.observableArrayList();
        comboBoxListCategory = FXCollections.observableArrayList();
        comboBoxListSupplier = FXCollections.observableArrayList();
        comboBoxListUnits = FXCollections.observableArrayList();

    }


    private void addToProductDataList(Product product) {
        this.productDataList.addAll(product);

    }

    public ObservableList<Product> getProductDataList() {
        return productDataList;

    }


    public ObservableList<String> getComboBoxListCategory() {
        return comboBoxListCategory;
    }

    private void addToComboBoxListCategory(String text) {
        this.comboBoxListCategory.addAll(text);

    }


    public ObservableList<String> getComboBoxListSupplier() {
        return comboBoxListSupplier;
    }

    private void addComboBoxListSupplier(String text) {
        this.comboBoxListSupplier.addAll(text);

    }


    public ObservableList<String> getComboBoxListUnits() {
        return comboBoxListUnits;
    }


    private void addComboBoxListUnits(String text) {
        this.comboBoxListUnits.addAll(text);

    }


    public void retriveProductsFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Products";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addToProductDataList(new Product(rs.getInt("ProductID"), rs.getString("ProductName"),
                        rs.getString("ProductCode"), rs.getString("Category"), rs.getString("Size"),
                        rs.getString("Units"), rs.getString("StockLevel"), rs.getString("Vendor"),
                        rs.getString("MainSuplier"), rs.getString("Price")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void retriveSearchProductFromDB(String searchWord, String radioBtnselection) {

        String sum = "";
        Character x = '\'';
        String y = "%";
        ResultSet rs = null;
        String searchWord1 = x + y + searchWord + y + x;
        String searchword2 = searchWord;


        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {

            // selction of the SQL statment depending on RadioButton clicked by the user
            if (radioBtnselection.equals("description")) {

                String SQL = "SELECT * FROM Products where Products.ProductName like " + searchWord1; // Returns rows where description column equals searchWord1.
                rs = stmt.executeQuery(SQL);


            } else if (radioBtnselection.equals("category")) {

                String SQL = "SELECT * FROM Products where Products.Category like " + searchWord1;  // Returns rows where category column equals searchWord1.
                rs = stmt.executeQuery(SQL);
            } else if (radioBtnselection.equals("vendor")) {

                String SQL = "SELECT * FROM Products where Products.Vendor like " + searchWord1;  //Returns rows where vendor column equals searchWord1.
                rs = stmt.executeQuery(SQL);
            } else if (radioBtnselection.equals("supplier")) {

                String SQL = "SELECT * FROM Products where Products.MainSuplier like " + searchWord1; //Returns rows where MainSupplier column equals searchWord1.
                rs = stmt.executeQuery(SQL);
            }
            else if (radioBtnselection.equals("stockLevel")) {

                String SQL = "SELECT * FROM Products where Products.StockLevel < " + searchword2; // Returns rows where StockLevel column equals searchWord2.
                rs = stmt.executeQuery(SQL);
            }
            while (rs.next()) {
                addToProductDataList(new Product(rs.getInt("ProductID"), rs.getString("ProductName"),
                        rs.getString("ProductCode"), rs.getString("Category"), rs.getString("Size"),
                        rs.getString("Units"), rs.getString("StockLevel"), rs.getString("Vendor"),
                        rs.getString("MainSuplier"), rs.getString("Price")));
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void retriveCategoryComboBoxListFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Category";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addToComboBoxListCategory(rs.getString("Category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void retriveSupplierComboBoxListFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Supplier";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addComboBoxListSupplier(rs.getString("SupplierName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void retriveUnitsComboBoxListFromDB() {

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM Units";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addComboBoxListUnits(rs.getString("Units"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addEditProductToDB(Product product, String addEditCustomer) throws Exception {


        Integer productId = product.getId();
        String description = product.getDescription();
        String code = product.getCode();
        String category = product.getCategory();
        String size = product.getSize();
        String unit = product.getUnit();
        String stockLevel = product.getStockLevel();
        String vendor = product.getVendor();
        String supplier = product.getMainSupplier();
        String price = product.getPrice();


        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();


            if (addEditCustomer.equals("addNewProduct")) {
                stm = con.prepareStatement("INSERT INTO Products(ProductID,ProductName,ProductCode,Category,Size,Units,StockLevel,Vendor,MainSuplier,Price) VALUES (?,?,?,?,?,?,?,?,?,?)");
                stm.setInt(1, productId);
                stm.setString(2, description);
                stm.setString(3, code);
                stm.setString(4, category);
                stm.setString(5, size);
                stm.setString(6, unit);
                stm.setString(7, stockLevel);
                stm.setString(8, vendor);
                stm.setString(9, supplier);
                stm.setString(10, price);
                stm.execute();


            } else if (addEditCustomer.equals("editProduct")) {

                stm = con.prepareStatement("UPDATE Products SET ProductName=?,ProductCode=?,Category=?,Size=?,Units=?,StockLevel=?,Vendor=?,MainSuplier=?,Price=? WHERE ProductID=?");

                stm.setString(1, description);
                stm.setString(2, code);
                stm.setString(3, category);
                stm.setString(4, size);
                stm.setString(5, unit);
                stm.setString(6, stockLevel);
                stm.setString(7, vendor);
                stm.setString(8, supplier);
                stm.setString(9, price);
                stm.setInt(10, productId);
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


    public void deleteProductFromDB(Integer productId) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("DELETE FROM Products WHERE ProductID=? ");
            stm.setInt(1, productId);

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


    public void updateProductStockLevelInDB(String productCode, String stockLevel) throws Exception {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();

            stm = con.prepareStatement("UPDATE Products SET Products.StockLevel=? WHERE ProductCode=? ");
            stm.setString(1, stockLevel);
            stm.setString(2, productCode);

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


    public String retriveProductStockLevelFromDB(String productCode) throws SQLException {


        Character x = '\'';
        ResultSet rs = null;
        String stockLevel=null;

        String text = x + productCode +  x;


        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT StockLevel FROM Products WHERE ProductCode="+ text;
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                stockLevel = rs.getString("StockLevel");
            }

        }

        return stockLevel;
    }



    public Integer retriveLastProductIDFromDB() throws SQLException {

        Integer lastProductNumber=null;

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
            String SQL = "SELECT MAX(Products.ProductID) as ProductID FROM Products";
            ResultSet  rs = stmt.executeQuery(SQL);

            if(rs.next()) {
                lastProductNumber = rs.getInt("ProductID");
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


        return lastProductNumber;
    }



}



