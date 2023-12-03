import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie09_Cestovatel {
    public static void spusti() {
        TriangularRNG XGenerator = new TriangularRNG(0.01, 0.04, 0.11); // Percenta
        UniformContinuousRNG YGenerator = new UniformContinuousRNG(0.05, 0.14); // Percenta

        double[] cenaJednotliveDni = new double[7];
        cenaJednotliveDni[0] = 500.0; // V Eurach
        double[] naplnenostLetuJednotliveDni = new double[7];
        naplnenostLetuJednotliveDni[0] = 0.27; // Percenta

        int pocetReplikacii = 1000;
        double cenaLetenka = 0.0;
        int denLetenka = 0;

        for (int a = 0; a < pocetReplikacii; a++) {

            boolean prvykratZmena = true;
            for (int i = 1; i < 7; i++) {
                // Pseudonahodne vygenerovane hodnoty
                double X = XGenerator.sample();
                double Y = YGenerator.sample();

                // Ak obsadenost dosiahne 75%
                if (naplnenostLetuJednotliveDni[i-1] >= 0.75) {
                    if (prvykratZmena) {
                        // Zvysenie ceny o 30%
                        cenaJednotliveDni[i] = cenaJednotliveDni[i-1] * 1.3;
                        // Dalsie dni sa uz cena nezmeni
                        prvykratZmena = false;
                    } else {
                        cenaJednotliveDni[i] = cenaJednotliveDni[i-1];
                    }
                    naplnenostLetuJednotliveDni[i] = naplnenostLetuJednotliveDni[i-1] + Y;
                } else {
                    // Letenka zlacnie
                    double zlavaZaTentoDen = cenaJednotliveDni[i-1] * X;
                    double aktualnaCena = cenaJednotliveDni[i-1] - zlavaZaTentoDen;
                    cenaJednotliveDni[i] = aktualnaCena;

                    // Zvysenie naplnenosti
                    naplnenostLetuJednotliveDni[i] = naplnenostLetuJednotliveDni[i-1] + Y;
                }
            }
            // Zistenie minimalnej ceny replikacie a dna
            double najnizsiaCena = cenaJednotliveDni[0];
            int poradieDna = 0;
            for (int j = 0; j < cenaJednotliveDni.length; j++) {
                if (najnizsiaCena > cenaJednotliveDni[j]) {
                    najnizsiaCena = cenaJednotliveDni[j];
                    poradieDna = j;
                }
            }
            cenaLetenka += najnizsiaCena;
            denLetenka += poradieDna;
        }
        // Vyhodnotenie replikacii
        double priemernaNajnizsiaCenaLetenky = cenaLetenka / pocetReplikacii;
        double priemernyDenLetenky = (double) denLetenka / pocetReplikacii;
        System.out.println(priemernaNajnizsiaCenaLetenky);
        System.out.println(priemernyDenLetenky);
    }
}
