public class Seller {
    private String companyName;
    private Address address;

    public Seller(Address address, String companyName) {
        this.address = address;
        this.companyName = companyName;


    }

    public String addressBoxFormat()
    {
        String retString = companyName + "\n";
        retString += address.addressBoxFormat();
        return retString;
    }

}
