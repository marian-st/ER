package System.LoginDemo;

import Component.Component;
import InterfaceController.LoginController;
import State.Command;
import State.StateEvent;
import javafx.fxml.FXMLLoader;
import State.Store;

public class LoginComponent<C extends Command> extends Component {
    private FXMLLoader loader;
    private Sistema sys = Sistema.getInstance();

    public LoginComponent() {
        LoginController logctl = new LoginController(sys.getStore(), sys.getStore().getEventStream());
        loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        loader.setController(logctl);
    }

    public LoginComponent(Store<C> store) {
        super(store);
    }

    protected void eventHook(StateEvent se) {

    }

    protected void draw() throws Exception {
        sys.setInterface("login");
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }
}


