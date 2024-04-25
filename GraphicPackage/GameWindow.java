package GraphicPackage;

import BoardPackage.Board;
import ToolsPackage.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.min;
import static sun.swing.MenuItemLayoutHelper.max;

public class GameWindow extends JFrame implements ActionListener {

    Board board;

    JButton[][] cardButton = new JButton[5 + 2][5 + 2];
    JButton[] coinButton = new JButton[7 + 2];

    public GameWindow() {
        board = new Board("neg", "nia");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Window");
        this.setSize(1475, 850);
        this.setLayout(null);

        JPanel storePanel = new JPanel(null);
        storePanel.setBounds(10, 10, 745, 800);
        storePanel.setBackground(Color.PINK);
        drawStore(storePanel);
        this.add(storePanel);

        JPanel slotMachinesPanel = new JPanel(null);
        slotMachinesPanel.setBounds(760, 10, 120, 800);
        slotMachinesPanel.setBackground(Color.orange);
        drawSlotMachines(slotMachinesPanel);
        this.add(slotMachinesPanel);

        JPanel firstPlayerPanel = new JPanel(null);
        firstPlayerPanel.setBounds(886, 10, 575, 397);
        firstPlayerPanel.setBackground(Color.green);
        drawPlayerTable(0, firstPlayerPanel);
        this.add(firstPlayerPanel);

        JPanel secondPlayerPanel = new JPanel(null);
        secondPlayerPanel.setBounds(886, 413, 575, 397);
        secondPlayerPanel.setBackground(Color.blue);
        drawPlayerTable(1, secondPlayerPanel);
        this.add(secondPlayerPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void drawPlayerTable(int playerId, JPanel panel) {

    }

    private void drawSlotMachines(JPanel panel) {
        JLabel storeTextLabel = new JLabel("Slot Machines");
        storeTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        storeTextLabel.setBounds(10, 0, 120, 120);
        panel.add(storeTextLabel);

        ImageIcon[] slotChip = new ImageIcon[7 + 2];

        slotChip[0] = new ImageIcon(getClass().getResource("resources/greenChip100.jpg"));
        slotChip[1] = new ImageIcon(getClass().getResource("resources/whiteChip100.jpg"));
        slotChip[2] = new ImageIcon(getClass().getResource("resources/blackChip100.jpg"));
        slotChip[3] = new ImageIcon(getClass().getResource("resources/blueChip100.jpg"));
        slotChip[4] = new ImageIcon(getClass().getResource("resources/redChip100.jpg"));
        slotChip[5] = new ImageIcon(getClass().getResource("resources/goldChip100.jpg"));

        int Y = 240;
        for (int i = 0; i < 5; i++) {
            coinButton[i] = new JButton();
            coinButton[i].setBounds(5, Y, 110, 110);
            coinButton[i].setLayout(null);
            JLabel slotChipLabel = new JLabel(slotChip[i]);
            JLabel slotMachinesCoinCountTextLabel = new JLabel(Integer.toString(board.slotMachines.coin[i].count));
            slotMachinesCoinCountTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            coinButton[i].add(slotMachinesCoinCountTextLabel);
            coinButton[i].add(slotChipLabel);
            slotChipLabel.setBounds(5, 5, 100, 100);
            slotMachinesCoinCountTextLabel.setBounds(47, 5, 100, 100);
            panel.add(coinButton[i]);
            Y += 111;
        }

        coinButton[5] = new JButton();
        coinButton[5].setBounds(5, 106, 110, 110);
        coinButton[5].setLayout(null);
        JLabel slotChipLabel = new JLabel(slotChip[5]);
        JLabel slotMachinesCoinCountTextLabel = new JLabel(Integer.toString(board.slotMachines.coin[5].count));
        slotMachinesCoinCountTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        coinButton[5].add(slotMachinesCoinCountTextLabel);
        coinButton[5].add(slotChipLabel);
        slotChipLabel.setBounds(5, 5, 100, 100);
        slotMachinesCoinCountTextLabel.setBounds(47, 5, 100, 100);
        panel.add(coinButton[5]);
    }

    private void drawStore(JPanel panel) {
        JLabel storeTextLabel = new JLabel("Store");
        storeTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        storeTextLabel.setBounds(20, 15, 200, 200);
        panel.add(storeTextLabel);

        for (int i = 0; i < board.store.cardCount[0]; i++) {
            cardButton[0][i] = new JButton();
            cardButton[0][i].setBounds(175 + i * 190, 40, 176, 176);
            drawPrizeClawCard(board.store.cards[0][i], cardButton[0][i]);
            panel.add(cardButton[0][i]);
        }

        JButton firstLevelCover = new JButton();
        firstLevelCover.setBounds(5, 240, 132, 176);
        panel.add(firstLevelCover);

        for (int i = 0; i < 4; i++) {
            cardButton[1][i] = new JButton();
            cardButton[1][i].setBounds(170 + i * 145, 240, 132, 176);
            drawCard(board.store.cards[1][i], cardButton[1][i]);
            panel.add(cardButton[1][i]);
        }

        JButton secondLevelCover = new JButton();
        secondLevelCover.setBounds(5, 430, 132, 176);
        panel.add(secondLevelCover);

        for (int i = 0; i < 4; i++) {
            cardButton[2][i] = new JButton();
            cardButton[2][i].setBounds(170 + i * 145, 430, 132, 176);
            drawCard(board.store.cards[2][i], cardButton[2][i]);
            panel.add(cardButton[2][i]);
        }

        JButton thirdLevelCover = new JButton();
        thirdLevelCover.setBounds(5, 620, 132, 176);
        panel.add(thirdLevelCover);

        JButton[] thirdLevel = new JButton[4 + 2];
        for (int i = 0; i < 4; i++) {
            cardButton[3][i] = new JButton();
            cardButton[3][i].setBounds(170 + i * 145, 620, 132, 176);
            drawCard(board.store.cards[3][i], cardButton[3][i]);
            panel.add(cardButton[3][i]);
        }
    }

    private void drawPrizeClawCard(Card card, JButton button) {
        button.setLayout(null);
        drawPrizeClawPrice(card, button);
        drawPoint(card, button);
    }

    private void drawPrizeClawPrice(Card card, JButton button) {
        int lastX = 17, lastY = 130;

        ImageIcon[] priceChip = new ImageIcon[7 + 2];

        priceChip[0] = new ImageIcon(getClass().getResource("resources/greenChip33.jpg"));
        priceChip[1] = new ImageIcon(getClass().getResource("resources/whiteChip33.jpg"));
        priceChip[2] = new ImageIcon(getClass().getResource("resources/blackChip33.jpg"));
        priceChip[3] = new ImageIcon(getClass().getResource("resources/blueChip33.jpg"));
        priceChip[4] = new ImageIcon(getClass().getResource("resources/redChip33.jpg"));


        for (int i = 0; i < 5; i++)
            for (int j = 0; j < card.price[i]; j++) {
                JLabel priceLabel = new JLabel(priceChip[i]);
                button.add(priceLabel);
                priceLabel.setBounds(lastX, lastY, 33, 33);
                lastX += 35;
                if (lastX >= 140) {
                    lastX = 17;
                    lastY -= 35;
                }
            }
    }
    private void drawCard(Card card, JButton button) {
        button.setLayout(null);
        drawValue(card, button);
        drawPrice(card, button);
        drawPoint(card, button);
    }

    private void drawPoint(Card card, JButton button) {
        JLabel pontLabel = new JLabel(Integer.toString(card.point));
        pontLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        button.add(pontLabel);
        pontLabel.setBounds(10, 0, 50, 50);
    }

    private void drawPrice(Card card, JButton button) {
        int lastX = 10, lastY = 151;

        ImageIcon[] priceChip = new ImageIcon[7 + 2];

        priceChip[0] = new ImageIcon(getClass().getResource("resources/greenChip15.jpg"));
        priceChip[1] = new ImageIcon(getClass().getResource("resources/whiteChip15.jpg"));
        priceChip[2] = new ImageIcon(getClass().getResource("resources/blackChip15.jpg"));
        priceChip[3] = new ImageIcon(getClass().getResource("resources/blueChip15.jpg"));
        priceChip[4] = new ImageIcon(getClass().getResource("resources/redChip15.jpg"));


        for (int i = 0; i < 5; i++)
            for (int j = 0; j < card.price[i]; j++) {
                JLabel priceLabel = new JLabel(priceChip[i]);
                button.add(priceLabel);
                priceLabel.setBounds(lastX, lastY, 15, 15);
                lastX += 16;
                if (lastX >= 110) {
                    lastX = 10;
                    lastY -= 16;
                }
            }
    }

    private void drawValue(Card card, JButton button) {
        ImageIcon valueChip = null;
        switch (card.value) {
            case 0:
                valueChip = new ImageIcon(getClass().getResource("resources/greenChip33.jpg"));
                break;
            case 1:
                valueChip = new ImageIcon(getClass().getResource("resources/whiteChip33.jpg"));
                break;
            case 2:
                valueChip = new ImageIcon(getClass().getResource("resources/blackChip33.jpg"));
                break;
            case 3:
                valueChip = new ImageIcon(getClass().getResource("resources/blueChip33.jpg"));
                break;
            case 4:
                valueChip = new ImageIcon(getClass().getResource("resources/redChip33.jpg"));
                break;
        }

        JLabel valeLabel = new JLabel(valueChip);
        button.add(valeLabel);
        valeLabel.setBounds(93, 10, 33, 33);
    }
}
