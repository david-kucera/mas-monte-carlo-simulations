import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.text.DecimalFormat;

public class Test3_MarStv07 {
    public static void spusti() {
        UniformContinuousRNG prvyRngX = new UniformContinuousRNG(0.4, 0.8);
        UniformContinuousRNG prvyRngY = new UniformContinuousRNG(0.0, 0.9);

        TriangularRNG druhyRngX = new TriangularRNG(0.2, 0.6, 1.0);
        TriangularRNG druhyRngY = new TriangularRNG(0.0, 0.4, 0.9);

        TriangularRNG tretiRngX = new TriangularRNG(0.3, 0.55, 0.9);
        TriangularRNG tretiRngY = new TriangularRNG(0.2, 0.35, 0.8);

        int pocetKol = 75;
        int pocetRep = 1000000;
        int vsetciTrafili = 0;
        int celkovoBodyPrvy = 0;
        int celkovoBodyDruhy = 0;
        int celkovoBodyTreti = 0;

        for (int i = 0; i < pocetRep; i++) {
            int bodyPrvy = 0;
            int bodyDruhy = 0;
            int bodyTreti = 0;

            for (int j = 0; j < pocetKol; j++) {
                double[] zasahPrvy = {prvyRngX.sample(), prvyRngY.sample()};
                double[] zasahDruhy = {druhyRngX.sample(), druhyRngY.sample()};
                double[] zasahTreti = {tretiRngX.sample(), tretiRngY.sample()};

                boolean trafilPrvy = trafil(zasahPrvy);
                boolean trafilDruhy = trafil(zasahDruhy);
                boolean trafilTreti = trafil(zasahTreti);

                if (trafilPrvy == trafilDruhy == trafilTreti) {
                    vsetciTrafili++;
                }

                if (trafilPrvy) {
                    bodyPrvy++;
                }
                if (trafilDruhy) {
                    bodyDruhy++;
                }
                if (trafilTreti) {
                    bodyTreti++;
                }
            }
            celkovoBodyPrvy += bodyPrvy;
            celkovoBodyDruhy += bodyDruhy;
            celkovoBodyTreti += bodyTreti;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Pravdepodobnost, ze v jednom kole trafia do terca vsetci traja: " + df.format(((double)vsetciTrafili / 75 / pocetRep) * 100) + "%.");
        System.out.println("Pocet bodov po 75 kolach prveho sutaziaceho: " + df.format((double)celkovoBodyPrvy / pocetRep));
        System.out.println("Pocet bodov po 75 kolach druheho sutaziaceho: " + df.format((double)celkovoBodyDruhy / pocetRep));
        System.out.println("Pocet bodov po 75 kolach tretieho sutaziaceho: " + df.format((double)celkovoBodyTreti / pocetRep));
    }

    private static boolean trafil(double[] zasah) {
        double[] stredKruhu = {0.5, 0.5};
        double polomer = 0.3;
        return Math.pow((zasah[0] - stredKruhu[0]), 2) + Math.pow((zasah[1] - stredKruhu[1]), 2) <= Math.pow(polomer, 2);
    }
}
