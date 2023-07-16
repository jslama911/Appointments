package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentsDB {
    /**
     * This method returns all appointments in the database and uses a join to get the contact name.
     */
    public static ObservableList<Appointments> getAppointments() throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("SELECT * FROM appointments as a LEFT OUTER JOIN contacts as c ON a.Contact_ID = c.Contact_ID");
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

            allAppointments.add(appointments);
        }

        sqlCommand.close();
        return allAppointments;
    }

    public static void deleteAppointment(int appointmentID) throws SQLException {
        PreparedStatement sqlCommand = JDBC.connection.prepareStatement("DELETE FROM appointments WHERE Appointment_ID = ?");
        sqlCommand.setInt(1, appointmentID);
        sqlCommand.executeUpdate();

        sqlCommand.close();
    }
}
