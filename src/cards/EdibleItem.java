package cards;

public class EdibleItem extends Card {
    protected int flavourPoints;

    public EdibleItem(CardType cardType, String cardName) {
        super(cardType, cardName);
        switch (cardName) {
            case "honeyfungus":
                flavourPoints = 1;
                break;
            case "treeear":
                flavourPoints = 1;
                break;
            case "lawyerswig":
                flavourPoints = 2;
                break;
            case "shiitake":
                flavourPoints = 2;
                break;
            case "henofwoods":
                flavourPoints = 3;
                break;
            case "birchbolete":
                flavourPoints = 3;
                break;
            case "porcini":
                flavourPoints = 3;
                break;
            case "chanterelle":
                flavourPoints = 4;
                break;
            case "morel":
                flavourPoints = 6;
                break;
        }
    }

    public int getFlavourPoints() {
        return flavourPoints;


    }
}
