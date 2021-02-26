package my.miniproject.game;


import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Dictionary {
    // 모든 단어를 저장하는 HashSet
    private final HashSet<String> dic = new HashSet<>(25143);
    // 사용자 정의 사전인 Vector
    private final Vector<String> customDic = new Vector<>();
    private final Random random = new Random();

    public Dictionary() {

        String word = "";
        if (Parameters.isUseDefaultDic()) {
            // 내부에 저장된 단어 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/my/miniproject/resource/words.txt")));
            try {
                while ((word = reader.readLine()) != null) {
                    dic.add(word);
                }
                reader.close();
            } catch (IOException e) {

            }
        }


        // 사용자가 입력하는 외부 단어 읽기
        try {
            BufferedReader fReader = new BufferedReader(new FileReader("words.txt"));
            while ((word = fReader.readLine()) != null) {
                customDic.add(word);
                dic.add(word);
            }
            fReader.close();
        } catch (IOException e) {
            System.out.println("외부 단어 파일을 읽을 수 없습니다.");
        }
    }

    // 사용자 정의 단어 반환
    public Vector getCustomWords() {
        return customDic;
    }

    // 사용자 정의 사전에 단어 추가
    public void addCustomWord(String word) {
        if (customDic.contains(word)) {
            JOptionPane.showMessageDialog(null, "이미 사전에 등록된 단어입니다");
        } else {
            customDic.add(word);
        }
    }

    // 사용자 정의 사전에서 단어 제거
    public void subtractWord(String word) {
        customDic.remove(word);
    }

    // 사용자가 입력한 단어 저장
    public void saveCustomDic() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("words.txt"));
            for (int i = 0; i < customDic.size(); i++) {
                writer.write(customDic.get(i) + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("파일 쓰기에 실패하였습니다.");
        }
    }

    // 랜덤 단어 생성
    public String get() {
        int index = random.nextInt(dic.size()) + 1;
        Iterator iter = dic.iterator();
        String word = "";
        for (int i = 0; i < index; i++) {
            if (iter.hasNext()) {
                word = (String) iter.next();
            }

        }
        return word;
    }
}
