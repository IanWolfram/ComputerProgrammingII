import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new BufferedInputStream(Files.newInputStream(file))))) {

                    // Header for the table
                    System.out.printf("%-12s %-12s %-15s %-8s %-4s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                    System.out.println("================================================================");

                    int line = 0;
                    while ((rec = reader.readLine()) != null) {
                        line++;

                        // Assuming records are comma-separated and in the order: ID, Firstname, Lastname, Title, YOB
                        String[] fields = rec.split(",\\s*");
                            System.out.printf("%-12s %-12s %-15s %-8s %-4s%n", fields[0], fields[1], fields[2], fields[3], fields[4]);

                    }

                    System.out.println("\n\nData file read!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
