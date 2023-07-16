package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Users {

    private Integer userID;
    private String username;
    private String password;
    /**
     * Constructor for Users
     */
    public Users(Integer userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * returns username
     */
    public String getUsername() {
        return username;
    }
    /**
     * sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * returns password
     */
    public String getPassword(){
        return password;
    }

    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    /**
     * adds user to allUsers
     */
    public static void addUser(Users user){
        allUsers.add(user);
    }
    /**
     * returns allUsers
     */
    public static ObservableList<Users> getAllUsers() {
        return allUsers;
    }

    /**
     * sets userID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    /**
     * returns userID
     */
    public Integer getUserID() {
        return userID;
    }
}
