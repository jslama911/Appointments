package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomersDB {
    /**
     * gets all the customers from the Customer Database
     */
    public static ObservableList<Customers> getCustomers() throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM customers;");
        ResultSet results = sqlCommand.executeQuery();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (results.next()) {
            int customerID = results.getInt("Customer_ID");
            String customerName = results.getString("Customer_Name");
            String customerAddress = results.getString("Address");
            String customerPostalCode = results.getString("Postal_Code");
            String customerPhone = results.getString("Phone");
            String customerCreateDate = results.getString("Create_Date");
            String customerCreatedBy = results.getString("Created_By");
            String customerLastUpdate = results.getString("Last_Update");
            String customerLastUpdatedBy = results.getString("Last_Updated_By");
            int divisionID = results.getInt("Division_ID");


            Customers customer = new Customers(customerID,
                    customerName,
                    customerAddress,
                    customerPostalCode,
                    customerPhone,
                    LocalDateTime.parse(customerCreateDate, formatter),
                    customerCreatedBy,
                    LocalDateTime.parse(customerLastUpdate, formatter),
                    customerLastUpdatedBy,
                    divisionID);
            customer.setCustomerID(customerID);
            customer.setCustomerName(customerName);
            customer.setCustomerAddress(customerAddress);
            customer.setCustomerPostalCode(customerPostalCode);
            customer.setCustomerPhone(customerPhone);
            customer.setCustomerCreateDate(LocalDateTime.parse(customerCreateDate, formatter));
            customer.setCustomerCreatedBy(customerCreatedBy);
            customer.setCustomerLastUpdate(LocalDateTime.parse(customerLastUpdate, formatter));
            customer.setCustomerLastUpdatedBy(customerLastUpdatedBy);
            customer.setDivisionID(divisionID);
            Customers.addCustomer(customer);
        }

        sqlCommand.close();
        return allCustomers;
    }
    /**
     * Deletes the customer from the database
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("DELETE FROM customers WHERE customer_ID = ?");
        sqlCommand.setInt(1, customerID);
        sqlCommand.executeUpdate();

        sqlCommand.close();
    }
}
