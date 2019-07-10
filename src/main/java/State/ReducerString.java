package State;


import Main.Triple;
import Main.Tuple;

import java.util.HashMap;
import java.util.function.BiFunction;

public class ReducerString implements Reducer<StringCommand> {
    private Store<StringCommand> store;
    private HashMap<String, Tuple<BiFunction<StringCommand, State, State>, StateChange>> commands =
            new HashMap<String, Tuple<BiFunction<StringCommand, State, State>, StateChange>>();
    private HashMap<String, Tuple<BiFunction<StringCommand, Store<StringCommand>, StringCommand>, StringCommand>>
            attached = new HashMap<>();
    public ReducerString() {

    }

    @Override
    public ReducerString with(String s, BiFunction<StringCommand, State, State> fun, StateChange stateChange) {
        this.commands.put(s, new Tuple<>(fun, stateChange));
        return this;
    }

    @Override
    public ReducerString with(Triple<String, BiFunction<StringCommand, State, State>, StateChange>... args) {
        for (Triple<String, BiFunction<StringCommand, State, State>, StateChange> a : args) {
            this.commands.put(a.fst(), new Tuple<>(a.snd(), a.trd()));
        }
        return this;
    }

    @Override
    public ReducerString attachTo(String st, BiFunction<StringCommand, Store<StringCommand>, StringCommand> fun, StateChange stateChange) {
        //TODO change
        return null;
    }

    @Override
    public void setStore(Store<StringCommand> store) {
        this.store = store;
    }

    @Override
    public Tuple<StateChange, State> run(State state, StringCommand s) {
        BiFunction<StringCommand, State, State> fun = this.commands.get(s.name()).fst();
        return new Tuple<>(this.commands.get(s.name()).snd(), fun.apply(s, state));
    }
}
