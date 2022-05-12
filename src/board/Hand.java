package board;

import cards.Card;

import java.util.ArrayList;

public class Hand implements Displayable {
    private final ArrayList<Card> handList = new ArrayList<>();

    public void add(Card c) {
        handList.add(c);
    }

    public int size() {
        return handList.size();
    }

    public Card getElementAt(int e) {
        return handList.get(e);
    }

    public Card removeElement(int r) {
        return handList.remove(r);
    }
}
