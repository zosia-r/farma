package Katastrofa;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Stodola.Stodola;

public class KatastrofaStodola implements Katastrofa {
    private final String komunikat;

    public KatastrofaStodola(String komunikat) {
        this.komunikat = komunikat;
    }

    public void katastrofa() {
        Stodola stodola = Gra.getInstance().getFarmaGracza().getStodola();
        stodola.usunWszystkieProdukty();
        OkienkoKatastrofa okienkoKatastrofa = new OkienkoKatastrofa(komunikat);
    }
}
