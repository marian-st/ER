package State;

import Main.Tuple;

public interface Middleware<C extends Command> {
    Middleware<C> with(String st, TriFunction<C, State, Middleware<C>, Tuple<C, State>> fun);
    Tuple<C, State> run(State s, C c);
    boolean check(String s);
}
