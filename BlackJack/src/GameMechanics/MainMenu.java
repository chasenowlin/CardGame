package GameMechanics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainMenu implements OnScreen {
    private static JFrame mainMenu;
    private static JPanel mainPanel;

    private static JLabel title;
    private static JButton submitName;
    private static JTextField namePrompt;

    public static String username = null;

    public static void startingWindow() {
        username = null;

        mainMenu = new JFrame("BlackJack Menu");
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setLayout(null);
        mainMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        device.setFullScreenWindow(mainMenu);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(screenWidth, screenHeight);
        mainPanel.setBackground(new Color(53, 101, 77));

        title = new JLabel("BlackJack");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setLocation(screenWidth / 2 - 400, screenHeight / 4);
        title.setSize(800,100);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 80));

        namePrompt = new JTextField("Sign Your Name Plate");
        namePrompt.setHorizontalAlignment(SwingConstants.CENTER);
        namePrompt.setLocation(screenWidth / 2 - 300, screenHeight / 2);
        namePrompt.setSize(600,100);
        namePrompt.setFont(new Font("Arial", Font.PLAIN, 50));
        namePrompt.setOpaque(true);
        namePrompt.setBackground(new Color(213, 181, 110));
        namePrompt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        namePrompt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (namePrompt.getText().equals("Sign Your Name Plate")) {
                    namePrompt.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (namePrompt.getText().isEmpty()) {
                    namePrompt.setText("Sign Your Name Plate");
                }
            }
        });

        submitName = new JButton("SUBMIT");
        submitName.setLocation(screenWidth / 2 - 100, screenHeight / 2 + 200);
        submitName.setSize(200,50);
        submitName.setBackground(Color.LIGHT_GRAY);
        submitName.setFont(new Font("Arial", Font.BOLD, 20));
        submitName.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 8));
        submitName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = namePrompt.getText();
            }
        });

        mainMenu.add(mainPanel);
        mainPanel.add(title);
        mainPanel.add(namePrompt);
        mainPanel.add(submitName);
        mainMenu.repaint();
        mainMenu.setVisible(true);

        while (username == null || username .equals("Sign Your Name Plate")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        mainMenu.dispose();
    }

     
}
