package State;

import Main.Tuple;
import java.util.function.Function;

public interface Captor<C extends Command> {
    State run(State s, C c);
    Captor with(C c, Function<State, State> fun);
    Captor with(Tuple<C, Function<State, State>>... args);
}
