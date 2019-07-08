package Component;

import State.State;
import State.StateEvent;
import State.Store;
import State.Command;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public abstract class Component<C extends Command> {
    protected Store <C> store;
    protected Disposable subscription;

    public Component() {}

    public Component(Store <C> store) {
        this.store = store;
        initialization(this.store.poll());
        this.subscription = this.store.getEventStream()
                .onErrorResumeNext(Observable.empty())
                .subscribe(
                    s -> eventHook(s) ,
                    throwable -> System.out.println(throwable)  );
    }

    protected void clean() {
        this.subscription.dispose();
    }

    protected final void sendCommand(C c) {
        this.store.update(c);
    }
    protected abstract void eventHook(StateEvent se);
    protected abstract State getState();
    protected abstract void initialization(State state);
    protected abstract void draw(State state);

}
