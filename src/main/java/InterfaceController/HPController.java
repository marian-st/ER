package InterfaceController;

import State.Entities.Patient;
import State.StateEvent;
import State.Store;
import State.StringCommand;
import System.LoginDemo.HP.HPComponent;
import System.LoginDemo.LoginComponent;
import System.LoginDemo.Sistema;
import io.reactivex.subjects.Subject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.UUID;

public class HPController {
    private Store<StringCommand> store;
    private final Sistema sys = Sistema.getInstance();
    @FXML private TableView<Patient> table;

    public HPController(Store<StringCommand> store, Subject<StateEvent> stream) {
        this.store = store;

        //TODO: sub2PewDiePie

        //TODO: get from state patients and write them into the table
        ObservableList<Patient> data  = table.getItems();
        //data.addAll(); --> var args of all the patients from state
    }

    @FXML protected void showPatients() {
        //TODO: get from state patients and write them into the table
        ObservableList<Patient> data  = table.getItems();
        //data.addAll(); --> var args of all the patients from state

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
