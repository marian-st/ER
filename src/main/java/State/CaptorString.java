package State;


import Main.Tuple;

import java.util.HashMap;
import java.util.function.Function;

public class CaptorString implements Captor<MyString> {
    private HashMap<String, Function<State, State>> commands = new HashMap<String, Function<State, State>>();

    public CaptorString() {

    }

    @Override
    public CaptorString with(MyString s, Function<State, State> fun) {
        this.commands.put(s.name(), fun);
        return this;
    }

    @Override
    public Captor with(Tuple<MyString, Function<State, State>>... args) {
        for (Tuple<MyString, Function<State, State>> a : args) {
            this.commands.put(a.fst().name(), a.snd());
        }
        return this;
    }

    @Override
    public State run(State state, MyString s) {
        Function<State, State> fun = this.commands.get(s.name());
        return fun.apply(state);
    }
}
