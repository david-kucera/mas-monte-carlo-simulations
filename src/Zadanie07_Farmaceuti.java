import OSPRNG.TriangularRNG;

public class Zadanie07_Farmaceuti {

    public static void spusti() {
        TriangularRNG triangularRNG = new TriangularRNG(1000.0, 4000.0, 8500.0);

        int pocetReplikacii = 10000;
        double[] dodatocneNakladyList = new double[4000];
        int[] pocetVakcinNaSkladeList = new int[4000];
        for (int i = 0; i < 4000; i++) {
            pocetVakcinNaSkladeList[i] = 4500 + i;
        }

        for (int i = 0; i < pocetReplikacii; i++) {

            for (int navysenie = 0; navysenie < 4000; navysenie++) {
                int sucasnost = 4500 + navysenie;
                double dodatocneNaklady = 0;
                double dopyt = triangularRNG.sample();

                if (dopyt == sucasnost) { dodatocneNaklady += 0; }
                if (dopyt > sucasnost) { dodatocneNaklady += (dopyt - sucasnost) * 150; }
                if (dopyt < sucasnost) { dodatocneNaklady += (sucasnost - dopyt) * 50; }

                dodatocneNakladyList[navysenie] += dodatocneNaklady;
            }
        }

        for (int i = 0; i < dodatocneNakladyList.length; i++) {
            dodatocneNakladyList[i] = dodatocneNakladyList[i]/pocetReplikacii;
        }

        double minimum = Double.MAX_VALUE;
        int indexMin = -1;
        for (int i = 0; i < dodatocneNakladyList.length; i++) {
            if (minimum > dodatocneNakladyList[i]) {
                minimum = dodatocneNakladyList[i];
                indexMin = i;
            }
        }
        System.out.println("Minimalne naklady budu, ak bude na sklade " + pocetVakcinNaSkladeList[indexMin] + " vakcin.");
    }
}
