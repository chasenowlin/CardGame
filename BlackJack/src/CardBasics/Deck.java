package CardBasics;
import java.util.ArrayList;

public class Deck {
    
private ArrayList<Card> activeCards = new ArrayList<Card>();

public Deck() {
    createNewDeck();
}

public void createNewDeck() {
    activeCards.clear();
    for (char s: Suit.suitList) {
        for (String r: Rank.rankList) {
            activeCards.add(new Card(r, s));
        }
    }
}

public ArrayList<Card> getActiveCards() {
    return activeCards;
}

public Card takeTopCard() {
    Card topCard = activeCards.get(0);
    activeCards.remove(0);
    return topCard;
}

public void printDeck() {
    for (Card c: activeCards) {
        System.out.print(c.getRank() + ":" + c.getSuit() + " | ");
    }
}

}
