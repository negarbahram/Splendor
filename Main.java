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
    }
}