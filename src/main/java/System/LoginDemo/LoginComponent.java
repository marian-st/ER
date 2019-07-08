package System.LoginDemo;

import Component.Component;
import State.State;
import State.Command;
import State.StateEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import State.Store;
import javafx.stage.Stage;

public class LoginComponent<C extends Command> extends Component {

    public LoginComponent(Store<C> store) {
        super(store);
    }

    @Override
    protected Class getView() {
        return LoginView.class;
    }

    protected void eventHook(StateEvent se) {

    }

    protected void initialization(State state) {
        draw(state);
    }

    public static class LoginView extends Application {
        @Override
        public void start(Stage stage) throws Exception {
            LoginUI loginUI = new LoginUI(Sistema.getStore(), Sistema.getStore().getEventStream());
            stage.setTitle("Login");
            stage.setScene(new Scene(loginUI.getRoot(), 300, 250));
            stage.show();
        }
    }
}


