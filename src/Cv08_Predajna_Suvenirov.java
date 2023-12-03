import OSPRNG.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Cv08_Predajna_Suvenirov {
    public static void spusti() {
        UniformContinuousRNG cenaNakupuGenerator = new UniformContinuousRNG(0.4, 0.7);
        TriangularRNG dopytGenerator = new TriangularRNG(70.0, 90.0, 110.0);
        double  cenaNakupu;
        double cenaVykupu;
        double cenaPredaja = 0.89;
        int dennyNakup = 80;
        double dennyPredaj;
        double dopyt;
        int replikacie = 1000;
        double smerodajnaOdchylka;

        double celkovyZisk = 0;
        double celkovyZiskMocnina = 0;

        try {
            PrintWriter writer = new PrintWriter("kupil80.txt", StandardCharsets.UTF_8);

            for (int i = 0; i < replikacie; i++) {
                double hospodarskyVysledok;
                cenaNakupu = cenaNakupuGenerator.sample();
                cenaVykupu = cenaNakupu / 2;

                dopyt = dopytGenerator.sample();

                dennyPredaj = Math.min(dennyNakup, dopyt);

                double nakupilZa = dennyNakup * cenaNakupu;
                double predaliZa = dennyPredaj * cenaPredaja;
                double predaliVyrobcovi = (dennyNakup - dennyPredaj) * cenaVykupu;

                hospodarskyVysledok = predaliZa + predaliVyrobcovi - nakupilZa;
                celkovyZisk += hospodarskyVysledok;
                celkovyZiskMocnina += Math.pow(hospodarskyVysledok, 2);
                writer.println((i+1) + ". " + hospodarskyVysledok);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double odhadHospVysledku = celkovyZisk/replikacie;
        System.out.printf("Odhad hospodárskeho výsledku: %.2f €%n", odhadHospVysledku);
        smerodajnaOdchylka = Math.sqrt((celkovyZiskMocnina/(replikacie-1)) - Math.pow(celkovyZisk/(replikacie-1), 2));
        System.out.printf("Odhad smerodajnej odchýlky: %.2f%n", smerodajnaOdchylka);
        double polkaSirkaIS = (1.96 * smerodajnaOdchylka) / Math.sqrt(replikacie);
        System.out.printf("Polovicny interval spolahlivosti: %.2f %n", polkaSirkaIS);
        double hornaHranica = (odhadHospVysledku + polkaSirkaIS);
        System.out.printf("Horna hranica 95%% intervalu spolahlivosti: %.2f %n", hornaHranica);
        double dolnaHranica = (odhadHospVysledku - polkaSirkaIS);
        System.out.printf("Dolna hranica 95%% intervalu spolahlivosti: %.2f %n", dolnaHranica);
    }
}
