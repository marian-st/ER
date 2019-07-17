package Generator;

import java.util.Random;

public class TemperatureGenerator implements GeneratorInterface {
    private final Random random = new Random();
    private double mean = 124;
    private double variance = 2.5;
    private double defaultMean;
    private double defaultVariance;

    public TemperatureGenerator() {
    }

    public void reset() {
        mean = defaultMean;
        variance = defaultVariance;
    }

    public Double getValue() {
        return mean + random.nextGaussian()*variance;
    }
}
