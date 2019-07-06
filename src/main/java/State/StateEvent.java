package State;

public class StateEvent {
    private final StateChange sc;
    private final State s;

    public StateEvent(StateChange sc, State s) {
        this.sc = sc;
        this.s = s;
    }
    public StateChange stateChange() {
        return this.sc;
    };
    public State state() {
        return this.s;
    };
}
