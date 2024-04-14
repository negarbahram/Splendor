package BoardPackage;

import BoardPackage.PlayerPackage.Player;
import BoardPackage.StorePackage.Store;
import ToolsPackage.Coin;

import java.util.Scanner;

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

    public int whoWins() {

        if (player[0].points >= 15)
            return 0;

        if (player[1].points >= 15)
            return 1;

        return -1;
    }

  /*  public boolean nextMoveProcess(int nextMove) {

        Scanner input = new Scanner(System.in);

        switch (nextMove) {
            case 0 :

                nextMove = input.nextInt(); // Coin Type

                if (!slotMachines.getCoinFirstType(nextMove))
                    return false;

                player[turn].wallet.coin[nextMove].count += 2;
                return true;
            case 1 :

                nextMove = input.nextInt();
                int cnt = 0;

                while (nextMove != 9 && cnt < 3) {

                    if (slotMachines.getCoinSecondType(nextMove)) {
                        player[turn].wallet.coin[nextMove].count++;
                        cnt++;
                    }

                    nextMove = input.nextInt();
                }
                return true;
            case 2 :

                int level = input.nextInt();
                nextMove = input.nextInt();

                if (! player[turn].wallet.isThereEnoughCoin(store.cards[level][nextMove].price))
                    return false;

                return true;
        }
    }

   */
}
