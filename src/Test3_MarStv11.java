import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.text.DecimalFormat;

public class Test3_MarStv11 {
    public static void spusti() {
        UniformContinuousRNG rng = new UniformContinuousRNG(0.0, 1.0);

        TriangularRNG behRng = new TriangularRNG(35.0, 50.0, 70.0);
        TriangularRNG cyklistikaRng = new TriangularRNG(50.0, 65.0, 85.0);
        TriangularRNG lyzeRng = new TriangularRNG(40.0, 50.0, 65.0);

        int pocetRep = 1000000;
        int pocetPretekarov = 85;
        double celkovyCasPretekarovMin = 0;
        int kvalifikovaloSaViacAko10 = 0;

        for (int i = 0; i < pocetRep; i++) {
            int pocetKvalifikovanych = 0;

            for (int j = 0; j < pocetPretekarov; j++) {
                double celkovyCasMinPretekar = 0;
                celkovyCasMinPretekar += behRng.sample();
                celkovyCasMinPretekar += cyklistikaRng.sample();

                // Zle namazane lyze
                if (rng.sample() <= 0.2) {
                    celkovyCasMinPretekar += (lyzeRng.sample() * 1.25);
                } else {
                    celkovyCasMinPretekar += lyzeRng.sample();
                }

                if (celkovyCasMinPretekar <= 165) {
                    pocetKvalifikovanych++;
                }
                celkovyCasPretekarovMin += celkovyCasMinPretekar;
            }

            if (pocetKvalifikovanych >= 10) {
                kvalifikovaloSaViacAko10++;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Odhad priemerneho casu 85 pretekarov je : " + df.format((celkovyCasPretekarovMin / pocetRep / 85)) ); // Treba podelit 85 pretekarmi a potom poctom replikacii
        System.out.println("Odhad pravdepodobnosti, ze sa kvalifikuje 10 alebo viac pretekarov je: " + df.format(((double)kvalifikovaloSaViacAko10 / pocetRep) * 100) + "%");
    }
}
