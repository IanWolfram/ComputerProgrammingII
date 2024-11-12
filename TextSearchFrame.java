import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextSearchFrame extends JFrame {
    private JTextArea originalTextArea, filteredTextArea;
    private JTextField searchField;
    private JButton loadButton, searchButton, quitButton;
    private JPanel buttonPanel, textPanel;
    private JScrollPane originalScrollPane, filteredScrollPane;
    private Path loadedFilePath;

    public TextSearchFrame() {
        setTitle("Text Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Initialize components
        originalTextArea = new JTextArea();
        originalTextArea.setEditable(false);
        originalTextArea.setLineWrap(true);
        originalTextArea.setWrapStyleWord(true);

        filteredTextArea = new JTextArea();
        filteredTextArea.setEditable(false);
        filteredTextArea.setLineWrap(true);
        filteredTextArea.setWrapStyleWord(true);
        searchField = new JTextField(20);

        loadButton = new JButton("Load File");
        loadButton.addActionListener(new LoadFileListener());

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());
        searchButton.setEnabled(false); // Enable only after file is loaded

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        // Scroll panes
        originalScrollPane = new JScrollPane(originalTextArea);
        originalScrollPane.setPreferredSize(new Dimension(380, 500));
        originalScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        filteredScrollPane = new JScrollPane(filteredTextArea);
        filteredScrollPane.setPreferredSize(new Dimension(380, 500));
        filteredScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Panels for layout
        textPanel = new JPanel(new GridLayout(1, 2));
        textPanel.add(originalScrollPane);
        textPanel.add(filteredScrollPane);

        buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Enter search string:"));
        buttonPanel.add(searchField);
        buttonPanel.add(loadButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(quitButton);

        // Add components to frame
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Load file and display its contents in the left text area
    private class LoadFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(TextSearchFrame.this);

            if (result == JFileChooser.APPROVE_OPTION)
            {
                loadedFilePath = chooser.getSelectedFile().toPath();
                try (Stream<String> lines = Files.lines(loadedFilePath))
                {
                    // Read and display the file contents
                    List<String> allLines = lines.collect(Collectors.toList());
                    originalTextArea.setText(String.join("\n", allLines));
                    searchButton.setEnabled(true); // Enable search after loading a file
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(TextSearchFrame.this,
                            "Error reading file: " + ex.getMessage(),
                            "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Search for the string in each line and display only the matching sentences in the right text area
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (loadedFilePath != null)
            {
                String searchString = searchField.getText().trim();
                if (!searchString.isEmpty()) {
                    try (Stream<String> lines = Files.lines(loadedFilePath)) {
                        // Collect only the sentences containing the search string
                        List<String> filteredSentences = lines
                                .flatMap(line -> {
                                    // Split line into individual sentences
                                    String[] sentences = line.split("(?<=[.!?])\\s+");
                                    // Filter sentences that contain the search string
                                    return Stream.of(sentences)
                                            .filter(sentence -> sentence.contains(searchString));
                                })
                                .collect(Collectors.toList());

                        // Display the filtered sentences in the right text area
                        filteredTextArea.setText(String.join("\n", filteredSentences));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TextSearchFrame.this,
                                "Error processing file: " + ex.getMessage(),
                                "Search Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TextSearchFrame.this,
                            "Please enter a search string.",
                            "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }




}
