package Component;

import State.Store;
import State.State;
import State.Command;

public class EventHandler<S extends State, C extends Command> {
    public EventHandler(Store<S, C> store) {

    }
}
