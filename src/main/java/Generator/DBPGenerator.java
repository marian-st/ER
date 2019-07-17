package Generator;

import java.util.Random;

public class DBPGenerator implements GeneratorInterface {
    private final Random random = new Random();
    private double mean = 124;
    private double variance = 2.5;
    private double defaultMean;
    private double defaultVariance;

    public DBPGenerator() {
    }

    @Override
    public void reset() {
        mean = defaultMean;
        variance = defaultVariance;
    }

    public Integer getValue() {
        return (int) (mean + random.nextGaussian()*variance);
    }
}
