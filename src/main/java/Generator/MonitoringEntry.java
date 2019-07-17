package Generator;


public class MonitoringEntry<T> {

    private Value value;
    private T entry;

    public MonitoringEntry() {}
    public MonitoringEntry(Value value, T entry) {
        this.value = value;
        this.entry = entry;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

}
