import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker("Ian", "Wolfram", "000001", "Mr.", 2005, 15);
    }

    @Test
    void calculateWeeklyPay() {

        double expectedWeeklyPayRegular = 15 * 40;
        assertEquals(expectedWeeklyPayRegular, worker.calculateWeeklyPay(40), 0.01);

        double expectedWeeklyPayOvertime = (40 * 15) + (10 * 15 * 1.5);
        assertEquals(expectedWeeklyPayOvertime, worker.calculateWeeklyPay(50), 0.01);
    }



    @Test
    void displayWeeklyPay() {
        String expectedOutput = "Hours of regular pay: 40.0\n"
                + "Regular earnings: 600.0\n"
                + "Hours of overtime pay: 0.0\n"
                + "Overtime earnings: 0.0\n"
                + "Total earnings: 600.0\n";
        assertEquals(expectedOutput, worker.displayWeeklyPay(40));

        String expectedOvertimeOutput = "Hours of regular pay: 40.0\n"
                + "Regular earnings: 600.0\n"
                + "Hours of overtime pay: 10.0\n"
                + "Overtime earnings: 225.0\n"
                + "Total earnings: 825.0\n";
        assertEquals(expectedOvertimeOutput, worker.displayWeeklyPay(50));
    }

    @Test
    void toCSV() {
        String expectedCSV = "\"Ian\",\"Wolfram\",\"000001\",\"Mr.\",2005,15";
        assertEquals(expectedCSV, worker.toCSV());
    }

    @Test
    void toXML() {
        String expectedXML = "<Person>\n" +
                "  <firstName>Ian</firstName>\n" +
                "  <lastName>Wolfram</lastName>\n" +
                "  <ID>000001</ID>\n" +
                "  <title>Mr.</title>\n" +
                "  <yob>2005</yob>\n" +
                "  <hourlyPayRate>15</hourlyPayRate>\n" +
                "</Person>";
        assertEquals(expectedXML, worker.toXML());
    }


    @Test
    void toJSON() {
        String expectedJSON = "{\"firstName\":\"Ian\",\"lastName\":\"Wolfram\",\"ID\":\"000001\",\"title\":\"Mr.\",\"yob\":\"2005\",\"hourlyPayRate\":\"15.0\"}";
        assertEquals(expectedJSON, worker.toJSON());
    }



}
