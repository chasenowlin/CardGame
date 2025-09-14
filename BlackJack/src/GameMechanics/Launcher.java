package GameMechanics;

import java.util.ArrayList;
import CardBasics.*;

import javax.swing.*;
import java.awt.*;

public class Launcher implements OnScreen {

    
    

    private static JFrame frame;
    private static JPanel dealerSide;
    private static JPanel divider;
    private static JPanel userSide;

    private static JLabel dNamePlate;
    private static JLabel uNamePlate;

    private static JLabel uCardSlots;

    private static JLabel dCardSlots;


    private static JPanel nesterUser;
    
    private String username;

	public Launcher() {
        username = MainMenu.startingWindow();
        gameWindow();
        runBJ();
	}

    public void gameWindow() {
		
        frame = new JFrame("BlackJack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 3));;
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        device.setFullScreenWindow(frame);

        dealerSide = new JPanel();
        Color darkGreen = new Color(53, 101, 77);
        dealerSide.setBackground(darkGreen);
        dealerSide.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        dealerSide.setLayout(null);

        dNamePlate = new JLabel("Dealer");
        dNamePlate.setLocation(screenWidth / 2 - 75, 0);
        dNamePlate.setSize(150,40);
        dNamePlate.setOpaque(true);
        dNamePlate.setBackground(new Color(213, 181, 110));
        dNamePlate.setFont(new Font("Arial", Font.BOLD, 25));
        dNamePlate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        dNamePlate.setHorizontalAlignment(SwingConstants.CENTER);
        dNamePlate.setVerticalAlignment(SwingConstants.CENTER);

        uNamePlate = new JLabel(username);
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
        nesterUser.setLayout(new FlowLayout(FlowLayout.LEADING));

        userSide = new JPanel();
        userSide.setBackground(darkGreen);
        //userSide.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        userSide.setLayout(new GridLayout(1,10));
        ((GridLayout) userSide.getLayout()).setHgap(10);

        frame.add(dealerSide);
        frame.add(divider);
        frame.add(nesterUser);
        nesterUser.add(userSide);
        divider.add(dNamePlate);
        divider.add(uNamePlate);

        frame.setVisible(true);
	}

    public void runBJ() {
        Deck gameDeck = new Deck();   
        Dealer dealer = new Dealer(gameDeck);
        Player user = new Player(username);
        playRound(dealer, user);

    }

    public void playRound(Dealer dealer, Player user) {
        dealer.shuffle();
        dealer.dealOut(user);
        String turn = "user";
        printBoard(turn, dealer, user);

        System.out.println("Hit or Stand?: ");
        frame.repaint();
        frame.revalidate();
    }

    public void printBoard(String turn, Dealer dealer, Player user) {
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
            uCardSlots = new JLabel(c.getRank());
            uCardSlots.setPreferredSize(new Dimension(150,250));
            uCardSlots.setOpaque(true);
            uCardSlots.setBackground(Color.WHITE);
            uCardSlots.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
            uCardSlots.setHorizontalAlignment(SwingConstants.LEADING);
            uCardSlots.setVerticalAlignment(SwingConstants.BOTTOM);
            userSide.add(uCardSlots);
        }

        System.out.println();
        System.out.println(user.getPlayerName() + "'s Hand:");
        for (Card c: userCards) {
            //System.out.println(cardTemplate1 + c.getRank() + cardTemplate2 + c.getRank() + cardTemplate3);
        }
        System.out.println("--------------------------------------");
    }
	
	public static void main(String[] args) {
		new Launcher();
	}
}