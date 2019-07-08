package State;


import Main.Triple;
import Main.Tuple;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CaptorString implements Captor<MyString> {
    private HashMap<String, Tuple<BiFunction<MyString, State, State>, StateChange>> commands =
            new HashMap<String, Tuple<BiFunction<MyString, State, State>, StateChange>>();

    public CaptorString() {

    }

    @Override
    public CaptorString with(MyString s, BiFunction<MyString, State, State> fun, StateChange stateChange) {
        this.commands.put(s.name(), new Tuple<>(fun, stateChange));
        return this;
    }

    @Override
    public Captor with(Triple<MyString, BiFunction<MyString, State, State>, StateChange>... args) {
        for (Triple<MyString, BiFunction<MyString, State, State>, StateChange> a : args) {
            this.commands.put(a.fst().name(), new Tuple<>(a.snd(), a.trd()));
        }
        return this;
    }

    @Override
    public Tuple<StateChange, State> run(State state, MyString s) {
        BiFunction<MyString, State, State> fun = this.commands.get(s.name()).fst();
        return new Tuple<>(this.commands.get(s.name()).snd(), fun.apply(s, state));
    }
}
