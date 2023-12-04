import OSPRNG.TriangularRNG;

import java.util.ArrayList;
import java.util.Collections;

public class Zadanie06_Zavodnici {
    public static void spusti() {
        TriangularRNG fast = new TriangularRNG(40.0, 50.0, 75.0);
        TriangularRNG furious = new TriangularRNG(35.0, 52.0, 80.0);

        int umiestnili = 0;
        int celkovo = 0;

        int pocetReplikacii = 1000;

        for (int j = 0; j < pocetReplikacii; j++) {
            ArrayList<Vysledok> vysledky = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Vysledok vyslFast = new Vysledok(Typ.Fast, fast.sample());
                Vysledok vyslFurious = new Vysledok(Typ.Furious, furious.sample());
                vysledky.add(vyslFast);
                vysledky.add(vyslFurious);
            }

            Collections.sort(vysledky);

            if (vysledky.get(0).getTyp() == Typ.Fast && vysledky.get(1).getTyp() == Typ.Fast) {
                umiestnili++;
            }
            celkovo++;
        }
        double perc = (double) umiestnili / celkovo * 100;
        System.out.println("Pravdepodobnost, ze sa Fast umiestnia na prvych dvoch poziciach je: " + perc + "%.");

        // Druha cast

        int lepsieFast = 0;
        int lepsieFurious = 0;
        for (int j = 0; j < pocetReplikacii ; j++) {
            int pocetBodovFast = 0;
            int pocetBodovFurious = 0;

            for (int i = 0; i < 15; i++) {
                ArrayList<Vysledok> vysledkyPretek = new ArrayList<>();

                for (int k = 0; k < 5; k++) {
                    Vysledok vyslFast = new Vysledok(Typ.Fast, fast.sample());
                    Vysledok vyslFurious = new Vysledok(Typ.Furious, furious.sample());
                    vysledkyPretek.add(vyslFast);
                    vysledkyPretek.add(vyslFurious);
                }

                Collections.sort(vysledkyPretek);
                for (int l = 0; l < vysledkyPretek.size(); l++) {
                    if (vysledkyPretek.get(l).getTyp() == Typ.Fast) {
                        pocetBodovFast += (10 - l);
                    } else {
                        pocetBodovFurious += (10 - l);
                    }
                }
            }

            if (pocetBodovFast > pocetBodovFurious) {
                lepsieFast++;
            } else {
                lepsieFurious++;
            }
        }

        String lepsi = null;
        double prav = 0;
        if (lepsieFast > lepsieFurious) {
            lepsi = "Fast";
            prav = (double) lepsieFast / (lepsieFast + lepsieFurious);
        } else {
            lepsi = "Furious";
            prav = (double) lepsieFurious / (lepsieFast + lepsieFurious);
        }
        System.out.println(lepsi + " ziska viac bodov s pravdepodobnostou " + prav * 100 + "%");
    }
}

class Vysledok implements Comparable<Vysledok> {
    private Typ typ;
    private Double cas;
    private int body;

    public Vysledok(Typ typ, double cas) {
        this.cas = cas;
        this.typ = typ;
        this.body = 0;
    }

    public Typ getTyp() {
        return this.typ;
    }

    public int getBody() {
        return this.body;
    }

    @Override
    public int compareTo(Vysledok other) {
        // Compare based on the cas values
        return this.cas.compareTo(other.cas);
    }
}

enum Typ {
    Fast,
    Furious
}
