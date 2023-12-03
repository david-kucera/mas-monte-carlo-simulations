import OSPRNG.TriangularRNG;

public class Zadanie08_Kombajny {
    public static void spusti() {
        int pocetKombajnov = 1; // Zaciname s 1 kombajnom
        double pravdepodobnost;

        // Opakujuci sa cyklus az kym sa nenajde pozadovany pocet kombajnov
        do {
            pravdepodobnost = simuluj(pocetKombajnov);
//            System.out.println("Pocet kombajnov: " + pocetKombajnov);
//            System.out.println("Pravdepodobnost: " + pravdepodobnost);

            // Dosiahli sme pozadovanu pravdepodobnost, koniec
            if (pravdepodobnost > 0.90) {
                break;
            }
            pocetKombajnov++;
        } while (true);

        System.out.println("Minimálny počet kombajnov potrebných na žatvu: " + pocetKombajnov);
    }

    private static double simuluj(int pocetKombajnov) {
        TriangularRNG rozdelenie = new TriangularRNG(1.0, 3.0, 3.5);
        double rozloha = 300.0;

        int stihli = 0;
        int nestihli = 0;

        int pocetReplikacii = 1000000;

        for (int i = 0; i < pocetReplikacii; i++) {
            // Simulujem 2 dni
            for (int den = 0; den < 2; den++) {
                for (int hodina = 0; hodina < 10; hodina++) {
                    for (int kombajn = 0; kombajn < pocetKombajnov; kombajn++) {
                        rozloha -= rozdelenie.sample();
                    }
                }
            }

            // Zistenie, ci stihli, alebo nie
            if (rozloha > 0) {
                nestihli += 1;
            } else {
                stihli += 1;
            }
        }

        return vypocitajPravdepodobnost(stihli/pocetReplikacii, nestihli/pocetReplikacii);
    }

    private static double vypocitajPravdepodobnost(int pocetStihli, int pocetNestihli) {
        int celkovo = pocetNestihli + pocetStihli;
        return (double) pocetStihli / celkovo;
    }
}
