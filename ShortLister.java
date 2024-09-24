import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

import static java.lang.System.setOut;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.lang.System.out;

/**
 *
 * @author wulft
 *
 * Use the thread safe NIO IO library to read a file and filter short words
 */
public class ShortLister {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Use a file chooser to pick the file
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                // Open the file for reading
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                // Create an instance of the ShortWordFilter
                ShortWordFilter filter = new ShortWordFilter();

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;

                    String[] words = rec.split("\\s+");
                    boolean printLine = false;

                    for (String word : words) {
                        if (filter.accept(word)) {
                            printLine = true;
                            out.println(word);

                        }
                    }

                }
                reader.close(); // Close the file to seal it and flush buffer
                out.println("\n\nData file read!");
            }
        } catch (FileNotFoundException e) {
            out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

