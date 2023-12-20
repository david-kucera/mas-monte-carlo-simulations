import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.text.DecimalFormat;

public class Test3_JanSt14 {
    public static void spusti() {
        TriangularRNG aRng = new TriangularRNG(-0.052, 0.012, 0.045); // Kazdodenna zmena, od Po do Pia
        UniformContinuousRNG bRng = new UniformContinuousRNG(-0.027, 0.059); // Kazdotyzdenna zmena

        int pocetTyzdnov = 52;
        double celkovoStrategia1A = 0;
        double celkovoStrategia1B = 0;
        double celkovoStrategia2A = 0;
        double celkovoStrategia2B = 0;

        int pocetRep = 1_000_000;
        for (int i = 0; i < pocetRep; i++) {
            double hodnotaStrategie1A = 2500;
            double hodnotaStretegia1B = 2500;
            double hodnotaStrategie2A = 1000;
            double hodnotaStretegia2B = 4000;


            for (int j = 0; j < pocetTyzdnov * 5; j++) {
                // Kazdodenna zmena
                double X = aRng.sample();
                hodnotaStrategie1A += hodnotaStrategie1A * X;
                hodnotaStrategie2A += hodnotaStrategie2A * X;

                // Tyzdenna zmena
                if (j % 5 == 0) {
                    double Y = bRng.sample();
                    hodnotaStretegia1B += Y * hodnotaStretegia1B;
                    hodnotaStretegia2B += Y * hodnotaStretegia2B;
                }
            }

            celkovoStrategia1A += hodnotaStrategie1A;
            celkovoStrategia1B += hodnotaStretegia1B;
            celkovoStrategia2A += hodnotaStrategie2A;
            celkovoStrategia2B += hodnotaStretegia2B;
        }

        DecimalFormat df = new DecimalFormat("#.#");
        System.out.println("Celkova hodnota majetku vo fonde A po 52 tyzdnoch investovania strategiou 1 bude: " + df.format(celkovoStrategia1A / pocetRep));
        System.out.println("Celkova hodnota majetku vo fonde B po 52 tyzdnoch investovania strategiou 1 bude: " + df.format(celkovoStrategia1B / pocetRep));
        System.out.println("Celkova hodnota majetku po 52 tyzdnoch investovania stretegiou 1 bude: " + df.format((celkovoStrategia1A + celkovoStrategia1B) / pocetRep));
        System.out.println();
        System.out.println("Celkova hodnota majetku vo fonde A po 52 tyzdnoch investovania strategiou 2 bude: " + df.format(celkovoStrategia2A / pocetRep));
        System.out.println("Celkova hodnota majetku vo fonde B po 52 tyzdnoch investovania strategiou 2 bude: " + df.format(celkovoStrategia2B / pocetRep));
        System.out.println("Celkova hodnota majetku po 52 tyzdnoch investovania stretegiou 2 bude: " + df.format((celkovoStrategia2A + celkovoStrategia2B) / pocetRep));
    }
}
