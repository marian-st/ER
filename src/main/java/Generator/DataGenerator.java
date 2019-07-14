package Generator;

public class DataGenerator {
    private static final long[] times = {2000L, 2000L, 3000L, 5000L};
    private static final int numTasks = 4;
    private static final Value[] taskJob = {
            Value.SBP,
            Value.DBP,
            Value.HEART_RATE,
            Value.TEMPERATURE
    };

    public static void main(String... args) {
        DataThread[] tasks = new DataThread[numTasks];

        for(int i=0; i<numTasks; i++)
            tasks[i] = new DataThread(125.0, Math.E, times[i], taskJob[i]);

        while(true) {
            for (DataThread t : tasks)
                t.start();
            for (DataThread t : tasks) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
