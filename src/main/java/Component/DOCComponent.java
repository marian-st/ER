package Component;

import InterfaceController.DOCControllerFactory.DOCController;
import InterfaceController.HPControllerFactory.HPController;
import State.Command;
import State.StateEvent;
import System.DOCInterfaceFactory.DOCInterface;
import System.Sistema;
import javafx.fxml.FXMLLoader;

public class DOCComponent<C extends Command> extends Component {
    private FXMLLoader loader;
    private final Sistema sys = Sistema.getInstance();
    public static final String DOCTitle = "ER - Doctor";

    public DOCComponent(DOCInterface ui, DOCController ctl) {
        this.loader = new FXMLLoader(getClass().getResource(ui.getFile()));
        loader.setController(ctl);
    }


    protected void eventHook(StateEvent se) {

    }

    protected final void draw() {
        sys.setInterface("DOCD", DOCTitle);
    }

    public final FXMLLoader getLoader() {
        return this.loader;
    }
}
