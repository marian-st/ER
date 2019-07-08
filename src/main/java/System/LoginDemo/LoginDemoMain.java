package System.LoginDemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginDemoMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sistema.systemSetUp();
        primaryStage.setTitle("Login");
        LoginComponent login = new LoginComponent(Sistema.getStore());
        primaryStage.setScene(new Scene(login.getInterface(), 300, 250));
        primaryStage.show();
    }
}
