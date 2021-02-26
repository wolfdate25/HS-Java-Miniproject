package my.miniproject.game;

public class Parameters {
    // 게임내 영향을 끼치는 파라미터 값
    private static boolean useDefaultDic = true;
    private static int fontSize = 12;
    private static int gameSpeed = 1000;
    private static int health = 5;
    private static int soliderSpeed = 10;

    public static boolean isUseDefaultDic() {
        return useDefaultDic;
    }

    public static void setUseDefaultDic(boolean exp) {
        useDefaultDic = exp;
    }

    public static int getFontSize() {
        return fontSize;
    }

    public static void setFontSize(int size) {
        fontSize = size;
    }

    public static int getGameSpeed() {
        return gameSpeed;
    }

    public static void setGameSpeed(int speed) {
        gameSpeed = speed;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int point) {
        health = point;
    }

    public static int getSoliderSpeed() {
        return soliderSpeed;
    }

    public static void setSoliderSpeed(int speed) {
        soliderSpeed = speed;
    }
}
