package BoardPackage.PlayerPackage;

import ToolsPackage.*;

public class Player {

    public String name;

    public Wallet wallet;

    public int[] cardCount;
    // cardCount[cardValue] : Number of cards with the value of cardValue.

    public int prizeClawCount;
    public Card[] prizeClaw;

    public int reserveCount;

    public Card[] reserve;

    public int score;

    public Player(String name) {

        this.name = name;

        this.score = 0;

        score = 0;

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

        prizeClawCount = 0;

        prizeClaw = new Card[5 + 2];

        reserveCount = 0;

        reserve = new Card[5 + 2];
    }

    public boolean isThereEnoughCards(int[] prize) {

        for (int i = 0; i < 5; i++)
            if (cardCount[i] < prize[i])
                return false;

        return true;
    }

    public void addPrizeClaw(Card prizeClaw) {
        this.prizeClaw[prizeClawCount++] = prizeClaw;
    }

    public void payThePrice(int[] wallet) {
        for (int i = 0; i <= 5; i++)
            this.wallet.coin[i].count -= wallet[i];
    }

    public void addCard(Card card) {
        cardCount[card.value]++;
        score += card.point;
    }

    public void addCoins(int[] wallet) {
        for (int i = 0; i <= 5; i++)
            this.wallet.coin[i].count += wallet[i];
    }

    public void addReserve(Card card) {
        reserve[reserveCount] = card;
        reserveCount++;
    }

    public void removeReserve(int id) {
        reserveCount--;
        for (int i = id; i < reserveCount; i++)
            reserve[i] = reserve[i + 1];
    }
}
