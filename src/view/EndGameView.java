package view;

import javax.swing.*;
import java.awt.*;

public class EndGameView extends JDialog {

    private final JLabel label = new JLabel("", SwingConstants.CENTER);
    private final JLabel gameInfo = new JLabel("", SwingConstants.CENTER);
    private final JButton restartButton = new JButton("다시 시작");
    private final JButton exitButton = new JButton("종료");
    public EndGameView() {
        setSize(200, 200);
        setLayout(new GridLayout(0, 1));
        add(label);
        add(gameInfo);
        add(restartButton);
        add(exitButton);
        setVisible(true);

    }

    public void setWinMessage() {
        label.setText("당신은 이겼습니다.");
    }

    public void setLoseMessage() {
        label.setText("당신은 졌습니다.");
    }

    public void setGameInfo(long startTime, int clickCount) {
        long gameTime = System.currentTimeMillis() - startTime;

        String message = String.format("게임 시간: %d초, 클릭 횟수: %d", gameTime / 1000, clickCount);
        gameInfo.setText(message);
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
