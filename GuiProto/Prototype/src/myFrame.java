import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myFrame extends JFrame
{
    private JFrame frame = new JFrame();
    private JPanel introPanel; private JPanel whatIsPanel; private JPanel playerCountPanel;
    private JLabel welcomeLabel; private JLabel whatIsLabel; private JLabel howManyLabel;


    public myFrame()
    {

        frame.setTitle("Black Jack Probability Calculator");//title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This lets you exit out of the program
        frame.setResizable(true); //prevents frame from being resized
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true); //This shows the GUI


        //ImageIcon image = new ImageIcon("BlackJackImageTemp.png");
        //frame.setIconImage(image.getImage()); //This will change icon of frame
        frame.getContentPane().setBackground(new Color(96, 189, 146));

    }

    public void labelAndPanels()
    {
        welcomeLabel = new JLabel(); //This creates a label
        whatIsLabel = new JLabel();
        howManyLabel = new JLabel();
        
        welcomeLabel.setText("Welcome to a mathematical experience");
        welcomeLabel.setBounds(250, 100, 100, 100);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Bank Gothic", Font.PLAIN, 12));

        whatIsLabel.setText("This program will show you how probability plays a part in a game of black jack!");
        whatIsLabel.setBounds(250, 200, 100, 100);
        whatIsLabel.setForeground(Color.BLACK);
        whatIsLabel.setFont(new Font("Bank Gothic", Font.PLAIN, 12));

        howManyLabel.setText("How many players are playing?");
        howManyLabel.setBounds(250, 300, 100, 100);
        howManyLabel.setForeground(Color.BLACK);
        howManyLabel.setFont(new Font("Bank Gothic", Font.PLAIN, 12));

        introPanel = new JPanel();
        introPanel.setBounds(0, 0, 500, 100);
        introPanel.setBackground(new Color(96, 189, 146));
        introPanel.add(welcomeLabel);

        whatIsPanel = new JPanel();
        whatIsPanel.setBounds(0, 200, 500, 100);
        whatIsPanel.setBackground(new Color(96, 189, 146));
        whatIsPanel.add(whatIsLabel);      

        playerCountPanel = new JPanel();
        playerCountPanel.setBounds(0, 300, 500, 100);
        playerCountPanel.setBackground(new Color(96, 189, 146));
        playerCountPanel.add(howManyLabel);

        frame.add(introPanel);
        frame.add(whatIsPanel);
        frame.add(playerCountPanel);
    }

    public void buttons()
    {
        JButton player = new JButton();
        //player.setBounds();
    }

    
}
