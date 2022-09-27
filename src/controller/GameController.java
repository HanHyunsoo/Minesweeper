package controller;

import model.CellType;
import model.DifficultyType;
import model.MinesweeperBoard;
import model.Point;
import view.DifficultySelectView;
import view.EndGameView;
import view.MinesweeperBoardView;
import view.button.CellButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameController {

    private final MinesweeperBoard minesweeperBoard;
    private final MinesweeperBoardView minesweeperBoardView;
    private final EndGameView endGameView;
    private long startTime;
    private int clickCount;

    public GameController(MinesweeperBoard minesweeperBoard, MinesweeperBoardView minesweeperBoardView) {
        this.minesweeperBoard = minesweeperBoard;
        this.minesweeperBoardView = minesweeperBoardView;
        endGameView = new EndGameView();
        endGameView.setVisible(false);

        endGameView.getRestartButton().addActionListener((e) -> restartGame());
        endGameView.getExitButton().addActionListener((e) -> endGame());
    }

    public void initController() {
        CellButton[][] cellButtons = minesweeperBoardView.getCellButtons();

        for (CellButton[] cellButton : cellButtons) {
            for (CellButton cellbutton : cellButton) {
                cellbutton.addActionListener((e) -> clickCell(cellbutton.getPoint()));
            }

        }

        startTime = System.currentTimeMillis();
        clickCount = 0;
    }

    private void updateView(List<Point> pointList) {
        for (Point visitedPoint : pointList) {
            int y = visitedPoint.getY();
            int x = visitedPoint.getX();

            CellButton cellButton = minesweeperBoardView.getCellButtons()[y][x];
            cellButton.setBackground(Color.GRAY);
            cellButton.setEnabled(false);
            if (minesweeperBoard.getMatrix()[y][x] != 0) {
                setTextAndColor(cellButton, minesweeperBoard.getMatrix()[y][x]);
            }
            cellButton.setOpaque(true);
        }

        minesweeperBoardView.repaint();
        if (isWin()) gameWinView();
    }

    private void gameWinView() {
        endGameView.setWinMessage();
        endGameView.setVisible(true);
        endGameView.setGameInfo(startTime, clickCount);
    }

    private void gameOverView(Point point) {
        CellButton[][] cellButtons = minesweeperBoardView.getCellButtons();

        for (CellButton[] button : cellButtons) {
            for (CellButton cellButton : button) {
                cellButton.setEnabled(false);
            }
        }

        CellButton cellButton = minesweeperBoardView.getCellButtons()[point.getY()][point.getX()];
        cellButton.setBackground(Color.RED);
        cellButton.setOpaque(true);

        cellButton.setText("*");

        endGameView.setLoseMessage();
        endGameView.setVisible(true);
        endGameView.setGameInfo(startTime, clickCount);
    }

    private void restartGame() {
        minesweeperBoardView.dispose();
        endGameView.dispose();

        DifficultyType[] difficultyTypes = DifficultyType.values();
        DifficultySelectView difficultySelectView = new DifficultySelectView(difficultyTypes);
        DifficultySelectController difficultySelectController = new DifficultySelectController(difficultySelectView);
        difficultySelectController.initController();
    }

    private void endGame() {
        System.exit(0);
    }

    private void clickCell(Point point) {
        clickCount++;
        if (point.getCellType(minesweeperBoard) == CellType.MINE) {
            gameOverView(point);
            return;
        }

        List<Point> pointList = minesweeperBoard.bfs(point);
        updateView(pointList);
    }

    private boolean isWin() {
        return minesweeperBoard.getVisitedCount() == minesweeperBoard.getTotalMineLess();
    }

    private void setTextAndColor(CellButton cellButton, int number) {
        JTextField textField = new JTextField(String.valueOf(number));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setSize(29, 29);

        if (number >= 3) {
            textField.setDisabledTextColor(Color.RED);
        } else if (number == 2) {
            textField.setDisabledTextColor(Color.GREEN);
        } else {
            textField.setDisabledTextColor(Color.BLUE);
        }

        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setEnabled(false);

        cellButton.add(textField);
    }
}
