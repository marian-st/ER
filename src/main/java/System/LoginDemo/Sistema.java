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
import System.LoginDemo.HP.HPComponent;
import javafx.stage.Stage;

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
                .with("LOG", (c, s) -> {
                    User x = (User) c.getArg();
                    if(x.equals(s.getUserCheck()))
                        s.setUser(s.getUserCheck());
                    return s;
                }, StateChange.LOGIN)
                .with("LOGOUT", (c, s) -> {
                    s.setUser(new User());
                    return s;
                }, StateChange.LOGIN);
                /*.attachTo("LOG", (c, sto) -> {
                    User u = (User) c.getArg();
                    if (u ==  sto.poll().getUserCheck()) {
                        return new StringCommand("LOGIN_SUCCESS", UUID.randomUUID());
                    }
                    else return new StringCommand("LOGIN_FAIL", UUID.randomUUID());
                }, StateChange.LOGIN);*/

        stateManager = new StateManager<StringCommand>(new Store<StringCommand>(new State(), reducer, new MiddlewareString()));
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

    public void setInterface(String component) {
        controller.activate(component);
    }
}