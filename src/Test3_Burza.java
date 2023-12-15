import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.text.DecimalFormat;

public class Test3_Burza {
    public static void spusti() {
        TriangularRNG fA = new TriangularRNG(-0.05, 0.014, 0.035);
        UniformContinuousRNG fB = new UniformContinuousRNG(-0.025, 0.045);

        int pocetRep = 1000000;
        int pocetTyzdnov = 52;

        double celkA = 0.0;
        double celkB = 0.0;
        for (int i = 0; i < pocetRep; i++) {
            double rozpocetA = 10000.0;
            double rozpocetB = 10000.0;

            for (int j = 0; j < pocetTyzdnov; j++) {
                double percY = fB.sample();
                double zmenaB = rozpocetB * percY;
                rozpocetB += zmenaB;

                for (int k = 0; k < 7; k++) {
                    double percX = fA.sample();
                    double zmenaA = rozpocetA * percX;
                    rozpocetA += zmenaA;
                }
            }
            celkA += rozpocetA;
            celkB += rozpocetB;
        }
        DecimalFormat df = new DecimalFormat("#.#");
        System.out.println("Celkova hodnota majetku po 52 tyzdnoch investovania do fondu A bude: " + df.format(celkA / pocetRep));
        System.out.println("Celkova hodnota majetku po 52 tyzdnoch investovania do fondu B bude: " + df.format(celkB / pocetRep));
    }
}
