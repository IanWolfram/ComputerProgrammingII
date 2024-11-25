import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListFrame extends JFrame
{
    private JPanel panel;
    private JTextField strings, searchStrings;
    private JTextArea listArea;
    private JScrollPane listScroll;
    private JLabel lbl, searchLbl;
    private JButton sort, search;
    private List list =  new List();



    public ListFrame ()
    {
        setTitle("List Sorter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new BorderLayout());


        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));
        strings = new JTextField();
        strings.setPreferredSize(new Dimension(200,20));
        lbl = new JLabel("Enter Strings to be Sorted:");
        sort = new JButton("Sort");
        sort.addActionListener(new sortListener());
        searchLbl = new JLabel("Search for String:");
        searchStrings = new JTextField();
        searchStrings.setPreferredSize(new Dimension(200,20));
        search = new JButton("Search");
        search.addActionListener(new searchListener());
        listArea = new JTextArea();
        listArea.setEditable(false);
        listScroll = new JScrollPane(listArea);
        listScroll.setPreferredSize(new Dimension(400,350));


        panel.add(lbl);
        panel.add(strings);
        panel.add(sort);
        panel.add(searchLbl);
        panel.add(searchStrings);
        panel.add(search);
        panel.add(listScroll);






        add(panel, BorderLayout.NORTH);
        setVisible(true);
    }

    private class searchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = searchStrings.getText().trim();

            if (str.isEmpty())
            {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a string to search.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            int index = list.binarySearch(str);

            if (index < list.getList().length && list.get(index).equals(str))
            {
                index++; // index starts at 0, ++ to fix this
                JOptionPane.showMessageDialog(
                        null,
                        "Element \"" + str + "\" found at index " + index + ".",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            else
            {
                index++;
                JOptionPane.showMessageDialog(
                        null,
                        "Element \"" + str + "\" not found. \n" +
                                "It would be located at index " + index + " in the sorted list.",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            searchStrings.setText("");
        }
    }


    private class sortListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = strings.getText().trim();


            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter some text to sort.");
                return;
            }

            list.splitStrings(strings);
            strings.setText("");
            list.sortList();

            listArea.setText("");
            String[] sortedList = list.getList();
            for (int i = 0; i < sortedList.length; i++)
            {
                listArea.append((i + 1) + " " + sortedList[i] + "\n");
            }
        }
    }




    public static void main(String[] args) {
        new ListFrame();
    }





















}
