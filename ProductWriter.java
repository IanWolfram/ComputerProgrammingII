import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = true;
        String r = "";
        String ID = "";
        String Name = "";
        String desc = "";
        double cost = 0;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        do
        {
            ID = SafeInput.getNonZeroLenString(in, "Enter product ID 00000A");
            Name = SafeInput.getNonZeroLenString(in, "Enter product name");
            desc = SafeInput.getNonZeroLenString(in, "Enter product description");
            cost = SafeInput.getDouble(in, "Enter your cost");


            r = ID + ", "+ Name +", "+ desc +","+cost;
            System.out.println(r);
            recs.add(r);

            doneInput = SafeInput.getYNConfirm(in, "Do you have another product?");


        }while(doneInput);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write r
                // 0 is where to start (1st char) the write
                // r. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}