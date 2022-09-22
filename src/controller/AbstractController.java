package controller;

import java.util.Scanner;

public abstract class AbstractController {
    protected Object validateInput(Scanner sc, Object[] objects) {
        Object resultType;

        while (true) {
            try {
                int inputIndex = Integer.parseInt(sc.nextLine());
                resultType = objects[inputIndex];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("(오류) - 위에 명시 된 번호로만 선택해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("(오류) - 숫자로만 입력해 주세요.");
            }
        }

        return resultType;
    }

    protected void clearConsole() {
        System.out.print("\033\143");
        System.out.flush();
    }
}
