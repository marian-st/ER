package State;

import Main.Triple;
import Main.Tuple;
import java.util.function.Function;

public interface Captor<C extends Command> {
    Tuple<StateChange, State> run(State s, C c);
    Captor with(C command, Function<State, State> fun, StateChange stateChange);
    Captor with(Triple<C, Function<State, State>, StateChange>... args);
}
