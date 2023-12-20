import OSPRNG.UniformContinuousRNG;

public class Zadanie03_OdhadPi {
    public static void spusti() {
        UniformContinuousRNG rng = new UniformContinuousRNG();

        double odhadPi = 0.0;
        int pocetBodov = 0;
        int pocetVKruhu = 0;

        while (Math.abs(odhadPi - Math.PI) >= 10e-6) {
            double[] bod = {rng.sample(), rng.sample()};
            pocetBodov++;

            if (jeVKruhu(bod)) {
                pocetVKruhu++;
            }
            odhadPi = ((double) pocetVKruhu / pocetBodov) / Math.pow(0.5, 2);
        }

        System.out.println("Pocet bodov potrebnych pre danu presnost: " + pocetBodov);
        System.out.println("Aproximovane PI: " + odhadPi);
    }

    private static boolean jeVKruhu(double[] bod) {
        return Math.pow(bod[0] - 0.5, 2) + Math.pow(bod[1] - 0.5, 2) <= Math.pow(0.5, 2);
    }
}
