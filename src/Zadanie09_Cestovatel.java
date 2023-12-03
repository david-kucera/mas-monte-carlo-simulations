import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie09_Cestovatel {
    public static void spusti() {
        TriangularRNG X = new TriangularRNG(0.01, 0.04, 0.11);
        UniformContinuousRNG Y = new UniformContinuousRNG(0.05, 0.14);

        int pocetReplikacii = 1000000;

        double[] cenyLeteniek = new double[7];
        for (int i = 0; i < pocetReplikacii; i++) {
            double cenaLetenky = 500;
            double naplnenostLetu = 0.27;
            boolean meniSaCena = true;

            for (int den = 0; den < 7; den++) {
                if (den != 0) {
                    naplnenostLetu += Y.sample();

                    if (meniSaCena) {
                        if (naplnenostLetu >= 0.75) {
                            cenaLetenky += cenaLetenky * 0.3;
                            meniSaCena = false;
                        } else
                            cenaLetenky -= cenaLetenky * X.sample();
                    }
                }

                cenyLeteniek[den] += cenaLetenky;
            }
        }

        double minCena = cenyLeteniek[0];
        int minDen = 0;
        for (int i = 0; i < 7; i++) {
            double priemernaCenaLetenky = cenyLeteniek[i] / pocetReplikacii;
            if (priemernaCenaLetenky < minCena) {
                minCena = priemernaCenaLetenky;
                minDen = i;
            }
        }

        System.out.println("Letenku sa oplatí kúpiť na " + minDen + ". deň za " + minCena + " €");
    }
}
