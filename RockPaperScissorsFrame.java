import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RockPaperScissorsFrame extends JFrame
{
    private int ties = 0, playerWins = 0, computerWins = 0;
    private String winText = "Start Playing! \n", playerMove = "", computerMove = "";
    private final JTextArea textArea;
    private JLabel labelPlayerWins, labelComputerWins, labelTies;
    private JPanel statsPanel;



    public RockPaperScissorsFrame() {
        JFrame frame = new JFrame("Rock Paper Scissors");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //creating panel
        JPanel rpcPanel = new JPanel();
        rpcPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        frame.add(rpcPanel, BorderLayout.CENTER);

        //creating buttons
        JButton rock = new JButton();
        ImageIcon rockIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab05_Rock_Paper_Scissors/src/rock.jpg");

        rock.setIcon(rockIcon);
        rock.setPreferredSize(new Dimension(100,100));
        rpcPanel.add(rock);

        JButton paper = new JButton();
        ImageIcon paperIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab05_Rock_Paper_Scissors/src/paper.jpg");

        paper.setIcon(paperIcon);
        paper.setPreferredSize(new Dimension(100,100));
        rpcPanel.add(paper);

        JButton scissors = new JButton();
        ImageIcon scissorsIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab05_Rock_Paper_Scissors/src/scissors.jpg");


        scissors.setIcon(scissorsIcon);
        scissors.setPreferredSize(new Dimension(100,100));
        rpcPanel.add(scissors);

        JButton quit = new JButton("Quit");
        rpcPanel.add(quit);





        //creating results panel
        JPanel resultsPanel = new JPanel();
        //creating text area for scroll pane
        textArea = new JTextArea(winText,5, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(textArea);
        resultsPanel.add(scrollPane);
        frame.add(resultsPanel, BorderLayout.SOUTH);

        //creating stats panel
        statsPanel = new JPanel();

         labelPlayerWins = new JLabel("Player Wins: ");
         labelComputerWins = new JLabel("Computer Wins: ");
         labelTies = new JLabel("Ties: ");

        statsPanel.add(labelPlayerWins);
        statsPanel.add(labelComputerWins);
        statsPanel.add(labelTies);

        frame.add(statsPanel, BorderLayout.NORTH);


        //creating action listeners
        rock.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerMove = "Rock";
                computerMove = generateComputerMove();
                compareMoves(playerMove, computerMove);
                updateResults();

            }
        });

        paper.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerMove = "Paper";
                computerMove = generateComputerMove();
                compareMoves(playerMove, computerMove);
                updateResults();
            }
        });

        scissors.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerMove = "Scissors";
                computerMove = generateComputerMove();
                compareMoves(playerMove, computerMove);
                updateResults();
            }
        });

        quit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });






        frame.setVisible(true);
    }


    //--------------methods-----------------------//


    public String generateComputerMove()
    {
        String cMove = "";
        Random r = new Random();
        int x = r.nextInt(3)+1;
        if(x == 1)
        {
            cMove = "Rock";
        }
        if(x == 2)
        {
            cMove = "Paper";
        }
        if(x==3)
        {
            cMove = "Scissors";
        }
        return cMove;

    }



    public void compareMoves(String playerMove, String computerMove)
    {
        if (playerMove.equals(computerMove))
        {
            ties++;
            winText = "It's a tie! Both chose " + playerMove;
        }
        else if (playerMove.equals("Rock"))
        {
            if (computerMove.equals("Scissors"))
            {
                playerWins++;
                winText = "Rock breaks Scissors (Player Wins)";
            }
            else
            {
                computerWins++;
                winText = "Paper covers Rock (Computer Wins)";
            }
        }
        else if (playerMove.equals("Paper"))
        {
            if (computerMove.equals("Rock"))
            {
                playerWins++;
                winText = "Paper covers Rock (Player Wins)";
            }
            else
            {
                computerWins++;
                winText = "Scissors cut Paper (Computer Wins)";
            }
        }
        else if (playerMove.equals("Scissors"))
        {
            if (computerMove.equals("Paper"))
            {
                playerWins++;
                winText = "Scissors cut Paper (Player Wins)";
            }
            else
            {
                computerWins++;
                winText = "Rock breaks Scissors (Computer Wins)";
            }
        }
    }


    private void updateResults() {
        // Update labels and text area with current game results
        textArea.append(winText + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // scrolls to bottom of pane automatically

        labelPlayerWins.setText("Player Wins: " + playerWins);
        labelComputerWins.setText("Computer Wins: " + computerWins);
        labelTies.setText("Ties: " + ties);


    }


}
