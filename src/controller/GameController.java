package controller;

import model.CellType;
import model.MinesweeperBoard;
import model.Point;
import view.MinesweeperBoardView;

import java.util.Scanner;

public class GameController extends AbstractController {

    private final Scanner sc;
    private final MinesweeperBoard minesweeperBoard;
    private final MinesweeperBoardView minesweeperBoardView;
    private Point currentPoint;
    private int pointCount = 0;
    private String inputErrorMessage = null;

    public void startGame() {
        System.out.printf("게임을 시작합니다.\n게임 난이도 : %s\n", minesweeperBoard.getDifficulty().toString());
        System.out.println("확인하고 싶은 칸의 좌표(y, x)를 입력해주세요.(예: 4 2)");
        long startTime = System.currentTimeMillis();
        long endTime;

        while (minesweeperBoard.getVisitedCount() != minesweeperBoard.getTotalMineLess()) {
            clearConsole();
            System.out.println(minesweeperBoardView.getHideBoard());
//            System.out.println();
//            System.out.println(minesweeperBoardView.getShowBoard(new Point(0, 0)));

            if (inputErrorMessage != null) {
                System.out.println(inputErrorMessage);
                inputErrorMessage = null;
            }
            currentPoint = inputPoint();
            if (currentPoint == null) continue;

            if (currentPoint.getCellType(minesweeperBoard) == CellType.MINE) {
                break;
            }

            minesweeperBoard.bfs(currentPoint);
        }

        endTime = System.currentTimeMillis();
        long totalGameSec = millisecondToSecond(endTime - startTime);
        endGame(totalGameSec, minesweeperBoard.getVisitedCount() == minesweeperBoard.getTotalMineLess());

    }

    public void endGame(long time, boolean isWin) {
        clearConsole();
        System.out.println(minesweeperBoardView.getShowBoard(currentPoint));
        System.out.println((isWin) ? "YOU WIN!" : "GAME OVER");
        System.out.printf("당신은 %d초 동안 %d만큼 클릭해서 게임을 마무리했습니다.\n", time, getPointCount());
    }

    private long millisecondToSecond(long ms) {
        return ms / 1000;
    }

    public GameController(Scanner sc, MinesweeperBoard minesweeperBoard) {
        this.sc = sc;
        this.minesweeperBoard = minesweeperBoard;
        this.minesweeperBoardView = new MinesweeperBoardView(minesweeperBoard);
    }

    public Point inputPoint() {
        Point resultPoint = null;

        try {
            System.out.print("좌표 입력(y x): ");
            resultPoint = new Point(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
            if (resultPoint.isOutBoardRange(getMinesweeperBoard())) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (resultPoint.isVisited(getMinesweeperBoard())) {
                inputErrorMessage = "이미 확인된 좌표입니다.";
            }
            pointCount++;
        } catch (NumberFormatException e) {
            inputErrorMessage = "(오류) - 숫자로만 입력해 주세요.";
        } catch (ArrayIndexOutOfBoundsException e) {
            inputErrorMessage = "(오류) - 지정된 범위 내 좌표만 입력해주세요.";
        }

        return (inputErrorMessage == null) ? resultPoint : null;
    }

    public MinesweeperBoard getMinesweeperBoard() {
        return minesweeperBoard;
    }

    public int getPointCount() {
        return pointCount;
    }
}
