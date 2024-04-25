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

    JPanel storePanel;

    JButton storeButton;

    JPanel slotMachinesPanel;

    JButton slotMachinesButton;

    JPanel reservePanel;

    JButton reserveButton;

    JPanel firstPlayerPanel;

    JPanel secondPlayerPanel;

    JButton cancelButton;

    String error = "";

    public GameWindow(Board board) {
        this.board = board;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Window");
        this.setSize(1475, 850);
        this.setLayout(null);

        storePanel = new JPanel(null);
        storePanel.setBounds(10, 10, 745, 800);
        storePanel.setBackground(new Color(194, 178, 128));
        drawStore(storePanel);
        this.add(storePanel);

        slotMachinesPanel = new JPanel(null);
        slotMachinesPanel.setBounds(760, 195, 120, 615);
        slotMachinesPanel.setBackground(new Color(210, 180, 140));
        drawSlotMachines(slotMachinesPanel);
        this.add(slotMachinesPanel);

        reservePanel = new JPanel(null);
        reservePanel.setBounds(760, 10, 120, 180);
        reservePanel.setBackground(new Color(210, 180, 140));
        drawReserve(reservePanel);
        this.add(reservePanel);

        firstPlayerPanel = new JPanel(null);
        firstPlayerPanel.setBounds(886, 10, 575, 397);
        firstPlayerPanel.setBackground(new Color(189, 183, 107));
        drawPlayerTable(0, firstPlayerPanel);
        this.add(firstPlayerPanel);

        secondPlayerPanel = new JPanel(null);
        secondPlayerPanel.setBounds(886, 413, 575, 397);
        secondPlayerPanel.setBackground(new Color(226, 218, 198));
        drawPlayerTable(1, secondPlayerPanel);
        this.add(secondPlayerPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (board.whoWins() != -1) {
            board.state = 6;
            this.removeAll();
        }

        if (! error.equals("")) {
            JFrame errorFrame = new JFrame("Invalid Action");
            errorFrame.setSize(400, 120);
            errorFrame.setLocationRelativeTo(null);
            errorFrame.setLayout(null);

            JLabel errorLabel = new JLabel(error);
            errorLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            errorLabel.setBounds(40, 0, 400, 90);

            JPanel errorPanel = new JPanel(null);
            errorPanel.setBounds(0, 0, 400, 120);
            errorPanel.setBackground(new Color(183,65,14));

            errorPanel.add(errorLabel);
            errorFrame.add(errorPanel);

            errorFrame.setVisible(true);
            error = "";
        }

        switch (board.state) {
            case 0:
                storePanel.setBackground(new Color(194, 178, 128));
                slotMachinesPanel.setBackground(new Color(210, 180, 140));
                reservePanel.setBackground(new Color(210, 180, 140));
                firstPlayerPanel.setBackground(new Color(189, 183, 107));
                secondPlayerPanel.setBackground(new Color(226, 218, 198));

                if (e.getSource() == storeButton)
                    board.state = 1;
                else if (e.getSource() == slotMachinesButton) {
                    board.emptyWallet();
                    board.state = 3;
                }
                else if (e.getSource() == reserveButton)
                    board.state = 4;

                break;
            case 1:
                storePanel.setBackground(new Color(194, 178, 128));
                slotMachinesPanel.setBackground(new Color(226, 218, 198));
                reservePanel.setBackground(new Color(226, 218, 198));
                firstPlayerPanel.setBackground(new Color(226, 218, 198));
                secondPlayerPanel.setBackground(new Color(226, 218, 198));

                if (e.getSource() == cancelButton) {
                    board.state = 0;
                    break;
                }

                for (int i = 0; i < board.store.cardCount[0]; i++)
                    if (e.getSource() == cardButton[0][i]) {
                        if (!board.player[board.turn].isThereEnoughCards(board.store.cards[0][i].price)) {
                            error = "You do not have enough cards to buy this prize claw.";
                            continue;
                        }
                        board.getPrizeClaw(i);
                        board.turn ^= 1;
                        board.state = 0;
                    }


                for (int i = 1; i <= 3; i++)
                    for (int j = 0; j < min(4, board.store.cardCount[i]); j++)
                        if (e.getSource() == cardButton[i][j]) {
                            if (! board.player[board.turn].wallet.isThereEnoughCoins(board.store.cards[i][j].price)) {
                                error = "You do not have enough coins to buy this card.";
                                continue;
                            }
                            board.emptyWallet();
                            board.chosenCardLevel = i;
                            board.chosenCardId = j;
                            board.state = 2;
                        }

                break;
            case 2:
                storePanel.setBackground(new Color(226, 218, 198));
                slotMachinesPanel.setBackground(new Color(226, 218, 198));
                reservePanel.setBackground(new Color(226, 218, 198));

                if (board.canBePaid()) {
                    board.getCard();
                    board.emptyWallet();
                    board.turn ^= 1;
                    board.state = 0;
                    break;
                }

                if (e.getSource() == cancelButton) {
                    board.emptyWallet();
                    board.state = 1;
                    break;
                }

                switch (board.turn) {
                    case 0:
                        firstPlayerPanel.setBackground(new Color(189, 183, 107));

                    break;
                    case 1:
                        secondPlayerPanel.setBackground(new Color(189, 183, 107));

                        break;
                }
                break;
            case 3:
                storePanel.setBackground(new Color(226, 218, 198));
                slotMachinesPanel.setBackground(new Color(210, 180, 140));
                reservePanel.setBackground(new Color(226, 218, 198));
                firstPlayerPanel.setBackground(new Color(226, 218, 198));
                secondPlayerPanel.setBackground(new Color(226, 218, 198));

                if (board.slotMachines.readyToGet(board.walletForCoins, board.walletCoinCnt)) {
                    board.getCoins();
                    board.emptyWallet();
                    board.turn ^= 1;
                    board.state = 0;
                    break;
                }

                if (e.getSource() == cancelButton) {
                    board.emptyWallet();
                    board.state = 0;
                    break;
                }

                for (int i = 0; i < 5; i++)
                    if (e.getSource() == coinButton[i]) {
                        if (! board.slotMachines.validCoin(board.walletForCoins, board.walletCoinCnt, i)) {
                            error = "You are not allowed to choose this coin.";

                            break;
                        }
                        board.walletCoinCnt++;
                        board.walletForCoins[i]++;
                    }
                break;
            case 4:
                storePanel.setBackground(new Color(194, 178, 128));
                slotMachinesPanel.setBackground(new Color(226, 218, 198));
                reservePanel.setBackground(new Color(210, 180, 140));
                firstPlayerPanel.setBackground(new Color(226, 218, 198));
                secondPlayerPanel.setBackground(new Color(226, 218, 198));

                if (board.player[board.turn].reserveCount == 3) {
                    error = "You can not reserve more than 3 cards.";
                    board.state = 0;
                    break;
                }

                for (int i = 1; i <= 3; i++)
                    for (int j = 0; j < min(4, board.store.cardCount[i]); j++)
                        if (e.getSource() == cardButton[i][j]) {
                            board.chosenCardLevel = i;
                            board.chosenCardId = j;
                            board.getReserve();
                            board.turn ^= 1;
                            board.state = 0;
                        }

                if (e.getSource() == cancelButton) {
                    board.state = 0;
                    break;
                }
                break;
            case 5:

        }

        storePanel.removeAll();
        drawStore(storePanel);
        storePanel.repaint();

        slotMachinesPanel.removeAll();
        drawSlotMachines(slotMachinesPanel);
        slotMachinesPanel.repaint();

        reservePanel.removeAll();
        drawReserve(reservePanel);
        reservePanel.repaint();

        this.repaint();

    }

    private void drawPlayerTable(int playerId, JPanel panel) {

    }

    private void drawReserve(JPanel panel) {
        JLabel reserveTextLabel = new JLabel("Reserve");
        reserveTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        reserveTextLabel.setBounds(26, 10,  50, 30);

        reserveButton = new JButton();
        reserveButton.addActionListener(this);
        reserveButton.setLayout(null);
        reserveButton.setBounds(10, 5, 100, 50);
        reserveButton.add(reserveTextLabel, BorderLayout.CENTER);
        panel.add(reserveButton);

        ImageIcon goldChip = new ImageIcon(getClass().getResource("resources/goldChip100.jpg"));

        coinButton[5] = new JButton();
        coinButton[5].setBounds(5, 60, 110, 110);
        coinButton[5].setLayout(null);
        JLabel slotChipLabel = new JLabel(goldChip);
        JLabel slotMachinesCoinCountTextLabel = new JLabel(Integer.toString(board.slotMachines.coin[5].count - board.walletForCoins[5]));
        slotMachinesCoinCountTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        coinButton[5].add(slotMachinesCoinCountTextLabel);
        coinButton[5].add(slotChipLabel);
        slotChipLabel.setBounds(5, 5, 100, 100);
        slotMachinesCoinCountTextLabel.setBounds(47, 5, 100, 100);
        panel.add(coinButton[5]);
    }

    private void drawSlotMachines(JPanel panel) {
        JLabel slotMachinesTextLabel = new JLabel("Slot Machines");
        slotMachinesTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        slotMachinesTextLabel.setSize(50, 40);

        slotMachinesButton = new JButton();
        slotMachinesButton.addActionListener(this);
        slotMachinesButton.setLayout(new BorderLayout());
        slotMachinesButton.setBounds(10, 5, 100, 50);
        slotMachinesButton.add(slotMachinesTextLabel, BorderLayout.CENTER);
        panel.add(slotMachinesButton);

        ImageIcon[] slotChip = new ImageIcon[7 + 2];

        slotChip[0] = new ImageIcon(getClass().getResource("resources/greenChip100.jpg"));
        slotChip[1] = new ImageIcon(getClass().getResource("resources/whiteChip100.jpg"));
        slotChip[2] = new ImageIcon(getClass().getResource("resources/blackChip100.jpg"));
        slotChip[3] = new ImageIcon(getClass().getResource("resources/blueChip100.jpg"));
        slotChip[4] = new ImageIcon(getClass().getResource("resources/redChip100.jpg"));

        int Y = 60;
        for (int i = 0; i < 5; i++) {
            coinButton[i] = new JButton();
            coinButton[i].setBounds(5, Y, 110, 110);
            coinButton[i].setLayout(null);
            JLabel slotChipLabel = new JLabel(slotChip[i]);
            JLabel slotMachinesCoinCountTextLabel = new JLabel(Integer.toString(board.slotMachines.coin[i].count - board.walletForCoins[i]));
            slotMachinesCoinCountTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            coinButton[i].add(slotMachinesCoinCountTextLabel);
            coinButton[i].add(slotChipLabel);
            slotChipLabel.setBounds(5, 5, 100, 100);
            slotMachinesCoinCountTextLabel.setBounds(47, 5, 100, 100);
            panel.add(coinButton[i]);
            Y += 111;
        }

    }

    private void drawStore(JPanel panel) {
        JLabel storeTextLabel = new JLabel("  Store");
        storeTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        storeTextLabel.setSize(50, 20);

        storeButton = new JButton();
        storeButton.addActionListener(this);
        storeButton.setLayout(new BorderLayout());
        storeButton.setBounds(10, 68, 122, 60);
        storeButton.add(storeTextLabel, BorderLayout.CENTER);
        panel.add(storeButton);

        JLabel cancelTextLabel = new JLabel("  Cancel");
        cancelTextLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        storeTextLabel.setSize(50, 20);

        cancelButton = new JButton();
        cancelButton.addActionListener(this);
        cancelButton.setLayout(new BorderLayout());
        cancelButton.setBounds(10, 135, 122, 60);
        cancelButton.add(cancelTextLabel, BorderLayout.CENTER);
        panel.add(cancelButton);

        for (int i = 0; i < board.store.cardCount[0]; i++) {
            cardButton[0][i] = new JButton();
            cardButton[0][i].addActionListener(this);
            cardButton[0][i].setBounds(175 + i * 190, 40, 176, 176);
            drawPrizeClawCard(board.store.cards[0][i], cardButton[0][i]);
            panel.add(cardButton[0][i]);
        }

        JButton firstLevelCover = new JButton();
        firstLevelCover.setBounds(5, 240, 132, 176);
        panel.add(firstLevelCover);

        for (int i = 0; i < min(4, board.store.cardCount[1]); i++) {
            cardButton[1][i] = new JButton();
            cardButton[1][i].addActionListener(this);
            cardButton[1][i].setBounds(170 + i * 145, 240, 132, 176);
            drawCard(board.store.cards[1][i], cardButton[1][i]);
            panel.add(cardButton[1][i]);
        }

        JButton secondLevelCover = new JButton();
        secondLevelCover.setBounds(5, 430, 132, 176);
        panel.add(secondLevelCover);

        for (int i = 0; i < min(4, board.store.cardCount[2]); i++) {
            cardButton[2][i] = new JButton();
            cardButton[2][i].addActionListener(this);
            cardButton[2][i].setBounds(170 + i * 145, 430, 132, 176);
            drawCard(board.store.cards[2][i], cardButton[2][i]);
            panel.add(cardButton[2][i]);
        }

        JButton thirdLevelCover = new JButton();
        thirdLevelCover.setBounds(5, 620, 132, 176);
        panel.add(thirdLevelCover);

        for (int i = 0; i < min(4, board.store.cardCount[3]); i++) {
            cardButton[3][i] = new JButton();
            cardButton[3][i].addActionListener(this);
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
