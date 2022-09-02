package model;

import java.util.HashSet;
import java.util.Random;

import static common.Constant.*;

public class MinesweeperBoard {
    private int[][] matrix;
    private int[][] visited;
    private DifficultyType difficulty;
    private HashSet<Point> mineSet = new HashSet<>();

    public MinesweeperBoard(DifficultyType difficulty) {
        this.difficulty = difficulty;
        matrix = new int[difficulty.getRowSize()][difficulty.getColumnSize()];
        visited = new int[difficulty.getRowSize()][difficulty.getColumnSize()];
        setRandomMines();
        mineSet.forEach(this::setCountAroundMine);
    }

    private void setRandomMines() {
        int mineSize = difficulty.getMineSize();
        int rowSize = difficulty.getRowSize();
        int columnSize = difficulty.getColumnSize();

        Random random = new Random();
        int randomRowSize;
        int randomColumnSize;
        Point minePoint;

        for (int i = 0; i < mineSize; i++) {
            randomRowSize = random.nextInt(rowSize);
            randomColumnSize = random.nextInt(columnSize);
            minePoint = new Point(randomRowSize, randomColumnSize);

            if (mineSet.contains(minePoint)) {
                i--;
            } else {
                mineSet.add(minePoint);
                plantMine(minePoint);
            }
        }
    }

    private void plantMine(Point minePoint) {
        matrix[minePoint.getY()][minePoint.getX()] = MINE_NUMBER;
        // 0은 미확인, 1은 확인, 2는 지뢰
        visited[minePoint.getY()][minePoint.getX()] = 2;
    }

    private void setCountAroundMine(Point minePoint) {
        for (int i = 0; i < 8; i++) {
            Point nearPoint = new Point(minePoint.getY() + moveY[i], minePoint.getX() + moveX[i]);
            // 좌표가 범위에 있고 해당 좌표에 지뢰가 없다면
            if (!(nearPoint.isOutBoardRange(this))) {
                if (!(nearPoint.isExistMine(this))) {
                    matrix[nearPoint.getY()][nearPoint.getX()] += 1;
                }
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getVisited() {
        return visited;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public HashSet<Point> getMineSet() {
        return mineSet;
    }
}
