package board;

import cards.*;

import java.util.ArrayList;

import static board.Board.getDecayPile;
import static board.Board.getForest;

public class Player {
    private final Hand h;
    private final Display d;
    private int score;
    private int sticks;
    private int handlimit;
//    public int getScore();

    public Player() {
        h = new Hand();
        d = new Display();
        handlimit = 8;
        score = 0;
        sticks = 0;
        addCardtoDisplay(new Pan());
    }

    public int getScore() {
        return score;
    }

    public int getHandLimit() {
        return handlimit;
    }

    public int getStickNumber() {
        return sticks;
    }

    public void addSticks(int a) {
        for (int i = 0; i < a; i++) {
            sticks += 1;
            addCardtoDisplay(new Stick());
        }
    }

    public void removeSticks(int a) {
        sticks -= a;
        for (int i = 0; i < a; i++) {
            for (int index = 0; index < getDisplay().size(); index++) {
                if (getDisplay().getElementAt(index).getType() == CardType.STICK) {
                    getDisplay().removeElement(index);
                    break;
                }
            }
        }
    }

    public Hand getHand() {
        return h;
    }

    public Display getDisplay() {
        return d;
    }

    public void addCardtoHand(Card a) {
        if (a.getType() == CardType.BASKET) {
            addCardtoDisplay(a);
            handlimit += 2;
        } else {
            h.add(a);
        }
    }

    public void addCardtoDisplay(Card a) {
        d.add(a);
    }

    public boolean takeCardFromTheForest(int i) {
        if (this.getStickNumber() < i - 2) {
            return false;
        }
        if (i >= 3) {
            this.removeSticks(i - 2);
        }
        if (h.size() < handlimit) {
            if (getForest().getElementAt(8 - i).getType() == CardType.BASKET) {
                handlimit += 2;
                addCardtoDisplay(getForest().getElementAt(8 - i));
            } else {
                this.addCardtoHand(getForest().getElementAt(8 - i));
                getForest().removeCardAt(8 - i);
            }
        }
        if (h.size() == handlimit) {
            if (getForest().getElementAt(8 - i).getType() == CardType.BASKET) {
                handlimit += 2;
                addCardtoDisplay(getForest().getElementAt(8 - i));
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean takeFromDecay() {
        int num_basket = 0;
        if (h.size() + getDecayPile().size() > this.handlimit) {
            for (int i = 0; i < getDecayPile().size(); i++) {
                if (getDecayPile().get(i).getType() == CardType.BASKET) {
                    num_basket += 1;
                }
            }
            if (getHand().size() + getDecayPile().size() - num_basket > getHandLimit() + num_basket * 2) {
                return false;
            } else {
                for (Card c : getDecayPile()) {
                    addCardtoHand(c);
                }
                Board.getDecayPile().clear();
                return true;
            }
        } else if (h.size() + getDecayPile().size() <= this.handlimit) {
            for (Card c : getDecayPile()) {
                addCardtoHand(c);
            }
            Board.getDecayPile().clear();
            return true;

        } else {
            System.out.println("2");
            return false;
        }
    }

    public boolean cookMushrooms(ArrayList<Card> a) {
        boolean panstat = false;
        int pan_in_h = 0;
        int pan_in_d = 0;
        int pan_in_a = 0;
        int num_butter = 0;
        int num_cider = 0;
        int butter_out = 0;
        int cider_out = 0;
        int numday = 0;
        int numnight = 0;
        Card mush = null;
        Card mushcook = null;
//        if (this.h.size() != 0) {
//            for (int i = 0; i < this.h.size(); i++) {
//                if (this.h.getElementAt(i).getType() == CardType.PAN) {
//                    pan_in_h += 1;
//                    return true;
//                }
//                else {
//                    return false;
//                }
//            }
//        }
//        for (int i = 0; i < this.d.size(); i++) {
//            if (this.d.getElementAt(i).getType() == CardType.PAN) {
//                pan_in_d += 1;
//                return true;
//            }
//            else {
//                return false;
//            }
//        }
//        for (int i = 0; i<a.size();i++){
//            if (a.get(i).getType()==CardType.BASKET);
//            return false;
//        }
//        for (int i = 0; i<a.size();i++){
//            if (a.get(i).getType()==CardType.STICK);
//            return false;
//        }
        for (int j = 0; j < a.size(); j++) {
            if (a.get(j).getType() == CardType.DAYMUSHROOM || a.get(j).getType() == CardType.NIGHTMUSHROOM) {
                if (mush == null) {
                    mush = a.get(j);
                } else if (mush != null) {
                    if (mush.getName() != a.get(j).getName()) {
                        return false;
                    }
                }
//            if (!Objects.equals(a.get(j).getName(), a.get(j + 1).getName())){
                if (a.get(j).getType() == CardType.DAYMUSHROOM) {
                    numday += 1;
                }
                if (a.get(j).getType() == CardType.NIGHTMUSHROOM) {
                    numnight += 1;
                }
            } else if (a.get(j).getType() == CardType.BUTTER) {
                butter_out += 1;
            } else if (a.get(j).getType() == CardType.PAN) {
                panstat = true;
                pan_in_a += 1;
            } else if (a.get(j).getType() == CardType.CIDER) {
                cider_out += 1;
            }
            if (a.get(j).getType()==CardType.BASKET){
                return false;
            }
        }
        for (int i = 0; i < d.size(); i++) {
            if (d.getElementAt(i).getType() == CardType.PAN) {
                pan_in_d += 1;
            }
        }
        for (int i = 0; i < h.size();i++){
            if (h.getElementAt(i).getType()==CardType.PAN){
                pan_in_h +=1;
            }
        }
//        if (panstat){
//            return false;
//        }
        if (numnight*2+numday < 3) {
            return false;
        }
        if (pan_in_d == 0 && pan_in_a == 0 && pan_in_h==0) {
            return false;
        }
        if (butter_out * 4 + cider_out * 5 > numnight*2+numday) {
            return false;
        }

        int mark = new EdibleItem(mush.getType(), mush.getName()).getFlavourPoints();

        score += mark * numday + 3*butter_out + 5*cider_out + mark * numnight*2;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getType() == CardType.PAN) {
                a.remove(i);
                break;
            }
        }
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getType() == CardType.PAN) {
                h.removeElement(i);
                break;
            } else if (h.getElementAt(i).getType() == a.get(0).getType()) {
                h.removeElement(i);
                break;
            }


        }
        for (int i = 0; i < d.size(); i++) {
            if (d.getElementAt(i).getType() == CardType.PAN) {
                d.removeElement(i);
                break;
            }
        }

        return true;

    }
//        if (this.h.size() != 0) {
//            for (int i = 0; i < this.h.size(); i++) {
//                if (this.h.getElementAt(i).getType() == CardType.BUTTER) {
//                    num_butter += 1;
//                }
//            }
//            for (int i = 0; i < this.h.size(); i++) {
//                if (this.h.getElementAt(i).getType() == CardType.CIDER) {
//                    num_cider += 1;
//                }
//            }
//
//        }

//        this.score += new EdibleItem(card_in_a.get(0).getType(), card_in_a.get(0).getName()).getFlavourPoints() * a.size();
//        return false;

    public boolean sellMushrooms(String a, int s) {
//        a = a.replaceAll("[$&+,:;=?@#|'<>.^*()%!-]", "");
        a = a.replaceAll("[^a-zA-Z ]", "");
        a = a.toLowerCase();
        a = a.replaceAll("\\s+", "");
        a = a.replaceAll(" ", "");
        int cardnum = 0;
        int count = 0;
        int stkvalue = 0;
        int point = new Mushroom(CardType.DAYMUSHROOM, a).getSticksPerMushroom();
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getName().equals(a)) {
//                Mushroom sellcard = (Mushroom)(h.getElementAt(i));
//                stkvalue = sellcard.getSticksPerMushroom();
//                int point = new Mushroom(CardType.DAYMUSHROOM,a).getSticksPerMushroom();
//                int point = new Mushroom(CardType.DAYMUSHROOM,a).getSticksPerMushroom();
//                cardnum +

                if (h.getElementAt(i).getType() == CardType.DAYMUSHROOM) {
                    count += 1;
                }
                if (h.getElementAt(i).getType() == CardType.NIGHTMUSHROOM) {
                    count += 2;
                }
            }
        }
        if (count < s || s < 2) {
            return false;
        }
//     this.sticks += stkvalue*count;
        int total = point * s;
        addSticks(total);
        for (int m = 0; m < s; m++) {
            for (int j = 0; j < h.size(); j++) {
                if (h.getElementAt(j).getName().equals(a) && h.getElementAt(j).getType() == CardType.DAYMUSHROOM) {
                    h.removeElement(j);
                    break;
                } else if (h.getElementAt(j).getName().equals(a) && h.getElementAt(j).getType() == CardType.NIGHTMUSHROOM) {
                    h.removeElement(j);
                    m += 1;
                    break;
                }
            }
        }
        return true;
    }

    public boolean putPanDown() {
        int count = 0;
        for (int i = 0; i < getHand().size(); i++) {
            if (getHand().getElementAt(i).getType() == CardType.PAN) {
                count += 1;
                addCardtoDisplay(getHand().removeElement(i));
                break;
            }
        }
        return count != 0;
    }

}
