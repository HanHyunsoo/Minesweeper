import controller.GameController;
import controller.MenuType;
import controller.StartMenuController;
import model.DifficultyType;
import model.MinesweeperBoard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StartMenuController startMenuController = new StartMenuController(sc);
        MenuType menuType = startMenuController.selectMenu(true);
        DifficultyType difficultyType = DifficultyType.EASY;

        switch (menuType) {
            case START_GAME:
                difficultyType = startMenuController.selectDifficultyType();
                break;
            case EXPLAIN_RULE:
                startMenuController.explainRole();
                startMenuController.selectMenu(false);
                break;
            case QUIT:
                startMenuController.exit();
                break;
        }

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(difficultyType);
        GameController gameController = new GameController(sc, minesweeperBoard);

        gameController.startGame();
    }
}
