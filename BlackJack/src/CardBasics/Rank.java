package CardBasics;
public interface Rank {
    static final String[] rankList = {"1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    String getRank();
    int getValue();
}
