package controller;

import model.CellType;
import model.DifficultyType;
import model.MinesweeperBoard;
import model.Point;
import view.MinesweeperBoardView;

import java.util.Scanner;

public class GameController extends AbstractController {

    private final Scanner sc;
    private MinesweeperBoard minesweeperBoard;
    private MinesweeperBoardView minesweeperBoardView;
    private Point currentPoint;
    private int pointCount = 0;

    public void selectDifficultyType() {
        System.out.println("난이도를 선택해 주세요.");
        DifficultyType[] difficultyTypes = DifficultyType.values();
        for (int i = 0; i < difficultyTypes.length; i++) {
            System.out.println(i + ". " + difficultyTypes[i].toString());
        }

        DifficultyType resultDifficulty = (DifficultyType) validateInput(sc, difficultyTypes);

        this.minesweeperBoard = new MinesweeperBoard(resultDifficulty);
        this.minesweeperBoardView = new MinesweeperBoardView(minesweeperBoard);
    }

    public void startGame() {
        if (minesweeperBoard == null) {
            selectDifficultyType();
        }
        System.out.printf("게임을 시작합니다.\n게임 난이도 : %s\n", minesweeperBoard.getDifficulty().toString());
        System.out.println("확인하고 싶은 칸의 좌표(y, x)를 입력해주세요.(예: 4 2)");
        long startTime = System.currentTimeMillis();
        long endTime;

        while (minesweeperBoard.getVisitedCount() != minesweeperBoard.getTotalMineLess()) {
            clearConsole();
            System.out.println(minesweeperBoardView.getHideBoard());

            currentPoint = inputPoint();
            pointCount++;

            if (currentPoint.getCellType(minesweeperBoard) == CellType.MINE) {
                endTime = System.currentTimeMillis();
                endGame(millisecondToSecond(endTime - startTime));
                break;
            } else if (currentPoint.isVisited(minesweeperBoard)) {
                System.out.println("이미 확인된 좌표입니다.");
                continue;
            }

            minesweeperBoard.bfs(currentPoint);
        }

        endTime = System.currentTimeMillis();
        endGame(millisecondToSecond(endTime - startTime));
    }

    public void endGame(long time) {
        clearConsole();
        System.out.println(minesweeperBoardView.getShowBoard(currentPoint));
        System.out.printf("당신은 %d초 동안 %d만큼 클릭해서 게임을 마무리했습니다.\n", time, getPointCount());
    }

    private void clearConsole() {
        System.out.print("\033\143");
        System.out.flush();
    }

    private long millisecondToSecond(long ms) {
        return ms / 1000;
    }

    public GameController(Scanner sc) {
        this.sc = sc;
    }

    public Point inputPoint() {
        Point resultPoint;

        while (true) {
            try {
                System.out.print("좌표 입력(y x): ");
                resultPoint = new Point(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
                if (resultPoint.isOutBoardRange(getMinesweeperBoard())) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("(오류) - 숫자로만 입력해 주세요.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("(오류) - 지정된 범위 내 좌표만 입력해주세요.");
            }
        }
        return resultPoint;
    }

    public MinesweeperBoard getMinesweeperBoard() {
        return minesweeperBoard;
    }

    public int getPointCount() {
        return pointCount;
    }
}
