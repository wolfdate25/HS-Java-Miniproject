package my.miniproject.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankFrame extends JFrame {
    RankPanel rankPanel = null;

    public RankFrame() {
        super("랭크");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rankPanel = new RankPanel(this);
        setContentPane(rankPanel);

        setVisible(true);
    }

    class RankPanel extends JPanel {
        private final ScoreBoard board = new ScoreBoard();
        private final Font headerFont = new Font("gothic", Font.BOLD, 16);
        private final Font defaultFont = new Font("gothic", Font.BOLD, 14);
        JList topTenPlayer = null;
        private JFrame windowFrame = null;

        public RankPanel(JFrame windowFrame) {
            this.windowFrame = windowFrame;

            setLayout(null);

            makeTopTenPane(); // topten 창 생성
            makeSearchPane(); // 검색창 생성
        }

        // topten 창 생성 함수
        private void makeTopTenPane() {
            JLabel topTenHeader = new JLabel("TOP 10");
            topTenHeader.setFont(headerFont);
            topTenHeader.setSize(100, 30);
            topTenHeader.setLocation(50, 10);
            add(topTenHeader);

            topTenPlayer = new JList(board.getTopTenPlayers());
            topTenPlayer.setFont(defaultFont);
            topTenPlayer.setSize(150, 220);
            topTenPlayer.setLocation(10, 50);
            add(topTenPlayer);
        }

        // 검색창 생성 함수
        private void makeSearchPane() {
            JLabel topTenHeader = new JLabel("검색");
            topTenHeader.setFont(headerFont);
            topTenHeader.setSize(100, 30);
            topTenHeader.setLocation(250, 10);
            add(topTenHeader);
            JTextField searchField = new JTextField();
            searchField.setSize(100, 40);
            searchField.setLocation(200, 50);
            add(searchField);
            JButton searchBtn = new JButton("찾기");
            searchBtn.setFont(new Font("batang", Font.BOLD, 12));
            searchBtn.setSize(70, 40);
            searchBtn.setLocation(310, 50);
            add(searchBtn);
            JList playerLog = new JList();
            JScrollPane searchedList = new JScrollPane(playerLog);
            searchedList.setSize(170, 330);
            searchedList.setLocation(200, 100);
            add(searchedList);

            // 찾기 버튼을 눌렀을 경우 해당하는 플레이어를 찾아 점수를 보여준다.
            searchBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String target = searchField.getText();
                    if (target == "") {
                        return;
                    }
                    if (board.getPlayerData(target) == null) {
                        JOptionPane.showMessageDialog(null, "해당 플레이어가 존재하지 않습니다");
                        return;
                    }
                    playerLog.setListData(board.getPlayerData(target));
                }
            });
        }
    }

}
