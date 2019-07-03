package State;

import Main.Tuple;
import java.util.function.Function;

public interface Captor<S extends State, C extends Command> {
    S run(S s, C c);
    Captor with(C c, Function<S, S> fun);
    Captor with(Tuple<C, Function<S, S>>... args);
}
