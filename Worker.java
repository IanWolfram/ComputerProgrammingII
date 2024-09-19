public class Worker extends Person {

    double hourlyPayRate;


    /**
     * @param firstName
     * @param lastName
     * @param ID
     * @param title
     * @param yob
     */
    public Worker(String firstName, String lastName, String ID, String title, int yob, double hourlyPayRate) {
        super(firstName, lastName, ID, title, yob);
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     *
     * @param hoursWorked
     * @return
     */
    double calculateWeeklyPay(double hoursWorked) {
        double pay;
        if (hoursWorked <= 40) {
            pay = hoursWorked * hourlyPayRate;
        } else {
            double regularHours = 40;
            double overtimeHours = hoursWorked - regularHours;
            double regularPay = regularHours * hourlyPayRate;
            double overtimePay = overtimeHours * hourlyPayRate * 1.5;
            pay = regularPay + overtimePay;
        }
        return pay;
    }


    /**
     *
     * @param hoursWorked
     * @return
     */
    public String displayWeeklyPay(double hoursWorked) {
        double regHours, otHours, regPay, otPay = 0, total;
        String retString;

        // Calculate overtime hours
        if (hoursWorked <= 40) {
            otHours = 0;
            regHours = hoursWorked;
        } else {
            otHours = hoursWorked - 40;
            regHours = 40;
        }

        regPay = regHours * hourlyPayRate;

        otPay = otHours * hourlyPayRate * 1.5;

        total = regPay + otPay;

        retString = "Hours of regular pay: " + regHours + "\n"
                + "Regular earnings: " + regPay + "\n"
                + "Hours of overtime pay: " + otHours + "\n"
                + "Overtime earnings: " + otPay + "\n"
                + "Total earnings: " + total + "\n";
        return retString;
    }


    /**
     *
     * @return
     */
    public String toCSV() {
        String retString = super.toCSV();
        // Format hourlyPayRate to remove decimal if it's a whole number
        if (hourlyPayRate % 1 == 0) {
            retString += "," + (int) hourlyPayRate;
        } else {
            retString += "," + hourlyPayRate;
        }
        return retString;
    }

    /**
     *
     * @return
     */
    public String toXML()
    {
        String retString = "";
        final String INDENT = "  ";
        final String NEWLINE = "\n";


        retString += super.toXML();
        retString = retString.substring(0, retString.length()-9); // removing </Person>

        retString += INDENT + "<hourlyPayRate>" + (int)hourlyPayRate + "</hourlyPayRate>" + NEWLINE;
        retString += "</Person>";
        return retString;

    }

    /**
     *
     * @return
     */
    public String toJSON() {
        String retString = super.toJSON();
        retString = retString.substring(0, retString.length() - 1); // Remove the "}"

        final String DQ = "\"";
        retString += "," + DQ + "hourlyPayRate" + DQ + ":" + (int)hourlyPayRate + "}";
        return retString;
    }



}

