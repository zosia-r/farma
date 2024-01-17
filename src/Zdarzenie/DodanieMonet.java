package Zdarzenie;

import GUI.OkienkoZdarzenie;
import Gra.Gra;

public class DodanieMonet implements Zdarzenie {

    private final String komunikat;

    public DodanieMonet(String komunikat) {
        this.komunikat = komunikat;
    }
    public void zdarzenie() {
        Gra.getInstance().setLiczbaMonet(Gra.getInstance().getLiczbaMonet()+50);
        new OkienkoZdarzenie(komunikat);
    }
}
