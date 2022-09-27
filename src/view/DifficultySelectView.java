package view;

import model.DifficultyType;
import view.button.DifficultyButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DifficultySelectView extends AbstractView {

    private final List<DifficultyButton> difficultyButtons = new ArrayList<>();

    public DifficultySelectView(DifficultyType[] difficultyTypes) {
        super(new Dimension(200, 200));
        setLayout(new GridLayout(0, 1));
        add(new JLabel("난이도 선택", SwingConstants.CENTER));
        for (DifficultyType difficultyType : difficultyTypes) {
            DifficultyButton button = new DifficultyButton(difficultyType);
            difficultyButtons.add(button);
            add(button, BorderLayout.CENTER);
        }

        repaint();
        setVisible(true);
    }

    public List<DifficultyButton> getDifficultyButtons() {
        return difficultyButtons;
    }
}