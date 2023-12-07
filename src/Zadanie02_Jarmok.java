import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

public class Zadanie02_Jarmok {
    public static void spusti() {
        TriangularRNG druhA = new TriangularRNG(1.0, 1.75, 2.5);
        TriangularRNG druhB = new TriangularRNG(0.7, 1.2, 1.7);

        UniformDiscreteRNG dopytA = new UniformDiscreteRNG(40, 80);
        UniformDiscreteRNG dopytB = new UniformDiscreteRNG(66, 155);

        int cenaA = 3;
        int cenaB = 2;
        int pocetA = 70;
        int pocetB = 90;

    }
}
