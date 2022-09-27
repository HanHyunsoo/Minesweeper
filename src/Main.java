import controller.StartMenuController;
import view.StartMenuControllerView;

public class Main {
    public static void main(String[] args) {
        StartMenuControllerView startMenuControllerView = new StartMenuControllerView();
        StartMenuController startMenuController = new StartMenuController(startMenuControllerView);
        startMenuController.initController();
    }
}
