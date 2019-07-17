package Generator;

import java.util.Random;

public class HeartRateGenerator implements GeneratorInterface {
    private final Random random = new Random();
    private double mean = 124;
    private double variance = 2.5;
    private double defaultMean;
    private double defaultVariance;

    public HeartRateGenerator() {
    }

    public void reset() {
        mean = defaultMean;
        variance = defaultVariance;
    }

    public Integer getValue() {
        return (int) (mean + random.nextGaussian()*variance);
    }
}
