package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class Customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private LocalDateTime customerCreateDate;
    private String customerCreatedBy;
    private LocalDateTime customerLastUpdate;
    private String customerLastUpdatedBy;
    private int divisionID;

    public Customers(int customerID,
                     String customerName,
                     String customerAddress,
                     String customerPostalCode,
                     String customerPhone,
                     LocalDateTime customerCreateDate,
                     String customerCreatedBy,
                     LocalDateTime customerLastUpdate,
                     String customerLastUpdatedBy,
                     int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerCreateDate = customerCreateDate;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
        this.divisionID = divisionID;
    }
    /**
     * returns customerID
     */
    public int getCustomerID() {

        return customerID;
    }
    /**
     * sets customerID
     */
    public void setCustomerID(int customerID) {

        this.customerID = customerID;
    }
    /**
     * returns customerName
     */
    public String getCustomerName() {

        return customerName;
    }
    /**
     * sets customerName
     */
    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }
    /**
     * returns customerAddress
     */
    public String getCustomerAddress() {

        return customerAddress;
    }
    /**
     * sets customerAddress
     */
    public void setCustomerAddress(String customerAddress) {

        this.customerAddress = customerAddress;
    }
    /**
     * returns customerPostalCode
     */
    public String getCustomerPostalCode() {

        return customerPostalCode;
    }
    /**
     * sets customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {

        this.customerPostalCode = customerPostalCode;
    }
    /**
     * returns customerPhone
     */
    public String getCustomerPhone() {

        return customerPhone;
    }
    /**
     * sets customerPhone
     */
    public void setCustomerPhone(String customerPhone) {

        this.customerPhone = customerPhone;
    }
    /**
     * sets divisionID
     */
    public void setDivisionID(int divisionID) {

        this.divisionID = divisionID;
    }
    /**
     * sets createDate
     */
    public void setCustomerCreateDate(LocalDateTime customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }
    /**
     * sets createdBy
     */
    public void setCustomerCreatedBy(String customerCreatedBy) {
    }
    /**
     * sets lastUpdate
     */
    public void setCustomerLastUpdate(LocalDateTime customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }
    /**
     * sets lastUpdatedBy
     */
public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
    }
    /**
     * returns divisionID
     */
    public int getDivisionID() {

        return divisionID;
    }
    /**
     * sets divisionID
     */
    public void setDivisionID(Integer divisionID) {

        this.divisionID = divisionID;
    }
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    /**
     * add customer to allCustomers
     */
    public static void addCustomer(Customers customer){
        allCustomers.add(customer);
    }
    /**
     * returns allCustomers
     */
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }
    /**
     * returns customerName by ID
     */
    public static int findCustomerByName(String customerName) {
        for (Customers customer : allCustomers) {
            if (customer.getCustomerName().equals(customerName)) {
                return customer.getCustomerID();
            }
        }
        return -1;
    }
    /**
     * clears allCustomers
     */
    public static void clearAllCustomers() {
        allCustomers.clear();
    }
}
