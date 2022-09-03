package model;

import static common.Constant.MINE_NUMBER;

public enum CellType {
    BLANK,
    NUMBER,
    MINE;

    public static CellType of(int number) {
        if (number == MINE_NUMBER) {
            return MINE;
        } else if (number == 0) {
            return BLANK;
        } else {
            return NUMBER;
        }
    }
}
