package BoardPackage;

import BoardPackage.PlayerPackage.Player;
import BoardPackage.StorePackage.Store;
import ToolsPackage.Card;
import ToolsPackage.Coin;

import java.util.Scanner;

public class Board {

    public Store store;

    public SlotMachine slotMachines;

    public Player[] player;

    public int turn;

    public int state;
    // 0 : player is about to choose between getting coins or cards or reserve a card.
    // 1 : player is going to choose a card to buy.
    // 2 : player is going to pay for a card they have chosen.
    // 3 : player is going to get coins from the slot machine.
    // 4 : player is going to reserve a card.
    // 5 : player is going to buy a reserved card.
    // 6 : game has ended.

    public int walletCoinCnt;

    public int[] walletForCoins;

    public int[][] walletForCoinsPay;

    public int[][] walletForCards;

    public int chosenCardLevel;

    public int chosenCardId;

    public int reservedChosenCardId;

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

        state = 0;

        walletForCoins = new int[5 + 2];

        walletForCards = new int[3][5 + 2];

        walletForCoinsPay = new int[3][5 + 2];
    }

    public int whoWins() {

        if (player[0].score >= 5)
            return 0;

        if (player[1].score >= 5)
            return 1;

        return -1;
    }

    public void getPrizeClaw(int id) {
        player[turn].addPrizeClaw(store.cards[0][id]);
        store.removePrizeClaw(id);
    }

    public void getCard(int type) {
        Card card;
        if (type == 0)
            card = store.cards[chosenCardLevel][chosenCardId];
        else
            card = player[turn].reserve[reservedChosenCardId];

        player[turn].payThePrice(walletForCoinsPay[turn]);
        player[turn].addCard(card);
        if (type == 0)
            store.removeCard(chosenCardLevel, chosenCardId);
        else
            player[turn].removeReserve(reservedChosenCardId);
        slotMachines.addCoin(walletForCoinsPay[turn]);
    }

    public void emptyWallet() {

        walletCoinCnt = 0;
        for (int i = 0; i <= 5; i++) {
            walletForCoins[i] = 0;
            walletForCards[0][i] = 0;
            walletForCards[1][i] = 0;
            walletForCoinsPay[0][i] = 0;
            walletForCoinsPay[1][i] = 0;
        }
    }

    public boolean canBePaid(Card card) {

        int goldCoinsInUse = 0;
        for (int i = 0; i < 5; i++)
            if (walletForCoinsPay[turn][i] + walletForCards[turn][i] < card.price[i])
                goldCoinsInUse += card.price[i] - walletForCoinsPay[turn][i] - walletForCards[turn][i];

        return goldCoinsInUse <= walletForCoinsPay[turn][5]? true: false;
    }

    public void getCoins() {
        player[turn].addCoins(walletForCoins);
        slotMachines.removeCoins(walletForCoins);
    }

    public void getReserve() {
        player[turn].addReserve(store.cards[chosenCardLevel][chosenCardId]);
        store.removeCard(chosenCardLevel, chosenCardId);
        emptyWallet();
        if (slotMachines.validCoin(walletForCoins, walletCoinCnt, 5))
            walletForCoins[5]++;
        player[turn].addCoins(walletForCoins);
        slotMachines.removeCoins(walletForCoins);
    }

}
