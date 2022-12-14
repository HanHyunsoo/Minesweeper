package model;

import java.util.*;

import static common.Constant.*;

public class MinesweeperBoard {
    private int[][] matrix;
    private int[][] visited;
    private DifficultyType difficulty;
    private HashSet<Point> mineSet = new HashSet<>();
    private int visitedCount;
    private int totalMineLess;

    public MinesweeperBoard(DifficultyType difficulty) {
        this.difficulty = difficulty;
        this.matrix = new int[difficulty.getRowSize()][difficulty.getColumnSize()];
        this.visited = new int[difficulty.getRowSize()][difficulty.getColumnSize()];
        setRandomMines();
        mineSet.forEach(this::setCountAroundMine);
        this.visitedCount = 0;
        this.totalMineLess = difficulty.getRowSize() * difficulty.getColumnSize() - difficulty.getMineSize();
    }

    public List<Point> bfs(Point point) {

        Queue<Point> pointQueue = new LinkedList<>();
        List<Point> resultVisitedPoint = new ArrayList<>();
        pointQueue.add(point);

        while (!pointQueue.isEmpty()) {
            Point pollPoint = pointQueue.poll();
            if (pollPoint.isVisited(this)) continue;

            visited[pollPoint.getY()][pollPoint.getX()] = 1;
            visitedCount++;
            resultVisitedPoint.add(pollPoint);

            if (pollPoint.getCellType(this) == CellType.NUMBER) continue;

            for (int i = 0; i < 8; i++) {
                Point nearPoint = new Point(pollPoint.getY() + moveY[i], pollPoint.getX() + moveX[i]);
                if (!nearPoint.isOutBoardRange(this)) {
                    if (nearPoint.getCellType(this) != CellType.MINE && visited[nearPoint.getY()][nearPoint.getX()] == 0) {
                        pointQueue.add(nearPoint);
                    }
                }
            }
        }

        return resultVisitedPoint;
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
        // 0??? ?????????, 1??? ??????, 2??? ??????
        visited[minePoint.getY()][minePoint.getX()] = 2;
    }

    private void setCountAroundMine(Point minePoint) {
        for (int i = 0; i < 8; i++) {
            Point nearPoint = new Point(minePoint.getY() + moveY[i], minePoint.getX() + moveX[i]);
            // ????????? ????????? ?????? ?????? ????????? ????????? ?????????
            if (!(nearPoint.isOutBoardRange(this))) {
                if (nearPoint.getCellType(this) != CellType.MINE) {
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

    public int getVisitedCount() {
        return visitedCount;
    }

    public int getTotalMineLess() {
        return totalMineLess;
    }
}
