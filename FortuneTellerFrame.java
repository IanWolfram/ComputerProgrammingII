import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame
{
    private JLabel titleLabel, imageLabel;
    private JTextArea fortuneArea;
    private JButton readButton, quitButton;
    private ArrayList<String> fortunes;
    private int lastFortuneIndex = -1;

    public FortuneTellerFrame()
    {
        ///////////top panel////////////////////

        setTitle("Fortune Teller");
        setLayout(new BorderLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width * 3 / 4, screenSize.height * 3 / 4);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Fortune Teller", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageLabel = new JLabel(new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab04_FortuneTeller/fortune_teller.jpg"));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(titleLabel);
        topPanel.add(imageLabel);
        add(topPanel, BorderLayout.NORTH);

        /////////middle panel////////////////////

        JPanel middlePanel = new JPanel();
        fortuneArea = new JTextArea(10, 30);
        fortuneArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        fortuneArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(fortuneArea);
        middlePanel.add(scrollPane);
        add(middlePanel, BorderLayout.CENTER);

        ///////////bottom panel//////////////////

        JPanel bottomPanel = new JPanel();
        readButton = new JButton("Read My Fortune!");
        readButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("SansSerif", Font.BOLD, 24));

        bottomPanel.add(readButton);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        ////////////////fortunes//////////////////

        fortunes = new ArrayList<>();
        fortunes.add("You will have a pleasant surprise today.");
        fortunes.add("An exciting opportunity lies ahead.");
        fortunes.add("A thrilling time is in your near future.");
        fortunes.add("You will make a new friend.");
        fortunes.add("You will achieve great success soon.");
        fortunes.add("A stranger will brighten your day.");
        fortunes.add("Adventure awaits you.");
        fortunes.add("Expect good news to arrive soon.");
        fortunes.add("Your hard work will soon pay off.");
        fortunes.add("Happiness comes from unexpected places.");
        fortunes.add("You will soon embark on a journey.");
        fortunes.add("Great fortune is headed your way.");

        readButton.addActionListener(this::readFortune);
        quitButton.addActionListener(e -> System.exit(0));
    }


    private void readFortune(ActionEvent e)
    {
        Random r = new Random();
        int fortuneIndex;
        do {
            fortuneIndex = r.nextInt(fortunes.size());
        } while (fortuneIndex == lastFortuneIndex);
        lastFortuneIndex = fortuneIndex;
        fortuneArea.append(fortunes.get(fortuneIndex) + "\n");
    }
}
