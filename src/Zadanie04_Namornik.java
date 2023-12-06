import OSPRNG.UniformDiscreteRNG;

public class Zadanie04_Namornik {
    public static void spusti() {

        int pocetRep = 100000;
        int pocetKrokov = 1000;
        UniformDiscreteRNG dsc = new UniformDiscreteRNG(0, 1);
        int celkovo = 0;
        for (int i = 0; i < pocetRep; i++) {
            int startBod = 0;
            for (int j = 0; j < pocetKrokov; j++) {
                int pohyb = dsc.sample(); // 0 alebo 1
                if (pohyb == 0) {
                    startBod -= 1;
                } else {
                    startBod += 1;
                }
            }
            celkovo += Math.abs(startBod);
        }
        System.out.println(celkovo/pocetRep);
    }
}
