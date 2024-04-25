package GraphicPackage;

import BoardPackage.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow extends JFrame implements ActionListener {
    private JTextField firstPlayerNameTextField;

    private JTextField secondPlayerNameTextField;

    private JButton startButton;

    public String firstPlayerName;

    public String secondPlayerName;

    public StartingWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Starting Window");
        this.setSize(400, 300);
        this.setLayout(null);

        firstPlayerNameTextField = new JTextField();
        firstPlayerNameTextField.setBounds(100, 50, 200, 40);
        firstPlayerNameTextField.setText("First player's name");

        secondPlayerNameTextField = new JTextField();
        secondPlayerNameTextField.setBounds(100, 100, 200, 40);
        secondPlayerNameTextField.setText("Second player's name");


        startButton = new JButton("Begin!");
        startButton.addActionListener(this);
        startButton.setBounds(125, 175, 150, 40);


        this.add(firstPlayerNameTextField);
        this.add(secondPlayerNameTextField);
        this.add(startButton);

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
