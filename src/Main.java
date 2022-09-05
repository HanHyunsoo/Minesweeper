import controller.StartMenuController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StartMenuController startMenuController = new StartMenuController(sc);
        startMenuController.selectMenu(true);
    }
}
