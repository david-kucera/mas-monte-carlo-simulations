import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie01_Tlac {
    public static void spusti() {
        double cenaZaVytlacok = 0.15;
        TriangularRNG cenaGen = new TriangularRNG(0.25, 0.6, 0.95);
        UniformContinuousRNG dobaRng = new UniformContinuousRNG(250.0, 420.0);

        int pocetRep = 1000000;
        double maxZisk = 0.0;
        int maxBaliky = 0;
        for (int i = 10; i <= 20; i++) {
            double celkovyZisk = 0.0;

            for (int j = 0; j < pocetRep; j++) {
                double cenaPredaj = cenaGen.sample();
                double dobaPredaja = dobaRng.sample();
                int nakupene = i * 10;

                int dopyt = (int) (dobaPredaja / 2.7) + 1;
                int predane = Math.min(dopyt, nakupene);

                double ziskDna = predane * cenaPredaj + (nakupene - predane) * cenaZaVytlacok * 0.65 - nakupene * cenaZaVytlacok;
                celkovyZisk += ziskDna;
            }

            double priemernyZisk = celkovyZisk / pocetRep;
            if (priemernyZisk > maxZisk) {
                maxZisk = priemernyZisk;
                maxBaliky = i;
            }
        }
        System.out.println("Max zisk bol pri " + maxBaliky + " balikoch: " + maxZisk);
    }
}
