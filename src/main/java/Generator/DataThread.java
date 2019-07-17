package Generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DataThread extends Thread{
    private final int numTask = 4;
    private final Long[] times = {2000L, 2000L, 3000L, 5000L}; //2-2-3-5 (s)
    private final Value[] taskJob = {Value.SBP, Value.DBP, Value.HEART_RATE, Value.TEMPERATURE};
    private ArrayList<TimerTask> tasks = new ArrayList<>();
    private ArrayList<Timer> timers = new ArrayList<>();

    public DataThread() {
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
        }
    }
}
