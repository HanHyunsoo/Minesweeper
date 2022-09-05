package controller;

public enum MenuType {
    START_GAME("Start Menu", "게임을 시작합니다."),
    EXPLAIN_RULE("Explain Rule", "지뢰찾기 설명."),
    QUIT("Quit", "게임을 종료합니다.");

    private String menuName;
    private String description;

    MenuType(String menuName, String description) {
        this.menuName = menuName;
        this.description = description;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getMenuName();
    }
}
