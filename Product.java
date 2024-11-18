import java.util.Objects;

public class Product {

    private String name;
    private String description;
    private String ID;
    private double cost;


    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
    public double getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(cost, product.cost) == 0 && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(ID, product.ID);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, ID, cost);
    }

    /**
     *
     * @return
     */
    public String toCSV()
    {
        String retString = "";
        final char DQ = '\"';
        retString = DQ + name + DQ + "," + DQ + description + DQ + "," + DQ + ID + DQ + "," + DQ + cost + DQ;
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
        retString = "{"+ DQ + "name" + DQ + ":" + DQ + name + DQ + ","
                    + DQ + "description" + DQ + ":" + DQ + description + DQ  + ","
                    + DQ + "ID" + DQ + ":" + DQ + ID + DQ  + ","
                    + DQ + "cost" + DQ + ":" + DQ + cost +DQ  + "}";
        return retString;
    }

    /**
     *
     * @return
     */
    public String toXML() {
        String retString = "";
        final String INDENT = "  ";
        final String NEWLINE = "\n";
        retString += "<Product>" + NEWLINE;
        retString += INDENT + "<name>" + name + "</name>" + NEWLINE;
        retString += INDENT + "<description>" + description + "</description>" + NEWLINE;
        retString += INDENT + "<ID>" + ID + "</ID>" + NEWLINE;
        retString += INDENT + "<cost>" + cost + "</cost>" + NEWLINE;
        retString += "</Product>";

        return retString;
    }




}
