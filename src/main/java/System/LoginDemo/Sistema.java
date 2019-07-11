package System.LoginDemo;

import State.Entities.User;
import State.Reducer;
import State.ReducerString;
import State.StringCommand;
import State.State;
import State.StateChange;
import State.Store;
import State.MiddlewareString;
import State.StateManager;
import State.Middleware;
import System.LoginDemo.HP.HPComponent;
import javafx.stage.Stage;
import Main.Tuple;
import java.util.UUID;

public class Sistema {
    private static Sistema s;
    private StateManager<StringCommand> stateManager;
    private InterfacesController controller;

    public static Sistema getInstance() {
        if (s == null)
            s = new Sistema();
        return s;
    }

    //will have all the information into the state and iterate over them
    //NB. This is just demo environment
    private Sistema() {
        Reducer<StringCommand> reducer = new ReducerString()
                .with("LOGIN", StateChange.LOGIN)
                .with("LOGOUT", (c, s) -> {
                    s.setUser(new User());
                    return s;
                }, StateChange.LOGIN);
        Middleware<StringCommand> middleware = new MiddlewareString()
                .with("LOGIN", (c, s, m) -> {
                    User u = (User) c.getArg();
                    System.out.println("Debug: " + u);
                    if (s.getUserCheck().equals(u)) {
                        s.setUser(s.getUserCheck());
                        return new Tuple<>(new StringCommand("LOGIN_SUCCESS", UUID.randomUUID()), s);
                    }
                    else {
                        return new Tuple<>(new StringCommand("LOGIN_FAILURE", UUID.randomUUID()), s);
                    }
                });
        stateManager = new StateManager<StringCommand>(new Store<StringCommand>(new State(), reducer, middleware));
    }

    public void setupUI(Stage stage){
        try {
            this.controller = new InterfacesController(stage);
            this.controller.addInterface("login", new LoginComponent<StringCommand>().getLoader().load());
            this.controller.addInterface("HP", new HPComponent<StringCommand>().getLoader().load());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error during interfaces setup");
        }
    }

    public Store getStore() {
        return stateManager.getStore();
    }

    public void setInterface(String component, String title) {
        controller.activate(component, title);
    }
}