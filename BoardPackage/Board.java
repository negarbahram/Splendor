package BoardPackage;

import BoardPackage.PlayerPackage.Player;
import BoardPackage.StorePackage.Store;
import ToolsPackage.Coin;

public class Board {

    public Store store;

    public SlotMachine slotMachines;

    public Player[] player;

    public int turn;

    public Board(String firstPlayer, String secondPlayer) {

        this.store = new Store();
        // Creating store.

        Coin[] coins = new Coin[7 + 3];
        // Making a list of coins with their count to creat slot machines.

        for (int i = 0; i < 5; i++)
            coins[i] = new Coin(4);
        // 0 : green
        // 1 : white
        // 2 : black
        // 3 : blue
        // 4 : red

        coins[5] = new Coin(5);
        // 5 : gold

        this.slotMachines = new SlotMachine(coins);
        // Creating slot machine.

        this.player = new Player[2 + 1];
        // Creating a list of players of size 2;

        player[0] = new Player(firstPlayer);
        player[1] = new Player(secondPlayer);
        // Creating two players.

        turn = 0;
        // Set turn to zero, so that player one is the first to move.
    }
}
