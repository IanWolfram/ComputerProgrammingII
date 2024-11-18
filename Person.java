import java.util.Objects;

public class Person {

   private String firstName;
   private String lastName;
   private String ID;
   private String title;
   private int yob;

    /**
     *
     * @param firstName
     * @param lastName
     * @param ID
     * @param title
     * @param yob
     */



    public Person(String firstName, String lastName, String ID, String title, int yob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.yob = yob;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public int getYob() {
        return yob;
    }

    /**
     *
     * @param yob
     */
    public void setYob(int yob) {
        this.yob = yob;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return yob == person.yob && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(ID, person.ID) && Objects.equals(title, person.title);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, ID, title, yob);
    }

    /**
     *
     * @return
     */
    public String toCSV()
    {
        String retString = "";
        final char DQ = '\"';
        retString = DQ + firstName + DQ + "," + DQ + lastName + DQ + "," + DQ + ID + DQ + "," + DQ + title + DQ + "," + yob;
        return retString;
    }

    /**
     *
     * @return
     */

    public String toJSON()
    {
        String retString = "";
        final char DQ = '\"';
        retString = "{"+ DQ + "firstName" + DQ + ":" + DQ + firstName +DQ + ","
                + DQ + "lastName" + DQ + ":" + DQ + lastName +DQ + ","
                + DQ + "ID" + DQ + ":" + DQ + ID +DQ + ","
                + DQ + "title" + DQ + ":" + DQ + title +DQ + ","
                + DQ + "yob" + DQ + ":" + DQ + yob +DQ +"}";
        return retString;
    }

    public String toXML() {
        String retString = "";
        final String INDENT = "  ";
        final String NEWLINE = "\n";
        retString += "<Person>" + NEWLINE;
        retString += INDENT + "<firstName>" + firstName + "</firstName>" + NEWLINE;
        retString += INDENT + "<lastName>" + lastName + "</lastName>" + NEWLINE;
        retString += INDENT + "<ID>" + ID + "</ID>" + NEWLINE;
        retString += INDENT + "<title>" + title + "</title>" + NEWLINE;
        retString += INDENT + "<yob>" + yob + "</yob>" + NEWLINE;
        retString += "</Person>";

        return retString;
    }


    /**
     *
     * @return
     */
    public String fullName()
    {

        return firstName + " " +lastName;
    }

    /**
     *
     * @return
     */
    public String formalName()
    {
        return title + " " + firstName + " " + lastName;
    }

    /**
     *
     * @return
     */

    public String getAge()
    {
        int calc = (2024 - yob);
        String age = ""+calc;
        return age;
    }

    /**
     *
     * @param year
     * @return
     */
    public String getAge(int year)
    {
        int calc = (year - yob);
        String age = ""+calc;
        return age;
    }


}
