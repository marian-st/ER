package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HP_base.fxml"));
        Scene scene = new Scene(root, 1120, 760);

        primaryStage.setTitle("ER - @UserName");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
