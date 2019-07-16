package Generator;

import java.util.Random;

/**
 Generate pseudo-random floating point values, with an
 approximately Gaussian (normal) distribution.
 */
public class RandomGaussian {
    private Random random = new Random();
    private double mean;
    private double variance;
    private final double defaultMean;
    private final double defaultVariance;

    public RandomGaussian(double mean, double variance) {
        this.mean = mean;
        this.variance = variance;
        this.defaultMean = mean;
        this.defaultVariance = variance;
    }

    public void changeValues(double m, double v) {
        this.mean = m;
        this.variance = v;
    }

    public void reset() {
        this.mean = this.defaultMean;
        this.variance = this.defaultVariance;
    }

    //nextGaussian returns a value of a normalized gaussian distribution
    public long getValue(){
        return Math.round(mean + random.nextGaussian() * variance);
    }
}