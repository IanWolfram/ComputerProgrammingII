public class Buyer {
    private String name;
    private Address address;
    private double balance;

    public Buyer(String name, Address address, double balance) {
        this.name = name;
        this.address = address;
        this.balance = balance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
