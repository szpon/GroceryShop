package einsporn.com.au.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;

public class SalesPageData {

   private ObservableList<Sales> salesDataList;
   private ObservableList<String> selectFilterOptionsList;


    public SalesPageData() {

        salesDataList = FXCollections.observableArrayList();

        selectFilterOptionsList =FXCollections.observableArrayList();
        selectFilterOptionsList.add("Customer Name");
        selectFilterOptionsList.add("Product Name");
        selectFilterOptionsList.add("Category");

    }


    public ObservableList<String> getSelectFilterOptionsList(){

        return selectFilterOptionsList;

    }


    public ObservableList<Sales> getSalesDataList(){

        return salesDataList;

    }

    private void addSaleToTableList(Sales sales){

        salesDataList.addAll(sales);

    }


    public void retriveSalesFromDB() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {

            String SQL = "SELECT * FROM Sales";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                addSaleToTableList(new Sales(rs.getInt("SaleNumber"),simpleDateFormat.format(rs.getDate("Date")), rs.getString("Time"), rs.getString("Customer"),
                        rs.getString("Product"), rs.getString("Code"), rs.getString("Size"),
                        rs.getString("Units"),rs.getString("Category"),rs.getString("Quantity"),rs.getString("UnitPrice"),rs.getString("SalePrice")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void addSaleToDB(Sales sale) throws Exception {


        Integer saleNumber = sale.getSaleNumber();
        String date = sale.getDate();
        String time = sale.getTime();
        String customer = sale.getCustomer();
        String product = sale.getProduct();
        String code = sale.getCode();
        String size = sale.getSize();
        String units = sale.getUnits();
        String category = sale.getCategory();
        String quantity = sale.getQuantity();
        String unitPrice = sale.getItemPrice();
        String salePrice = sale.getSalePrice();

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBConnection.getconnection();


                stm = con.prepareStatement("INSERT INTO Sales(SaleNumber,Date,Time,Customer,Product,Code,Size,Units,Category,Quantity,UnitPrice,SalePrice)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                stm.setInt(1, saleNumber);
                stm.setString(2, date);
                stm.setString(3, time);
                stm.setString(4, customer);
                stm.setString(5, product);
                stm.setString(6, code);
                stm.setString(7, size);
                stm.setString(8, units);
                stm.setString(9, category);
                stm.setString(10, quantity);
                stm.setString(11, unitPrice);
                stm.setString(12, salePrice);
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


    // method retrives filtered data from DB
    public void retriveSearchSaleDataFromDB(String search1,String search2, String selection) throws SQLException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


        Character x = '\'';
        String y = "%";
        ResultSet rs = null;
        ResultSet rs2 = null;

        String searchWord = x + y + search1 + y + x;
        String searchWord1 = x + search1 + x;
        String searchWord2 = x + search2 + x;


        try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {

            // selction of the SQL statment depending on RadioButton clicked by the user
            if (selection.equals("SaleNumber")) {

                String SQL = "SELECT * FROM Sales where Sales.SaleNumber>=" + search1 + " AND " + "Sales.SaleNumber<=" + search2; // filters rows by Sale Number
                rs = stmt.executeQuery(SQL);


            } else if (selection.equals("dateSelected")) {

                String SQL = "SELECT * FROM Sales where Sales.Date between " + searchWord1 + " AND " + searchWord2; // filters rows by selected date
                rs = stmt.executeQuery(SQL);


            } else if (selection.equals("customerName")) {

                String SQL = "SELECT * FROM Sales where Sales.Customer like " + searchWord;  // filters rows by typed Customer Name
                rs = stmt.executeQuery(SQL);
            } else if (selection.equals("productName")) {

                String SQL = "SELECT * FROM Sales where Sales.Product like " + searchWord;  // filters rows by typed Product Name
                rs = stmt.executeQuery(SQL);
            } else if (selection.equals("category")) {

                String SQL = "SELECT * FROM Sales where Sales.Category like " + searchWord;  // filters rows by typed Category
                rs = stmt.executeQuery(SQL);
            }

            while (rs.next()) {
                addSaleToTableList(new Sales(rs.getInt("SaleNumber"), simpleDateFormat.format(rs.getDate("Date")), rs.getString("Time"), rs.getString("Customer"),
                        rs.getString("Product"), rs.getString("Code"), rs.getString("Size"),
                        rs.getString("Units"), rs.getString("Category"), rs.getString("Quantity"), rs.getString("UnitPrice"), rs.getString("SalePrice")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

        public Integer retriveLastSaleNumberFromDB () throws SQLException {

            Integer lastSaleID = null;

            try (Connection con = DBConnection.getconnection(); Statement stmt = con.createStatement()) {
                String SQL = "SELECT MAX(Sales.SaleNumber) as SaleID FROM Sales";
                ResultSet rs = stmt.executeQuery(SQL);

                if (rs.next()) {
                    lastSaleID = rs.getInt("SaleID");
                }


            } catch (SQLException e) {
                e.printStackTrace();

            }

            return lastSaleID;
        }
    }

