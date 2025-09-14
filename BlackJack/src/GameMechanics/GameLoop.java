package GameMechanics;

import CardBasics.*;

public class GameLoop {

    private static boolean uBust = false;
    private static boolean dBust = false;

    public static void runBJ() {
        Deck gameDeck = new Deck();   
        Dealer dealer = new Dealer(gameDeck);
        Player user = new Player(MainMenu.username);

        while (true) {
            BJTable.waitForRoundStart();
            playRound(dealer, user);
        }
    }

    public static boolean busted(Player p) {
        if (p.getHandValue() > 21) {
            BJTable.showBusted(p);
            return true;
        }
        return false;
    }

    public static void playRound(Dealer dealer, Player user) {
        dealer.shuffle();
        dealer.dealOut(user);
        String turn = "user";
        uBust = false;
        dBust = false;

        while (turn.equals("user")) {
            BJTable.printBoard(turn, dealer, user, dBust, uBust);
            String decision = BJTable.promptDecision();
            if (decision.equals("hit")) {
                dealer.hit(user);
                BJTable.printBoard(turn, dealer, user, dBust, uBust);
                if (busted(user)) {
                    uBust = true;
                    BJTable.printBoard(turn, dealer, user, dBust, uBust);
                    turn = "dealer";
                }
            } else {
                turn = "dealer";
            }
        }
        wait(2000);
        while (turn.equals("dealer")) {
            BJTable.printBoard(turn, dealer, user, dBust, uBust);
            if (dealer.getHandValue() < 17) {
                wait(2000);
                dealer.hit(dealer);
                BJTable.printBoard(turn, dealer, user, dBust, uBust);
                if (busted(dealer)) {
                    turn = "finished";
                    dBust = true;
                    BJTable.printBoard(turn, dealer, user, dBust, uBust);
                }
            } else {
                turn = "finished";
            }
        }

        if (uBust && dBust) {
            BJTable.showWinner(null , null);
        } else if (uBust && !dBust) {
            BJTable.showWinner(dealer, user);
        } else if (!uBust && dBust) {
            BJTable.showWinner(user, dealer);
        } else {
            if (user.getHandValue() == dealer.getHandValue()) {
                BJTable.showWinner(null, null);
            } else if (user.getHandValue() < dealer.getHandValue()) {
                BJTable.showWinner(dealer, user);
            } else {
                BJTable.showWinner(user, dealer);
            }
        }
        
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
