package controller;

import model.DifficultyType;
import model.MinesweeperBoard;
import view.button.DifficultyButton;
import view.DifficultySelectView;
import view.MinesweeperBoardView;

public class DifficultySelectController {

    private DifficultyType difficultyType;
    private final DifficultySelectView difficultySelectView;

    public DifficultySelectController(DifficultySelectView difficultySelectView) {
        this.difficultySelectView = difficultySelectView;
    }

    public void initController() {
        for (DifficultyButton difficultyButton : difficultySelectView.getDifficultyButtons()) {
            difficultyButton.addActionListener((e) -> {
                this.difficultyType = difficultyButton.getDifficultyType();
                difficultySelectView.dispose();
                gameStart();
            });
        }
    }

    private void gameStart() {
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(difficultyType);
        MinesweeperBoardView minesweeperBoardView = new MinesweeperBoardView(difficultyType, true);
        GameController gameController = new GameController(minesweeperBoard, minesweeperBoardView);
        gameController.initController();
    }
}
