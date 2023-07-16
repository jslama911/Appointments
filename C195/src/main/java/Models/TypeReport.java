package Models;

public class TypeReport {
        public String appointmentType;
        public int typeCount;
        /**
         * Constructor for TypeReport
         */
        public TypeReport(String appointmentType, int typeCount) {
            this.appointmentType = appointmentType;
            this.typeCount = typeCount;
        }

        public String getType() {
            return appointmentType;
        }

    public int getCount() {
        return typeCount;
    }
}
