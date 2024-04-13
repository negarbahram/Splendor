
public class SlotMachine extends Wallet {

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
