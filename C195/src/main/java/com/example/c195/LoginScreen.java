package com.example.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import helper.JDBC;
import static Models.Appointments.clearAllAppointments;
import static Models.UsersDB.getAllUsers;

public class LoginScreen extends Application {

    /**
     * The LoginScreen class launches the login screen of the application.
     * This is also where the database originally opens.
     */
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        clearAllAppointments();

        JDBC.openConnection();
        String userLanguage = Locale.getDefault().getLanguage();
        System.out.println(userLanguage);


        if (userLanguage.equals("en")) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginScreen.class.getResource("/Views/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            getAllUsers();

            JDBC.closeConnection();
        }
        else if(userLanguage.equals("fr")) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginScreen.class.getResource("/Views/LoginFrench.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            getAllUsers();

            JDBC.closeConnection();

        }
    }
    /**
     * launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}