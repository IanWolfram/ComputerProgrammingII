import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person per1, per2, per3;

    @BeforeEach
    void setUp() {
        // Since firstName and lastName are static, changing one instance will affect others.
        per1 = new Person("John", "Doe", "001", "Mr.", 1980);
        per2 = new Person("Jane", "Smith", "002", "Ms.", 1990);
        per3 = new Person("Alice", "Brown", "003", "Dr.", 1975);
    }

    @Test
    void setFirstName() {
        per1.setFirstName("Johnny");
        assertEquals("Johnny", per1.getFirstName());

    }

    @Test
    void setLastName() {
        per1.setLastName("Smith");
        assertEquals("Smith", per1.getLastName());
    }

    @Test
    void setID() {
        per1.setID("004");
        assertEquals("004", per1.getID());
    }

    @Test
    void setTitle() {
        per1.setTitle("Prof.");
        assertEquals("Prof.", per1.getTitle());
    }

    @Test
    void setYob() {
        per1.setYob(1985);
        assertEquals(1985, per1.getYob());
    }

    @Test
    void toCSV() {
        String expectedCSV = "\"John\",\"Doe\",\"001\",\"Mr.\",1980";
        assertEquals(expectedCSV, per1.toCSV());
    }

    @Test
    void toJSON() {
        String expectedJSON = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"ID\":\"001\",\"title\":\"Mr.\",\"yob\":\"1980\"}";
        assertEquals(expectedJSON, per1.toJSON());
    }

    @Test
    void toXML() {
        String expectedXML = "<Person>\n" +
                "  <firstName>John</firstName>\n" +
                "  <lastName>Doe</lastName>\n" +
                "  <ID>001</ID>\n" +
                "  <title>Mr.</title>\n" +
                "  <yob>1980</yob>\n" +
                "</Person>";
        assertEquals(expectedXML, per1.toXML());
    }

    @Test
    void fullName() {
        assertEquals("John Doe", per1.fullName());
    }

    @Test
    void formalName() {
        assertEquals("Mr. John Doe", per1.formalName());
    }

    @Test
    void getAge() {
        assertEquals("44", per1.getAge());
    }

    @Test
    void getAgeWithYear() {
        assertEquals("34", per1.getAge(2014));
    }
}
