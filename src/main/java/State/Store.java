package State;

import Main.Triple;
import Main.Tuple;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.function.BiFunction;

/**
 *
 */
public class Store<C extends Command> {
    private State state;
    private Reducer<C> reducer;
    private int counter = 0;
    private Middleware<C> middleware;

    //streams
    private PublishSubject<StateEvent> state$ = PublishSubject.create();
    private PublishSubject<String> commands$= PublishSubject.create();

    public Store(State state, Reducer<C> reducer, Middleware middleware, Triple<String, BiFunction<C, State,State>, StateChange>...args) {
        //TODO remove -- logging
        this.state$.subscribe(s -> {
            System.out.println(String.valueOf(counter++) + ": " + s.state());
        });

        this.middleware = middleware;
        this.state = state;
        this.state$.onNext(new StateEvent(StateChange.INITIAL, state));
        this.reducer = reducer;
        // <Command, Function> tuples can be passed here to the reducer
        if (args.length != 0) this.reducer = this.reducer.with(args);

        //this.reducer.setStore(this);

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

        State state;
        String commandName;

        Tuple<StateChange, State> resultReducer = this.reducer.run(this.state, command);
        if (this.middleware.check(command.name())) {
            Tuple<String, State> resultMiddleware = this.middleware.run(resultReducer.snd(), command);
            state = resultMiddleware.snd();
            commandName = resultMiddleware.fst();
        } else {
            state = resultReducer.snd();
            commandName = command.name();
        }
        StateChange sc = resultReducer.fst();

        this.state$.onNext(new StateEvent(sc, state));
        this.commands$.onNext(commandName);

        // notify subscribers about the state change

    }

    public Subject<StateEvent> getEventStream() {
        return state$;
    }

    public Subject<String> getCommandStream() {
        return commands$;
    }

}
