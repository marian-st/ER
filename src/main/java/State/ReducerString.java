package State;


import Main.Triple;
import Main.Tuple;

import java.util.HashMap;
import java.util.function.BiFunction;

public class ReducerString implements Reducer<StringCommand> {
    private HashMap<String, BiFunction<StringCommand, State, State>> commands =
            new HashMap<String, BiFunction<StringCommand, State, State>>();

    public ReducerString() {

    }

    @Override
    public ReducerString with(String st, BiFunction<StringCommand, State, State> fun) {
        this.commands.put(st, fun);
        return this;
    }

    @Override
    public ReducerString with(String st) {
        BiFunction<StringCommand, State, State> fun = (c, s) -> s;
        this.commands.put(st, fun);
        return this;
    }

    @Override
    public ReducerString with(Tuple<String, BiFunction<StringCommand, State, State>> ... args) {
        for (Tuple<String, BiFunction<StringCommand, State, State>> a : args) {
            this.commands.put(a.fst(), a.snd());
        }
        return this;
    }

    @Override
    public State run(State state, StringCommand s) throws NonExistentCommandException {
        BiFunction<StringCommand, State, State> fun = this.commands.get(s.name());
        try {
            return fun.apply(s, state);
        } catch (NullPointerException e) {
            throw new NonExistentCommandException();
        }
    }

    public class NonExistentCommandException extends Exception {

    }
}
