package State;

import Main.Tuple;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import java.util.function.Function;

/**
 *
 */
public class Store<S extends State, C extends Command> {
    private S state;
    private Runner<S, C> reducer;

    //hot observable to which state updates are pushed
    private Subject<S> state$ = BehaviorSubject.create();

    public Store(S state, Runner<S, C> reducer, Tuple<C, Function<S,S>>...args) {

        this.state = state;
        this.state$.onNext(state);
        this.reducer = reducer;
        if (args.length != 0) this.reducer = this.reducer.with(args);

    }

    public State poll() {
        return this.state;
    }

    /**
     * Upon calling this method the Store updates the state
     * as indicated by command and broadcast the change to all
     * subscribers
     *
     * @param command command that specifies how the state is updated
     * @implNote synchronized because it shall not be called by multiple threads
     *           at the same time as it can produce data inconsistencies because
     *           of the non deterministic order in which this.state is called
     */
     synchronized public void update(C command) {
        transmute(command);
        //notify subscribers
        this.state$.onNext(state);
    }

    public Subject<S> getState() {
        return state$;
    }

    //transmute the state based on the reducer
    private void transmute(C command) {
        this.state = this.reducer.run(state, command);
    }

}
