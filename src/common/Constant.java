package common;

public class Constant {
    public static final int MINE_NUMBER = 10000;

    // 인덱스 순서대로 북, 북동, 동, 남동, 남, 남서, 서, 북서
    public static final int[] moveY = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] moveX = {0, 1, 1, 1, 0, -1, -1, -1};
}
