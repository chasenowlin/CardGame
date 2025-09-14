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

        frame.add(nesterDealer);
        frame.add(divider);
        frame.add(nesterUser);
        nesterUser.add(userSide);
        nesterDealer.add(dealerSide);
        divider.add(dNamePlate);
        divider.add(uNamePlate);

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

    public static void printBoard(String turn, Dealer dealer, Player user) {
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
        System.out.println("--------------------------------------");
        System.out.println("Dealer's Hand:");
        for (Card c: dealerCards) {
            dCardSlots = new JLabel(" " + c.getRank() + c.getSuit());
            dCardSlots.setPreferredSize(new Dimension(150,230));
            dCardSlots.setOpaque(true);
            dCardSlots.setBackground(new Color(255,250,240));
            dCardSlots.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
            dCardSlots.setHorizontalAlignment(SwingConstants.LEADING);
            dCardSlots.setVerticalAlignment(SwingConstants.TOP);
            Color cardColor;
            if (c.getSuit() == ' ') {
                cardColor = Color.LIGHT_GRAY;
                dCardSlots.setBackground(Color.LIGHT_GRAY);
            } else if (c.getSuit() == '♤' || c.getSuit() == '♧') {
                cardColor = Color.BLACK;
            } else {
                cardColor = Color.RED;
            }
            dCardSlots.setForeground(cardColor);
            dCardSlots.setBorder(BorderFactory.createLineBorder(cardColor, 5));
            dealerSide.add(dCardSlots);
        }

        System.out.println();
        System.out.println(user.getPlayerName() + "'s Hand:");
        for (Card c: userCards) {
            uCardSlots = new JLabel(" " + c.getRank() + c.getSuit());
            uCardSlots.setPreferredSize(new Dimension(150,230));
            uCardSlots.setOpaque(true);
            uCardSlots.setBackground(new Color(255,250,240));
            uCardSlots.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
            uCardSlots.setHorizontalAlignment(SwingConstants.LEADING);
            uCardSlots.setVerticalAlignment(SwingConstants.TOP);
            Color cardColor;
            if (c.getSuit() == ' ') {
                cardColor = Color.LIGHT_GRAY;
                uCardSlots.setBackground(Color.LIGHT_GRAY);
            }
            else if (c.getSuit() == '♤' || c.getSuit() == '♧') {
                cardColor = Color.BLACK;
            } else {
                cardColor = Color.RED;
            }
            uCardSlots.setForeground(cardColor);
            uCardSlots.setBorder(BorderFactory.createLineBorder(cardColor, 5));
            userSide.add(uCardSlots);
        }
        System.out.println("--------------------------------------");
    }

    public static void waitForRoundStart() {
        start = false;

        startRound = new JButton("Deal Out");
        startRound.setLocation(screenWidth / 2 - 125, screenHeight / 6 - 50);
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        divider.remove(startRound);
    }

    public static String promptDecision() {
        decision = null;

        chooseHit = new JButton("HIT");
        chooseHit.setLocation(screenWidth / 2 - 375, screenHeight / 6 - 50);
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
                System.out.println("i hitted");
            }
        });

        chooseStand = new JButton("STAND");
        chooseStand.setLocation(screenWidth / 2 + 125, screenHeight / 6 - 50);
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        divider.remove(chooseHit);
        divider.remove(chooseStand);
        return decision;
    }
}
