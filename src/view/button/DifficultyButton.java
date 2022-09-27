package view.button;


import model.DifficultyType;

import javax.swing.*;

public class DifficultyButton extends JButton {
    private final DifficultyType difficultyType;

    public DifficultyButton(DifficultyType difficultyType) {
        super(difficultyType.getDescription());
        this.difficultyType = difficultyType;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }
}
