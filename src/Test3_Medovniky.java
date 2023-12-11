import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Test3_Medovniky {
    public static void spusti() {
        int pocetReplikacii = 1000000;
        int ostaloCelkovo = 0;
        double zarobilaCelkovo = 0.0;

        TriangularRNG cenaRNG = new TriangularRNG(0.5, 0.75, 1.0);
        TriangularRNG interarrivalRNG = new TriangularRNG(2.0, 6.0, 10.0);
        UniformContinuousRNG zlavRNG = new UniformContinuousRNG(1.0, 3.0);

        for (int i = 0; i < pocetReplikacii; i++) {
            double pocetMinutOtvoreneZotavajuce = 8 * 60;
            double dnesnaCena = cenaRNG.sample();
            boolean zlavnene = false;
            double zarobila = 0.0;
            int pocetMedovnikov = 100;

            while (pocetMinutOtvoreneZotavajuce > 0.0) {

                if (pocetMinutOtvoreneZotavajuce <= 60 && pocetMedovnikov > 10 && !zlavnene) {
                    zlavnene = true;
                }

                if (zlavnene) {
                    double prichodNavstevnika = zlavRNG.sample();
                    pocetMinutOtvoreneZotavajuce -= prichodNavstevnika;

                    zarobila += (dnesnaCena/4);
                    pocetMedovnikov--;
                } else {
                    double prichodNavstevnika = interarrivalRNG.sample();
                    pocetMinutOtvoreneZotavajuce -= prichodNavstevnika;

                    zarobila += dnesnaCena;
                    pocetMedovnikov--;
                }

                if (pocetMedovnikov == 0) {
//                    System.out.println("Babka predala vsetky medovniky!");
                    break;
                }

            }
//            System.out.println("Babka zarobila " + zarobila);
//            System.out.println("Babke ostalo " + pocetMedovnikov);
            zarobilaCelkovo += zarobila;
            ostaloCelkovo += pocetMedovnikov;
        }

        System.out.println("Babka zarobila priemerne za den: " + zarobilaCelkovo/pocetReplikacii + " EUR.");
        System.out.println("Babke ostalo priemerne za den: " + (double)ostaloCelkovo/pocetReplikacii + " medovnikov.");
    }
}