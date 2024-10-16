import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIForm extends JFrame {
    private JRadioButton thinCrust, regularCrust, deepDish;
    private JComboBox<String> sizeComboBox;
    private JCheckBox[] toppings;
    private JTextArea orderTextArea;
    private final double[] sizePrices = {8.00, 12.00, 16.00, 20.00}; // Small, Medium, Large, Super
    private final String[] toppingNames = {"Cheese", "Pepperoni", "Mushrooms", "Onions", "Dragon Scales", "Unicorn Horns"};

    public PizzaGUIForm() {
        setTitle("Pizza Order");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crust selection panel
        JPanel crustPanel = new JPanel();
        crustPanel.setBorder(BorderFactory.createTitledBorder("Select Crust"));
        thinCrust = new JRadioButton("Thin");
        regularCrust = new JRadioButton("Regular");
        deepDish = new JRadioButton("Deep-dish");

        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDish);

        crustPanel.add(thinCrust);
        crustPanel.add(regularCrust);
        crustPanel.add(deepDish);

        // Size selection panel
        JPanel sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Select Size"));
        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);
        sizeComboBox.setPreferredSize(new Dimension(115, 45));
        sizeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        sizePanel.add(sizeComboBox);

        // Toppings selection panel
        JPanel toppingsPanel = new JPanel();
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));
        toppingsPanel.setLayout(new BoxLayout(toppingsPanel, BoxLayout.Y_AXIS));
        toppings = new JCheckBox[toppingNames.length];

        for (int i = 0; i < toppingNames.length; i++) {
            toppings[i] = new JCheckBox(toppingNames[i]);
            toppingsPanel.add(toppings[i]);
        }

        // Order summary panel
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderTextArea = new JTextArea(18, 30);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        summaryPanel.add(scrollPane);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton orderButton = new JButton("Order");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        // Add action listeners
        orderButton.addActionListener(new OrderButtonListener());
        clearButton.addActionListener(new ClearButtonListener());
        quitButton.addActionListener(new QuitButtonListener());

        // Add all panels to the frame
        add(crustPanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.CENTER);
        add(toppingsPanel, BorderLayout.EAST);
        add(summaryPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String crust = "";
            if (thinCrust.isSelected()) crust = "Thin";
            else if (regularCrust.isSelected()) crust = "Regular";
            else if (deepDish.isSelected()) crust = "Deep-dish";

            String size = (String) sizeComboBox.getSelectedItem();
            double price = sizePrices[sizeComboBox.getSelectedIndex()];

            StringBuilder orderDetails = new StringBuilder();
            orderDetails.append("=========================================\n")
                    .append("Type of Crust: ").append(crust).append("\n")
                    .append("Size: ").append(size).append(" - $").append(price).append("\n")
                    .append("Toppings:\n");

            double toppingsTotal = 0;
            for (JCheckBox topping : toppings) {
                if (topping.isSelected()) {
                    orderDetails.append(topping.getText()).append(" - $1.00\n");
                    toppingsTotal += 1.00;
                }
            }

            double subTotal = price + toppingsTotal;
            double tax = subTotal * 0.07;
            double total = subTotal + tax;

            orderDetails.append("\nSub-total: $").append(String.format("%.2f", subTotal)).append("\n")
                    .append("Tax: $").append(String.format("%.2f", tax)).append("\n")
                    .append("-----------------------------------------------------------------\n")
                    .append("Total: $").append(String.format("%.2f", total)).append("\n")
                    .append("=========================================\n");

            orderTextArea.setText(orderDetails.toString());
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            thinCrust.setSelected(false);
            regularCrust.setSelected(false);
            deepDish.setSelected(false);
            sizeComboBox.setSelectedIndex(0);
            for (JCheckBox topping : toppings) {
                topping.setSelected(false);
            }
            orderTextArea.setText("");
        }
    }

    private class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Quit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

}
