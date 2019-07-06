package State;


import Main.Triple;
import Main.Tuple;

import java.util.HashMap;
import java.util.function.Function;

public class CaptorString implements Captor<MyString> {
    private HashMap<String, Tuple<Function<State, State>, StateChange>> commands =
            new HashMap<String, Tuple<Function<State, State>, StateChange>>();

    public CaptorString() {

    }

    @Override
    public CaptorString with(MyString s, Function<State, State> fun, StateChange stateChange) {
        this.commands.put(s.name(), new Tuple<>(fun, stateChange));
        return this;
    }

    @Override
    public Captor with(Triple<MyString, Function<State, State>, StateChange>... args) {
        for (Triple<MyString, Function<State, State>, StateChange> a : args) {
            this.commands.put(a.fst().name(), new Tuple<>(a.snd(), a.trd()));
        }
        return this;
    }

    @Override
    public Tuple<StateChange, State> run(State state, MyString s) {
        Function<State, State> fun = this.commands.get(s.name()).fst();
        return new Tuple<>(this.commands.get(s.name()).snd(), fun.apply(state));
    }
}
