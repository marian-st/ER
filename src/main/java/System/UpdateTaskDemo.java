package System;


import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UpdateTaskDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label();
        label.setText("Count: 0");
        javafx.scene.control.Button runInTaskButton = new Button("Update in background Task");
        HBox buttons = new HBox(10, runInTaskButton);
        buttons.setPadding(new Insets(10));
        VBox root = new VBox(10, label, buttons);
        root.setPadding(new Insets(10));

        runInTaskButton.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    for (int i=1; i<=10; i++) {
                        updateMessage("Count: "+i);
                        Thread.sleep(250);
                    }
                    return null;
                }
            };
            task.messageProperty().addListener((obs, oldMessage, newMessage) -> label.setText(newMessage));
            new Thread(task).start();
        });

        primaryStage.setScene(new Scene(root, 300, 100));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}