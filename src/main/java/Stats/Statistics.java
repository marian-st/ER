package Stats;


import org.apache.commons.math3.distribution.BetaDistribution;

public class Statistics {
    public static void generate_values(int alpha, int beta, int size) {

    //alpha > beta => more dense on the right,
    //beta > alpha => more dense on the left
    //the higher the values the less the variance and
    //values are concentrated around the EV
    BetaDistribution Beta = new BetaDistribution(alpha, beta);

    for (int i = 0; i < size; i++) {
        System.out.println(Math.floor(Beta.sample()*80+80));
    }
}

}
