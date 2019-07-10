package InterfaceController;

import State.StateChange;
import State.StateEvent;
import State.StringCommand;
import System.LoginDemo.Sistema;
import System.LoginDemo.User;
import io.reactivex.subjects.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import State.Store;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.UUID;

public class LoginController {
    private Store<StringCommand> store;
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private Label test;

    public LoginController(Store store, Subject<StateEvent> stream) {
        this.store = store;

        stream.filter(se -> se.stateChange() == StateChange.LOGIN)
                .subscribe(se -> {
                    if (se.state().getUser() == se.state().getUserCheck()) {
                        test.setVisible(true);
                        test.setText("Logged as: " + se.state().getUser().getName());
                    } else {
                        test.setVisible(true);
                        test.setText("Invalid username and/or password");
                    }
                });
    }

    @FXML protected void login(ActionEvent event) {
        store.update(new StringCommand("LOG", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
    }
}
