package InterfaceController.NURControllerFactory;

import Component.LoginComponent;
import Component.NURComponent;
import State.StateEvent;
import State.Store;
import State.StringCommand;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import System.Sistema;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Date;

public class NURAPController implements NURController {
    private Store<StringCommand> store;
    private Sistema sys = Sistema.getInstance();
    private Disposable dis;
    @FXML private Label nurseNameLabel;
    @FXML private TextField surnameLabel;
    @FXML private TextField nameLabel;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private DatePicker bDayDate;
    @FXML private TextField placeLabel;
    @FXML private TextField cfLabel;

    public NURAPController(Store<StringCommand> store, Subject<StateEvent> stream) {
        this.store = store;

        try {
            dis.dispose();
        } catch (NullPointerException e) {}

        dis = stream.subscribe(se -> {
            Platform.runLater(() -> nurseNameLabel.setText(se.state().getUser().toString()));
        });
    }

    @FXML public void initialize() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        bDayDate.setDayCellFactory(dayCellFactory);
    }

    @FXML protected void submit() {
        try {
            if(!surnameLabel.getText().equals("") && !nameLabel.getText().equals("") && (male.isSelected() || female.isSelected()) && bDayDate.getValue() != null && !placeLabel.getText().equals("") && !cfLabel.getText().equals("")) {
                System.out.println("\n\n" + new FiscalCodeCalculator().calculateFC(nameLabel.getText(), surnameLabel.getText(), (male.isSelected()) ? 'M' : 'F', bDayDate.getValue().toString()));
                System.out.println("ALL DATA IS CORRECT");
            } else throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("NOT ALL DATA IS CORRECT OR INSERTED");
        } finally {
            nameLabel.clear();
            surnameLabel.clear();
            if(male.isSelected())
                male.setSelected(false);
            if(female.isSelected())
                female.setSelected(false);
            bDayDate.setValue(null);
            placeLabel.clear();
            cfLabel.clear();
        }
    }

    @FXML protected void calcolateCF() {
        if(!surnameLabel.getText().equals("") && !nameLabel.getText().equals("") && (male.isSelected() || female.isSelected()) && bDayDate.getValue() != null)
            cfLabel.setText(new FiscalCodeCalculator().calculateFC(nameLabel.getText(), surnameLabel.getText(), (male.isSelected()) ? 'M' : 'F', bDayDate.getValue().toString()));
        else System.out.println("NOT ALL FIELD TO CALCULATE CF WERE SET");
    }

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

    @FXML
    protected void logout() {
        store.update(new StringCommand("LOGOUT"));
        sys.setInterface("login", LoginComponent.loginTitle);
    }

    @FXML protected void close() {
        sys.endSystem();
    }
}