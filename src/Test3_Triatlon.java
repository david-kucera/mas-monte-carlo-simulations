import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.text.DecimalFormat;

public class Test3_Triatlon {
    public static void spusti() {
        TriangularRNG plavanieRng = new TriangularRNG(20.0, 32.0, 40.0);
        TriangularRNG cyklistikaRng = new TriangularRNG(60.0, 70.0, 86.0);
        TriangularRNG behRng = new TriangularRNG(36.0, 46.0, 63.0);

        UniformContinuousRNG maDefektRng = new UniformContinuousRNG(0.0, 1.0);
        UniformContinuousRNG defektRng = new UniformContinuousRNG(0.5, 5.0);
        UniformContinuousRNG maSnurkyRng = new UniformContinuousRNG(0.0, 1.0);
        UniformContinuousRNG snurkyRng = new UniformContinuousRNG(1.0, 5.0);

        int pocetPretekarov = 66;
        int pocetReplikacii = 1000000;
        double celkovoKvalifikovanych = 0;
        double celkovoSportovcov = pocetPretekarov * pocetReplikacii;

        for (int i = 0; i < pocetReplikacii; i++) {
            int pocetKvalifikovanych = 0;

            for (int j = 0; j < pocetPretekarov; j++) {
                double casPretekara = plavanieRng.sample() + cyklistikaRng.sample() + behRng.sample();

                int pocetDefektov = 0;
                double defektPerc = defektRng.sample();
                if (defektPerc < 0.07) {
                    pocetDefektov = 1;
                } else if (defektPerc < 0.11) {
                    pocetDefektov = 2;
                } else if (defektPerc < 0.125) {
                    pocetDefektov = 3;
                }

                for (int k = 0; k < pocetDefektov; k++) {
                    casPretekara += maDefektRng.sample();
                }

                int pocetRozviazani = 0;
                double rozviazaniePerc = maSnurkyRng.sample();
                if (rozviazaniePerc < 0.1) {
                    pocetRozviazani = 1;
                } else if (rozviazaniePerc < 0.145) {
                    pocetRozviazani = 2;
                }

                for (int k = 0; k < pocetRozviazani; k++) {
                    casPretekara += snurkyRng.sample();
                }

                if (casPretekara < 140.0) {
                    pocetKvalifikovanych++;
                }

            }
            celkovoKvalifikovanych += pocetKvalifikovanych;
        }

        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("Kvalifikovalo sa " + f.format( (celkovoKvalifikovanych / celkovoSportovcov) * 100 ) + "% sportovcov.");
    }
}
