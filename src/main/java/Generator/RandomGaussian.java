package Generator;

import java.util.Random;

/**
 Generate pseudo-random floating point values, with an
 approximately Gaussian (normal) distribution.
 */
public final class RandomGaussian {
    private Random fRandom = new Random();

    public static void main(String... aArgs){
        RandomGaussian gaussian = new RandomGaussian();
        double MEAN = 125.0f;
        double VARIANCE = 2.5f;
        for (int i = 0; i < 100; i++)
            System.out.println("Generated : " + gaussian.getGaussian(MEAN, VARIANCE));
    }

    //nextGaussian returns a value of a normalized gaussian distribution
    private double getGaussian(double aMean, double aVariance){
        return aMean + fRandom.nextGaussian() * aVariance;
    }
}