package Controllers;

import Models.Contacts;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import static Models.ContactsDB.getContacts;

public class AddContactController {

    @FXML
    private TextField contactNameTextField;

    @FXML
    private TextField contactEmailTextField;

    /**
     * This method is called when the user clicks the save button.
     * It checkes that the fields have been filled out.
     * It saves the contact to the database.
     */
    public void saveContactClicked(ActionEvent actionEvent) throws Exception {

        String contactName = contactNameTextField.getText();
        String contactEmail = contactEmailTextField.getText();

        if(contactName.equals(" ") || contactEmail.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }

        else{
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?)");
            sqlStatement.setString(1, contactName);
            sqlStatement.setString(2, contactEmail);

            sqlStatement.executeUpdate();
            sqlStatement.close();


            Contacts.clearAllContacts();
            getContacts();

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
     * This returns the user to the main dashboard.
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
