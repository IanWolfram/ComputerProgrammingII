public class Product {

    private String name;
    private double unitprice;

    public Product(String name, double unitprice) {
        this.name = name;
        this.unitprice = unitprice;
    }


    public String getName() {
        return name;
    }

    public double getUnitprice() {
        return unitprice;
    }
}