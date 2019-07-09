package System.LoginDemo;

import State.Captor;
import State.CaptorString;
import State.MyString;
import State.State;
import State.StateChange;
import State.Store;

import java.util.UUID;

public class Sistema {
    private static Sistema s = new Sistema();
    private static UUID id;
    private static Store store;

    public static Sistema getInstance() {
        return s;
    }

    public static void systemSetUp() {
        System.out.println("System Setup initialized");
        //other setup stuff
        id = UUID.randomUUID();

        Captor<MyString> captor = new CaptorString()
                .with(new MyString("LOG", id), (c, s) -> {
                            User x = (User) c.getArg();
                            if(x.equals(s.getUserCheck()))
                                s.setUser(s.getUserCheck());
                            return s;
                }, StateChange.LOGIN)
                .with(new MyString("LOG_UNCHECKED", id), (c, s) -> {
                    User x = (User) c.getArg();
                    s.setUser(x);
                    return s;
                }, StateChange.LOGIN)
                .with(new MyString("LOG_FAIL", id), (c, s) -> {
                    User x = (User) c.getArg();
                    if(x.equals(s.getUserCheck()))
                        s.setUser(s.getUserCheck());
                    else s.setUser(new User());
                    return s;
                }, StateChange.LOGIN);

        store = new Store<MyString>(new State(), captor);
    }

    //will have all the information into the state and iterate over them
    //NB. This is just demo environment
    private Sistema() {
    }

    public static Store getStore() {
        return store;
    }

}
