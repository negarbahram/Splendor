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

    public GameWindow() {
        board = new Board("neg", "nia");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Window");
        this.setSize(1475, 850);
        this.setLayout(null);

        for (int i = 0; i < board.store.cardCount[0]; i++) {
            cardButton[0][i] = new JButton();
            cardButton[0][i].setBounds(185 + i * 190, 50, 176, 176);
            drawPrizeClawCard(board.store.cards[0][i], cardButton[0][i]);
            this.add(cardButton[0][i]);
        }

        JButton firstLevelCoverPanel = new JButton();
        firstLevelCoverPanel.setBounds(15, 250, 132, 176);
        ImageIcon Chip = new ImageIcon(getClass().getResource("resources/blackChip44.jpg"));
        JLabel firstLevelCoreLabel = new JLabel(Chip);
        firstLevelCoverPanel.add(firstLevelCoreLabel);
        firstLevelCoreLabel.setBounds(5, 5, 44, 44);
        firstLevelCoverPanel.setVisible(true);
        this.add(firstLevelCoverPanel);

        for (int i = 0; i < 4; i++) {
            cardButton[1][i] = new JButton();
            cardButton[1][i].setBounds(180 + i * 145, 250, 132, 176);
            drawCard(board.store.cards[1][i], cardButton[1][i]);
            this.add(cardButton[1][i]);
        }

        JButton secondLevelCover = new JButton();
        secondLevelCover.setBounds(15, 440, 132, 176);
        this.add(secondLevelCover);

        for (int i = 0; i < 4; i++) {
            cardButton[2][i] = new JButton();
            cardButton[2][i].setBounds(180 + i * 145, 440, 132, 176);
            drawCard(board.store.cards[2][i], cardButton[2][i]);
            this.add(cardButton[2][i]);
        }

        JButton thirdLevelCover = new JButton();
        thirdLevelCover.setBounds(15, 630, 132, 176);
        this.add(thirdLevelCover);

        JButton[] thirdLevel = new JButton[4 + 2];
        for (int i = 0; i < 4; i++) {
            cardButton[3][i] = new JButton();
            cardButton[3][i].setBounds(180 + i * 145, 630, 132, 176);
            drawCard(board.store.cards[3][i], cardButton[3][i]);
            this.add(cardButton[3][i]);
        }

        /*
        JButton secondPlayerCards = new JButton();
        secondPlayerCards.setBounds(20, 660, 75, 100);
        this.add(secondPlayerCards);
         */

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
