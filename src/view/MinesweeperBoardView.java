package view;

import model.MinesweeperBoard;
import model.Point;

import java.util.Arrays;

import static common.Constant.MINE_NUMBER;
import static common.ConsoleColors.*;

public class MinesweeperBoardView {
    private final MinesweeperBoard minesweeperBoard;
    private final String[][] hideMatrix;
    private final StringBuilder sb = new StringBuilder();
    private final String format = "%2s ";

    public String getHideBoard() {
        int[][] matrix = minesweeperBoard.getMatrix();
        int[][] visited = minesweeperBoard.getVisited();

        int rowSize = minesweeperBoard.getDifficulty().getRowSize();
        int colSize = minesweeperBoard.getDifficulty().getColumnSize();

        for (int i = -1; i < rowSize; i++) {
            for (int j = -1; j < colSize; j++) {
                // 테이블 행, 열 제목 추가
                if (i == -1 || j == -1) {
                    setTableTitle(i, j);
                    continue;
                }

                if (visited[i][j] == 1) {
                    // 해당 좌표에 있는 값이 0이면 눌린 네모 추가
                    if (matrix[i][j] == 0) {
                        sb.append(String.format(format, "□"));
                    // 해당 좌표에 있는 값이 양수라면 숫자 추가
                    } else {
                        sb.append(String.format(getBoldFormat(matrix[i][j]), matrix[i][j]));
                    }
                } else {
                    sb.append(String.format(format, "■"));
                }
                sb.append(' ');
            }
            sb.append('\n');
        }

        String hideBoard = sb.toString();
        sb.setLength(0);

        return hideBoard;
    }

    public String getShowBoard(Point minePoint) {
        int[][] matrix = minesweeperBoard.getMatrix();
        int rowSize = minesweeperBoard.getDifficulty().getRowSize();
        int colSize = minesweeperBoard.getDifficulty().getColumnSize();

        for (int i = -1; i < rowSize; i++) {
            for (int j = -1; j < colSize; j++) {
                // 테이블 행, 열 제목 추가
                if (i == -1 || j == -1) {
                    setTableTitle(i, j);
                    continue;
                }

                switch (matrix[i][j]) {
                    case MINE_NUMBER:
                        if (i == minePoint.getY() && j == minePoint.getX()) {
                            sb.append(String.format(RED_BACKGROUND + format + RESET, "*"));
                        } else {
                            sb.append(String.format(PURPLE_BACKGROUND + format + RESET, "*"));
                        }
                        break;
                    case 0:
                        sb.append(String.format(format, "□"));
                        break;
                    default:
                        sb.append(String.format(getBoldFormat(matrix[i][j]), matrix[i][j]));
                        break;
                }
                sb.append(' ');
            }
            sb.append('\n');
        }

        String failureBoard = sb.toString();
        sb.setLength(0);

        return failureBoard;
    }

    private void setTableTitle(int y, int x) {
        sb.append(WHITE_BACKGROUND);

        if (y == -1 && x == -1) {
            sb.append("y\\x").append(' ');
        } else if (y == -1) {
            sb.append(String.format(format, x)).append(' ');
        } else {
            sb.append(String.format(format, y)).append(RESET);
        }

        if (y != -1) {
            sb.append(' ');
        } else {
            sb.append(RESET);
        }
    }

    private String getBoldFormat(int number) {
        String resultFormat;

        if (number >= 3) {
            resultFormat = RED_BOLD + format + RESET;
        } else if (number == 2) {
            resultFormat = GREEN_BOLD + format + RESET;
        } else {
            resultFormat = BLUE_BOLD + format + RESET;
        }

        return resultFormat;
    }

    public MinesweeperBoardView(MinesweeperBoard minesweeperBoard) {
        this.minesweeperBoard = minesweeperBoard;
        int rowSize = minesweeperBoard.getDifficulty().getRowSize();
        int columnSize = minesweeperBoard.getDifficulty().getColumnSize();
        this.hideMatrix = new String[rowSize][columnSize];
        setHideMatrix();
    }

    private void setHideMatrix() {
        for (String[] matrix : hideMatrix) {
            Arrays.fill(matrix, "■");
        }
    }
}
