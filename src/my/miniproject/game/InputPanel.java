package my.miniproject.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    private final JTextField inputTextField = new JTextField(30);

    // 하단 입력 패널
    public InputPanel(GamePanel gamePanel) {
        setLayout(new FlowLayout());
        setBackground(Color.CYAN);
        inputTextField.setFont(new Font("gothic", Font.BOLD, 15));
        inputTextField.setHorizontalAlignment(JTextField.CENTER);
        add(inputTextField);
        // 엔터키를 입력시 해당하는 병사를 죽인다.
        inputTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField t = (JTextField) e.getSource();
                String inWord = t.getText();
                gamePanel.killEnemy(inWord);
                t.setText("");
            }
        });
    }
}