package cards;

public class Card {
    protected CardType type;

    protected String cardName;

    public Card(CardType type, String cardName) {
        this.type = type;
        this.cardName = cardName;
        // something
    }

    public CardType getType() {
        return type;
        // return
    }

    public String getName() {
        return cardName;
        // return
    }
}
