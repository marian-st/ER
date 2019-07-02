package State;

import Main.Main;
import Main.Tuple;
import java.util.HashMap;
import java.util.function.Function;

public class ReducerString implements Runner<State, Main.MyString> {
    private HashMap<String, Function<State, State>> commands = new HashMap<String, Function<State, State>>();

    public ReducerString() {

    }

    @Override
    public ReducerString with(Main.MyString s, Function<State, State> fun) {
        this.commands.put(s.name(), fun);
        return this;
    }

    @Override
    public Runner with(Tuple<Main.MyString, Function<State, State>>... args) {
        for (Tuple<Main.MyString, Function<State, State>> a : args) {
            this.commands.put(a.fst().name(), a.snd());
        }
        return this;
    }

    @Override
    public State run(State state, Main.MyString s) {
        Function<State, State> fun = this.commands.get(s.name());
        return fun.apply(state);
    }
}
