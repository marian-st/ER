package Generator;

import java.util.*;

import Entities.Recovery;
import Main.Tuple;
import State.Store;
import State.StringCommand;

public class DataThread extends Thread {
    private final Store<StringCommand> store;
    private final Recovery recovery;
    private final int numTask = 3;
    private final Long[] times = {2000L, 3000L, 5000L}; //2-2-3-5 (s)
    private final Value[] taskJob = {Value.BP, Value.HEART_RATE, Value.TEMPERATURE};
    private ArrayList<TimerTask> tasks = new ArrayList<>();
    private ArrayList<Timer> timers = new ArrayList<>();

    public DataThread(Store<StringCommand> store, Recovery recovery) {
        this.store = store;
        this.recovery = recovery;
        for(int i=0; i<numTask; i++) {
            this.tasks.add(new DataTask(taskJob[i]));
            this.timers.add(new Timer());
        }
    }

    public void start() {
        for(int i=0; i<numTask; i++)
            this.timers.get(i).scheduleAtFixedRate(this.tasks.get(i), new Date(), times[i]);
    }

    private class DataTask extends TimerTask {
        private GeneratorInterface generator;
        private Value value;

        public DataTask(Value v) {
            this.value = v;
            generator = new RandomGaussianFactory().getDataGenerator(v);
        }

        public void run() {
            System.out.println(this.value + ": " + generator.getValue());
            MonitoringEntry monitoringEntry;
            if (value == Value.BP) {
                monitoringEntry = new MonitoringEntry<Tuple<Integer, Integer>>();
            }
            else if (value == Value.HEART_RATE) {
                monitoringEntry = new MonitoringEntry<Integer>();
            }
            else {
                monitoringEntry = new MonitoringEntry<Double>();
            }
            monitoringEntry.setValue(this.value);
            monitoringEntry.setEntry(generator.getValue());
            store.update(new StringCommand("ADD_MONITORING_ENTRY", UUID.randomUUID(), monitoringEntry));
        }
    }
}
