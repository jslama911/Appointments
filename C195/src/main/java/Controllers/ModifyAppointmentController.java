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

public class ModifyAppointmentController {

    @FXML
    private TextField modifyAppointmentTitleTextField;

    @FXML
    private TextField modifyAppointmentLocationTextField;

    @FXML
    private ComboBox <String> modifyAppointmentContactComboBox;

    @FXML
    private TextField modifyAppointmentTypeTextField;

    @FXML
    private TextField modifyAppointmentStartTextField;

    @FXML
    private TextField modifyAppointmentEndTextField;

    @FXML
    private TextField modifyAppointmentIDTextField;

    @FXML
    private TextArea modifyAppointmentDescriptionTextArea;

    @FXML
    private ComboBox<String> modifyAppointmentCustomerComboBox;

    @FXML
    private ComboBox<Integer> modifyAppointmentUserComboBox;

    @FXML
    private DatePicker modifyAppointmentDatePicker;

    public Appointments selectedAppointment;

    /**
     * Initializes the combo boxes with the appropriate data.
     */
    public void initialize() {


        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customers customer : Customers.getAllCustomers()) {
            customerNames.add(customer.getCustomerName());
        }
        modifyAppointmentCustomerComboBox.setItems(customerNames);

        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contacts contact : Contacts.getAllContacts()) {
            contactNames.add(contact.getContactName());
        }
        modifyAppointmentContactComboBox.setItems(contactNames);

        ObservableList<Integer> userID = FXCollections.observableArrayList();
        for (Users users : Users.getAllUsers()) {
            userID.add(users.getUserID());
        }
        modifyAppointmentUserComboBox.setItems(userID);
    }
    /**
     * Sets the selected appointment to the appointment that was selected in the main screen.
     */
    public void setAppointment (Appointments selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
        modifyAppointmentTitleTextField.setText(String.valueOf(selectedAppointment.getAppointmentTitle()));
        modifyAppointmentLocationTextField.setText(selectedAppointment.getAppointmentLocation());
        modifyAppointmentTypeTextField.setText(String.valueOf(selectedAppointment.getAppointmentType()));
        modifyAppointmentIDTextField.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        modifyAppointmentDescriptionTextArea.setText(selectedAppointment.getAppointmentDescription());

        String start = (String.valueOf(selectedAppointment.getAppointmentStart()));
        String end = (String.valueOf(selectedAppointment.getAppointmentEnd()));

        String startYear = start.substring(0,4);
        String startMonth = start.substring(5,7);
        String startDay = start.substring(8,10);
        String startHour = start.substring(11,13);
        String startMinute = start.substring(14,16);

        String endHour = end.substring(11,13);
        String endMinute = end.substring(14,16);


        modifyAppointmentStartTextField.setText(startHour + ":" + startMinute);
        modifyAppointmentEndTextField.setText(endHour + ":" + endMinute);
        modifyAppointmentDatePicker.setValue(LocalDate.of(Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay)));


    }

    /**
     * Saves the modified appointment to the database.
     * Checks the fields to make sure they are not empty.
     * Checks that the times are correct.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws Exception {

        if (modifyAppointmentEndTextField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a title");
            alert.showAndWait();
        }
        if (modifyAppointmentDescriptionTextArea.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a description");
            alert.showAndWait();
        }

        if (modifyAppointmentLocationTextField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a location");
            alert.showAndWait();
        }

        if (modifyAppointmentContactComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a contact");
            alert.showAndWait();
        }

        if (modifyAppointmentTypeTextField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a type");
            alert.showAndWait();
        }

        if (modifyAppointmentDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a date");
            alert.showAndWait();
        }

        if (modifyAppointmentStartTextField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a start time");
            alert.showAndWait();
        }

        if (modifyAppointmentEndTextField.getText() == " ") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter an end time");
            alert.showAndWait();
        }

        if (modifyAppointmentCustomerComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
        }

        if (modifyAppointmentUserComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a user");
            alert.showAndWait();
        }

        if (modifyAppointmentStartTextField.getText().length() != 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid start time");
            alert.showAndWait();
        }

        if (modifyAppointmentEndTextField.getText().length() != 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid end time");
            alert.showAndWait();
        }
        if (Integer.parseInt(modifyAppointmentStartTextField.getText(0, 2)) < 8 || Integer.parseInt(modifyAppointmentEndTextField.getText(0, 2)) > 17) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter times during business hours (8:00 - 17:00)");
            alert.showAndWait();
        } else {
            String appointmentTitle = modifyAppointmentTitleTextField.getText();
            String appointmentDescription = modifyAppointmentDescriptionTextArea.getText();
            String appointmentLocation = modifyAppointmentLocationTextField.getText();
            String appointmentContact = modifyAppointmentContactComboBox.getSelectionModel().getSelectedItem();
            String appointmentType = modifyAppointmentTypeTextField.getText();
            String appointmentStart = modifyAppointmentStartTextField.getText();
            String appointmentEnd = modifyAppointmentEndTextField.getText();
            LocalDate appointmentDate = modifyAppointmentDatePicker.getValue();
            Integer appointmentUserID = modifyAppointmentUserComboBox.getSelectionModel().getSelectedItem();

            Integer contactID = ContactsDB.findContactID(appointmentContact);
            Integer appointmentID = selectedAppointment.getAppointmentID();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            appointmentStart = appointmentDate + " " + appointmentStart + ":00";
            appointmentEnd = appointmentDate + " " + appointmentEnd + ":00";

            String selectedCustomerName = modifyAppointmentCustomerComboBox.getSelectionModel().getSelectedItem();
            int appointmentCustomerID = Customers.findCustomerByName(selectedCustomerName);


            selectedAppointment.setAppointmentTitle(appointmentTitle);
            selectedAppointment.setAppointmentDescription(appointmentDescription);
            selectedAppointment.setAppointmentLocation(appointmentLocation);
            selectedAppointment.setAppointmentContact(appointmentContact);
            selectedAppointment.setAppointmentType(appointmentType);
            selectedAppointment.setAppointmentStart(LocalDateTime.parse(appointmentStart, formatter));
            selectedAppointment.setAppointmentEnd(LocalDateTime.parse(appointmentEnd, formatter));
            String createdBy = Appointments.getCreatedBy();
            String editedBy = LoginScreenController.currentUser;
            LocalDateTime createDate = LocalDateTime.now().withSecond(0).withNano(0);
            LocalDateTime lastUpdate = LocalDateTime.now().withSecond(0).withNano(0);


            //Now there is a foreign key issue to deal with
            //Caused by: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`client_schedule`.`appointments`, CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`Contact_ID`) REFERENCES `contacts` (`Contact_ID`))

            PreparedStatement sqlCommand = JDBC.connection.prepareStatement("UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?, Create_Date = ?, Created_By = ?, Last_Updated_By = ?, Last_Update = ? WHERE Appointment_ID = ?");
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
            sqlCommand.setInt(14, appointmentID);


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
     * This method is called when the user clicks the cancel button. It returns the user to the main screen.
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
