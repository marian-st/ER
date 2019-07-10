package State;

public class StateManager<C extends Command> {
    private Store<C> store;
    private DatabaseManager databaseManager;

    public StateManager(Store<C> store, DatabaseManager dbmng) {
        this.store = store;
        this.databaseManager = dbmng;
    }
    public StateManager(Store<C> store) {
        this.store = store;
        this.databaseManager = new DatabaseManager();
    }

    public Store<C> getStore() {
        return store;
    }

    public void setStore(Store<C> store) {
        this.store = store;
    }


}
