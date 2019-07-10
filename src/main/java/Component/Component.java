package Component;

import State.State;
import State.StateEvent;
import State.Store;
import State.Command;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;import javafx.application.Application;

public abstract class Component<C extends Command> {

    private Store <C> store;
    private Disposable subscription;

    public Component(Store <C> store) {
        this.store = store;
        this.subscription = this.store.getEventStream()
                .onErrorResumeNext(Observable.empty())
                .subscribe(
                        s -> eventHook(s) ,
                        throwable -> System.out.println(throwable)  );
        initialization(this.store.poll());

    }
    protected State getState() {
        return this.store.poll();
    }

    protected void draw() {
        Application.launch(this.getView(), "");
    };

    private void clean() {
        this.subscription.dispose();
    }
    protected final void sendCommand(C c) {
        this.store.update(c);
    }

    protected abstract Class getView();
    protected abstract void eventHook(StateEvent se);
    protected abstract void initialization(State state);


    /**
     * GETTERS AND SETTERS
     */
    public Store<C> getStore() {
        return store;
    }

}
