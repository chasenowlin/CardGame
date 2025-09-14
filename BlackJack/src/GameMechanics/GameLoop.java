package GameMechanics;

import CardBasics.*;

public class GameLoop {

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
            return true;
        }
        return false;
    }

    public static void playRound(Dealer dealer, Player user) {
        dealer.shuffle();
        dealer.dealOut(user);
        String turn = "user";
        BJTable.printBoard(turn, dealer, user);

        while (busted(user) == false && turn.equals("user")) {
            String decision = BJTable.promptDecision();
            if (decision.equals("hit")) {
                dealer.hit(user);
                BJTable.printBoard(turn, dealer, user);
            } else {
                turn = "dealer";
            }
        }
        turn = "dealer";
        BJTable.printBoard(turn, dealer, user);
        
        while (dealer.getHandValue() < 17) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            dealer.hit(dealer);
            BJTable.printBoard(turn, dealer, user);
        }
    }

}
