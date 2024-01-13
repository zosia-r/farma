package Strategia;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Stodola.Stodola;

public class Strategia1 implements Strategia {
    private final String komunikat;

    public Strategia1(String komunikat) {
        this.komunikat = komunikat;
        }
    }
    public void katastrofa() {
        Stodola stodola = Gra.getInstance().getFarmaGracza().getStodola();
        stodola.usunWszystkieProdukty();
        OkienkoKatastrofa okienkoKatastrofa = new OkienkoKatastrofa(komunikat);
    }
}
