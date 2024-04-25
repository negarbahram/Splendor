import BoardPackage.Board;
import GraphicPackage.GameWindow;
import GraphicPackage.StartingWindow;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StartingWindow startingWindow = new StartingWindow();

        while (startingWindow.secondPlayerName == null)
            System.out.println("Waiting for players to enter their names...");

        Board board = new Board(startingWindow.firstPlayerName, startingWindow.secondPlayerName);
        GameWindow gameWindow = new GameWindow(board);

        // Check store : done!
        /*
        for (int i = 0; i <= 3; i++) {
            System.out.println(i + " : " + board.store.cardCount[i]);
            for (int j = 0; j < board.store.cardCount[i]; j++) {
                System.out.println(board.store.cards[i][j].type);
                System.out.println(board.store.cards[i][j].value);
                System.out.println(board.store.cards[i][j].point);
                System.out.println("price :");
                for (int k = 0; k <= 5; k++)
                    System.out.println("  " + k + " : " + board.store.cards[i][j].price[k]);
                System.out.println("************");
            }
            System.out.println("################");
        }
         */

        // Check lot machine
        /*
        for (int i = 0; i <= 5; i++)
            System.out.println(i + " : " + board.slotMachines.coin[i].count);
         */

        // Check Players :
        /*
        System.out.println("first player :");
        System.out.println(board.player[0].name);
        System.out.println(board.player[0].points);
        for (int i = 0; i <= 5; i++)
            System.out.println(board.player[0].wallet.coin[i].count);
        for (int i = 0; i <= 5; i++) {
            System.out.println(i + " : " + board.player[0].cardCount[i]);
            for (int j = 0; j < board.player[0].cardCount[i]; j++) {
                System.out.println(board.player[0].cards[i][j].type);
                System.out.println(board.player[0].cards[i][j].value);
                System.out.println(board.player[0].cards[i][j].point);
                System.out.println("price :");
                for (int k = 0; k <= 5; k++)
                    System.out.println("  " + k + " : " + board.player[0].cards[i][j].price[k]);
                System.out.println("************");
            }
            System.out.println("################");
        }

        System.out.println("second player :");
        System.out.println(board.player[1].name);
        System.out.println(board.player[1].points);
        for (int i = 0; i <= 5; i++)
            System.out.println(board.player[1].wallet.coin[i].count);
        for (int i = 0; i <= 5; i++) {
            System.out.println(i + " : " + board.player[1].cardCount[i]);
            for (int j = 0; j < board.player[1].cardCount[i]; j++) {
                System.out.println(board.player[1].cards[i][j].type);
                System.out.println(board.player[1].cards[i][j].value);
                System.out.println(board.player[1].cards[i][j].point);
                System.out.println("price :");
                for (int k = 0; k <= 5; k++)
                    System.out.println("  " + k + " : " + board.player[0].cards[i][j].price[k]);
                System.out.println("************");
            }
            System.out.println("################");
        }
        */
    }
}