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
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Models.CustomersDB.getCustomers;


public class AddCustomerController {

    @FXML
    private TextField customerNameTextField;

    @FXML
    private ComboBox<String> customerCountryComboBox;

    @FXML
    private ComboBox customerDivisionComboBox;

    @FXML
    private TextField customerAddressTextField;

    @FXML
    private TextField customerPostalCodeTextField;

    @FXML
    private TextField customerPhoneNumberTextField;

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
        customerCountryComboBox.setItems(allCountries);
        sqlCommand.close();
        //Lambda Function to populate the Division ComboBox based on the Country ComboBox
        customerCountryComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                customerDivisionComboBox.getItems().clear();
                customerDivisionComboBox.setDisable(true);

            } else {
                customerDivisionComboBox.setDisable(false);
                ObservableList<String> allDivisions = FXCollections.observableArrayList();
                try {
                    PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE COUNTRY_ID = (SELECT Country_ID FROM countries WHERE Country = ?)");
                    sqlStatement.setString(1, newVal);
                    ResultSet resultSet = sqlStatement.executeQuery();
                    while (resultSet.next()) {
                        allDivisions.add(resultSet.getString("Division"));
                    }
                    customerDivisionComboBox.setItems(allDivisions);
                    sqlStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }




    /**
     * This method is called when the save button is clicked.
     * It saves the new customer to the database and creates a new customer object.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws Exception, SQLException {

        String customerName = customerNameTextField.getText();
        String customerAddress = customerAddressTextField.getText();
        String customerPostalCode = customerPostalCodeTextField.getText();
        String customerPhoneNumber = customerPhoneNumberTextField.getText();
        String customerDivision = (String) customerDivisionComboBox.getValue();
        if (customerName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out customer name.");
            alert.showAndWait();
        } else if (customerAddress.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out customer address.");
            alert.showAndWait();
        } else if (customerPostalCode.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out customer postal code.");
            alert.showAndWait();
        } else if (customerPhoneNumber.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out customer phone number.");
            alert.showAndWait();
        } else if (customerDivision == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a division.");
            alert.showAndWait();
        } else {
            PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Division_ID FROM first_level_divisions WHERE Division = ?");
            sqlCommand.setString(1, customerDivision);
            ResultSet results = sqlCommand.executeQuery();
            int selectedDivision_ID = 0;
            if (results.next()) {
                selectedDivision_ID = results.getInt("Division_ID");
            }
            sqlCommand.close();

            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sqlStatement.setString(1, customerName);
            sqlStatement.setString(2, customerAddress);
            sqlStatement.setString(3, customerPostalCode);
            sqlStatement.setString(4, customerPhoneNumber);
            sqlStatement.setObject(5, LocalDateTime.now());
            sqlStatement.setString(6, "admin");
            sqlStatement.setObject(7, LocalDateTime.now());
            sqlStatement.setString(8, "admin");
            sqlStatement.setInt(9, selectedDivision_ID);
            sqlStatement.executeUpdate();
            sqlStatement.close();


            Customers.clearAllCustomers();
            getCustomers();
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
     * This method returns the user to the main screen.
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
