import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecursiveLister::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Recursive Lister");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Recursive Lister", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedDirectory = fileChooser.getSelectedFile();
                textArea.setText(""); // Clear previous content
                listFilesRecursively(selectedDirectory, textArea);
            }
        });

        // quit Button Action
        quitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private static void listFilesRecursively(File directory, JTextArea textArea)
    {
        File[] files = directory.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    textArea.append("[Directory] " + file.getAbsolutePath() + "\n");
                    listFilesRecursively(file, textArea);
                }
                else
                {
                    textArea.append("[File] " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }







}
