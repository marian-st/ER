package State;

import Main.Triple;
import Main.Tuple;

import java.util.function.BiFunction;

public interface Reducer<C extends Command> {
    State run(State s, C c) throws ReducerString.NonExistentCommandException;
    Reducer<C> with(String st);
    Reducer<C> with(String st, BiFunction<C, State, State> fun);
    Reducer<C> with(Tuple<String, BiFunction<C, State, State>>... args);
}
