package System.LoginDemo;

import Component.Component;
import State.State;
import State.StateEvent;
import javafx.scene.layout.VBox;
import State.Store;

public class LoginComponent extends Component {
    private LoginUI loginUI;

    public LoginComponent(Store store) {
        loginUI = new LoginUI(store, store.getEventStream());
    }

    protected void eventHook(StateEvent se) {
        System.out.println("LoginComponent.eventHook()");
    }

    protected State getState() {
        return null;
    }

    protected void initialization(State state) {

    }

    protected void draw(State state) {

    }

    public VBox getInterface() {
        return this.loginUI.getRoot();
    }
}
