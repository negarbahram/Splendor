import BoardPackage.Board;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String firstPlayer = input.next();
        String secondPlayer = input.next();


        Board board = new Board(firstPlayer, secondPlayer);

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

        JFrame frame = new JFrame();
        frame.setTitle("Splendor");
        frame.setSize(1300, 850);
        frame.setResizable(false);
        frame.setVisible(true);

       /* while (board.whoWins() == -1) {
            int nextMove = input.nextInt();
            // 0 : getting coins type 1
            // 1 : getting coins type 2
            // 2 : buy card
            // 3 : reserve card
            if (board.nextMoveProcess(nextMove))
                board.turn ^= 1;
        }

        */
    }
}