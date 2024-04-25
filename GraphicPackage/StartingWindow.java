package GraphicPackage;

import BoardPackage.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class StartingWindow extends JFrame implements ActionListener {
    private JTextField firstPlayerNameTextField;

    private JTextField secondPlayerNameTextField;

    private JButton startButton;

    public String firstPlayerName;

    public String secondPlayerName;

    public StartingWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Starting Window");
        this.setSize(1475, 850);
        this.setLayout(new BorderLayout());

        JPanel startPanel = new JPanel(null);
        startPanel.setBounds(0, 0, 1475, 850);
        startPanel.setBackground(new Color(189, 183, 107));

        firstPlayerNameTextField = new JTextField();
        firstPlayerNameTextField.setBounds(600, 325, 275, 50);
        firstPlayerNameTextField.setText("First player's name");

        secondPlayerNameTextField = new JTextField();
        secondPlayerNameTextField.setBounds(600, 385, 275, 50);
        secondPlayerNameTextField.setText("Second player's name");


        startButton = new JButton("Begin!");
        startButton.addActionListener(this);
        startButton.setBounds(650, 475, 175, 50);


        startPanel.add(firstPlayerNameTextField);
        startPanel.add(secondPlayerNameTextField);
        startPanel.add(startButton);

        this.add(startPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            firstPlayerName = firstPlayerNameTextField.getText();
            secondPlayerName = secondPlayerNameTextField.getText();
            this.dispose();
        }
    }
}
