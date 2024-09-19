import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SalaryWorkerTest {

    private SalaryWorker salaryWorker;

    @BeforeEach
    void setUp() {
        salaryWorker = new SalaryWorker("Alice", "Johnson", "000004", "Mrs.", 1995, 18, 50000);
    }

    @Test
    void calculateWeeklyPay() {
        double expectedWeeklyPay = 50000 / 52.0;
        assertEquals(expectedWeeklyPay, salaryWorker.calculateWeeklyPay(40));
    }

    @Test
    void displayWeeklyPay() {
        String expectedOutput = "Hours of regular pay: 40.0"  + "\n"
                + "Regular earnings: 961.5384615384615" + "\n"
                + "Hours of overtime pay: 0.0"+  "\n"
                + "Overtime earnings: 0.0" + "\n"
                + "Total earnings: 961.5384615384615" + "\n"
                + "The weekly pay of 50000.0 a year is 961.5384615384615";

        assertEquals(expectedOutput, salaryWorker.displayWeeklyPay(40));
    }

    @Test
    void toCSV() {

        String expectedCSV = "\"Alice\",\"Johnson\",\"000004\",\"Mrs.\",1995,18.0,50000.0";
        assertEquals(expectedCSV, salaryWorker.toCSV());
    }

    @Test
    void toXML() {
        String expectedXML = "<Person>\n" +
                "  <firstName>Alice</firstName>\n" +
                "  <lastName>Johnson</lastName>\n" +
                "  <ID>000004</ID>\n" +
                "  <title>Mrs.</title>\n" +
                "  <yob>1995</yob>\n" +
                "  <hourlyPayRate>18.0</hourlyPayRate>\n" +
                "  <annualSalary>50000.0</annualSalary>\n" +
                "  </Person>";

        assertEquals(expectedXML, salaryWorker.toXML());
    }



    @Test
    void toJSON() {
        String expectedJSON = "{\"firstName\":\"Alice\",\"lastName\":\"Johnson\",\"ID\":\"000004\",\"title\":\"Mrs.\",\"yob\":\"1995\",\"hourlyPayRate\":\"18.0\",\"annualSalary\":\"50000.0\"}";
        assertEquals(expectedJSON, salaryWorker.toJSON());
    }
}
