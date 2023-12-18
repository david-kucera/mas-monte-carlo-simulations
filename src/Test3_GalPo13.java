import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Test3_GalPo13 {
    public static void spusti() {
        TriangularRNG trenerkaRng = new TriangularRNG(0.0, 10.0, 20.0);
        UniformContinuousRNG dietaRng = new UniformContinuousRNG(0.0, 1.0);
        UniformContinuousRNG RNG = new UniformContinuousRNG(0.0, 100.0);

        int pocetRep = 1000000;
        int pocetNaplanovanych = 0;

        for (int i = 0; i < pocetRep; i ++) {
            int[] detiPoctyTreningov = new int[20];
            int pocetDetiKtoreMozuIstPredstavenie = 0;

            while (pocetDetiKtoreMozuIstPredstavenie < 15) {

                double sancaChorobyTrenerka = trenerkaRng.sample();
                if (RNG.sample() >= sancaChorobyTrenerka) {
                    for (int j = 0; j < detiPoctyTreningov.length; j++) {
                        if (dietaRng.sample() >= 0.45) {
                            detiPoctyTreningov[j] += 1;
                        } else {
                            detiPoctyTreningov[j] += 0;
                        }
                    }
                }
                pocetNaplanovanych++;

                pocetDetiKtoreMozuIstPredstavenie = 0;
                for (int j = 0; j < detiPoctyTreningov.length; j++) {
                    if (detiPoctyTreningov[j] >= 5) {
                        pocetDetiKtoreMozuIstPredstavenie += 1;
                    } else {
                        pocetDetiKtoreMozuIstPredstavenie += 0;
                    }
                }
            }
        }
//        System.out.println(pocetNaplanovanych);
        System.out.println("Bolo potrebne naplanovat minimalne " + Math.ceil(((double) pocetNaplanovanych / pocetRep)) + " treningov.");
    }
}
