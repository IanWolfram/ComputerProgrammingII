import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SafeInputObjTest {

    SafeInputObj s1;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getNonZeroLenString() {
        Scanner scanner = new Scanner("hello\n");
        s1 = new SafeInputObj(scanner);
        assertEquals("hello", s1.getNonZeroLenString("Enter a non-zero length string"));
    }

    @Test
    void getRangedInt() {
        Scanner scanner = new Scanner("42\n");
        s1 = new SafeInputObj(scanner);
        assertEquals(42, s1.getRangedInt("Enter an int between 1 and 50", 1, 50));
    }

    @Test
    void getInt() {
        Scanner scanner = new Scanner("42\n");
        s1 = new SafeInputObj(scanner);
        assertEquals(42, s1.getInt("Enter an int"));
    }

    @Test
    void getRangedDouble() {
        Scanner scanner = new Scanner("3.14\n");
        s1 = new SafeInputObj(scanner);
        assertEquals(3.14, s1.getRangedDouble("Enter a double between 1 and 5", 1, 5));
    }

    @Test
    void getDouble() {
        Scanner scanner = new Scanner("2.718\n");
        s1 = new SafeInputObj(scanner);
        assertEquals(2.718, s1.getDouble("Enter a double"));
    }

    @Test
    void getYNConfirm() {
        Scanner scanner = new Scanner("Y\n");
        s1 = new SafeInputObj(scanner);
        assertTrue(s1.getYNConfirm("Confirm [Y/N]"));
    }

    @Test
    void getRegExString() {
        Scanner scanner = new Scanner("validInput\n");
        s1 = new SafeInputObj(scanner);
        assertEquals("validInput", s1.getRegExString("Enter a valid input", "[a-zA-Z0-9]+"));
    }
}
