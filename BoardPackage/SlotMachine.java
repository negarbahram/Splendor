package BoardPackage;

import BoardPackage.PlayerPackage.Wallet;
import ToolsPackage.Coin;

public class SlotMachine extends Wallet {

    // coin[coinType].count = how many coins of type coinType exists in the wallet.
    // 0 : green
    // 1 : white
    // 2 : black
    // 3 : blue
    // 4 : red
    // 5 : gold

    public SlotMachine(Coin[] coin) {

        super(coin);
    }

    public boolean getCoinFirstType(int coinType) {

        if (coin[coinType].count < 4)
            return false;

        coin[coinType].count -= 2;
        return true;
    }

    public void getCoinSecondType(int[] coinType) {

        for (int i = 0; i < 3; i++)
            coin[coinType[i]].count--;

    }
}
