package InterfaceController;

import State.Entities.Role;
import State.Entities.User;
import State.StateEvent;
import State.StringCommand;
import System.LoginDemo.HP.HPComponent;
import System.LoginDemo.Sistema;
import io.reactivex.subjects.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import State.Store;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.UUID;

public class LoginController {
    private Store<StringCommand> store;
    @FXML private TextField userField;
    @FXML private PasswordField passField;

    public LoginController(Store store, Subject<StateEvent> stream) {
        this.store = store;
        stream.subscribe(se -> {
            userField.clear();
            passField.clear();
            if (se.state().getUser() == se.state().getUserCheck() && se.state().getUser().getRole() == Role.HEAD_PHYSICIAN) {
                Sistema.getInstance().setInterface("HPDF", HPComponent.HPTitle);
            } else {
                System.out.println("Invalid username and/or password");
            }
        });
    }

    @FXML protected void login() {
        store.update(new StringCommand("LOGIN", UUID.randomUUID(), new User(userField.getText(), passField.getText())));
    }

    @FXML protected void close() {
        Sistema.getInstance().endSystem();
    }

    @FXML protected void buttonPressed(KeyEvent e)
    {
        if(e.getCode().toString().equals("ENTER"))
        {
            this.login();
        }
    }
}
