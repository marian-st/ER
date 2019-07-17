package Component;

import State.Command;
import State.StateEvent;
import State.Store;
import javafx.fxml.FXMLLoader;
import System.Sistema;

public class MonitoringComponent<C extends Command> extends Component {
    private FXMLLoader loader;
    private final Sistema sys = Sistema.getInstance();
    public static final String monitoringTitle = "ER - Monitoring";

    public MonitoringComponent() {
        //LoginController logctl = new LoginController(sys.getStore(), sys.getStore().getEventStream());
        loader = new FXMLLoader(getClass().getResource("/A_Monitoring.fxml"));
        //loader.setController(logctl);
    }

    public MonitoringComponent(Store<C> store) {
        super(store);
    }

    protected void eventHook(StateEvent se) {

    }

    protected void draw() {
        sys.setInterface("MON", monitoringTitle);
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }
}
