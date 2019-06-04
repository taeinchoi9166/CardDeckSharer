package entity;

import java.util.List;
import java.util.Map;

public class Deck {
    private List<UserCard> cardList;

    public List<UserCard> getCardList() {
        return cardList;
    }

    public void setCardList(List<UserCard> cardList) {
        this.cardList = cardList;
    }
}
