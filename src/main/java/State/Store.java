package State;

import Main.Tuple;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import java.util.function.Function;

/**
 *
 */
public class Store<C extends Command> {
    private State state;
    private Captor<State, C> captor;

    //hot observable to which state updates are pushed
    private BehaviorSubject<State> state$ = BehaviorSubject.createDefault(new State());

    public Store(State state, Captor<State, C> captor, Tuple<C, Function<State,State>>...args) {

        this.state = state;
        this.state$.onNext(state);
        this.captor = captor;
        if (args.length != 0) this.captor = this.captor.with(args);

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

    public Subject<State> getStateStream() {
        return state$;
    }

    //transmute the state based on the captor
    private void transmute(C command) {
        this.state = this.captor.run(state, command);
    }

}
