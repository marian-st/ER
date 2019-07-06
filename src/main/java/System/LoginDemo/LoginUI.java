package System.LoginDemo;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class LoginUI {
    private static LoginUI loginUI = new LoginUI();
    private static VBox root;

    private LoginUI() {
        Label userLabel;
        Label passLabel;
        TextField userField;
        TextField passField;
        Label loginFeedback;

        userLabel = new Label("User");
        passLabel = new Label("Password");
        userField = new TextField();
        userField.setPromptText("username");
        passField = new TextField();
        passField.setPromptText("password");
        HBox user = new HBox(10, userLabel, userField);
        user.setAlignment(Pos.CENTER);
        HBox pass = new HBox(10, passLabel, passField);
        pass.setAlignment(Pos.CENTER);
        loginFeedback = new Label();
        HBox loginFeedBack = new HBox(10, loginFeedback);

        Button log = new Button("Log");
        log.setAlignment(Pos.CENTER);
        log.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        User x = new User(userField.getText(), passField.getText());
                        updateMessage(x.toString());
                    } catch (IllegalArgumentException e) {
                        updateMessage("Username and Password Invalid!!");
                    }

                    return null;
                }
            };
            task.messageProperty().addListener((obs, oldMessage, newMessage) -> loginFeedback.setText(newMessage));
            new Thread(task).start();
        });

        root = new VBox(10, user, pass, log, loginFeedBack);
        root.setPadding(new Insets(10));
    }

    public static LoginUI getInstance() {
        return loginUI;
    }

    public static VBox getRoot() {
        return root;
    }
}
