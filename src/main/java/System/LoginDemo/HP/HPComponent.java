package System.LoginDemo.HP;

import Component.Component;
import InterfaceController.HPController;
import State.Command;
import State.StateEvent;
import System.LoginDemo.Sistema;
import javafx.fxml.FXMLLoader;
import State.Store;


public class HPComponent<C extends Command> extends Component {
    private FXMLLoader loader;
    private final Sistema sys = Sistema.getInstance();
    public static final String HPTitle = "ER - HeadPhysician";

    public HPComponent() {
        HPController hpctl = new HPController(sys.getStore(), sys.getStore().getEventStream());
        this.loader = new FXMLLoader(getClass().getResource("/HP_base.fxml"));
        loader.setController(hpctl);
    }

    public HPComponent(Store<C> store) {
        super(store);
    }

    protected void eventHook(StateEvent se) {

    }

    protected void draw() throws Exception {
        sys.setInterface("HP", HPTitle);
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }
}
