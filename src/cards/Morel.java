package cards;

public class Morel extends Mushroom {
    public Morel(CardType cardType) {
        super(cardType, "morel");
        this.flavourPoints = 6;
        this.sticksPerMushroom = 4;
    }
}