package my.miniproject.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyPanel extends JPanel {
    private final JButton easyButton = new JButton("Easy");
    private final JButton normalButton = new JButton("Normal");
    private final JButton hardButton = new JButton("Hard");
    private final HeaderPanel headerPanel = null;
    private GamePanel gamePanel = null;
    private JFrame frame = null;

    public DifficultyPanel(GamePanel gamePanel, JFrame frame, HeaderPanel headerPanel) {
        this.gamePanel = gamePanel;
        this.frame = frame;

        setBackground(Color.YELLOW);
        setLayout(new GridLayout(3, 1));

        // 버튼 생성
        add(easyButton);
        add(normalButton);
        add(hardButton);
        // 쉬움 난이도 병사 생성시간 5000ms
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startGame();
                gamePanel.setGeneratingTime(5000);
                headerPanel.setDifficulty(1);
                frame.setVisible(false);
            }
        });
        // 보통 난이도 병사 생성시간 4000ms
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startGame();
                gamePanel.setGeneratingTime(4000);
                headerPanel.setDifficulty(2);
                frame.setVisible(false);
            }
        });

        // 어려움 난이도 병사 생성시간 3000ms
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startGame();
                gamePanel.setGeneratingTime(3000);
                headerPanel.setDifficulty(3);
                frame.setVisible(false);
            }
        });
    }
}
