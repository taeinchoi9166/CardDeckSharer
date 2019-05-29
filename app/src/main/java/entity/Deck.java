package entity;

import java.util.List;
import java.util.Map;

class Deck {
    private List<Card> cardList;
    private Map<Integer,Integer> countMap;

    public Deck() {

    }

    public Deck(List<Card> cardList, Map<Integer, Integer> countMap) {
        this.cardList = cardList;
        this.countMap = countMap;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Map<Integer, Integer> getCountMap() {
        return countMap;
    }

    public void setCountMap(Map<Integer, Integer> countMap) {
        this.countMap = countMap;
    }

    public void changeCardCount(int cardId,int count){
        this.countMap.remove(cardId);
        this.countMap.put(cardId,count);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cardList=" + cardList +
                ", countMap=" + countMap +
                '}';
    }
}
