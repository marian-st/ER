package State;

import Main.Tuple;

public interface Middleware<C extends Command> {
    Middleware<C> with(String st, TriFunction<C, State, Middleware<C>, State> fun, String commandReturned);
    Tuple<String, State> run(State s, C c);
    public boolean check(String s);
}
