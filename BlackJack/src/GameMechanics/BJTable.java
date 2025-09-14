package GameMechanics;

import java.util.ArrayList;
import CardBasics.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

public class BJTable implements OnScreen {

    private static JFrame frame;
    private static JPanel dealerSide;
    private static JPanel divider;
    private static JPanel userSide;
    private static JLabel dNamePlate;
    private static JLabel uNamePlate;
    private static JPanel nesterDealer;
    private static JPanel nesterUser;
    private static JLabel uCardSlots;
    private static JLabel dCardSlots;

    private static JButton startRound;
    private static JButton chooseHit;
    private static JButton chooseStand;
    private static JLabel uSayBust;
    private static JLabel dSayBust;

    private static JLabel infoScreen;

    private static JPanel scoreBoard;
    private static JLabel scoreTitle;
    private static JLabel scoreBreak;
    private static JLabel lossScore;
    private static JLabel winScore;

    private static int wins = 0;
    private static int losses = 0;

    private static Timer timer;

    private static boolean start = false;
    private static String decision = null;

    public static void gameWindow() {
		
        frame = new JFrame("BlackJack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 3));;
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        device.setFullScreenWindow(frame);

        Color darkGreen = new Color(53, 101, 77);

        nesterDealer = new JPanel();
        nesterDealer.setBackground(darkGreen);
        nesterDealer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        nesterDealer.setLayout(new FlowLayout(FlowLayout.CENTER)); 

        dealerSide = new JPanel();
        dealerSide.setBackground(darkGreen);
        dealerSide.setBorder(new EmptyBorder(30,30,30,30));
        dealerSide.setLayout(new GridLayout(1,10));
        ((GridLayout) dealerSide.getLayout()).setHgap(20);

        dNamePlate = new JLabel("Dealer");
        dNamePlate.setLocation(screenWidth / 2 - 75, 0);
        dNamePlate.setSize(150,40);
        dNamePlate.setOpaque(true);
        dNamePlate.setBackground(new Color(213, 181, 110));
        dNamePlate.setFont(new Font("Arial", Font.BOLD, 25));
        dNamePlate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        dNamePlate.setHorizontalAlignment(SwingConstants.CENTER);
        dNamePlate.setVerticalAlignment(SwingConstants.CENTER);

        uNamePlate = new JLabel(MainMenu.username);
        uNamePlate.setLocation(screenWidth / 2 - 75, screenHeight / 3 - 64);
        uNamePlate.setSize(150,40);
        uNamePlate.setOpaque(true);
        uNamePlate.setBackground(new Color(213, 181, 110));
        uNamePlate.setFont(new Font("Arial", Font.BOLD, 25));
        uNamePlate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        uNamePlate.setHorizontalAlignment(SwingConstants.CENTER);
        uNamePlate.setVerticalAlignment(SwingConstants.CENTER);

        divider = new JPanel();
        divider.setBackground(new Color(53, 150, 77));
        divider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        divider.setLayout(null);

        nesterUser = new JPanel();
        nesterUser.setBackground(darkGreen);
        nesterUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        nesterUser.setLayout(new FlowLayout(FlowLayout.CENTER));

        userSide = new JPanel();
        userSide.setBackground(darkGreen);
        userSide.setBorder(new EmptyBorder(30,30,30,30));
        userSide.setLayout(new GridLayout(1,10));
        ((GridLayout) userSide.getLayout()).setHgap(20);

        infoScreen = new JLabel("WELCOME");
        infoScreen.setLocation(screenWidth / 25, screenHeight / 6 - 85);
        infoScreen.setSize(400,150);
        infoScreen.setOpaque(true);
        infoScreen.setBackground(Color.BLACK);
        infoScreen.setForeground(Color.WHITE);
        infoScreen.setFont(new Font("Arial", Font.BOLD, 25));
        infoScreen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        infoScreen.setHorizontalAlignment(SwingConstants.CENTER);
        infoScreen.setVerticalAlignment(SwingConstants.CENTER);

        scoreBoard = new JPanel();
        scoreBoard.setLocation(screenWidth - screenWidth / 25 - 300, 20);
        scoreBoard.setSize(250,300);
        scoreBoard.setOpaque(true);
        scoreBoard.setBackground(Color.WHITE);
        scoreBoard.setForeground(Color.BLACK);
        scoreBoard.setFont(new Font("Arial", Font.BOLD, 25));
        scoreBoard.setBorder(BorderFactory.createLineBorder(new Color(137,81,41), 10));
        scoreBoard.setLayout(new GridLayout(4,1));

        scoreTitle = new JLabel(("Scoreboard"));
        scoreTitle.setFont(new Font("Arial", Font.BOLD, 25));
        scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
        scoreTitle.setVerticalAlignment(SwingConstants.BOTTOM);

        scoreBreak = new JLabel(("-------------------------"));
        scoreBreak.setFont(new Font("Arial", Font.BOLD, 25));
        scoreBreak.setHorizontalAlignment(SwingConstants.CENTER);
        scoreBreak.setVerticalAlignment(SwingConstants.TOP);

        winScore = new JLabel("Wins: " + wins);
        winScore.setFont(new Font("Arial", Font.BOLD, 25));
        winScore.setHorizontalAlignment(SwingConstants.CENTER);
        winScore.setVerticalAlignment(SwingConstants.TOP);

        lossScore = new JLabel("Losses: " + losses);
        lossScore.setFont(new Font("Arial", Font.BOLD, 25));
        lossScore.setHorizontalAlignment(SwingConstants.CENTER);
        lossScore.setVerticalAlignment(SwingConstants.TOP);

        frame.add(nesterDealer);
        frame.add(divider);
        frame.add(nesterUser);
        nesterUser.add(userSide);
        nesterDealer.add(dealerSide);
        divider.add(dNamePlate);
        divider.add(uNamePlate);
        divider.add(infoScreen);
        divider.add(scoreBoard);
        scoreBoard.add(scoreTitle);
        scoreBoard.add(scoreBreak);
        scoreBoard.add(winScore);
        scoreBoard.add(lossScore);

        frame.setVisible(true);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.revalidate();
                frame.repaint(); 
            }
        });
        timer.start();
	}

    public static void printBoard(String turn, Dealer dealer, Player user, Boolean dBust, Boolean uBust) {
        userSide.removeAll();
        dealerSide.removeAll();

        ArrayList<Card> dealerCards = new ArrayList<Card>();
        ArrayList<Card> userCards = new ArrayList<Card>();
        if (turn.equals("user")) {
            Card c1 = dealer.getHand().get(0);
            dealerCards.add(c1);
            dealerCards.add(new Card(" ", ' '));

            for (Card c: user.getHand()) {
                userCards.add(c);
            }
        } else {
            for (Card c: dealer.getHand()) {
                dealerCards.add(c);
            }

            for (Card c: user.getHand()) {
                userCards.add(c);
            }
        }
        for (Card c: dealerCards) {
            dCardSlots = new JLabel(" " + c.getRank() + c.getSuit());
            dCardSlots.setPreferredSize(new Dimension(150,230));
            dCardSlots.setOpaque(true);
            dCardSlots.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
            dCardSlots.setHorizontalAlignment(SwingConstants.LEADING);
            dCardSlots.setVerticalAlignment(SwingConstants.TOP);
            Color cardColor;
            if (c.getSuit() == ' ' || dBust) {
                cardColor = Color.DARK_GRAY;
                dCardSlots.setBackground(Color.LIGHT_GRAY);
            } else if (c.getSuit() == '♤') {
                cardColor = Color.BLACK;
                dCardSlots.setBackground(new Color(255,250,240));
            } else if (c.getSuit() == '♧') {
                cardColor = new Color(6,64,43);
                dCardSlots.setBackground(new Color(255,250,240));
            } else if (c.getSuit() == '♡') {
                cardColor = new Color(220,20,60);
                dCardSlots.setBackground(new Color(255,250,240));
            } else {
                cardColor = new Color(255, 95, 31);
                dCardSlots.setBackground(new Color(255,250,240));
            }
            dCardSlots.setForeground(cardColor);
            dCardSlots.setBorder(BorderFactory.createLineBorder(cardColor, 5));
            dealerSide.add(dCardSlots);
        }
        
        for (Card c: userCards) {
            uCardSlots = new JLabel(" " + c.getRank() + c.getSuit());
            uCardSlots.setPreferredSize(new Dimension(150,230));
            uCardSlots.setOpaque(true);
            uCardSlots.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
            uCardSlots.setHorizontalAlignment(SwingConstants.LEADING);
            uCardSlots.setVerticalAlignment(SwingConstants.TOP);
            Color cardColor;
            if (c.getSuit() == ' ' || uBust) {
                cardColor = Color.DARK_GRAY;
                uCardSlots.setBackground(Color.LIGHT_GRAY);
            } else if (c.getSuit() == '♤') {
                cardColor = Color.BLACK;
                uCardSlots.setBackground(new Color(255,250,240));
            } else if (c.getSuit() == '♧') {
                cardColor = new Color(6,64,43);
                uCardSlots.setBackground(new Color(255,250,240));
            } else if (c.getSuit() == '♡') {
                cardColor = new Color(220,20,60);
                uCardSlots.setBackground(new Color(255,250,240));
            } else {
                cardColor = new Color(255, 95, 31);
                uCardSlots.setBackground(new Color(255,250,240));
            }
            uCardSlots.setForeground(cardColor);
            uCardSlots.setBorder(BorderFactory.createLineBorder(cardColor, 5));
            userSide.add(uCardSlots);
        }
    }

    public static void waitForRoundStart() {
        start = false;

        startRound = new JButton("Deal Out");
        startRound.setLocation(screenWidth / 2 - 125, screenHeight / 6 - 60);
        startRound.setSize(250,100);
        startRound.setBackground(Color.LIGHT_GRAY);
        startRound.setFont(new Font("Arial", Font.BOLD, 25));
        startRound.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        startRound.setHorizontalAlignment(SwingConstants.CENTER);
        startRound.setVerticalAlignment(SwingConstants.CENTER);
        startRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = true;
            }
        });
        divider.add(startRound);

        while(start == false) {
            GameLoop.wait(100);
        }

        divider.remove(startRound);

        for (Component c: divider.getComponents()) {
            if (c instanceof JLabel) {
                if (((JLabel) c).getText().equals("Busted!")) {
                    divider.remove(c);
                }
            }   
        }
        infoScreen.setText("Game in Progress");
        
    }

    public static String promptDecision() {
        decision = null;

        chooseHit = new JButton("HIT");
        chooseHit.setLocation(screenWidth / 2 - 375, screenHeight / 6 - 60);
        chooseHit.setSize(250,100);
        chooseHit.setBackground(Color.LIGHT_GRAY);
        chooseHit.setFont(new Font("Arial", Font.BOLD, 25));
        chooseHit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        chooseHit.setHorizontalAlignment(SwingConstants.CENTER);
        chooseHit.setVerticalAlignment(SwingConstants.CENTER);
        chooseHit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decision = "hit";
            }
        });

        chooseStand = new JButton("STAND");
        chooseStand.setLocation(screenWidth / 2 + 125, screenHeight / 6 - 60);
        chooseStand.setSize(250,100);
        chooseStand.setBackground(Color.LIGHT_GRAY);
        chooseStand.setFont(new Font("Arial", Font.BOLD, 25));
        chooseStand.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        chooseStand.setHorizontalAlignment(SwingConstants.CENTER);
        chooseStand.setVerticalAlignment(SwingConstants.CENTER);
        chooseStand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decision = "stand";
            }
        });

        divider.add(chooseHit);
        divider.add(chooseStand);

        while(decision == null) {
            GameLoop.wait(100);
        }
        divider.remove(chooseHit);
        divider.remove(chooseStand);
        return decision;
    }

    public static void showBusted(Player p) {

        uSayBust = new JLabel("Busted!");
        dSayBust = new JLabel("Busted!");

        if (p.getPlayerName().equals("Dealer")) {
            dSayBust.setLocation(screenWidth / 2 - 75, 25);
            dSayBust.setSize(150,75);
            dSayBust.setBackground(Color.LIGHT_GRAY);
            dSayBust.setForeground(Color.RED);
            dSayBust.setFont(new Font("Arial", Font.BOLD, 25));
            dSayBust.setHorizontalAlignment(SwingConstants.CENTER);
            dSayBust.setVerticalAlignment(SwingConstants.CENTER);
            divider.add(dSayBust);
        } else {
            uSayBust.setLocation(screenWidth / 2 - 75, screenHeight / 3 - 124);
            uSayBust.setSize(150,75);
            uSayBust.setBackground(Color.LIGHT_GRAY);
            uSayBust.setForeground(Color.RED);
            uSayBust.setFont(new Font("Arial", Font.BOLD, 25));
            uSayBust.setHorizontalAlignment(SwingConstants.CENTER);
            uSayBust.setVerticalAlignment(SwingConstants.CENTER);
            divider.add(uSayBust);
        }
        
        GameLoop.wait(1000);
    }

    public static void showWinner(Player w, Player l) {

        if (w == null) {
            infoScreen.setText("It's a Draw!");
        } else {
            String scoreW = String.valueOf(w.getHandValue());
            if (w.getHandValue() > 21) {
                scoreW = "BUST";
            }
            
            String scoreL = String.valueOf(l.getHandValue());
            if (l.getHandValue() > 21) {
                scoreL = "BUST";
            }

            infoScreen.setText(w.getPlayerName() + " Wins! " + scoreW + " - " + scoreL);

            if (w.getPlayerName().equals("Dealer")) {
                losses++;
            } else {
                wins++;
            }

            winScore.setText("Wins: " + wins);
            lossScore.setText("Losses: " + losses);
        }
    }
}
