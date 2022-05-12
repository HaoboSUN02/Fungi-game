package board;

import cards.Card;

import java.util.ArrayList;

public class Display implements Displayable {
    private final ArrayList<Card> displayList = new ArrayList<>();

    public void add(Card c) {
        displayList.add(c);
    }

    public int size() {
        return displayList.size();
    }

    public Card getElementAt(int e) {
        return displayList.get(e);
    }

    public Card removeElement(int e) {
        return displayList.remove(e);
    }
}
