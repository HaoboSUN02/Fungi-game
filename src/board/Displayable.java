package board;

import cards.Card;

public interface Displayable {
    void add(Card c);

    int size();

    Card getElementAt(int a);

    Card removeElement(int a);


}
