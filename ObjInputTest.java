import java.util.Scanner;

public class ObjInputTest {

    public static void main(String[] args) {
        SafeInputObj SIO = new SafeInputObj(new Scanner(System.in));

        String nonZeroLenString = SIO.getNonZeroLenString("Enter a non-zero length string");
        System.out.println("Non-zero length string: " + nonZeroLenString);

        int rangedInt = SIO.getRangedInt("Enter an int between 1 and 100", 1, 100);
        System.out.println("Ranged int: " + rangedInt);

        int intValue = SIO.getInt("Enter an int");
        System.out.println("Int value: " + intValue);

        double rangedDouble = SIO.getRangedDouble("Enter a double between 1.0 and 10.0", 1, 10);
        System.out.println("Ranged double: " + rangedDouble);

        double doubleValue = SIO.getDouble("Enter a double");
        System.out.println("Double value: " + doubleValue);

        boolean ynConfirm = SIO.getYNConfirm("Confirm");
        System.out.println("YN confirmation: " + ynConfirm);

        String regexString = SIO.getRegExString("Enter a 5-digit ID", "\\d{5}");
        System.out.println("ID: " + regexString);
    }
}
