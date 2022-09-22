package controller;

import model.DifficultyType;

import java.util.Scanner;

public class StartMenuController extends AbstractController {

    private final Scanner sc;

    public MenuType selectMenu(boolean showMenu) {
        MenuType[] menuTypes = MenuType.values();
        if (showMenu) {
            System.out.println("메뉴 리스트");
            for (int i = 0; i < menuTypes.length; i++) {
                System.out.println(i + ". " + menuTypes[i].getMenuName());
            }
        }

        System.out.print("메뉴 선택: ");

        return (MenuType) validateInput(sc, menuTypes);
    }

    public DifficultyType selectDifficultyType() {
        System.out.println("난이도를 선택해 주세요.");
        DifficultyType[] difficultyTypes = DifficultyType.values();
        for (int i = 0; i < difficultyTypes.length; i++) {
            System.out.println(i + ". " + difficultyTypes[i].toString());
        }

        DifficultyType resultDifficulty = (DifficultyType) validateInput(sc, difficultyTypes);
        clearConsole();

        return resultDifficulty;
    }

    public void explainRole() {
        System.out.println("지뢰찾기 게임은 칸마다 무작위하게 지뢰가 깔려있습니다. \n게임을 클리어 하려면 지뢰가 있는 칸을 제외한 다른 모든 칸을 (y, x)형태로 입력받아서 해당 칸을 열어야 합니다. \n지뢰가 없는 칸을 열면 숫자가 써져 있는데, 이 숫자는 이웃한 칸에 있는 지뢰의 개수를 뜻합니다");
    }

    public void exit() {
        System.exit(0);
    }

    public StartMenuController(Scanner sc) {
        this.sc = sc;
    }
}
