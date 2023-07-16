package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDB {
    /**
     * gets all users from database
     */
    public static ObservableList<String> getAllUsers() throws SQLException {
        ObservableList<String> allUsers = FXCollections.observableArrayList();
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM users;");
        ResultSet results = sqlCommand.executeQuery();

        while (results.next()) {
            Integer userID = results.getInt("User_ID");
            String username = results.getString("User_Name");
            String password = results.getString("Password");

            Users user = new Users(userID, username, password);
            user.setUserID(userID);
            user.setUsername(username);
            user.setPassword(password);
            Users.addUser(user);
        }

        sqlCommand.close();
        return allUsers;
    }
}
