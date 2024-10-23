public class Address
{
    private int streetNumber;
    private String streetName;
    private String city;
    private String state;
    private int zip;

    public Address (int streetNumber, String streetName, String city, String state, int zip)
    {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

   public String addressBoxFormat()
   {
       String retString = "";
       retString = streetNumber + " " + streetName + "\n" + city + ", " + state + " " + zip;
       return retString;
   }


}
