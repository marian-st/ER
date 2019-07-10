package State;

import Main.Tuple;

import java.util.HashMap;
import java.util.UUID;

public class MiddlewareString implements Middleware<StringCommand> {
    private HashMap<String, Tuple<TriFunction<StringCommand, State, Middleware<StringCommand>, State>, String>> map =
            new HashMap<String, Tuple<TriFunction<StringCommand, State, Middleware<StringCommand>, State>, String>>();

    @Override
    public Middleware<StringCommand> with(String st, TriFunction<StringCommand, State,
            Middleware<StringCommand>, State> fun, String commandReturned) {
        this.map.put(st, new Tuple<>(fun, st));
        return this;
    }

    @Override
    public Tuple<String, State> run(State s, StringCommand stringCommand) {
        Tuple<TriFunction<StringCommand, State, Middleware<StringCommand>, State>, String> tup = this.map.get(stringCommand.name());
        State result = tup.fst().apply(stringCommand, s, this);
        return new Tuple<>(tup.snd(), result);
    }
    public boolean check(String s) {
        return this.map.containsKey(s);
    }
}
