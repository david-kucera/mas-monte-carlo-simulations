import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

public class Zadanie02_Jarmok {
    public static void spusti() {
        TriangularRNG nakladyA = new TriangularRNG(1.0, 1.75, 2.5);
        TriangularRNG nakladyB = new TriangularRNG(0.7, 1.2, 1.7);
        UniformDiscreteRNG dopytA = new UniformDiscreteRNG(40, 80);
        UniformDiscreteRNG dopytB = new UniformDiscreteRNG(66, 155);

        int cenaA = 3, cenaB = 2;
        int maxA = 70, maxB = 90;

        int pocetRep = 1000000;
        double celkA = 0.0, celkB = 0.0;

        for (int i = 0; i < pocetRep; i++) {
            int predanychA = Math.min(dopytA.sample(), maxA);
            int predanychB = Math.min(dopytB.sample(), maxB);
            double naklA = 0.0, naklB = 0.0;

            for (int j = 0; j < maxA; j++) {
                naklA += nakladyA.sample();
            }
            for (int j = 0; j < maxB; j++) {
                naklB += nakladyB.sample();
            }

            celkA += cenaA * predanychA - naklA;
            celkB += cenaB * predanychB - naklB;
        }
        System.out.println("Celkovy zisk z predaja produktov A: " + (celkA/pocetRep));
        System.out.println("Celkovy zisk z predaja produktov B: " + (celkB/pocetRep));
    }
}
