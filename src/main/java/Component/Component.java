package Component;

import State.State;
import State.Store;
import State.Command;
import io.reactivex.disposables.Disposable;

public abstract class Component<S extends State, C extends Command> {
    private final Store<S,C> store;
    private final Disposable subscription;

    public Component(Store<S, C> store) {
        this.store = store;
        initialization(this.store.poll());
        this.subscription = this.store.getStateStream().subscribe(this::eventHook);
    }

    void clean() {
        this.subscription.dispose();
    }

    abstract void eventHook(State state);
    abstract State getState();
    abstract void initialization(State state);
    abstract void draw();

}
