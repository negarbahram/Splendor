package BoardPackage.PlayerPackage;

import ToolsPackage.Coin;

public class Wallet {

    public Coin[] coin;
    // coin[coinType].count = how many coins of type coinType exists in the wallet.
    // 0 : green
    // 1 : white
    // 2 : black
    // 3 : blue
    // 4 : red
    // 5 : gold

    public Wallet(Coin[] coin) {

        this.coin = coin;
    }

    public boolean isThereEnoughCoins(int[] price) {

        int goldCoinsInUse = 0;
        for (int i = 0; i < 5; i++)
            if (coin[i].count < price[i])
                goldCoinsInUse += price[i] - coin[i].count;

        return goldCoinsInUse <= coin[5].count? true: false;
    }
}