package Controllers;

import Models.Customers;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class ModifyCustomerController {

    @FXML
    private TextField modifyCustomerIDTextField;

    @FXML
    private ComboBox<String> modifyCustomerCountryComboBox;

    @FXML
    private ComboBox<String> modifyCustomerDivisionComboBox;

    @FXML
    private TextField modifyCustomerNameTextField;

    @FXML
    private TextField modifyCustomerAddressTextField;

    @FXML
    private TextField modifyCustomerPostalCodeTextField;

    @FXML
    private TextField modifyCustomerPhoneNumberTextField;

    public Customers selectedCustomer;

    private Stage stage;

    /**
     * This initializes the Add Customer Controller.
     * It populates the Country ComboBox with all countries in the database.
     */
    public void initialize() throws Exception {


        ObservableList<String> allCountries = FXCollections.observableArrayList();
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Country FROM countries");
        ResultSet results = sqlCommand.executeQuery();

        while (results.next()) {
            allCountries.add(results.getString("Country"));
        }
        modifyCustomerCountryComboBox.setItems(allCountries);
        sqlCommand.close();
        //Lambda Function to populate the Division ComboBox based on the Country ComboBox
        modifyCustomerCountryComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                modifyCustomerDivisionComboBox.getItems().clear();
                modifyCustomerDivisionComboBox.setDisable(true);

            } else {
                modifyCustomerDivisionComboBox.setDisable(false);
                ObservableList<String> allDivisions = FXCollections.observableArrayList();
                try {
                    PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE COUNTRY_ID = (SELECT Country_ID FROM countries WHERE Country = ?)");
                    sqlStatement.setString(1, newVal);
                    ResultSet resultSet = sqlStatement.executeQuery();
                    while (resultSet.next()) {
                        allDivisions.add(resultSet.getString("Division"));
                    }
                    modifyCustomerDivisionComboBox.setItems(allDivisions);
                    sqlStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void setCustomer(Customers selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        modifyCustomerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        modifyCustomerNameTextField.setText(selectedCustomer.getCustomerName());
        modifyCustomerAddressTextField.setText(selectedCustomer.getCustomerAddress());
        modifyCustomerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
        modifyCustomerPhoneNumberTextField.setText(selectedCustomer.getCustomerPhone());

    }
    /**
     * This updates the database and changes the customer information based on customerID.
     * It checks that the fields have been filled out.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws Exception {
        String customerName = modifyCustomerNameTextField.getText();
        String customerAddress = modifyCustomerAddressTextField.getText();
        String customerPostalCode = modifyCustomerPostalCodeTextField.getText();
        String customerPhone = modifyCustomerPhoneNumberTextField.getText();
        String customerDivision = modifyCustomerDivisionComboBox.getValue();
        int customerID = selectedCustomer.getCustomerID();

        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Division_ID FROM first_level_divisions WHERE Division = ?");
        sqlCommand.setString(1, customerDivision);
        ResultSet results = sqlCommand.executeQuery();
        int selectedDivision_ID = 0;
        if (results.next()) {
            selectedDivision_ID = results.getInt("Division_ID");
        }
        sqlCommand.close();


        selectedCustomer.setCustomerName(customerName);
        selectedCustomer.setCustomerAddress(customerAddress);
        selectedCustomer.setCustomerPostalCode(customerPostalCode);
        selectedCustomer.setCustomerPhone(customerPhone);
        selectedCustomer.setDivisionID(selectedDivision_ID);

        if (customerName.isEmpty() || customerAddress.isEmpty() || customerPostalCode.isEmpty() || customerPhone.isEmpty() || modifyCustomerDivisionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
            return;
        } else {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement(" UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?");
            sqlStatement.setString(1, customerName);
            sqlStatement.setString(2, customerAddress);
            sqlStatement.setString(3, customerPostalCode);
            sqlStatement.setString(4, customerPhone);
            sqlStatement.setObject(5, LocalDateTime.now());
            sqlStatement.setString(6, "admin");
            sqlStatement.setInt(7, selectedDivision_ID);
            sqlStatement.setInt(8, customerID);
            sqlStatement.executeUpdate();
            sqlStatement.close();


            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/main.fxml"));
            Parent root = loader.load();
            loader.getController();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * This returns the user to the main screen.
     */
    public void exitButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/main.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
