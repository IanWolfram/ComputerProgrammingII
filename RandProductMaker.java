import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

import static javax.swing.BoxLayout.Y_AXIS;

public class RandProductMaker extends JFrame {
    private JPanel panel;
    private JTextField productName, productID, description, price, recordCountField;
    private JLabel name, ID, desc, cost, recordCountLabel;
    private JButton add;
    private int recordCount = 0;

    public RandProductMaker() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Random Product Maker");
        setLayout(new BorderLayout());
        setSize(500, 300);

        name = new JLabel("Name:");
        ID = new JLabel("ID:");
        desc = new JLabel("Description:");
        cost = new JLabel("Cost:");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));
        Dimension fixedSize = new Dimension(750, 25);
        productName = new JTextField();
        productName.setMaximumSize(fixedSize);
        productID = new JTextField();
        productID.setMaximumSize(fixedSize);
        description = new JTextField();
        description.setMaximumSize(fixedSize);
        price = new JTextField();
        price.setMaximumSize(fixedSize);

        add = new JButton("Add Product Data");
        add.addActionListener(new addListener());

        recordCountField = new JTextField("Record Count:");
        recordCountField.setEditable(false);
        recordCountField.setMaximumSize(new Dimension(200, 20));


        panel.add(name);
        panel.add(productName);
        panel.add(ID);
        panel.add(productID);
        panel.add(desc);
        panel.add(description);
        panel.add(cost);
        panel.add(price);
        panel.add(add);
        panel.add(Box.createRigidArea(new Dimension(0,15))); //adding space between button and textfield
        panel.add(recordCountField);
        add(panel, BorderLayout.NORTH);



        setVisible(true);
    }

    private String padString (String str, int length)
    {
        if (str.length() > length)
        {
            return str.substring(0, length);
        }
        return String.format("%" + length + "s", str); // padding with spaces
    }

    public class addListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Validate inputs
                if (productName.getText().isEmpty() || productID.getText().isEmpty() ||
                        description.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out!");
                    return;
                }

                double productPrice;
                try {
                    productPrice = Double.parseDouble(price.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number.");
                    return;
                }

                // Pad fields
                String paddedName = padString(productName.getText(), 35);
                String paddedID = padString(productID.getText(), 6);
                String paddedDescription = padString(description.getText(), 75);

                // Write to Random Access File
                try (RandomAccessFile raf = new RandomAccessFile("products.dat", "rw")) {
                    raf.seek(raf.length()); // move to the end of the file
                    raf.writeBytes(paddedName);
                    raf.writeBytes(paddedID);
                    raf.writeBytes(paddedDescription);
                    raf.writeDouble(productPrice); 
                }

                // Clear fields and update record count
                productName.setText("");
                productID.setText("");
                description.setText("");
                price.setText("");

                recordCount++;
                recordCountField.setText("Record Count:" + recordCount);


                JOptionPane.showMessageDialog(null, "Product record saved successfully!");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error saving product record: " + ioException.getMessage());
            }

        }
    }


    public static void main(String[] args)
    {
        new RandProductMaker();
    }

}



