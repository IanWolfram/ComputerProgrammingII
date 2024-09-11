import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p1, p2, p3;

    @BeforeEach
    void setUp() {
        p1 = new Product("Apple", "honeycrisp apple", "00000A", 2);
        p2 = new Product("Grapes", "green grapes", "00000B", 3);
        p3 = new Product("Peach", "juicy peaches", "00000C", 5);
    }

    @Test
    void setName() {
        p1.setName("Banana");
        assertEquals("Banana", p1.getName());
    }

    @Test
    void setDescription() {
        p1.setDescription("Sweet banana");
        assertEquals("Sweet banana", p1.getDescription());
    }

    @Test
    void setID() {
        p1.setID("00000D");
        assertEquals("00000D", p1.getID());
    }

    @Test
    void setCost() {
        p1.setCost(4);
        assertEquals(4, p1.getCost());
    }

    @Test
    void testSetName() {
        p2.setName("Strawberry");
        assertEquals("Strawberry", p2.getName());
    }

    @Test
    void testSetDescription() {
        p2.setDescription("Fresh strawberries");
        assertEquals("Fresh strawberries", p2.getDescription());
    }

    @Test
    void testSetID() {
        p2.setID("00000E");
        assertEquals("00000E", p2.getID());
    }

    @Test
    void testSetCost() {
        p2.setCost(6);
        assertEquals(6, p2.getCost());
    }

    @Test
    void toCSV() {
        String expectedCSV = "\"Apple\",\"honeycrisp apple\",\"00000A\",\"2.0\"";
        assertEquals(expectedCSV, p1.toCSV());
    }

    @Test
    void toJSON() {
        String expectedJSON = "{\"name\":\"Apple\",\"description\":\"honeycrisp apple\",\"ID\":\"00000A\",\"cost\":\"2.0\"}";
        assertEquals(expectedJSON, p1.toJSON());
    }

    @Test
    void toXML() {
        String indent = "  ";
        String expectedXML = "<Product>\n"+indent+"<name>Apple</name>\n"+indent+"<description>honeycrisp apple</description>" +
                "\n"+indent+"<ID>00000A</ID>\n"+indent+"<cost>2.0</cost>\n</Product>";
        assertEquals(expectedXML, p1.toXML());
    }
}
