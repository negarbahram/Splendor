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

    public boolean getCoinSecondType(int coinType) {

        if (coin[coinType].count == 0)
            return false;

        coin[coinType].count--;
        return true;
    }

    public void addCoin(int[] wallet) {
        for (int i = 0; i <= 5; i++)
            coin[i].count += wallet[i];
    }

    public boolean readyToGet(int[] wallet, int count) {

        if (count == 3)
            return true;

        if (count == 0) {
            for (int i = 0; i < 5; i++)
                if (coin[i].count - wallet[i] > 0)
                    return false;
            return true;
        }

        if (count == 1) {

            int isThereAnyMore = 0;
            for (int i = 0; i < 5; i++)
                if (coin[i].count - wallet[i] > 0)
                    isThereAnyMore++;

            if (isThereAnyMore > 1)
                return false;
            else
                return true;
        }

        int typeCount = 0;
        for (int i = 0; i < 5; i++)
            if (wallet[i] > 0)
                typeCount++;

        if (typeCount == 1)
            return true;

        int isThereAnyMore = 0;
        for (int i = 0; i < 5; i++)
            if (coin[i].count - wallet[i] > 0)
                isThereAnyMore++;

        if (isThereAnyMore > 2)
            return false;

        return true;
    }

    public void removeCoins(int[] wallet) {
        for (int i = 0; i <= 5; i++)
            coin[i].count -= wallet[i];
    }

    public boolean validCoin(int[] wallet, int count, int type) {
        if (coin[type].count - wallet[type] == 0)
            return false;

        if (count == 0 || count == 1)
            return true;

        for (int i = 0; i <= 5; i++)
            if (wallet[i] > 0 && i == type)
                return false;

        return true;
    }
}
