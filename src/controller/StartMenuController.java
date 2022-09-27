package controller;

import model.DifficultyType;
import view.DifficultySelectView;
import view.ExplainRuleView;
import view.StartMenuControllerView;

public class StartMenuController {

    private final StartMenuControllerView startMenuControllerView;
    private final ExplainRuleView explainRuleView;

    public StartMenuController(StartMenuControllerView startMenuControllerView) {
        this.startMenuControllerView = startMenuControllerView;
        this.explainRuleView = new ExplainRuleView();
        explainRuleView.setVisible(false);

        explainRuleView.getConfirmButton()
                .addActionListener((e) -> confirmRule());
    }

    public void initController() {
        startMenuControllerView.getDifficultySelectButton()
                .addActionListener((e) -> selectDifficulty());
        startMenuControllerView.getExplainRuleButton()
                .addActionListener((e) -> explainRule());
        startMenuControllerView.getExitButton()
                .addActionListener((e) -> exit());
    }

    private void selectDifficulty() {
        startMenuControllerView.dispose();
        explainRuleView.dispose();
        DifficultyType[] difficultyTypes = DifficultyType.values();
        DifficultySelectView difficultySelectView = new DifficultySelectView(difficultyTypes);
        DifficultySelectController difficultySelectController = new DifficultySelectController(difficultySelectView);
        difficultySelectController.initController();
    }

    private void explainRule() {
        explainRuleView.setVisible(true);
    }

    private void confirmRule() {
        explainRuleView.setVisible(false);
    }

    private void exit() {
        System.exit(0);
    }
}
