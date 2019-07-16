package InterfaceController;

import Component.MonitoringComponent;
import Entities.Role;
import Entities.User;
import State.StateEvent;
import State.StringCommand;
import Component.HPComponent;
import System.Sistema;
import io.reactivex.subjects.Subject;
import javafx.fxml.FXML;
import State.Store;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.UUID;

public class LoginController {
    private Sistema sys = Sistema.getInstance();
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
        sys.endSystem();
    }

    @FXML protected void buttonPressed(KeyEvent e)
    {
        if(e.getCode().toString().equals("ENTER"))
        {
            this.login();
        }
    }

    @FXML protected void startMonitoring() {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/logo.png"));
        stage.setScene(new Scene(sys.getInterface("MON")));
        stage.setTitle(MonitoringComponent.monitoringTitle);
        stage.sizeToScene();
        stage.show();
    }
}
