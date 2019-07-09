package State;

import Main.Triple;
import Main.Tuple;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 */
public class Store<C extends Command> {
    private State state;
    private Captor<C> captor;
    private int counter = 0;
    //hot observable to which state updates are pushed
    private PublishSubject<StateEvent> state$ = PublishSubject.create();

    public Store(State state, Captor<C> captor, Triple<String, BiFunction<C, State,State>, StateChange>...args) {
        //TODO remove -- logging
        this.state$.subscribe(s -> {
            System.out.println(String.valueOf(counter++) + ": " + s.state());
        });

        this.state = state;
        this.state$.onNext(new StateEvent(StateChange.INITIAL, state));
        this.captor = captor;
        // <Command, Function> tuples can be passed here to the captor
        if (args.length != 0) this.captor = this.captor.with(args);

    }
    /*
    * TODO check this, may not follow reactive paradigm
     */
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
     *    at the same time as it can produce data inconsistencies because
     *    of the non deterministic order in which this.state is called
     */
     synchronized public void update(C command) {
         //TODO remove -- logging
         System.out.println("Command: " + command.name());
        // change the state based on the command
        StateChange sc = transmute(command);
        // notify subscribers about the state change
        this.state$.onNext(new StateEvent(sc, this.state));
    }

    public Subject<StateEvent> getEventStream() {
        return state$;
    }

    /*
    * Transmute the state based on the captor which will
    * apply the function associated with the command
     */
    private StateChange transmute(C command) {
        Tuple<StateChange, State> result = this.captor.run(state, command);
        this.state = result.snd();
        return result.fst();
    }

}
