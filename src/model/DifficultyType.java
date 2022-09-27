package model;

public enum DifficultyType {
    EASY(9, 9, 10, "초급"),
    NORMAL(16, 16, 40, "중급"),
    HARD(16, 30, 99, "고급"),
    HELL(30, 30, 150, "지옥");

    private int rowSize;
    private int columnSize;
    private int mineSize;
    private String description;

    DifficultyType(int rowSize, int columnSize, int mineSize, String description) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.mineSize = mineSize;
        this.description = description;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public int getMineSize() {
        return mineSize;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s(사이즈 = %d x %d, 지뢰 개수 = %d)", description, rowSize, columnSize, mineSize);
    }
}
