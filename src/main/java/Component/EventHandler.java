package Component;

import State.Store;
import State.State;
import State.Command;

public class EventHandler<C extends Command> {
    public EventHandler(Store<C> store) {

    }
}
