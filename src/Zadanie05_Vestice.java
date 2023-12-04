import java.util.Random;

public class Zadanie05_Vestice {
    public static void spusti() {
        int pocetReplikacii = 10000000;
        int prvaDruha = 0;
        int vsetky = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            boolean otazka = dajOtazku();
            boolean vestica1 = dajOdpoved();
            boolean vestica2 = dajOdpoved();
            boolean vestica3 = dajOdpoved();

            if (vestica1 == vestica2) {
                if (otazka == vestica1) {
                    prvaDruha++;
                }
            }

            if (vestica1 == vestica2 && vestica2 == vestica3) {
                if (otazka == vestica1) {
                    vsetky++;
                }
            }
        }
        double percPrvaDruha = ((double) prvaDruha / pocetReplikacii) * 100;
        double percVsetky = ((double) vsetky / pocetReplikacii) * 100;
        System.out.println(percPrvaDruha);
        System.out.println(percVsetky);
    }

    private static boolean dajOtazku() {
        Random rnd = new Random();
        return rnd.nextDouble() <= 0.5;
    }

    private static boolean dajOdpoved() {
        Random random = new Random();
        return random.nextDouble() <= 0.8;
    }
}
