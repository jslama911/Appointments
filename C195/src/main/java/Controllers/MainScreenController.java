package Controllers;

import Models.*;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
    @FXML
    private TableView<Appointments> appointmentsTable;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDColumn;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleColumn;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionColumn;

    @FXML
    private TableColumn<Appointments, String> appointmentLocationColumn;

    @FXML
    private TableColumn<Appointments, String> appointmentContactColumn;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appointmentStartColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appointmentEndColumn;

    @FXML
    private TableColumn<Appointments, Integer> appointmentCustomerIDColumn;

    @FXML
    private TableColumn<Appointments, Integer> appointmentUserIDColumn;

    @FXML
    private TableColumn<Appointments, Integer> appointmentContactIDColumn;

    @FXML
    private TableView<Customers> customersTable;

    @FXML
    private TableColumn<Customers, Integer> customerIDColumn;

    @FXML
    private TableColumn<Customers, String> customerNameColumn;

    @FXML
    private TableColumn<Customers, String> customerAddressColumn;

    @FXML
    private TableColumn<Customers, String> customerPostalCodeColumn;

    @FXML
    private TableColumn<Customers, String> customerPhoneColumn;

    @FXML
    private TableColumn<Customers, Integer> customerDivisionIDColumn;

    @FXML
    private TableView<Contacts> contactsTable;

    @FXML
    private TableColumn<Contacts, Integer> contactIDColumn;

    @FXML
    private TableColumn<Contacts, String> contactNameColumn;

    @FXML
    private TableColumn<Contacts, String> contactEmailColumn;

    @FXML
    private ComboBox <String> customerByTypeComboBox;

    @FXML
    private ComboBox <String> customerByMonthComboBox;

    @FXML
    private ComboBox <String> customerScheduleComboBox;

    @FXML
    private ComboBox <Integer> userScheduleComboBox;

    @FXML
    private TableView <TypeReport> typeTotalTable;

    @FXML
    private TableColumn <TypeReport, String> typeTotalTypeColumn;

    @FXML
    private TableColumn <TypeReport, Integer> typeTotalTotalColumn;

    @FXML
    private TableView <MonthReport> monthTotalTable;

    @FXML
    private TableColumn <MonthReport, String> monthTotalMonthColumn;

    @FXML
    private TableColumn <MonthReport, Integer> monthTotalTotalColumn;

    @FXML
    private TableView <Appointments> customerScheduleTable;

    @FXML
    private TableColumn <Appointments, Integer> customerScheduleIDColumn;

    @FXML
    private TableColumn <Appointments, String> customerScheduleTitleColumn;

    @FXML
    private TableColumn <Appointments, String> customerScheduleDescriptionColumn;

    @FXML
    private TableColumn <Appointments, String> customerScheduleLocationColumn;

    @FXML
    private TableColumn <Appointments, String> customerScheduleContactColumn;

    @FXML
    private TableColumn <Appointments, String> customerScheduleTypeColumn;

    @FXML
    private TableColumn <Appointments, LocalDateTime> customerScheduleStartColumn;

    @FXML
    private TableColumn <Appointments, LocalDateTime> customerScheduleEndColumn;

    @FXML
    private TableColumn <Appointments, Integer> customerScheduleCustomerIDColumn;

    @FXML
    private TableColumn <Appointments, Integer> customerScheduleUserIDColumn;

    @FXML
    private TableColumn <Appointments, Integer> customerScheduleContactIDColumn;

    @FXML
    private TableView <Appointments> userScheduleTable;

    @FXML
    private TableColumn <Appointments, Integer> userScheduleIDColumn;

    @FXML
    private TableColumn <Appointments, String> userScheduleTitleColumn;

    @FXML
    private TableColumn <Appointments, String> userScheduleDescriptionColumn;

    @FXML
    private TableColumn <Appointments, String> userScheduleLocationColumn;

    @FXML
    private TableColumn <Appointments, String> userScheduleContactColumn;

    @FXML
    private TableColumn <Appointments, String> userScheduleTypeColumn;

    @FXML
    private TableColumn <Appointments, LocalDateTime> userScheduleStartColumn;

    @FXML
    private TableColumn <Appointments, LocalDateTime> userScheduleEndColumn;

    @FXML
    private TableColumn <Appointments, Integer> userScheduleCustomerIDColumn;

    @FXML
    private TableColumn <Appointments, Integer> userScheduleUserIDColumn;

    @FXML
    private TableColumn <Appointments, Integer> userScheduleContactIDColumn;

    /**
     * This method is used to initialize the main screen controller.
     * It sets the itmes in the tables and the combo boxes.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointments> allAppointments = null;
        try {
            allAppointments = AppointmentsDB.getAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Appointments> initialAppointments = FXCollections.observableArrayList();
        if (allAppointments != null) {
            initialAppointments.addAll(allAppointments);
        }
        appointmentsTable.setItems(initialAppointments);
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
        appointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
        appointmentContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContactID"));

        customersTable.getItems().clear();
        customersTable.setItems(Customers.getAllCustomers());
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        contactsTable.setItems(Contacts.getAllContacts());
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactEmailColumn.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));

        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customers customer : Customers.getAllCustomers()) {
            customerNames.add(customer.getCustomerName());
        }
        customerScheduleComboBox.setItems(customerNames);
        customerByTypeComboBox.setItems(customerNames);
        customerByMonthComboBox.setItems(customerNames);

        ObservableList<Integer> userID = FXCollections.observableArrayList();
        for (Users users : Users.getAllUsers()) {
            userID.add(users.getUserID());
        }
        userScheduleComboBox.setItems(userID);

        /**
         * This lambda is used to add an event listener to the customerByTypeComboBox so that it can be used with the function to update the table.
         */
        customerByTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> appointmentsByType());
        /**
         * This lambda is used to add an event listener to the customerByMonthComboBox so that it can be used with the function to update the table.
         */
        customerByMonthComboBox.valueProperty().addListener((obs, oldVal, newVal) -> appointmentsByMonth());
        /**
         * This lambda is used to add an event listener to the customerScheduleComboBox so that it can be used with the function to update the table.
         */
        customerScheduleComboBox.valueProperty().addListener((obs, oldVal, newVal) -> appointmentsByCustomer());
        /**
         * This lambda is used to add an event listener to the userScheduleComboBox so that it can be used with the function to update the table.
         */
        userScheduleComboBox.valueProperty().addListener((obs, oldVal, newVal) -> appointmentsByUser());

    }

    /**
     * This method is used to open the screen to add an appointment.
     * It first checks to see if an appointment is selected.
     */
    public void addAppointment(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddAppointment.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(root));
        stage.show();

    }
    /**
     * This method is used to open the screen to add a customer.
     * It first checks to see if a customer is selected.
     */
    public void addCustomer(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddCustomer.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();

    }
    /**
     * This method is used to open the scree to add a contact.
     * It first checks to see if a contact is selected.
     */
    public void addContact(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddContact.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setTitle("Add Contact");
        stage.setScene(new Scene(root));
        stage.show();

    }
    /**
     * This method is used to open the screen to edit an appointment.
     * It first checks to see if an appointment is selected.
     */
    public void modifyAppointment(ActionEvent actionEvent) throws Exception {
        Appointments selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if(selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an appointment to modify.");
            alert.showAndWait();
            return;
        }
        else {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifyAppointment.fxml"));
            Parent root = loader.load();
            ModifyAppointmentController modifyAppointmentController = loader.getController();
            modifyAppointmentController.setAppointment(selectedAppointment);
            stage.setTitle("Modify Appointment");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    /**
     * This method is used to open the screen to edit a customer.
     * It first checks to see if a customer is selected.
     */
    public void modifyCustomer(ActionEvent actionEvent) throws Exception {
        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a customer to modify.");
            alert.showAndWait();
            return;
        }
        else {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifyCustomer.fxml"));
            Parent root = loader.load();
            ModifyCustomerController modifyCustomerController = loader.getController();
            modifyCustomerController.setCustomer(selectedCustomer);
            stage.setTitle("Modify Customer");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    /**
     * This method is used to open the screen to modify a contact.
     * It first checks to see if a contact is selected.
     */
    public void modifyContact(ActionEvent actionEvent) throws Exception {
        Contacts selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a contact to modify.");
            alert.showAndWait();
            return;
        }
        else {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifyContact.fxml"));
            Parent root = loader.load();
            ModifyContactController modifyContactController = loader.getController();
            modifyContactController.setContact(selectedContact);
            stage.setTitle("Modify Contact");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    /**
     * This method is used to delete an appointment.
     * It makes sure an appointment is selected.
     */
    public void deleteAppointment() throws SQLException {
        Appointments selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No appointment selected");
            alert.setContentText("Please select a appointment to delete");
            alert.showAndWait();
        } else {
            if (confirmationPopup("Delete", "Are you sure you want to delete this appointment?")) {
                AppointmentsDB.deleteAppointment(selectedAppointment.getAppointmentID());
                appointmentsTable.getItems().remove(selectedAppointment);
                appointmentsTable.refresh();
            }
        }
    }
    /**
     * This method is used to delete a customer.
     * It makes sure a customer is selected.
     */
    public void deleteCustomer() throws SQLException {

        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No customer selected");
            alert.setContentText("Please select a customer to delete");
            alert.showAndWait();
        } else {
            if (confirmationPopup("Delete", "Are you sure you want to delete this part?")) {
                CustomersDB.deleteCustomer(selectedCustomer.getCustomerID());
                customersTable.getItems().remove(selectedCustomer);
                customersTable.refresh();
            }
        }
    }
    /**
     * This method is used to delete a contact.
     * It checks to make sure a contact is selected.
     */
    public void deleteContact() throws SQLException {
        Contacts selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No contact selected");
            alert.setContentText("Please select a contact to delete");
            alert.showAndWait();
        } else {
            if (confirmationPopup("Delete", "Are you sure you want to delete this part?")) {
                ContactsDB.deleteContact(selectedContact.getContactID());
                contactsTable.getItems().remove(selectedContact);
                contactsTable.refresh();
            }
        }
    }

    /**
     * This method is used to exit the program.
     */
    public void exitProgram() {
        System.exit(0);
    }
    /**
     * This is the popup that comes up to confirm the user wants to do an action.
     */
    static boolean confirmationPopup(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("You Sure About That?");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * This shows all appointments when a user clicks a radio button.
     */
    @FXML
    void appointmentsAllTime(){
        try {
            ObservableList<Appointments> allAppointments = AppointmentsDB.getAppointments();

            appointmentsTable.getItems().clear();
            appointmentsTable.setItems(allAppointments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This shows appointments for the week when a user clicks a radio button.
     */
    @FXML
    void appointmentsThisWeek(){
        try {
            ObservableList<Appointments> allAppointments = AppointmentsDB.getAppointments();
            ObservableList<Appointments> appointmentsWeek = FXCollections.observableArrayList();

            LocalDateTime currentWeekStart = LocalDateTime.now().minusWeeks(1);
            LocalDateTime currentWeekEnd = LocalDateTime.now().plusWeeks(1);

            appointmentsTable.getItems().clear();

            allAppointments.forEach(appointment -> {
                LocalDateTime x = appointment.getAppointmentStart();
                LocalDateTime y = appointment.getAppointmentEnd();
                if (x.isAfter(currentWeekStart) && y.isBefore(currentWeekEnd)) {
                    appointmentsWeek.add(appointment);
                }
            });

            appointmentsTable.setItems(appointmentsWeek);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This shows appointments for the month when a user clicks a radio button.
     */
    @FXML
    void appointmentsThisMonth()  {
        try {
            ObservableList<Appointments> allAppointments = AppointmentsDB.getAppointments();
            ObservableList<Appointments> appointmentsMonth = FXCollections.observableArrayList();

            LocalDateTime currentMonthStart = LocalDateTime.now().minusMonths(1);
            LocalDateTime currentMonthEnd = LocalDateTime.now().plusMonths(1);

            appointmentsTable.getItems().clear();

            allAppointments.forEach(appointment -> {
                LocalDateTime x = appointment.getAppointmentStart();
                LocalDateTime y = appointment.getAppointmentEnd();
                if (x.isAfter(currentMonthStart) && y.isBefore(currentMonthEnd)) {
                    appointmentsMonth.add(appointment);
                }
            });

            appointmentsTable.setItems(appointmentsMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to show the amount of appointments of each type for a selected customer.
     */
    void appointmentsByType() {
        ObservableList<TypeReport> typeTotal = FXCollections.observableArrayList();
        String selectedCustomer = customerByTypeComboBox.getValue();
        int selectedCustomerID = Customers.findCustomerByName(selectedCustomer);

            if (selectedCustomer != null) {
                try {

                    PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Type, COUNT(*) as count FROM appointments WHERE Customer_ID = ? GROUP BY Type");
                    sqlCommand.setInt(1, selectedCustomerID);
                    ResultSet results = sqlCommand.executeQuery();

                    while (results.next()) {
                        String type = results.getString("Type");
                        int count = results.getInt("count");
                        TypeReport report = new TypeReport(type, count);
                        typeTotal.add(report);
                    }



                    typeTotalTable.setItems(typeTotal);
                    typeTotalTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                    typeTotalTotalColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    /**
     * This method is used to show the amount of appointments of each month for a selected customer.
     */
    void appointmentsByMonth() {
        ObservableList<MonthReport> typeTotal = FXCollections.observableArrayList();
        String selectedCustomer = customerByMonthComboBox.getValue();
        int selectedCustomerID = Customers.findCustomerByName(selectedCustomer);

        if (selectedCustomer != null) {
            try {
                PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Start, COUNT(*) as count FROM appointments WHERE Customer_ID = ? GROUP BY Start");
                sqlCommand.setInt(1, selectedCustomerID);
                ResultSet results = sqlCommand.executeQuery();

                while (results.next()) {
                    LocalDateTime startDateTime = results.getObject("Start", LocalDateTime.class);
                    String month = startDateTime.format(DateTimeFormatter.ofPattern("MM"));

                    if (month.equals("01")) {
                        month = "January";
                    } else if (month.equals("02")) {
                        month = "February";
                    } else if (month.equals("03")) {
                        month = "March";
                    } else if (month.equals("04")) {
                        month = "April";
                    } else if (month.equals("05")) {
                        month = "May";
                    } else if (month.equals("06")) {
                        month = "June";
                    } else if (month.equals("07")) {
                        month = "July";
                    } else if (month.equals("08")) {
                        month = "August";
                    } else if (month.equals("09")) {
                        month = "September";
                    } else if (month.equals("10")) {
                        month = "October";
                    } else if (month.equals("11")) {
                        month = "November";
                    } else if (month.equals("12")) {
                        month = "December";
                    } else {
                        month = "Error";}

                    int count = results.getInt("count");
                    MonthReport report = new MonthReport(month, count);
                    typeTotal.add(report);
                }

                monthTotalTable.setItems(typeTotal);
                monthTotalMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
                monthTotalTotalColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    /**
     * This method is used to show the appointments sorted by start time for a selected customer.
     */
    void appointmentsByCustomer() {
        ObservableList<Appointments> appointmentsByCustomer = FXCollections.observableArrayList();
        String selectedCustomer = customerScheduleComboBox.getValue();
        int selectedCustomerID = Customers.findCustomerByName(selectedCustomer);

        if (selectedCustomer != null) {
            try {
                PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM appointments as a LEFT OUTER JOIN contacts as c ON a.Contact_ID = c.Contact_ID WHERE Customer_ID = ? ORDER BY Start");
                sqlCommand.setInt(1, selectedCustomerID);
                ResultSet results = sqlCommand.executeQuery();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                while (results.next()) {
                    String appointmentID = results.getString("Appointment_ID");
                    String appointmentTitle = results.getString("Title");
                    String appointmentDescription = results.getString("Description");
                    String appointmentLocation = results.getString("Location");
                    String appointmentContact = results.getString("Contact_Name");
                    String appointmentType = results.getString("Type");
                    String appointmentStart = results.getString("Start");
                    String appointmentEnd = results.getString("End");
                    String appointmentCustomerID = results.getString("Customer_ID");
                    String appointmentUserID = results.getString("User_ID");
                    String appointmentContactID = results.getString("Contact_ID");
                    String createdBy = results.getString("Created_By");

                    Appointments appointments = new Appointments(
                            Integer.parseInt(appointmentID),
                            appointmentTitle,
                            appointmentDescription,
                            appointmentLocation,
                            appointmentContact,
                            appointmentType,
                            LocalDateTime.parse(appointmentStart, formatter),
                            LocalDateTime.parse(appointmentEnd, formatter),
                            Integer.parseInt(appointmentCustomerID),
                            Integer.parseInt(appointmentUserID),
                            Integer.parseInt(appointmentContactID),
                            createdBy


                    );

                    appointmentsByCustomer.add(appointments);

                    customerScheduleTable.setItems(appointmentsByCustomer);
                    customerScheduleIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    customerScheduleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                    customerScheduleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
                    customerScheduleLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
                    customerScheduleContactColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
                    customerScheduleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
                    customerScheduleStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
                    customerScheduleEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
                    customerScheduleCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
                    customerScheduleUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
                    customerScheduleContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContactID"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This method is used to show the appointments sorted by start time for a selected user.
     * It can be used as a user schedule.
     * It is the added report that I used.
     */
    void appointmentsByUser() {
        ObservableList<Appointments> appointmentsByUser = FXCollections.observableArrayList();
        Integer selectedUser = userScheduleComboBox.getValue();

        if (selectedUser != null) {
            try {
                PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM appointments as a LEFT OUTER JOIN contacts as c ON a.Contact_ID = c.Contact_ID WHERE User_ID = ? ORDER BY Start");
                sqlCommand.setInt(1, selectedUser);
                ResultSet results = sqlCommand.executeQuery();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                while (results.next()) {
                    String appointmentID = results.getString("Appointment_ID");
                    String appointmentTitle = results.getString("Title");
                    String appointmentDescription = results.getString("Description");
                    String appointmentLocation = results.getString("Location");
                    String appointmentContact = results.getString("Contact_Name");
                    String appointmentType = results.getString("Type");
                    String appointmentStart = results.getString("Start");
                    String appointmentEnd = results.getString("End");
                    String appointmentCustomerID = results.getString("Customer_ID");
                    String appointmentUserID = results.getString("User_ID");
                    String appointmentContactID = results.getString("Contact_ID");
                    String createdBy = results.getString("Created_By");

                    Appointments appointments = new Appointments(
                            Integer.parseInt(appointmentID),
                            appointmentTitle,
                            appointmentDescription,
                            appointmentLocation,
                            appointmentContact,
                            appointmentType,
                            LocalDateTime.parse(appointmentStart, formatter),
                            LocalDateTime.parse(appointmentEnd, formatter),
                            Integer.parseInt(appointmentCustomerID),
                            Integer.parseInt(appointmentUserID),
                            Integer.parseInt(appointmentContactID),
                            createdBy


                    );

                    appointmentsByUser.add(appointments);

                    userScheduleTable.setItems(appointmentsByUser);
                    userScheduleIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    userScheduleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                    userScheduleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
                    userScheduleLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
                    userScheduleContactColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
                    userScheduleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
                    userScheduleStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
                    userScheduleEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
                    userScheduleCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
                    userScheduleUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
                    userScheduleContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContactID"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
