package System.LoginDemo;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginDemoMain extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sistema sys = Sistema.getInstance();
        sys.setupUI(primaryStage);

        sys.setInterface("login");
        primaryStage.show();
    }

}
