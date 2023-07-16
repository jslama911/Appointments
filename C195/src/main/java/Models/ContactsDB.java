package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDB {
    /**
     * gets all contacts from database
     */
    public static ObservableList<Contacts> getContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM contacts;");
        ResultSet results = sqlCommand.executeQuery();

        while (results.next()) {
            int contactID = results.getInt("Contact_ID");
            String contactName = results.getString("Contact_Name");
            String contactEmail = results.getString("Email");

            Contacts contact = new Contacts(contactID, contactName, contactEmail);
            contact.setContactID(contactID);
            contact.setContactName(contactName);
            contact.setContactEmail(contactEmail);
            Contacts.addContact(contact);
        }


        sqlCommand.close();
        return allContacts;
    }
    /**
     * finds the contactID based on the contactName from the SQL database
     */
    public static Integer findContactID(String contactName) throws SQLException {
        Integer contactID = -1;
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT Contact_ID, Contact_Name " +
                "FROM contacts WHERE Contact_Name = ?");
        sqlCommand.setString(1, contactName);
        ResultSet results = sqlCommand.executeQuery();

        while (results.next()) {
            contactID = results.getInt("Contact_ID");
        }
        sqlCommand.close();
        return contactID;
    }
    /**
     * deletes the contact from the SQL database
     */
    public static void deleteContact(int contactID) throws SQLException {
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("DELETE FROM contacts WHERE contact_ID = ?");
        sqlCommand.setInt(1, contactID);
        sqlCommand.executeUpdate();

        sqlCommand.close();
    }

    }
