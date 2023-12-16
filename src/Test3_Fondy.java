import OSPRNG.NormalRNG;
import OSPRNG.TriangularRNG;

public class Test3_Fondy {
    public static void spusti() {
        int POCET_REPLIKACII = 10000;
        int POCET_DNI = 10680;
        int POCET_MESIACOV = 12 * 30;

        TriangularRNG genCenaFondA = new TriangularRNG(-9.25, 3.37, 5.98);
        NormalRNG genCenaFondB = new NormalRNG(1.0, 3.0);

        double celkomHodnotaA = 0;
        double celkomHodnotaB = 0;
        for (int i = 0; i < POCET_REPLIKACII; i++) {
            double investiciaA = 0;
            double investiciaB = 0;
            int mesacnyVklad = 100;

            // A
            double POPLATOK_A = 5.75;
            for (int j = 0; j < POCET_DNI; j++) {
                double cenaFondA = genCenaFondA.sample();
                double podielA = 1 + (cenaFondA / 100);

                if (j % 30 == 0) {
                    investiciaA += mesacnyVklad;
                    investiciaA -= POPLATOK_A;
                }
                investiciaA *= podielA;
            }
            celkomHodnotaA += investiciaA;

            // B
            double POPLATOK_B = 8.75;
            for (int j = 0; j < POCET_MESIACOV; j++) {
                double cenaFondB = genCenaFondB.sample();
                double podielB = 1 + (cenaFondB / 100);

                investiciaB += mesacnyVklad;
                investiciaB -= POPLATOK_B;
                investiciaB *= podielB;
            }
            celkomHodnotaB += investiciaB;
        }
        System.out.println("Hodnota majetku vo fonde A: " + celkomHodnotaA / POCET_REPLIKACII);
        System.out.println("Hodnota majetku vo fonde B: " + celkomHodnotaB / POCET_REPLIKACII);
        if (celkomHodnotaA > celkomHodnotaB) {
            System.out.println("Oplati sa fond A");
        } else {
            System.out.println("Oplati sa fond B");
        }
    }
}
