public class Board {

    public Store store;

    public SlotMachine slotMachines;

    public Player[] player;

    public int turn;

    public Board(String firstPlayer, String secondPlayer) {

        this.store = new Store();

        Coin[] coins = new Coin[7 + 3];
        for (int i = 0; i < 5; i++)
            coins[i] = new Coin(4);
        coins[5] = new Coin(5);

        this.slotMachines = new SlotMachine(coins);

        this.player = new Player[2 + 1];
        player[0] = new Player(firstPlayer);
        player[1] = new Player(secondPlayer);

        turn = 0;
    }
    
}
