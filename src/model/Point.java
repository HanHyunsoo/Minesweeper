package model;

import static common.Constant.MINE_NUMBER;

public class Point {
    private int y;
    private int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isExistMine(MinesweeperBoard minesweeperBoard) {
        return minesweeperBoard.getMatrix()[y][x] == MINE_NUMBER;
    }

    public boolean isOutBoardRange(MinesweeperBoard minesweeperBoard) {
        DifficultyType difficulty = minesweeperBoard.getDifficulty();
        int rowSize = difficulty.getRowSize();
        int columnSize = difficulty.getColumnSize();

        // y와 x가 지정된 범위 밖에 있으면 true 안에 있으면 false
        return (y < 0 || y >= rowSize) || (x < 0 || x >= columnSize);
    }

    public void movePoint(int y, int x) {
        this.y += y;
        this.x += x;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashValue = 1;
        hashValue = hashValue * prime * y * x;
        return hashValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            throw  new ClassCastException();
        }

        Point other = (Point) obj;
        return (y == other.y && x == other.x);
    }
}
