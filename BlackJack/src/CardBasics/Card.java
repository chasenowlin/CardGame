package CardBasics;
public class Card implements Rank, Suit{
    
    private String rank;
    private char suit;

    public Card(String rank, char suit){
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int getValue() {
        if (rank.equals("A")) {
            return 11; //aceValueSet();
        }
        else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            return 10;
        }
        else {
            return Integer.parseInt(rank);
        }
    }

    @Override
    public String getRank() {
        return rank;
    }

    @Override
    public char getSuit() {
        return suit;
    }

}
