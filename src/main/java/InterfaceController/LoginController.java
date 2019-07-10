package InterfaceController;

import State.Entities.Role;
import State.Entities.User;
import State.StateChange;
import State.StateEvent;
import State.StringCommand;
import System.LoginDemo.Sistema;
import io.reactivex.subjects.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import State.Store;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.UUID;

public class LoginController {
    private Store<StringCommand> store;
    @FXML private TextField userField;
    @FXML private PasswordField passField;

    public LoginController(Store store, Subject<StateEvent> stream) {
        this.store = store;

        stream.filter(se -> se.stateChange() == StateChange.LOGIN)
                .subscribe(se -> {
                    if (se.state().getUser() == se.state().getUserCheck() && se.state().getUser().getRole() == Role.HEAD_PHYSICIAN) {
                        Sistema.getInstance().setInterface("HP");
                    } else {
                        System.out.println("Invalid username and/or password");
                    }
                });
    }

    @FXML protected void login(ActionEvent event) {
        store.update(new StringCommand("LOG", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
    }
}
