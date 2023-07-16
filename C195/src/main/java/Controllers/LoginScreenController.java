package Controllers;

import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.Users;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static Models.AppointmentsDB.getAppointments;
import static Models.ContactsDB.getContacts;
import static Models.CustomersDB.getCustomers;

public class LoginScreenController {
    public static String currentUser;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private Label locationLabel;
    /**
     * This initializes the Login Screen Controller.
     * It populates the location label with the user's location.
     */
    public void initialize() {
        TimeZone timeZone = TimeZone.getDefault();
        String location = String.valueOf(timeZone.getID());
        locationLabel.setText(location);
    }
    /**
     * This method is called when the user clicks the Login button.
     * It checks the username and password against the database.
     * If the username and password match, the user is taken to the main screen.
     * If the username and password do not match, the user is shown an error message.
     */
    public void LoginButton(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

        Users matchedUser = null;
        for (Users user : Users.getAllUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                matchedUser = user;
                break;
            }
        }

        if (matchedUser != null) {
            JDBC.openConnection();
            getAppointments();
            getCustomers();
            getContacts();
            currentUser = matchedUser.getUsername();
            outputFile.print("user: " + username + " successful login at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
            outputFile.flush();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/main.fxml"));
            Parent root = loader.load();
            loader.getController();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect username or password");
            alert.showAndWait();
            outputFile.print("user: " + username + " failed login at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
            outputFile.flush();
        }
    }

}
