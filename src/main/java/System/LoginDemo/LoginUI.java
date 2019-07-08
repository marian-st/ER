package System.LoginDemo;

import State.StateEvent;
import State.StateChange;
import State.Store;
import State.MyString;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
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
    private final Subject<StateEvent> stream;

    public LoginUI(Store store, Subject<StateEvent> stream) {
        this.stream = stream;

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
                    //send and handle response from state
                    Disposable sub = stream.subscribe(se -> {
                        if(se.stateChange() == StateChange.LOGIN) {
                            User u = se.state().getUser();
                            if(!u.isValid())
                                updateMessage("Invalid username and password!");
                            else updateMessage(u.toString());

                        }
                    });
                    store.update(new MyString("LOG", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
                    return null;
                }
            };
            task.messageProperty().addListener((obs, oldMessage, newMessage) -> loginFeedback.setText(newMessage));
            new Thread(task).start();
        });

        root = new VBox(10, user, pass, log, loginFeedBack);
        root.setPadding(new Insets(10));
    }

    public VBox getRoot() {
        return root;
    }
}
