package CardBasics;

import java.util.ArrayList;

public class Player {

    private String name;
    private Hand currentHand = new Hand();;

    public Player(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return currentHand.getInHand();
    }

    public int getHandValue() {
        return currentHand.getValue();
    }

    public void addToHand(Card c) {
        currentHand.addCard(c);
    }

    public void resetHand() {
        currentHand = new Hand();
    }


}
