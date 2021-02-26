package my.miniproject.game;

import my.miniproject.game.enemy.Enemy;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SettingFrame extends JFrame {

    public SettingFrame() {
        super("설정");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(new SettingPanel(this));

    }

    class SettingPanel extends JPanel {
        private final Dictionary dic = new Dictionary();
        private final JButton applyBtn = new JButton("적용");
        private final JScrollPane wordPane = new JScrollPane();
        private SettingFrame frame = null;
        private JList<String> wordList = null;

        // 헤더 폰트
        private final Font defaultFont = new Font("gothic", Font.BOLD, 13);
        // 기본 폰트
        private final Font headerFont = new Font("gothic", Font.BOLD, 16);
        JTextField healthField = null;

        // 기본 값
        private boolean useOnlyCustomDicSetting = false;
        private int fontSizeSetting = 12;
        private int gameSpeedSetting = 1000;
        private int healthSetting = 5;
        private int soliderSpeedSetting = 10;

        public SettingPanel(SettingFrame frame) {
            this.frame = frame;
            healthSetting = Parameters.getHealth();
            setLayout(null);

            // 단어 추가 제거
            dictionarySetting();
            //

            // 파라미터 수정
            parameterSetting();

            // 적용 버튼
            applyBtn.setSize(70, 40);
            applyBtn.setLocation(310, 410);
            add(applyBtn);

            applyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dic.saveCustomDic();
                    closeSettingFrame();
                }
            });
        }

        // 우측 박스 구현
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.setColor(Color.BLACK);
            g.drawRect(180, 50, 195, 350);
        }

        private void parameterSetting() {

            // 공통 설정 라벨
            JLabel text = new JLabel("공통 설정");
            text.setFont(headerFont);
            text.setSize(100, 30);
            text.setLocation(240, 10);
            add(text);

            // 사용자 단어 옵션 라벨
            JLabel useOnlyCustomDicText = new JLabel("사용자 단어만 사용하기");
            useOnlyCustomDicText.setFont(defaultFont);
            useOnlyCustomDicText.setSize(200, 30);
            useOnlyCustomDicText.setLocation(190, 55);
            add(useOnlyCustomDicText);

            // 사용자 단어 선택 박스
            JCheckBox useOnlyCustomDicBox = new JCheckBox();
            useOnlyCustomDicBox.setSize(20, 20);
            useOnlyCustomDicBox.setLocation(330, 60);
            add(useOnlyCustomDicBox);

            useOnlyCustomDicBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    useOnlyCustomDicSetting = e.getStateChange() == ItemEvent.SELECTED;
                }
            });

            // 병사 크기 조절 라벨
            JLabel fontSliderText = new JLabel("병사 크기 조절");
            fontSliderText.setFont(defaultFont);
            fontSliderText.setSize(150, 30);
            fontSliderText.setLocation(190, 85);
            add(fontSliderText);

            // 병사 크기 조절 슬라이더
            JSlider fontSlider = new JSlider(JSlider.HORIZONTAL, 9, 18, fontSizeSetting);
            fontSlider.setSize(180, 50);
            fontSlider.setLocation(190, 115);
            fontSlider.setPaintLabels(true);
            fontSlider.setPaintTrack(true);
            fontSlider.setPaintTicks(true);
            fontSlider.setMajorTickSpacing(1);
            add(fontSlider);
            fontSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider slider = (JSlider) e.getSource();
                    int fontSize = slider.getValue();
                    SettingPanel.this.fontSizeSetting = fontSize;
                }
            });

            // 게임 속도 조절 라벨
            JLabel gameSpeedSliderText = new JLabel("<빠름> 게임 속도 조절 <느림>");
            gameSpeedSliderText.setFont(defaultFont);
            gameSpeedSliderText.setSize(200, 30);
            gameSpeedSliderText.setLocation(190, 165);
            add(gameSpeedSliderText);

            // 게임 속도 조절 슬라이더
            JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 500, 1500, 1000);
            speedSlider.setSize(180, 50);
            speedSlider.setLocation(190, 195);
            speedSlider.setPaintLabels(true);
            speedSlider.setPaintTrack(true);
            speedSlider.setPaintTicks(true);
            speedSlider.setMajorTickSpacing(500);
            speedSlider.setMinorTickSpacing(100);
            add(speedSlider);
            speedSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider slider = (JSlider) e.getSource();
                    int gameSpeed = slider.getValue();
                    SettingPanel.this.gameSpeedSetting = gameSpeed;
                }
            });

            // 마을 체력 라벨
            JLabel healthText = new JLabel("마을 체력");
            healthText.setFont(defaultFont);
            healthText.setSize(80, 30);
            healthText.setLocation(190, 255);
            add(healthText);

            // 마을 체력 설정 필드
            healthField = new JTextField();
            healthField.setSize(50, 30);
            healthField.setLocation(260, 255);
            healthField.setFont(defaultFont);
            healthField.setText(Integer.toString(healthSetting));
            add(healthField);

            // 병사 기본 속도 라벨
            JLabel soliderText = new JLabel("병사 기본 속도");
            soliderText.setFont(defaultFont);
            soliderText.setSize(120, 30);
            soliderText.setLocation(190, 295);
            add(soliderText);

            // 병사 기본속도 조절 슬라이더
            JSlider soliderSpeedSlider = new JSlider(JSlider.HORIZONTAL, 5, 35, 10);
            soliderSpeedSlider.setSize(180, 50);
            soliderSpeedSlider.setLocation(190, 325);
            soliderSpeedSlider.setPaintLabels(true);
            soliderSpeedSlider.setPaintTrack(true);
            soliderSpeedSlider.setPaintTicks(true);
            soliderSpeedSlider.setMajorTickSpacing(3);
            soliderSpeedSlider.setMinorTickSpacing(5);
            add(soliderSpeedSlider);
            soliderSpeedSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider slider = (JSlider) e.getSource();
                    int soliderSpeed = slider.getValue();
                    SettingPanel.this.soliderSpeedSetting = soliderSpeed;
                }
            });

        }

        private void dictionarySetting() {
            // 단어 추가 제거 라벨
            JLabel dicLabel = new JLabel("사용자 사전");
            dicLabel.setSize(100, 30);
            dicLabel.setLocation(40, 10);
            dicLabel.setFont(headerFont);
            add(dicLabel);
            // 사용자 사전 단어 스크롤 pane
            wordList = new JList<>(dic.getCustomWords());
            wordPane.setSize(150, 350);
            wordPane.setLocation(10, 50);
            wordPane.setViewportView(wordList);
            add(wordPane);
            // 단어 추가 버튼
            JButton addBtn = new JButton("추가");
            addBtn.setSize(70, 40);
            addBtn.setLocation(10, 400);
            add(addBtn);
            // 단어 제거 버튼
            JButton removeBtn = new JButton("제거");
            removeBtn.setSize(70, 40);
            removeBtn.setLocation(90, 400);
            add(removeBtn);

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 단어를 추가할 시 빈칸일 경우 무시
                    String word = JOptionPane.showInputDialog("추가할 단어를 입력하세요");
                    if (word == null) {
                        return;
                    } else if (word.equals("")) {
                        JOptionPane.showMessageDialog(null, "단어를 한 글자 이상 입력해야 합니다");
                    } else {
                        dic.addCustomWord(word);
                        wordList.setListData(dic.getCustomWords());
                    }
                }
            });

            removeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String word = wordList.getSelectedValue();
                    if (word == null) {
                        return;
                    }
                    dic.subtractWord(word);
                    wordList.setListData(dic.getCustomWords());
                }
            });
            //
        }

        public boolean checkALLParameterIsValid() {
            // 사용자 단어만 사용 옵션을 켤 시 단어가 2개 이상 입력되어 있는지 검사
            if (useOnlyCustomDicSetting) {
                if (dic.getCustomWords().size() < 2) {
                    JOptionPane.showMessageDialog(null, "사용자 단어만 사용하기 옵션을 사용하시려면\n" +
                            "최소한 2개 이상의 단어를 입력해야 합니다");
                    return false;
                }
            }

            // 체력 필드에 정수 값 이외의 문자가 입력되어 있는지 검사
            try {
                Integer.parseInt(healthField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "체력 옵션은 정수만 입력이 가능합니다");
                return false;
            }

            return true;
        }

        public void closeSettingFrame() {
            // 올바르게 설정이 구성되어 있는지 검사
            if (!checkALLParameterIsValid()) {
                return;
            }

            // 파라미터 값에 반영
            Parameters.setGameSpeed(gameSpeedSetting);
            Parameters.setHealth(Integer.parseInt(healthField.getText()));
            Parameters.setSoliderSpeed(soliderSpeedSetting);
            Parameters.setUseDefaultDic(!useOnlyCustomDicSetting);
            Parameters.setFontSize(fontSizeSetting);

            // 사전 갱신
            Enemy.dicReload();
            // 프레임 숨김
            frame.setVisible(false);
        }
    }

}
