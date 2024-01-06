package Przetwornia;
import Produkt.Produkt;
import java.util.Timer;
import java.util.TimerTask;
import Stodola.Stodola;
import Gra.Gra;

public class Przetwor extends Produkt {
    private Produkt ProduktPotrzebnyDoWyprodukowania;
    private int iloscPotrzebnegoProduktu;

    public Przetwor(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int czasProdukcji, String adresIkonki, Produkt ProduktPotrzebnyDoWyprodukowania, int iloscPotrzebnegoProduktu) {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, czasProdukcji, adresIkonki);
        int l = Stodola.getDostepnosc(ProduktPotrzebnyDoWyprodukowania.getNazwa());
        if (l >= iloscPotrzebnegoProduktu) {
            uruchomLiczniki();
            for (int i = 0; i <= iloscPotrzebnegoProduktu; i++) {
                Stodola.usunProdukt(ProduktPotrzebnyDoWyprodukowania, 1);
            }
        }
    }
    public void przerwijProdukcjePrzet() {
        setGotoweDoZebrania(false);
        getWzrostTimer().cancel();
        getOdliczanieTimer().cancel();
        for (int i = 0; i <= iloscPotrzebnegoProduktu; i++) {
            Gra.getInstance().getFarmaGracza().getStodola().dodajProdukt(ProduktPotrzebnyDoWyprodukowania);
        }

    }

}
