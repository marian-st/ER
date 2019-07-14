package Generator;

public class DataThread extends Thread{
    private final RandomGaussian random;
    private final long timeToWait;
    private final Value valueHandled;

    public DataThread(double mean, double variance, long timeToWait, Value value) {
        this.random = new RandomGaussian(mean, variance);
        this.timeToWait = timeToWait;
        this.valueHandled = value;
    }

    public void start() {
        try {
            //Send message to state to update monitored value -> see valueHandled
            System.out.println("Thread to generate " + valueHandled + " generated: " + random.getValue());
            this.sleep(timeToWait);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
