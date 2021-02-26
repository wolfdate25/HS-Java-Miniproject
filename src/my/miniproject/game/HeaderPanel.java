package my.miniproject.game;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private final JLabel scoreText = new JLabel("점수");
    private final JLabel scoreLabel = new JLabel("0점");
    private final JLabel phaseText = new JLabel("페이즈");
    private final JLabel phaseLabel = new JLabel("0단계");
    private final JLabel timeText = new JLabel("다음 페이즈까지 남은 시간");
    private final JLabel timeLabel = new JLabel("0초");
    private final JLabel difficultyText = new JLabel("난이도");
    private final JLabel difficultyLabel = new JLabel("미정");
    private final JLabel healthText = new JLabel("체력");
    private final JLabel healthLabel = new JLabel("5");
    private int score = 0;
    private int phase = 0;
    private int time = 0;
    private int health = 5;

    public HeaderPanel() {
        setBackground(Color.YELLOW);
        setLayout(null);

        // 헤더 패널에 있는 글자들을 표현
        scoreText.setSize(50, 20);
        scoreText.setLocation(10, 10);
        scoreText.setFont(new Font("batang", Font.BOLD, 17));
        add(scoreText);

        scoreLabel.setSize(100, 20);
        scoreLabel.setLocation(55, 10);
        scoreLabel.setFont(new Font("batang", Font.BOLD, 17));
        add(scoreLabel);

        phaseText.setSize(50, 20);
        phaseText.setLocation(120, 10);
        phaseText.setFont(new Font("batang", Font.BOLD, 17));
        add(phaseText);

        phaseLabel.setSize(100, 20);
        phaseLabel.setLocation(175, 10);
        phaseLabel.setFont(new Font("batang", Font.BOLD, 17));
        add(phaseLabel);

        timeText.setSize(250, 20);
        timeText.setLocation(250, 10);
        timeText.setFont(new Font("batang", Font.BOLD, 17));
        add(timeText);

        timeLabel.setSize(100, 20);
        timeLabel.setLocation(460, 10);
        timeLabel.setFont(new Font("batang", Font.BOLD, 17));
        add(timeLabel);

        difficultyText.setSize(100, 20);
        difficultyText.setLocation(520, 10);
        difficultyText.setFont(new Font("batang", Font.BOLD, 17));
        add(difficultyText);

        difficultyLabel.setSize(100, 20);
        difficultyLabel.setLocation(580, 10);
        difficultyLabel.setFont(new Font("batang", Font.BOLD, 17));
        add(difficultyLabel);

        healthText.setSize(50, 20);
        healthText.setLocation(650, 10);
        healthText.setFont(new Font("batang", Font.BOLD, 17));
        add(healthText);

        healthLabel.setSize(100, 20);
        healthLabel.setLocation(695, 10);
        healthLabel.setFont(new Font("batang", Font.BOLD, 17));
        add(healthLabel);
    }

    // 모든 헤더의 값들을 초기화
    public void clearParameter() {
        this.score = 0;
        scoreLabel.setText(Integer.toString(this.score));
        this.phase = 0;
        phaseLabel.setText(Integer.toString(this.phase));

        this.time = 0;
        timeLabel.setText(Integer.toString(this.time));

        this.health = Parameters.getHealth();
        healthLabel.setText(Integer.toString(this.health));

    }

    // 체력 감소
    public void decreaseHealth() {
        health--;
        healthLabel.setText(Integer.toString(health));
    }

    public int getHealth() {
        return health;
    }

    // 페이즈 증가
    public void increasePhase() {
        phaseLabel.setText(++phase + "단계");
    }

    public int getPhase() {
        return phase;
    }

    // 시간 증가
    public void increaseTime() {
        time++;
        if (time / 60 != 0) {
            time = 0;
            increasePhase();
        }
        timeLabel.setText((60 - time) + "초");
    }

    // 난이도 텍스트 표현
    public void setDifficulty(int difficulty) {
        switch (difficulty) {
            case 0:
                difficultyLabel.setText("");
                break;
            case 1:
                difficultyLabel.setText("쉬움");
                break;
            case 2:
                difficultyLabel.setText("보통");
                break;
            case 3:
                difficultyLabel.setText("어려움");
                break;
        }
    }

    // 점수 증가
    public void increaseScore(int score) {
        this.score += score;
        scoreLabel.setText(this.score + "점");
    }

    public int getScore() {
        return score;
    }
}
