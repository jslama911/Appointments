package Controllers;

import Models.*;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddAppointmentController {

    @FXML
    private TextField appointmentTitleField;

    @FXML
    private TextArea appointmentDescriptionField;

    @FXML
    private TextField appointmentLocationField;

    @FXML
    private ComboBox <String> appointmentContactCombo;

    @FXML
    private TextField appointmentTypeField;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private TextField appointmentStartField;

    @FXML
    private TextField appointmentEndField;

    @FXML
    private ComboBox <String> appointmentCustomerCombo;

    @FXML
    private ComboBox <Integer> appointmentUserCombo;

    /**
     * Initializes the controller and sets up the appointment form.
     * It populates the customer, user, and contact combo boxes with appropriate values.
     */

    public void initialize() {
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customers customer : Customers.getAllCustomers()) {
            customerNames.add(customer.getCustomerName());
        }
        appointmentCustomerCombo.setItems(customerNames);

        ObservableList<Integer> userID = FXCollections.observableArrayList();
        for (Users users : Users.getAllUsers()) {
            userID.add(users.getUserID());
        }
        appointmentUserCombo.setItems(userID);

        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contacts contact : Contacts.getAllContacts()) {
            contactNames.add(contact.getContactName());
        }
        appointmentContactCombo.setItems(contactNames);
    }


    /**
     * Makes sure the input fields are correct and shows error alerts for any missing or invalid values.
     * If all fields are valid, it creates a new appointment object, adds it to the database, and navigates to the main screen.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws Exception {
        if (appointmentTitleField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a title");
            alert.showAndWait();
        }
        if (appointmentDescriptionField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a description");
            alert.showAndWait();
        }

        if (appointmentLocationField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a location");
            alert.showAndWait();
        }

        if (appointmentContactCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a contact");
            alert.showAndWait();
        }

        if (appointmentTypeField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a type");
            alert.showAndWait();
        }

        if (appointmentDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a date");
            alert.showAndWait();
        }

        if (appointmentStartField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a start time");
            alert.showAndWait();
        }

        if (appointmentEndField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter an end time");
            alert.showAndWait();
        }

        if (appointmentCustomerCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
        }

        if (appointmentUserCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a user");
            alert.showAndWait();
        }

        if (appointmentStartField.getText().length() != 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid start time");
            alert.showAndWait();
        }

        if (appointmentEndField.getText().length() != 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid end time");
            alert.showAndWait();
        }
        if (Integer.parseInt(appointmentStartField.getText(0, 2)) < 8 || Integer.parseInt(appointmentStartField.getText(0,2)) > 17) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter times during business hours (8:00 - 17:00)");
            alert.showAndWait();
        }


             else {
                String appointmentTitle = appointmentTitleField.getText();
                String appointmentDescription = appointmentDescriptionField.getText();
                String appointmentLocation = appointmentLocationField.getText();
                String appointmentContact = appointmentContactCombo.getSelectionModel().getSelectedItem();
                String appointmentType = appointmentTypeField.getText();
                String appointmentStart = appointmentStartField.getText();
                String appointmentEnd = appointmentEndField.getText();
                LocalDate appointmentDate = appointmentDatePicker.getValue();
                Integer appointmentUserID = appointmentUserCombo.getSelectionModel().getSelectedItem();

                Integer contactID = ContactsDB.findContactID(appointmentContact);
                Integer appointmentID = Appointments.getAllAppointments().size() + 1;
                String createdBy = Appointments.getCreatedBy();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                appointmentStart = appointmentDate + " " + appointmentStart + ":00";
                appointmentEnd = appointmentDate + " " + appointmentEnd + ":00";

                String selectedCustomerName = appointmentCustomerCombo.getSelectionModel().getSelectedItem();
                int appointmentCustomerID = Customers.findCustomerByName(selectedCustomerName);


                Appointments appointments = new Appointments(
                        appointmentID,
                        appointmentTitle,
                        appointmentDescription,
                        appointmentLocation,
                        appointmentContact,
                        appointmentType,
                        LocalDateTime.parse(appointmentStart, formatter),
                        LocalDateTime.parse(appointmentEnd, formatter),
                        appointmentCustomerID,
                        appointmentUserID,
                        contactID,
                        createdBy
                );


                appointments.setAppointmentTitle(appointmentTitle);
                appointments.setAppointmentDescription(appointmentDescription);
                appointments.setAppointmentLocation(appointmentLocation);
                appointments.setAppointmentContact(appointmentContact);
                appointments.setAppointmentType(appointmentType);
                appointments.setAppointmentStart(LocalDateTime.parse(appointmentStart, formatter));
                appointments.setAppointmentEnd(LocalDateTime.parse(appointmentEnd, formatter));
                Appointments.addAppointment(appointments);
                createdBy = LoginScreenController.currentUser;
                String editedBy = LoginScreenController.currentUser;
                LocalDateTime createDate = LocalDateTime.now().withSecond(0).withNano(0);
                LocalDateTime lastUpdate = LocalDateTime.now().withSecond(0).withNano(0);


                //Now there is a foreign key issue to deal with
                //Caused by: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`client_schedule`.`appointments`, CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`Contact_ID`) REFERENCES `contacts` (`Contact_ID`))

                PreparedStatement sqlCommand = JDBC.connection.prepareStatement("INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Create_Date, Created_By, Last_Updated_By, Last_Update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                sqlCommand.setString(1, appointmentTitle);
                sqlCommand.setString(2, appointmentDescription);
                sqlCommand.setString(3, appointmentLocation);
                sqlCommand.setString(4, appointmentType);
                sqlCommand.setString(5, appointmentStart);
                sqlCommand.setString(6, appointmentEnd);
                sqlCommand.setInt(7, appointmentCustomerID);
                sqlCommand.setInt(8, appointmentUserID);
                sqlCommand.setInt(9, contactID);
                sqlCommand.setObject(10, createDate);
                sqlCommand.setString(11, createdBy);
                sqlCommand.setString(12, editedBy);
                sqlCommand.setObject(13, lastUpdate);


                sqlCommand.executeUpdate();


                sqlCommand.close();

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
     * This method is used to cancel the appointment creation and return to the main screen.
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
