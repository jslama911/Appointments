package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentContact;
    private String appointmentType;
    private LocalDateTime start;
    private LocalDateTime end;
    private static String createdBy;
    public int customerID;
    public int userID;
    public int contactID;

    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription,
                        String appointmentLocation, String appointmentContact, String appointmentType, LocalDateTime start, LocalDateTime end, int customerID,
                        int userID, int contactID, String createdBy) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentContact = appointmentContact;
        this.appointmentType = appointmentType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.createdBy = createdBy;
    }
    /**
     * returns appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * return appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * sets appointmentTitle
     */
    public void setAppointmentTitle(String appointmentTitle) {

        this.appointmentTitle = appointmentTitle;
    }

    /**
     * returns appointmentDescription
     */
    public String getAppointmentDescription() {

        return appointmentDescription;
    }

    /**
     * sets appointmentDescription
     */
    public void setAppointmentDescription(String appointmentDescription) {

        this.appointmentDescription = appointmentDescription;
    }

    /**
     * returns appointmentLocation
     */
    public String getAppointmentLocation() {

        return appointmentLocation;
    }

    /**
     * sets appointmentLocation
     */
    public void setAppointmentLocation(String appointmentLocation) {

        this.appointmentLocation = appointmentLocation;
    }

    /**
     * returns appointmentType
     */
    public String getAppointmentType() {

        return appointmentType;
    }

    /**
     * sets appointmentContact
     */
    public void setAppointmentContact(String appointmentContact) {

        this.appointmentContact = appointmentContact;
    }

    /**
     * sets appointmentType
     */
    public void setAppointmentType(String appointmentType) {

        this.appointmentType = appointmentType;
    }


    /**
     * returns start time
     */
    public LocalDateTime getAppointmentStart() {

        return start;
    }

    /**
     * sets start time
     */
    public void setAppointmentStart(LocalDateTime start) {

        this.start = start;
    }

    /**
     * returns end time
     */
    public LocalDateTime getAppointmentEnd() {

        return end;
    }

    /**
     * sets end time
     */
    public void setAppointmentEnd(LocalDateTime end) {

        this.end = end;
    }

    /**
     * returns createdBy
     */
    public static String getCreatedBy() {

        return createdBy;
    }

    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * adds an appointment to allAppointments
     */
    public static void addAppointment(Appointments appointment) {
        allAppointments.add(appointment);
    }

    /**
     * returns allAppointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;

    }

    /**
     * deletes an appointment from allAppointments
     */
    public static void deleteAppointment(Appointments appointment) {
        allAppointments.remove(appointment);
    }

    /**
     * clears allAppointments
     */
    public static void clearAllAppointments() {
        allAppointments.clear();
    }


        public int getAppointmentCustomerID () {

            return customerID;
        }
        public void setAppointmentCustomerID(int customerID) {

            this.customerID = customerID;
        }


        public int getAppointmentUserID() {

            return userID;
        }
        public void setAppointmentUserID(int userID) {

            this.userID = userID;
        }


        public int getAppointmentContactID() {

            return contactID;
        }
        public void setAppointmentContactID(int contactID) {

            this.contactID = contactID;
        }
        public String getAppointmentContact() {

            return appointmentContact;
        }
    }


