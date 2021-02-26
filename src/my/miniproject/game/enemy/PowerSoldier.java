package my.miniproject.game.enemy;

import java.awt.*;

public class PowerSoldier extends Enemy {
    public PowerSoldier() {
        setForeground(Color.MAGENTA);
        super.setSpeed(super.getSpeed() + 10);

    }
}
