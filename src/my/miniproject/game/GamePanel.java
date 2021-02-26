package my.miniproject.game;

import my.miniproject.game.enemy.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GamePanel extends JPanel {
    private final Vector<Enemy> enemy = new Vector<>();
    private final ScoreBoard scoreBoard = new ScoreBoard();
    private Thread levelThread = null;
    private Thread gameThread = null;
    private Thread timeThread = null;
    private HeaderPanel headerPanel = null;
    private GameGroundPanel gameGroundPanel = null;
    private boolean starting = false;

    private int generatingTime = 5000;

    public GamePanel(HeaderPanel headerPanel) {
        this.headerPanel = headerPanel;

        setLayout(new BorderLayout());

        gameGroundPanel = new GameGroundPanel();
        add(gameGroundPanel, BorderLayout.CENTER);
    }

    public boolean isStarted() {
        return starting;
    }

    // 게임을 시작하는 함수
    // 스레드가 죽어 있을 경우, 스레드를 생성하고 시작하여 게임을 시작
    public void startGame() {
        try {
            if (levelThread.isAlive() || gameThread.isAlive() || timeThread.isAlive()) {
                return;
            }
        } catch (NullPointerException e) {

        }
        headerPanel.clearParameter();
        levelThread = new LevelThread();
        gameThread = new GameThread();
        timeThread = new TimeThread();
        levelThread.start();
        timeThread.start();
        gameThread.start();
        starting = true;
    }

    // 게임을 종료시키는 함수
    // 게임이 진행중이 아닐 경우 취소
    public void stopGame() {
        try {
            if (!levelThread.isAlive() || !gameThread.isAlive() || !timeThread.isAlive()) {
                return;
            }
            levelThread.interrupt();
            gameThread.interrupt();
            timeThread.interrupt();
            for (int i = 0; i < enemy.size(); i++) {
                gameGroundPanel.remove(enemy.get(i));
            }
            enemy.clear();
            repaint();
            starting = false;
        } catch (Exception e) {

        }
    }

    public void killEnemy(String text) {
        // 병사를 살해 시도
        for (int i = 0; i < enemy.size(); i++) {
            Enemy target = enemy.get(i);
            if (target.getText().equals(text)) {
                int score = target.killMe();
                if (score == 0) {
                    return;
                }
                // 점수를 올리고 병사를 제거한다.
                headerPanel.increaseScore(score);
                gameGroundPanel.remove(target);
                enemy.remove(i);
                repaint();
                break;
            }
        }
    }

    // 게임이 종료되었을 경우 실행되는 함수
    public void gameEnd() {
        JOptionPane.showMessageDialog(null, "게임 종료!\n" +
                "점수 : " + headerPanel.getScore());
        String name = "";
        int score = headerPanel.getScore();
        while (name == null || name == "") {
            name = JOptionPane.showInputDialog("기록할 이름을 입력하세요");
        }
        scoreBoard.saveScoreData(name, score);
    }

    // 체력이 0이 되었을 경우 게임을 종료시키는 함수
    public void checkGameOver() {
        // 체력이 0이 되면 게임을 종료시킨다.
        int health = headerPanel.getHealth();
        if (health == 0) {
            stopGame();
            gameEnd();
        }
    }

    public void setGeneratingTime(int time) {
        generatingTime = time;
    }

    class GameGroundPanel extends JPanel {
        private final Image backgroundImg = new ImageIcon(getClass().getResource("/my/miniproject/resource/ground.png")).getImage();

        public GameGroundPanel() {
            setLayout(null);
        }

        // 배경 그림을 그린다.
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 백그라운드 이미지 그리기
            g.drawImage(backgroundImg, -30, 0, this);
        }
    }

    class LevelThread extends Thread {

        int generatingTime;
        int currentPhase = 0;

        public LevelThread() {
            this.generatingTime = GamePanel.this.generatingTime;
        }

        // 병사를 생성하는 스레드 조절하는 스레드
        public void run() {
            double phaseMul = 1.0;
            // 병사를 생성하여 백터에 넣는다
            while (true) {
                Enemy soldier = generateEnemy();
                gameGroundPanel.add(soldier); // 게임 내에 병사를 추가
                repaint();
                enemy.add(soldier);

                adjustGenTime(); // 페이즈 별 병사 생성 간격 조절

                try {
                    Thread.sleep(generatingTime);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        // 페이즈 별 병사 생성 시간 조절
        private void adjustGenTime() {
            if (currentPhase == headerPanel.getPhase())
                return;
            currentPhase = headerPanel.getPhase() - (2000 - Parameters.getGameSpeed());
            switch (currentPhase) {
                case 0:
                    this.generatingTime = GamePanel.this.generatingTime;
                    break;
                case 1:
                    this.generatingTime = (int) (GamePanel.this.generatingTime * 0.9);
                    break;
                case 2:
                    this.generatingTime = (int) (GamePanel.this.generatingTime * 0.8);
                    break;
                case 3:
                    this.generatingTime = (int) (GamePanel.this.generatingTime * 0.7);
                    break;
                case 4:
                    this.generatingTime = (int) (GamePanel.this.generatingTime * 0.6);
                    break;
                case 5:
                    this.generatingTime = (int) (GamePanel.this.generatingTime * 1.5);
                    break;
            }
        }

        // 병사 종류를 결정하는 함수
        public Enemy generateEnemy() {
            // 병사 랜덤 생성
            int rn = (int) (Math.random() * 8); //0~2까지의 난수 발생
            Enemy enemy = selectEnemyType(rn);
            return enemy;
        }

        // 입력받은 난수에 해당하는 병사를 생성
        public Enemy selectEnemyType(int type) {
            Enemy enemy = null;
            switch (type) {
                case 0:
                case 1:
                case 2:
                    enemy = new Soldier();
                    break;
                case 3:
                case 4:
                    enemy = new SpeedSoldier();
                    break;
                case 5:
                case 6:
                    enemy = new ShieldSoldier();
                    break;
                case 7:
                    enemy = new PowerSoldier();
                    break;
            }
            return enemy;
        }

    }

    // 타이머를 움직이는 스레드
    class TimeThread extends Thread {
        private final int clock = 1000;

        public void run() {
            try {
                while (true) {
                    headerPanel.increaseTime();
                    sleep(clock);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    // 게임을 진행하는 스레드
    class GameThread extends Thread {
        private final int gameSpeed;

        public GameThread() {
            gameSpeed = Parameters.getGameSpeed();
        }

        public void run() {
            try {
                // 모든 병사들을 이동시킨다.
                while (true) {
                    // 페이즈 6 도달시 게임을 멈춘다.
                    if (headerPanel.getPhase() > 6) {
                        stopGame(); // 게임을 멈춘다
                        gameEnd(); // 게임 클리어 함수 실행
                    }
                    int n = enemy.size();
                    if (n != 0) {


                        for (int i = 0; i < n; i++) {
                            JLabel temp = enemy.get(i);

                            // 글자 도달 시 삭제
                            if (temp.getX() > 770) { // 마진 선에 도달 시
                                enemy.remove(i--);
                                gameGroundPanel.remove(temp); // 해당 병사를 제거한 뒤
                                headerPanel.decreaseHealth(); // 체력을 감소시킨다
                                checkGameOver(); // 게임 오버인지 검사한다
                                n--; // 해당 병사를 제거할 경우 vector의 사이즈가 1 감소된다.
                            } else {
                                // 도달안된 병사의 위치를 이동
                                temp.setLocation(temp.getX() + enemy.get(i).getSpeed(), temp.getY());
                            }
                        }

                    }
                    try {
                        Thread.sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                // 가끔 발생하는 배열 오류 예외 처리
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        } //  run 함수 끝


    }

}