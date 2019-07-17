package Generator;

import Main.Tuple;

import java.util.Random;

public class BPGenerator implements GeneratorInterface {
    private final Random randomS = new Random();
    private double meanS = 124;
    private double varianceS = 2.5;
    private double defaultMeanS;
    private double defaultVarianceS;

    private final Random randomD = new Random();
    private double meanD = 124;
    private double varianceD = 2.5;
    private double defaultMeanD;
    private double defaultVarianceD;

    public BPGenerator() {
    }

    public void reset() {
        meanS = defaultMeanS;
        varianceS = defaultVarianceS;
        meanD = defaultMeanD;
        varianceD = defaultVarianceD;
    }

    public Tuple<Integer, Integer> getValue() {
        return new Tuple<>((int) (meanD + randomD.nextGaussian()*varianceD), (int) (meanS + randomS.nextGaussian()*varianceS));
    }
}
