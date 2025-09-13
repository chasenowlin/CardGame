package GameMechanics;

import java.util.Collections;

import CardBasics.*;

public class Dealer extends Player {

    Deck gameDeck;


    public Dealer(Deck deck) {
        super("Dealer");
        gameDeck = deck;
    }

    public void shuffle() {
        gameDeck.createNewDeck();
        Collections.shuffle(gameDeck.getActiveCards());
    }

    public void dealOut(Player p) {
        p.addToHand(gameDeck.takeTopCard());
        addToHand(gameDeck.takeTopCard());
        p.addToHand(gameDeck.takeTopCard());
        addToHand(gameDeck.takeTopCard());
    }

    public void hit(Player p) {
        p.addToHand(gameDeck.takeTopCard());
    }

}
