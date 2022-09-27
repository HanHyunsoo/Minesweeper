package view;

import javax.swing.*;
import java.awt.*;

public class ExplainRuleView extends JDialog {
    private final JTextArea rule = new JTextArea("지뢰찾기의 규칙은 간단합니다. \n게임판이 칸으로 나뉘어 있고, 무작위한 칸에 지뢰가 깔려있습니다. \n게임을 클리어 하려면 지뢰가 있는 칸을 제외한 다른 모든 칸을 클릭해서 열어야 합니다. \n지뢰가 없는 칸을 열면 숫자가 써져 있는데, 이 숫자는 이웃한 칸에 있는 지뢰의 개수를 뜻합니다.");
    private final JButton confirmButton = new JButton("확인");

    public ExplainRuleView() {
        setSize(500, 200);
        setLayout(new GridLayout(0, 1));
        rule.setEditable(false);
        rule.setBackground(new Color(0, 0, 0, 0));
        rule.setLineWrap(true);

        add(rule);
        add(confirmButton);
        setVisible(true);

    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
