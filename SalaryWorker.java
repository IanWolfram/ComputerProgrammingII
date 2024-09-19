public class SalaryWorker extends Worker
{
    double annualSalary;

    /**
     * @param firstName
     * @param lastName
     * @param ID
     * @param title
     * @param yob
     * @param hourlyPayRate
     */
    public SalaryWorker(String firstName, String lastName, String ID, String title, int yob, double hourlyPayRate, double annualSalary) {
        super(firstName, lastName, ID, title, yob, hourlyPayRate);
        this.annualSalary = annualSalary;
    }
    public double calculateWeeklyPay(double hoursWorked)
    {
        double pay = 0;
        pay = annualSalary /52;
        return pay;
    }

    public String displayWeeklyPay(double hoursWorked)
    {
        String retString = super.displayWeeklyPay(hoursWorked);
        retString += "The weekly pay of "+annualSalary+" a year is "+ calculateWeeklyPay(hoursWorked);
        return retString;

    }
    /**
     *
     * @return
     */
    public String toCSV()
    {
        String retString = super.toCSV();
        retString += "," + annualSalary;
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

        retString += "<annualSalary>" + annualSalary + "</annualSalary>" + NEWLINE;
        retString += INDENT + "</Person>";
        return retString;

    }

    /**
     *
     * @return
     */
    public String toJSON()
    {
        String retString = super.toJSON();
        retString = retString.substring(0, retString.length()-1); //removing "}"


        final char DQ = '\"';
        retString += "," + DQ + "annualSalary" + DQ + ":" + DQ + annualSalary + DQ + "}";
        return retString;
    }
}
