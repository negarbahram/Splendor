public class Wallet {
    private int[] coinCount;
    // coinCount[coinType] = how many coins of type coinType exists in the wallet.
    // 0 : green
    // 1 : white
    // 2 : black
    // 3 : blue
    // 4 : red
    // 5 : gold

    public boolean isThereEnoughCoin(int[] prize) {

        int goldCoinsInUse = 0;
        for (int i = 0; i < 5; i++)
            if (coinCount[i] < prize[i])
                goldCoinsInUse += prize[i] - coinCount[i];

        return goldCoinsInUse <= coinCount[5]? true: false;
    }
}