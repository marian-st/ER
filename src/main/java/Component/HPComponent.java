package Component;

import InterfaceController.HPController;
import State.Command;
import State.StateEvent;
import System.HP.HPFactory;
import javafx.fxml.FXMLLoader;
import System.Sistema;

public class HPComponent<C extends Command> extends Component {
    private FXMLLoader loader;
    private final Sistema sys = Sistema.getInstance();
    public static final String HPTitle = "ER - HeadPhysician";

    public HPComponent(String type) {
        HPController hpctl = new HPController(sys.getStore(), sys.getStore().getEventStream());
        this.loader = new FXMLLoader(getClass().getResource(new HPFactory().getHPInterface(type)));
        loader.setController(hpctl);
    }


    protected void eventHook(StateEvent se) {

    }

    protected final void draw() {
        sys.setInterface("HPDF", HPTitle);
    }

    public final FXMLLoader getLoader() {
        return this.loader;
    }
}
