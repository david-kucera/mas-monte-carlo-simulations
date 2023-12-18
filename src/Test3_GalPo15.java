import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Test3_GalPo15 {
//    Peťo má 6 člennú rodinu.
//
//    Na vianoce chce chytiť rodine rybu.
//    Chodí každý deň na ryby, keď sa ráno vyberie je 25% šanca že ho kamaráti zatiahnú do pajzlu.
//    Ak sa dostane na ryby, je šanca uniformcontinuous(30.0, 40.0) že chytí rybu.
//    Váha ryby je triangular(2.5, 4.0, 7.0).
//    na nakŕmenie jeho rodiny musí mať ryba minimálne 5kg(vrátane).
//    Ak nemá ryba dostatočnú veľkosť, ide domov, a zajtra skúša znovu.
//
//    -Aká je šanca, že hneď prvý deň chytí dostatočne veľkú rybu?
//    -Minimálne koľko dní (v priemere) potrebuje na chytenie dostatočne veľkej ryby?
    public static void spusti() {
        UniformContinuousRNG chytiRybuRng = new UniformContinuousRNG(0.3, 0.4);
        TriangularRNG vahaRybyRng = new TriangularRNG(2.5, 4.0, 7.0);
        UniformContinuousRNG RNG = new UniformContinuousRNG(0.0, 1.0);

        int pocetRep = 1000000;
        int pocetPrvyDen = 0;
        int celkPocetDni = 0;
        for (int i = 0; i < pocetRep; i++) {
            int pocetDni = 0;
            double vahaRyby = 0;

            while (vahaRyby < 5) {
                pocetDni++;
                celkPocetDni++;

                if (RNG.sample() <= 0.25) {
                    continue; // Ide do krcmy
                }
                if (RNG.sample() <= chytiRybuRng.sample()) {
                    vahaRyby = vahaRybyRng.sample();
                }
            }
            if (pocetDni == 1) {
                pocetPrvyDen++;
            }
        }
        System.out.println("Sanca, ze hned prvy den chyti dostatocne velku rybu je " + ((double) pocetPrvyDen / pocetRep) * 100);
        System.out.println("V priemere potrebuje " +  (int)Math.ceil(((double) celkPocetDni / pocetRep)) + " dni na chytenie 5 kg ryby.");
    }
}
