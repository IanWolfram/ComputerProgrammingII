import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RandProductSearch extends JFrame {
    private JTextField searchField;
    private JButton searchButton, selectFileButton;
    private JTextArea resultsArea;
    private File selectedFile;

    public RandProductSearch() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Search");
        setSize(800, 400);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter partial product name:"));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        selectFileButton = new JButton("Select Product File");
        inputPanel.add(searchField);
        inputPanel.add(selectFileButton);
        inputPanel.add(searchButton);

        // Results area
        resultsArea = new JTextArea(15, 50);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        searchButton.addActionListener(new SearchButtonListener());
        selectFileButton.addActionListener(new SelectFileButtonListener());

        setVisible(true);
    }

    private class SelectFileButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(null, "Please select a product file first.");
                return;
            }

            String searchQuery = searchField.getText().trim().toLowerCase();
            if (searchQuery.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter a product name to search.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                resultsArea.setText(""); // Clear previous results
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null)
                {
                    String[] fields = line.split(",");

                    String id = fields[0].trim();
                    String name = fields[1].trim().toLowerCase();
                    String description = fields[2].trim();
                    String cost = fields[3].trim();

                    // Check if the name contains the search query
                    if (name.contains(searchQuery))
                    {
                        found = true;
                        resultsArea.append("ID: " + id + "\n");
                        resultsArea.append("Name: " + fields[1].trim() + "\n");
                        resultsArea.append("Description: " + description + "\n");
                        resultsArea.append("Cost: $" + cost + "\n");
                        resultsArea.append("---------------------------\n");
                    }
                }

                if (!found)
                {
                    resultsArea.setText("No products found matching: " + searchQuery);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}
