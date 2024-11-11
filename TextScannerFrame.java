import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.CREATE;

public class TextScannerFrame extends JFrame {
    private JButton pickText, pickNoise, start, toFile;
    private JPanel buttonPanel, textPanel, filePanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private ArrayList<String> stopWords;
    private ArrayList<String> text;
    private Map<String, Integer> wordCountMap;

    public TextScannerFrame() {
        setTitle("Text Scanner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BorderLayout());

        // Create buttons
        pickText = new JButton("Select Text File");
        pickText.setPreferredSize(new Dimension(150, 50));
        pickText.addActionListener(new SelectFileListener());

        pickNoise = new JButton("Select Noise File");
        pickNoise.setPreferredSize(new Dimension(150, 50));
        pickNoise.addActionListener(new SelectNoiseFileListener());

        start = new JButton("Start");
        start.addActionListener(new StartActionListener());
        start.setPreferredSize(new Dimension(150, 50));

        toFile = new JButton("Save To File");
        toFile.addActionListener(new SaveToFileListener());

        // Create panel and add buttons
        buttonPanel = new JPanel();
        buttonPanel.add(pickText);
        buttonPanel.add(pickNoise);
        buttonPanel.add(start);

        // Create text area and put it in the scrollpane
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(900, 850));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        textPanel = new JPanel();
        textPanel.add(scrollPane);

        // Create bottom panel
        filePanel = new JPanel();
        filePanel.add(toFile);

        // Add panels to frame
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        add(filePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class SelectFileListener implements ActionListener {
        public void actionPerformed(ActionEvent action) {
            JFileChooser chooser = new JFileChooser();
            File selectedFile;

            try {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();
                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    text = new ArrayList<>();
                    while (reader.ready()) {
                        String line = reader.readLine().toLowerCase();
                        text.add(line);
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SelectNoiseFileListener implements ActionListener {
        public void actionPerformed(ActionEvent action) {
            JFileChooser chooser = new JFileChooser();
            File selectedFile;

            try {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();
                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    stopWords = new ArrayList<>();
                    while (reader.ready()) {
                        stopWords.add(reader.readLine().toLowerCase());
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class StartActionListener implements ActionListener {
        public void actionPerformed(ActionEvent action) {
            wordCountMap = new HashMap<>();

            // Remove stop words and count occurrences of each word
            for (String line : text) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!stopWords.contains(word) && !word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }

            // Display word count results in textArea
            textArea.setText("");
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                textArea.append(entry.getValue() + " " + entry.getKey() + "\n");
            }
        }
    }

    private class SaveToFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Word Count to File");

            int userSelection = fileChooser.showSaveDialog(TextScannerFrame.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                    for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                        writer.write(entry.getValue() + " " + entry.getKey());
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(TextScannerFrame.this, "File saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(TextScannerFrame.this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }


}
