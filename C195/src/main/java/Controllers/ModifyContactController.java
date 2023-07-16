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
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyContactController {

    @FXML
    private TextField modifyContactIDTextField;

    @FXML
    private TextField modifyContactNameTextField;

    @FXML
    private TextField modifyContactEmailTextField;

    public Contacts selectedContact;
    /**
     * This method sets the selected contact to the contact that was selected in the main screen.
     */
    public void setContact(Contacts selectedContact) {
        this.selectedContact = selectedContact;
        modifyContactIDTextField.setText(String.valueOf(selectedContact.getContactID()));
        modifyContactNameTextField.setText(selectedContact.getContactName());
        modifyContactEmailTextField.setText(selectedContact.getContactEmail());

    }

    /**
     * This method updates the contact in the database with the new information.
     * If the user does not fill out all fields, they are shown an error message.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws SQLException, IOException {

        String contactName = modifyContactNameTextField.getText();
        String contactEmail = modifyContactEmailTextField.getText();

        if (contactName.equals(" ") || contactEmail.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }
        else {
            selectedContact.setContactName(contactName);
            selectedContact.setContactEmail(contactEmail);

            PreparedStatement sqlCommand = JDBC.connection.prepareStatement("UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?");
            sqlCommand.setString(1, contactName);
            sqlCommand.setString(2, contactEmail);
            sqlCommand.setInt(3, selectedContact.getContactID());
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
