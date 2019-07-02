package State;

import Main.Tuple;
import java.util.function.Function;

public interface Runner<S extends State, C extends Command> {
    S run(S r, C c);
    Runner with(C c, Function<S, S> fun);
    Runner with(Tuple<C, Function<S, S>>... args);
}
