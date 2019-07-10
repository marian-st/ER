package System.LoginDemo;

import State.Entities.User;
import State.Reducer;
import State.ReducerString;
import State.StringCommand;
import State.State;
import State.StateChange;
import State.Store;
import State.StateManager;

public class Sistema {
    private static Sistema s = new Sistema();
    private static StateManager<StringCommand> stateManager;

    public static Sistema getInstance() {
        return s;
    }

    public static void systemSetUp() {
        
        //other setup stuff

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

        stateManager = new StateManager<StringCommand>(new Store<StringCommand>(new State(), reducer));
    }

    //will have all the information into the state and iterate over them
    //NB. This is just demo environment
    private Sistema() {
    }

    public static Store getStore() {
        return stateManager.getStore();
    }

}
