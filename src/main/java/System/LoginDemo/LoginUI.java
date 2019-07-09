package System.LoginDemo;

import State.StateEvent;
import State.StateChange;
import State.Store;
import State.MyString;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.UUID;

public class LoginUI {
    private VBox root;

    public LoginUI(Store store, Subject<StateEvent> stream) {

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
        HBox loginFeedBackBox = new HBox(10, loginFeedback);

        Button log = new Button("Log");
        log.setAlignment(Pos.CENTER);
        log.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                //send and handle response from state
                store.update(new MyString("LOG", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
                return null;
                }
            };
            new Thread(task).start();
        });

        Button logout = new Button("Logout");
        logout.setAlignment(Pos.CENTER_RIGHT);
        logout.setVisible(false);

        logout.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                    //send and handle response from state
                    store.update(new MyString("LOGOUT", UUID.randomUUID()));
                    return null;
                }
            };
            new Thread(task).start();
        });
        stream.filter(se -> se.stateChange() == StateChange.LOGIN)
                .subscribe(se -> {
                    if (se.state().getUser() == se.state().getUserCheck()) {
                        logout.setVisible(true);
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                loginFeedback.setText("Logged as: " + se.state().getUser().getName());
                            }});
                    } else {
                        logout.setVisible(false);
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                loginFeedback.setText("Invalid username and/or password");
                            }});
                    }
        });
        root = new VBox(10, user, pass, log, logout, loginFeedBackBox);
        root.setPadding(new Insets(10));
    }

    public VBox getRoot() {
        return root;
    }
}
