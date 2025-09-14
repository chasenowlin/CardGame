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

    private static String username = null;

    public static String startingWindow() {
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
        title.setFont(new Font("Arial", Font.BOLD, 80));

        namePrompt = new JTextField("Enter Your Name Here");
        namePrompt.setHorizontalAlignment(SwingConstants.CENTER);
        namePrompt.setLocation(screenWidth / 2 - 400, screenHeight / 2);
        namePrompt.setSize(800,100);
        namePrompt.setFont(new Font("Arial", Font.PLAIN, 50));
        namePrompt.setOpaque(true);
        namePrompt.setBackground(Color.LIGHT_GRAY);
        namePrompt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        namePrompt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (namePrompt.getText().equals("Enter Your Name Here")) {
                    namePrompt.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (namePrompt.getText().isEmpty()) {
                    namePrompt.setText("Enter Your Name Here");
                }
            }
        });

        submitName = new JButton("SUBMIT");
        submitName.setLocation(screenWidth / 2 - 100, screenHeight / 2 + 200);
        submitName.setSize(200,50);
        submitName.setBackground(Color.WHITE);
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

        while (username == null || username .equals("Enter Your Name Here")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        mainMenu.dispose();
        return username;
    }

     
}
