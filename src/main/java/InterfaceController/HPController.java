package InterfaceController;

import State.StateEvent;
import State.Store;
import State.StringCommand;
import System.LoginDemo.HP.HPComponent;
import System.LoginDemo.LoginComponent;
import System.LoginDemo.Sistema;
import io.reactivex.subjects.Subject;
import javafx.fxml.FXML;

import java.util.UUID;

public class HPController {
    private Store<StringCommand> store;
    private final Sistema sys = Sistema.getInstance();

    public HPController(Store<StringCommand> store, Subject<StateEvent> stream) {
        this.store = store;

    }

    @FXML protected void showPatients() {
        sys.setInterface("HPDF", HPComponent.HPTitle);
    }

    @FXML protected void search() {
        sys.setInterface("HPS", HPComponent.HPTitle);
    }
    @FXML protected void dismissPatient() {
        sys.setInterface("HPD", HPComponent.HPTitle);
    }
    @FXML protected void showMonitoring() {
        sys.setInterface("HPM", HPComponent.HPTitle);
    }

    @FXML protected void logout() {
        store.update(new StringCommand("LOGOUT", UUID.randomUUID()));
        sys.setInterface("login", LoginComponent.loginTitle);
    }
}
