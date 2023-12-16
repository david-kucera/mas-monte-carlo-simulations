import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Test3_Zalubenci {
    public static void spusti() {
        // Cas od 13.00 do 17.00, teda celkovo 4 hod = 60 * 4 = 240 min
        UniformContinuousRNG casRomeo = new UniformContinuousRNG(0.0, 240.4);
        TriangularRNG trvanieRomeo = new TriangularRNG(15.0, 24.0, 32.0);

        UniformContinuousRNG casJulia = new UniformContinuousRNG(0.0, 240.0);
        TriangularRNG trvanieJulia = new TriangularRNG(10.0, 16.0, 22.0);

        UniformContinuousRNG pestunka = new UniformContinuousRNG(0.0, 1.0);

        int POCET_REPLIKACII = 1000000;
        int stretliSa = 0;
        int nestretliSa = 0;
        for (int i = 0; i < POCET_REPLIKACII; i++) {
            double prichodRomea = casRomeo.sample();
            double prichodJulie = casJulia.sample();

            double casRomea = trvanieRomeo.sample();
            double casJulie = trvanieJulia.sample();

            double odchodRomea = prichodRomea + casRomea;
            double odchodJulie = prichodJulie + casJulie;

            if (pestunka.sample() <= 0.04) {
                nestretliSa++;
                continue;
            }

            if (prichodRomea < prichodJulie && prichodJulie < odchodRomea) {
                stretliSa++;
            }
            else if (prichodJulie < prichodRomea && prichodRomea < odchodJulie) {
                stretliSa++;
            }

            nestretliSa++;
        }
        System.out.println("Pravdepodobnost, ze sa zalubenci stretnu je: " + (((double)stretliSa/POCET_REPLIKACII) * 100));
    }
}
