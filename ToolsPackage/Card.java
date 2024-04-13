package ToolsPackage;

import java.util.Random;

public class Card {

    public int type;
    // 1 is the easiest to buy and 3 is the hardest. 0 is prizeClaw.

    public int[] price;
    // Prize[coinType] : how many coins of type coinType should be paid to buy this card.

    public int value;
    // What type of permanent discount will be considered for the owner :
    // 0 : green
    // 1 : white
    // 2 : black
    // 3 : blue
    // 4 : red

    public int point;
    // How much points will the owner get of buying this card

    // Constructor
    private Card(int type, int[] price, int value, int point) {

        this.type = type;
        this.price = price;
        this.value = value;
        this.point = point;
    }

    public static Card[] generateCards(int type, int count) {

        Card[] cardList = new Card[20 + 10];

        for (int i = 0; i < count; i++)
            if (type > 0) // The card is not prizeClaw
                cardList[i] = new Card(type, generatePrize(type), generateValue(type), generatePoint(type));
            else
                cardList[i] = new Card(type, generatePrize(type), -1, generatePoint(type));

        return cardList;
    }

    private static int[] generatePrize(int type) {

        int[] prize = new int[10 + 5];

        Random rand = new Random();

        int totalPrize = 0; // Total number of coin required to buy this type of card.
        switch (type) {
            case 1 : // FirstClass
                totalPrize = rand.nextInt(3) + 4;
                break;
            case 2 : // MiddleClass
                totalPrize = rand.nextInt(3) + 6;
                break;
            case 3 : // FinalClass
                totalPrize = rand.nextInt(4) + 7;
                break;
            case 0 : // PrizeClaw
                totalPrize = rand.nextInt(5) + 8;
                break;
        }

        // Randomly determine how many coin of each type (0, 1, 2, 3, 4) is needed.
        for (int i = 0; i < 4 && totalPrize > 0; i++) {
            prize[i] = rand.nextInt(totalPrize + 1);
            totalPrize -= prize[i];
        }
        prize[4] = totalPrize;

        return prize;
    }

    private static int generateValue(int type) {

        Random rand = new Random();

        return rand.nextInt(5);
    }

    private static int generatePoint(int type) {

        Random rand = new Random();

        int point = 0;
        switch (type) {
            case 1 : // FirstClass
                point = rand.nextInt(2);
                break;
            case 2 : // MiddleClass
                point = rand.nextInt(3) + 2;
                break;
            case 3 : // FinalClass
                point = rand.nextInt(3) + 3;
                break;
            case 0 : // PrizeClaw
                point = rand.nextInt(2) + 3;
                break;
        }

        return point;
    }
}

