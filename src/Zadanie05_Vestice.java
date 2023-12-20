import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie05_Vestice {
    public static void spusti() {
        uniform();
        triangular();
    }

    private static void triangular() {
        System.out.println("Trojuholnikove rozdelenie:");
        TriangularRNG random = new TriangularRNG(0.3, 0.8, 1.0);
        int pocetReplikacii = 10000000;
        int prvaDruha = 0;
        int pocPrvaDruha = 0;
        int vsetky = 0;
        int pocVsetky = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            boolean otazka = true;
            boolean vestica1 = random.sample() <= 0.8;
            boolean vestica2 = random.sample() <= 0.8;
            boolean vestica3 = random.sample() <= 0.8;

            if (vestica1 == vestica2) {
                pocPrvaDruha++;
                if (otazka == vestica1) {
                    prvaDruha++;
                }
            }

            if (vestica1 == vestica2 && vestica2 == vestica3) {
                pocVsetky++;
                if (otazka == vestica1) {
                    vsetky++;
                }
            }
        }
        double percPrvaDruha = ((double) prvaDruha / pocPrvaDruha) * 100;
        double percVsetky = ((double) vsetky / pocVsetky) * 100;
        System.out.println(percPrvaDruha);
        System.out.println(percVsetky);
    }

    private static void uniform() {
        UniformContinuousRNG random = new UniformContinuousRNG();
        System.out.println("Rovnomerne rozdelenie:");
        int pocetReplikacii = 10000000;
        int prvaDruha = 0;
        int pocPrvaDruha = 0;
        int vsetky = 0;
        int pocVsetky = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            boolean otazka = true;
            boolean vestica1 = random.sample() <= 0.8;
            boolean vestica2 = random.sample() <= 0.8;
            boolean vestica3 = random.sample() <= 0.8;

            if (vestica1 == vestica2) {
                pocPrvaDruha++;
                if (otazka == vestica1) {
                    prvaDruha++;
                }
            }

            if (vestica1 == vestica2 && vestica2 == vestica3) {
                pocVsetky++;
                if (otazka == vestica1) {
                    vsetky++;
                }
            }
        }
        double percPrvaDruha = ((double) prvaDruha / pocPrvaDruha) * 100;
        double percVsetky = ((double) vsetky / pocVsetky) * 100;
        System.out.println(percPrvaDruha);
        System.out.println(percVsetky);
    }
}
