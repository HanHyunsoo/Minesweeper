package view;

import model.DifficultyType;
import model.Point;
import view.button.CellButton;

import java.awt.*;

public class MinesweeperBoardView extends AbstractView {
    private final CellButton[][] cellButtons;

    public MinesweeperBoardView(DifficultyType difficultyType, boolean visible) {
        super(
                new Dimension(
                        difficultyType.getColumnSize() * 30,
                        difficultyType.getRowSize() * 30
                )
        );
        int rowSize = difficultyType.getRowSize();
        int colSize = difficultyType.getColumnSize();
        cellButtons = new CellButton[rowSize][colSize];
        setLayout(new GridLayout(rowSize, colSize));

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                CellButton button = new CellButton(new Point(i, j));
                button.setMargin(new Insets(0, 0, 0, 0));
                cellButtons[i][j] = button;
                add(button);
            }
        }

        repaint();
        setVisible(visible);

    }

    public CellButton[][] getCellButtons() {
        return cellButtons;
    }


}
