package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts {

    private int contactID;
    private String contactName;
    private String contactEmail;

    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /**
     * returns contactID
     */
    public int getContactID() {

        return contactID;
    }
    /**
     * sets contactID
     */
    public void setContactID(int contactID) {

        this.contactID = contactID;
    }
    /**
     * returns contactName
     */
    public String getContactName() {

        return contactName;
    }
    /**
     * sets contactName
     */
    public void setContactName(String contactName) {

        this.contactName = contactName;
    }
    /**
     * returns contactEmail
     */
    public String getContactEmail() {

        return contactEmail;
    }
    /**
     * sets contactEmail
     */
    public void setContactEmail(String contactEmail) {

        this.contactEmail = contactEmail;
    }

    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    /**
     * adds the contact to allContacts
     */
    public static void addContact(Contacts contact){
        allContacts.add(contact);
    }
    /**
     * returns allContacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        return allContacts;
    }
    /**
     * clears allContacts
     */
    public static void clearAllContacts() {
        allContacts.clear();
    }
}
