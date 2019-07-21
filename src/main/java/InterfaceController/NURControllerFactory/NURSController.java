package InterfaceController.NURControllerFactory;


import Component.LoginComponent;
import Component.NURComponent;
import Entities.Patient;
import Entities.Recovery;
import State.StateEvent;
import State.Store;
import State.StringCommand;
import System.Sistema;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.stream.Collectors;

public class NURSController implements NURController {
    private final Store<StringCommand> store;
    private final Sistema sys = Sistema.getInstance();
    @FXML private TableView<Recovery> recoveryTable = new TableView<>();
    @FXML private TextField searchPatient;
    @FXML private Label nameLabel;
    private Disposable dis;

    public NURSController(Store<StringCommand> store, Subject<StateEvent> stream) {
        this.store = store;

        try {
            dis.dispose();
        } catch (NullPointerException e) {}

        dis = stream.subscribe(se -> {
            Platform.runLater(() -> nameLabel.setText(se.state().getUser().toString()));
            if(se.command().name().equals("SEARCH_PATIENT"))
                updateRecoveries((String) se.command().getArg());
        });
    }

    @FXML protected void initialize() {

        recoveryTable.setRowFactory( tv -> {
            TableRow<Recovery> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Recovery rowData = row.getItem();
                    store.update(new StringCommand("CHOSEN_RECOVERY_TO_SHOW", rowData));
                    searchResult();
                }
            });
            return row ;
        });


    }


    @FXML protected void updateRecoveries(String nameandsurname) {
        String[] arr = nameandsurname.split(" ");
        String name = arr[0];
        String surname = arr[1];
        Patient p = store.poll().getPatients().stream()
                .filter(pa -> pa.getName().equals(name) && pa.getSurname().equals(surname)).findFirst().orElse(null);

        if (p != null) {
            ObservableList<Recovery> data = recoveryTable.getItems();
            data.removeAll(data);
            //todo check
            data.addAll(p.getAllRecoveries()
                    .stream().filter(r -> {try {
                        return !r.isActive()
                            && r.getDischargeSummary() != null
                            && !r.getDischargeSummary().equals("");
                    } catch (Recovery.RecoveryNullFieldException e) {
                        return false;
                    }


                    }).collect(Collectors.toList()));
        }
    }

    @FXML protected void searchPatient() {
        this.updateRecoveries(searchPatient.getText());
        this.searchPatient.clear();
    }

//--------------------------------------------------------
@FXML protected void showMonitoring() {
    store.update(new StringCommand("SHOW_MONITORING"));
    store.update(new StringCommand("START_MONITORING"));
}

    @FXML protected void search() {
        sys.setInterface("NURS", NURComponent.NURTitle);
    }

    @FXML protected void addAdministration() {
        sys.setInterface("NURD", NURComponent.NURTitle);
    }

    @FXML protected void addPatient() {
        sys.setInterface("NURAP", NURComponent.NURTitle);
    }

    @FXML protected void showLast2H() {
        sys.setInterface("NURM", NURComponent.NURTitle);
    }

    @FXML protected void searchResult() {
        sys.setInterface("NURSR", NURComponent.NURTitle);
    }

    @FXML protected void logout() {
        store.update(new StringCommand("LOGOUT"));
        sys.setInterface("login", LoginComponent.loginTitle);
    }

    @FXML protected void close() {
        sys.endSystem();
    }
}
