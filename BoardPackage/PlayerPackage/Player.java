package BoardPackage.PlayerPackage;

import ToolsPackage.*;

public class Player {

    public String name;

    public int points;

    public Wallet wallet;

    public int[] cardCount;
    // cardCount[cardValue] : Number of cards with the value of cardValue.

    public Card[][] cards;
    // cards[cardValue] : List of cards with the value of cardValue.

    public Player(String name) {

        this.name = name;

        this.points = 0;

        Coin[] coins = new Coin[7 + 3];
        // Making a list of coins with their count to creat a wallet.

        for (int i = 0; i <= 5; i++)
            coins[i] = new Coin(0);
        // 0 : green
        // 1 : white
        // 2 : black
        // 3 : blue
        // 4 : red
        // 5 : gold

        this.wallet = new Wallet(coins);
        // Creating wallet.

        cardCount = new int[7 + 3];
        // Making a list of cards with their count to save player's cards based on their value.

        for (int i = 0; i <= 5; i++)
            cardCount[i] = 0;

        cards = new Card[7 + 3][50 + 10];
    }
}
