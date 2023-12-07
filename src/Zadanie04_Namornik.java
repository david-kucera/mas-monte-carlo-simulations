import OSPRNG.UniformDiscreteRNG;

public class Zadanie04_Namornik {
    public static void spusti() {
        cast1();
        cast2();
        cast3();
    }

    private static void cast3() {
        int pocetRep = 100000;
        int pocetKrokov = 1000;
        UniformDiscreteRNG dsc = new UniformDiscreteRNG(0, 5);
        int celkovo = 0;
        for (int i = 0; i < pocetRep; i++) {
            int[] startBod = {0, 0, 0};
            for (int j = 0; j < pocetKrokov; j++) {
                int smer = dsc.sample(); // 0 = x+, 1 = x-, 2 = y+, 3 = y-, 4 = z+, 5 = z-
                switch (smer) {
                    case 0:
                        startBod[0] += 1;
                        break;
                    case 1:
                        startBod[0] -= 1;
                        break;
                    case 2:
                        startBod[1] += 1;
                        break;
                    case 3:
                        startBod[1] -= 1;
                        break;
                    case 4:
                        startBod[2] += 1;
                        break;
                    case 5:
                        startBod[2] -= 1;
                        break;
                }
            }
            celkovo += Math.abs(startBod[0]);
            celkovo += Math.abs(startBod[1]);
            celkovo += Math.abs(startBod[2]);
        }
        System.out.println("Vysledok tretej casti prikladu: " + celkovo/pocetRep);
        //double teor = Math.sqrt((6 * pocetKrokov)/Math.PI);
        //System.out.println("Teoreticky odhad vysledku: " + Math.round(teor));
    }

    private static void cast2() {
        int pocetRep = 100000;
        int pocetKrokov = 1000;
        UniformDiscreteRNG dsc = new UniformDiscreteRNG(0, 1);
        int celkovo = 0;
        for (int i = 0; i < pocetRep; i++) {
            int[] startBod = {0, 0};
            for (int j = 0; j < pocetKrokov; j++) {
                int smer = dsc.sample(); // 0 = x, 1 = y os
                int pohyb = dsc.sample(); // 0 = plus, 1 = minus
                int zmena = 0;
                if (pohyb == 0) {
                    zmena = 1;
                } else {
                    zmena = -1;
                }
                if (smer == 0) {
                    startBod[0] += zmena;
                } else {
                    startBod[1] += zmena;
                }
            }
            celkovo += Math.abs(startBod[0]);
            celkovo += Math.abs(startBod[1]);
        }
        System.out.println("Vysledok druhej casti prikladu: " + celkovo/pocetRep);
        //double teor = Math.sqrt((4 * pocetKrokov)/Math.PI);
        //System.out.println("Teoreticky odhad vysledku: " + Math.round(teor));
    }

    private static void cast1() {
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
        System.out.println("Vysledok prvej casti prikladu: " + celkovo/pocetRep);
        //double teor = Math.sqrt((2 * pocetKrokov)/Math.PI);
        //System.out.println("Teoreticky odhad vysledku: " + Math.round(teor));
    }
}
