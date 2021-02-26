package my.miniproject.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private final JButton startBtn = new JButton("Start");
    private final JButton stopBtn = new JButton("Stop");
    private final JButton settingBtn = new JButton("Setting");
    private final JButton rankBtn = new JButton("Ranking");

    private final HeaderPanel headerPanel = new HeaderPanel();
    private final GamePanel gamePanel = new GamePanel(headerPanel);
    private final InputPanel inputPanel = new InputPanel(gamePanel);

    private final SettingFrame settingFrame = new SettingFrame();

    public GameFrame() {
        setTitle("타이핑 게임");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splitPane();
        makeToolBar();
        setResizable(false);
        setVisible(true);
    }

    private void splitPane() {

        // 상단 패널
        JSplitPane topPane = new JSplitPane();
        getContentPane().add(topPane, BorderLayout.CENTER);
        topPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        topPane.setDividerLocation(40);
        topPane.setEnabled(false);

        // 하단 패널
        JSplitPane bottomPane = new JSplitPane();
        bottomPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bottomPane.setDividerLocation(440);
        bottomPane.setEnabled(false);

        // 중간 패널
        topPane.setTopComponent(headerPanel);
        topPane.setBottomComponent(bottomPane);
        bottomPane.setTopComponent(gamePanel);
        bottomPane.setBottomComponent(inputPanel);
    }

    private void makeToolBar() {
        // 게임 시작, 정지, 설정, 랭킹 버튼이 위치하는 툴바
        JToolBar tBar = new JToolBar();
        tBar.add(startBtn);
        tBar.add(stopBtn);
        tBar.add(settingBtn);
        tBar.add(rankBtn);
        getContentPane().add(tBar, BorderLayout.NORTH);

        startBtn.addActionListener(new StartAction());
        stopBtn.addActionListener(new StopAction());
        settingBtn.addActionListener(new SettingAction());
        rankBtn.addActionListener(new RankAction());

    }

    // 정지 버튼 누를 시
    private class StopAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.stopGame();
        }
    }

    // 시작 버튼 누를 시
    private class StartAction implements ActionListener {
        StartFrame frame = new StartFrame();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gamePanel.isStarted()) {
                JOptionPane.showMessageDialog(null, "게임이 현재 진행중입니다");
            } else {
                frame.setVisible(true);

            }
        }
    }

    // 설정 버튼 누를 시
    private class SettingAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gamePanel.isStarted()) {
                JOptionPane.showMessageDialog(null, "게임이 현재 진행중입니다");

            } else {
                settingFrame.setVisible(true);
            }
        }
    }

    // 랭킹 버튼 누를 시
    private class RankAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gamePanel.isStarted()) {
                JOptionPane.showMessageDialog(null, "게임이 현재 진행중입니다");

            } else {
                new RankFrame();
            }
        }
    }

    // 난이도 설정하는 프레임
    private class StartFrame extends JFrame {
        public StartFrame() {
            super("난이도 설정");
            setSize(300, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            DifficultyPanel difficultyPanel = new DifficultyPanel(gamePanel, this, headerPanel);
            setContentPane(difficultyPanel);

            setVisible(false);
        }
    }
}
