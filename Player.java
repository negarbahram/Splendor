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
        for (int i = 0; i < 5; i++)
            coins[i] = new Coin(0);

        this.wallet = new Wallet(coins);

        cardCount = new int[7 + 3];
        for (int i = 0; i <= 5; i++)
            cardCount[i] = 0;

        cards = new Card[7 + 3][50 + 10];
    }
}
