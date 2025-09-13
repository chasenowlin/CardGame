package GameMechanics;

import java.util.ArrayList;
import java.util.Scanner;
import CardBasics.*;

import javax.swing.*;
import java.awt.GridLayout;

public class Launcher{

    public static JFrame frame;

	public Launcher() {
        createWindow();
        runBJ();
	}

    public void createWindow() {
		
        frame = new JFrame("Black Jack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 3));
        frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

        
	}

    public void runBJ() {
        Deck gameDeck = new Deck();   
        Dealer dealer = new Dealer(gameDeck);
        Scanner scnr = new Scanner(System.in);

        System.out.println("What is your name?: ");
        Player user = new Player(scnr.nextLine());
        scnr.close();

        playRound(dealer, user);

    }

    public void playRound(Dealer dealer, Player user) {
        dealer.shuffle();
        dealer.dealOut(user);
        String turn = "user";
        printBoard(turn, dealer, user);

        System.out.println("Hit or Stand?: ");
    }

    public void printBoard(String turn, Dealer dealer, Player user) {
        ArrayList<Card> dealerCards = new ArrayList<Card>();
        ArrayList<Card> userCards = new ArrayList<Card>();
        String cardTemplate1 = "+-------+\n|";
        String cardTemplate2 = "      |\n|       |\n|       |\n|      ";
        String cardTemplate3 = "|\n+-------+";
        if (turn == "user") {
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
            System.out.println(cardTemplate1 + c.getRank() + cardTemplate2 + c.getRank() + cardTemplate3);
        }

        System.out.println();
        System.out.println(user.getPlayerName() + "'s Hand:");
        for (Card c: userCards) {
            System.out.println(cardTemplate1 + c.getRank() + cardTemplate2 + c.getRank() + cardTemplate3);
        }
        System.out.println("--------------------------------------");
    }
	
	public static void main(String[] args) {
		new Launcher();
	}
}