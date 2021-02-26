package my.miniproject.game.enemy;

import my.miniproject.game.Dictionary;
import my.miniproject.game.Parameters;

import javax.swing.*;
import java.awt.*;

abstract public class Enemy extends JLabel {
    private static int position = 0;
    private static Dictionary dic = new Dictionary();
    private int speed = 10;

    // 병사가 기본적으로 가지는 특징을 구현하고
    // 상속을 이용하여 해당 병사가 가지는 특징만 구현
    public Enemy() {
        this.speed = Parameters.getSoliderSpeed();
        String newWord = dic.get();
        setText(newWord);
        int fontSize = Parameters.getFontSize();
        setFont(new Font("malgungothic", Font.PLAIN, fontSize));
        setSize(newWord.length() * fontSize + 5, 27);
        if (position == 0) {
            setLocation(0, (int) (Math.random() * 185) + 10);
            position = 1;
        } else {
            setLocation(0, (int) (Math.random() * 185) + 195);
            position = 0;
        }
        setOpaque(false);
    }

    public static void dicReload() {
        dic = new Dictionary();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // 병사의 방패가 벗겨질 경우 실행되는 함수
    // 병사의 글자를 변경함
    private void changeWord() {
        String newWord = dic.get();
        setText(newWord);
        setSize(newWord.length() * 9 + 5, 27);
    }

    public int killMe() {
        // 해당 병사가 무슨 종류의 병사인지 파악하여
        // 해당 병사의 특성에 따라 변화를 주거나 죽임
        if (this instanceof PowerSoldier) {
            if (this.getForeground() == Color.MAGENTA) {
                this.setForeground(Color.ORANGE);
                changeWord();
                return 0;
            } else {
                return 30;
            }
        } else if (this instanceof ShieldSoldier) {
            if (this.getForeground() == Color.BLUE) {
                this.setForeground(Color.BLACK);
                changeWord();
                return 0;
            } else {
                return 20;
            }
        } else if (this instanceof SpeedSoldier) {
            return 20;
        } else {
            return 10;
        }

    }
}