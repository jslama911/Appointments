package Models;

public class MonthReport {
    public String appointmentMonth;
    public int monthCount;
    /**
     * Constructor for MonthReport
     */
    public MonthReport(String appointmentMonth, int monthCount) {
        this.appointmentMonth = appointmentMonth;
        this.monthCount = monthCount;
    }

    public String getMonth() {
        return appointmentMonth;
    }

    public int getCount() {
        return monthCount;
    }
}
