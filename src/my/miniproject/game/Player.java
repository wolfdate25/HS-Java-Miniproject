package my.miniproject.game;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Vector;

public class Player implements Serializable {
    private final Vector<PlayerData> dataList = new Vector<>();
    private String name = "";
    private int maxScore = 0;

    // 플레이어 점수 추가
    public void addScoreData(String name, int score) {
        this.name = name;
        if (maxScore < score) {
            maxScore = score;
        }
        dataList.add(new PlayerData(name, score));
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getName() {
        return name;
    }

    // 플레이어가 가진 점수 목록 반환
    public Vector<PlayerData> getDataList() {
        return dataList;
    }

    class PlayerData implements Serializable {
        private final String name;
        private final int score;
        private final LocalDateTime time;

        public PlayerData(String name, int score) {
            this.name = name;
            this.score = score;
            this.time = LocalDateTime.now();
        }

        public int getScore() {
            return score;
        }

        public String getDateTime() {
            // 시간에 대한 글자 생성
            StringBuffer text = new StringBuffer();
            text.append("[");
            text.append((time.getYear() % 100)).append("-");
            text.append(time.getMonthValue()).append("-");
            text.append(time.getDayOfMonth()).append("/");
            text.append(time.getHour()).append(":");
            text.append(time.getMinute()).append("]");
            return text.toString();
        }
    }
}
