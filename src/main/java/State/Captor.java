package State;

import Main.Triple;
import Main.Tuple;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Captor<C extends Command> {
    Tuple<StateChange, State> run(State s, C c);
    Captor with(C command, BiFunction<C, State, State> fun, StateChange stateChange);
    Captor with(Triple<C, BiFunction<C, State, State>, StateChange>... args);
}
