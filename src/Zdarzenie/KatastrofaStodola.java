package Zdarzenie;

import GUI.OkienkoZdarzenie;
import Gra.Gra;
import Stodola.Stodola;

public class KatastrofaStodola implements Zdarzenie {
    private final String komunikat;

    public KatastrofaStodola(String komunikat) {
        this.komunikat = komunikat;
    }

    public void zdarzenie() {
        Stodola stodola = Gra.getInstance().getFarmaGracza().getStodola();
        if(stodola.getWycena()!=0) {
            stodola.usunWszystkieProdukty();
            new OkienkoZdarzenie(komunikat);
        }
    }
}
