package InterfaceController;

import State.Entities.Patient;
import State.StateEvent;
import State.Store;
import State.StringCommand;
import System.LoginDemo.HP.HPComponent;
import System.LoginDemo.LoginComponent;
import System.LoginDemo.Sistema;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.UUID;

public class HPController {
    private final Store<StringCommand> store;
    private final Sistema sys = Sistema.getInstance();
    @FXML private TableView<Patient> table = new TableView<>();

    Disposable dis;

    public HPController(Store<StringCommand> store, Subject<StateEvent> stream) {

        this.store = store;
        ObservableList<Patient> data  = table.getItems();
        data.addAll(store.poll().getPatients());


        try {
            dis.dispose();
        } catch (NullPointerException e) {}

        dis = stream.subscribe(se ->
        {
            ObservableList<Patient> data2  = table.getItems();
            data2.removeAll(table.getItems());
            data2.addAll(se.state().getPatients());
        });

    }

    @FXML protected void showPatients() {
        //TODO: get from state patients and write them into the table
        /*ObservableList<Patient> data  = table.getItems();
        data.removeAll(table.getItems());
        data.addAll(store.poll().getPatients());*/
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
