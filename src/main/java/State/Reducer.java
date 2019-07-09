package State;

import Main.Triple;
import Main.Tuple;

import java.util.function.BiFunction;

public interface Reducer<C extends Command> {
    Tuple<StateChange, State> run(State s, C c);
    Reducer with(String st, BiFunction<C, State, State> fun, StateChange stateChange);
    Reducer with(Triple<String, BiFunction<C, State, State>, StateChange>... args);
}
