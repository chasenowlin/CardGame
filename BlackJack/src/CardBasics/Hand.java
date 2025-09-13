package CardBasics;
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> inHand = new ArrayList<Card>();
    
    public ArrayList<Card> getInHand() {
        return inHand;
    }

    public void addCard(Card c) {
        inHand.add(c);
    }

    public int getValue() {
        int total = 0;
        for (Card c: inHand) {
            total += c.getValue();
        }
        return total;
    }

}
