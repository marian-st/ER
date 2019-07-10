package InterfaceController;

import State.StateChange;
import State.StateEvent;
import State.StringCommand;
import State.Entities.User;
import io.reactivex.subjects.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import State.Store;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class LoginController {
    private Store<StringCommand> store;
    @FXML private AnchorPane mainAnchor;
    @FXML private Button loginButton;
    @FXML private Button logoutButton;

    @FXML private TextField userField;
    @FXML private PasswordField passField;
    public LoginController(Store store, Subject<StateEvent> stream) {
        this.store = store;
        stream.filter(se -> se.stateChange() == StateChange.LOGIN)
                .subscribe(se -> {
                    if (se.state().getUser() == se.state().getUserCheck()) {
                        System.out.println("Logged as: " + se.state().getUser().getName());
                        logoutButton.setVisible(true);
                    } else {
                        logoutButton.setVisible(false);
                        System.out.println("Invalid username and/or password");
                    }
                });
    }

    @FXML protected void login(ActionEvent event) {
        store.update(new StringCommand("LOG", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
    }

    @FXML protected void logout(ActionEvent event) {
        store.update(new StringCommand("LOGOUT", UUID.randomUUID()));
    }
}
