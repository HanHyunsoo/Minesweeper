package view;

import javax.swing.*;
import java.awt.*;

public class StartMenuControllerView extends AbstractView {

    private final JLabel titleLabel = new JLabel("지뢰 찾기", SwingConstants.CENTER);
    private final JButton difficultySelectButton = new JButton("난이도 선택");
    private final JButton explainRuleButton = new JButton("룰 설명");
    private final JButton exitButton = new JButton("종료");

    public StartMenuControllerView() {
        super(new Dimension(200, 200));
        setLayout(new GridLayout(0, 1));
        add(titleLabel);
        add(difficultySelectButton);
        add(explainRuleButton);
        add(exitButton);
        setVisible(true);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getDifficultySelectButton() {
        return difficultySelectButton;
    }

    public JButton getExplainRuleButton() {
        return explainRuleButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
