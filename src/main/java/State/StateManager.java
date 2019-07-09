package State;

public class StateManager<C extends Command> {
    private Store<C> store;

    public StateManager(Store<C> store) {
        this.store = store;
    }

    public Store<C> getStore() {
        return store;
    }

    public void setStore(Store<C> store) {
        this.store = store;
    }


}
