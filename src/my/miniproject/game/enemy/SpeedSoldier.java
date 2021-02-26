package my.miniproject.game.enemy;

import java.awt.*;

public class SpeedSoldier extends Enemy {
    public SpeedSoldier() {
        setForeground(Color.ORANGE);
        super.setSpeed(super.getSpeed() + 10);
    }
}
