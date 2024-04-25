package BoardPackage.StorePackage;

import ToolsPackage.Card;

public class Store {

    public int[] cardCount;
    // cardCount[Level] : Number of available cards in the store with level of Level.

    public Card[][] cards;
    // cards[Level] : List of available cards in the store with level of Level.

    public Store() {

        cardCount = new int[5 + 2];
        // Making a list of cards with their count to save store's remaining cards based on their level.

        cardCount[0] = 3;
        for (int i = 1; i <= 3; i++)
            cardCount[i] = 15;

        cards = new Card[5 + 2][];

        for (int i = 0; i <= 3; i++)
            cards[i] = Card.generateCards(i, cardCount[i]);
    }

    public void removePrizeClaw(int id) {
        cardCount[0]--;
        for (int i = id; i < cardCount[0]; i++)
            cards[0][i] = cards[0][i + 1];
    }

    public void removeCard(int level, int id) {
        cardCount[level]--;
        for (int i = id; i < cardCount[level]; i++)
            cards[level][i] = cards[level][i + 1];
    }
}
