package Component;

import State.State;
import State.StateEvent;
import State.Store;
import State.Command;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public abstract class Component<C extends Command> {
    private final Store <C> store;
    private Disposable subscription;

    public Component(Store <C> store) {
        this.store = store;
        initialization(this.store.poll());
        this.subscription = this.store.getEventStream()
                .onErrorResumeNext(Observable.empty())
                .subscribe(
                    s -> eventHook(s) ,
                    throwable -> System.out.println(throwable)  );
    }

    void clean() {
        this.subscription.dispose();
    }

    void sendCommand(C c) {
        this.store.update(c);
    }
    abstract void eventHook(StateEvent se);
    abstract State getState();
    abstract void initialization(State state);
    abstract void draw(State state);

}
