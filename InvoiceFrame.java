import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceFrame extends JFrame {
    private JTextField productNameField, unitPriceField, quantityField;
    private JTextArea invoiceDisplayArea;
    private JScrollPane scrollPane;
    private Invoice invoice;
    private Seller seller;
    private Address sellerAddress;


    public InvoiceFrame() {
        // Initialize the Invoice object once
        invoice = new Invoice();

        // Set up JFrame properties
        setTitle("Invoice");
        setSize(500,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);



        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Line Item");
        JButton generateButton = new JButton("Generate Invoice");

        buttonPanel.add(addButton);
        buttonPanel.add(generateButton);

        //Invoice Panel
        JPanel invoicePanel = new JPanel(new BorderLayout());
        invoicePanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        JLabel invoiceLabel = new JLabel("Invoice");
        invoiceLabel.setFont(new Font("Arial", Font.BOLD, 35));
        invoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Creating seller and its address
        sellerAddress = new Address(600, "Daniel's Street", "Cincinnati", "OH", 45234);
        seller = new Seller(sellerAddress, "Ian's Grocery Store");

        //Address Box
        JTextArea addressTextArea = new JTextArea();
        addressTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        addressTextArea.setPreferredSize(new Dimension(150, 50));
        addressTextArea.setEditable(false);
        addressTextArea.setText(seller.addressBoxFormat());







        // JTextArea to display the invoice
        invoiceDisplayArea = new JTextArea();
        invoiceDisplayArea.setEditable(false);
        scrollPane = new JScrollPane(invoiceDisplayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrollPane.setPreferredSize(new Dimension(200, 100));

        //Adding to Invoice panel
        invoicePanel.add(invoiceLabel, BorderLayout.NORTH);
        invoicePanel.add(addressTextArea, BorderLayout.WEST);
        invoicePanel.add(scrollPane, BorderLayout.SOUTH);

        // Add panels to JFrame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(invoicePanel, BorderLayout.SOUTH);


        // Add action listeners to buttons
        addButton.addActionListener(new AddLineItemListener());
        generateButton.addActionListener(new GenerateInvoiceListener());

        setVisible(true);
    }

    // Listener to handle adding a line item
    private class AddLineItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = productNameField.getText();
                double unitPrice = Double.parseDouble(unitPriceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Product product = new Product(name, unitPrice);
                LineItem item = new LineItem(product, quantity);

                invoice.addLineItem(item);  // Add the item to the invoice

                JOptionPane.showMessageDialog(
                        InvoiceFrame.this, "Line item added successfully!"
                );

                // Clear input fields
                productNameField.setText("");
                unitPriceField.setText("");
                quantityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        InvoiceFrame.this, "Please enter valid numbers for price and quantity.",
                        "Input Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Listener to generate and display the invoice
    public class GenerateInvoiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            invoiceDisplayArea.setFont(new Font("Arial", Font.BOLD, 15));
            invoiceDisplayArea.setText("Item\t\tQty\tPrice\tTotal\n");
            invoiceDisplayArea.setFont(new Font("Dialog", Font.PLAIN, 12));

            // Loop through all line items in the invoice and display them
            for (LineItem item : invoice.getLineItems()) {
                Product product = item.getProduct();
                invoiceDisplayArea.append(product.getName() +"\t\t" + item.getQuantity() +"\t"+ product.getUnitprice() +"\t" + item.getTotal()+"\n");
            }

            // Display the total amount due
            invoiceDisplayArea.append(
                    String.format("\nTotal Amount Due: $%.2f", invoice.getTotal())
            );
        }
    }


}
