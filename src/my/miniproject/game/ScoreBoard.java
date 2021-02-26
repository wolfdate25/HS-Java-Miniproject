package my.miniproject.game;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class ScoreBoard {

    private HashMap<String, Player> scoreBoard;

    public ScoreBoard() {
        try {
            scoreBoard = loadScoreBoard();
        } catch (Exception e) {
            scoreBoard = new HashMap<>();
        }
    }

    // 플레이어의 점수 기록을 저장하는 함수
    public void saveScoreData(String playerName, int score) {
        // 플레이어가 존재할 경우 추가한다
        if (scoreBoard.containsKey(playerName)) {
            scoreBoard.get(playerName).addScoreData(playerName, score);
        } else {
            // 존재하지 않을 경우 플레이어를 생성하고 점수를 추가한다
            Player player = new Player();
            player.addScoreData(playerName, score);
            scoreBoard.put(playerName, player);
        }
        saveScoreBoard();
    }

    // 스코어보드(랭킹)을 저장한다.
    public void saveScoreBoard() {
        // 바이너리 방식으로 파일을 열어 HashMap 객체를 통채로 저장한다.
        try {
            ObjectOutputStream writer = (new ObjectOutputStream(new FileOutputStream("scoreboard.ser")));

            writer.writeObject(scoreBoard);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 쓰기에 실패하였습니다.");
        }
    }

    // HashMap 객체를 읽어온다.
    public HashMap loadScoreBoard() throws Exception {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("scoreboard.ser"));
        HashMap map = (HashMap) reader.readObject();
        reader.close();
        return map;
    }

    // 플레이어 데이터를 얻어 점수를 출력한다.
    public String[] getPlayerData(String name) {
        Vector<Player.PlayerData> v = null;
        if (scoreBoard.containsKey(name)) {
            v = scoreBoard.get(name).getDataList();
        }
        if (v == null) {
            return null;
        }
        String[] values = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            values[i] = v.get(i).getDateTime() + " " + v.get(i).getScore() + "점";
        }
        return values;
    }


    // Top Ten 플레이어 10명의 이름과 점수를 가진 문자열을 반환하는 함수
    public String[] getTopTenPlayers() {

        // 해시 맵에 있는 최대 값을 추출하고
        // 그 다음 값을 추출하는 순으로 저장
        HashMap<String, Player> board = (HashMap<String, Player>) scoreBoard.clone();
        int n = 0;
        String tempMaxKey = "";
        String[] topTens = new String[10]; // 10명의 정보를 저장하는 String 배열 생성
        int boardSize = board.size();

        for (int i = 0; i < boardSize; i++) { // scoreboard에 저장된 플레이어의 수 만큼 반복
            int max = 0; // 최대 값
            if (n == 10) break; // 10에 도달시 종료
            for (var player : board.entrySet()) { // scoreboard에 있는 최대 값 추출
                int maxScore = player.getValue().getMaxScore();
                if (max < maxScore) {
                    max = maxScore;
                    topTens[n] = player.getKey() + " : " + max + "점";
                    tempMaxKey = player.getKey();
                }
            }
            board.remove(tempMaxKey); // 최대 값 삭제
            n++; // 다음 칸으로 이동
        }

        return topTens;
    }
}

