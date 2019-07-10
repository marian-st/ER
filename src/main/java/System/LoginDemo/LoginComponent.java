package System.LoginDemo;

import Component.Component;
import InterfaceController.LoginController;
import State.State;
import State.Command;
import State.StateEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        draw();
    }

    public static class LoginView extends Application {
        @Override
        public void start(Stage stage) throws Exception {
            LoginController logctl = new LoginController(Sistema.getStore(), Sistema.getStore().getEventStream());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            loader.setController(logctl);

            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("ER - Login");
            stage.setScene(scene);
            stage.show();
        }
    }
}


