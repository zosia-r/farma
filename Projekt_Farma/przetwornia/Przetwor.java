package Przetwornia;
import Produkt.Produkt;
import java.util.Timer;
import java.util.TimerTask;
import Stodola.Stodola;
import Gra.Gra;

public class Przetwor extends Produkt {
    private Produkt ProduktPotrzebnyDoWyprodukowania;
    private int iloscPotrzebnegoProduktu;
    private Timer odliczanieTimer;
    private Timer wzrostTimer;

    public Przetwor(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int czasProdukcji, String adresIkonki, Produkt ProduktPotrzebnyDoWyprodukowania, int iloscPotrzebnegoProduktu) {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, czasProdukcji, adresIkonki);
        int l = Stodola.sprawdzDostepnosc(ProduktPotrzebnyDoWyprodukowania.getNazwa());
        if (l >= iloscPotrzebnegoProduktu) {
            uruchomLicznikCzasu();
            uruchomLicznikOdliczania();
            for (int i = 0; i <= iloscPotrzebnegoProduktu; i++) {
                Stodola.usunProdukt(ProduktPotrzebnyDoWyprodukowania);
            }
        }
    }
    public void przerwijProdukcjePrzet() {
        setGotoweDoZebrania(false);
        wzrostTimer.cancel();
        odliczanieTimer.cancel();
        for (int i = 0; i <= iloscPotrzebnegoProduktu; i++) {
            Gra.getInstance().getFarmaGracza().getStodola().dodajProdukt(ProduktPotrzebnyDoWyprodukowania);
        }

    }

    private void uruchomLicznikCzasu() {
        wzrostTimer = new Timer();
        wzrostTimer.schedule(new TimerTask() {
            public void run() {
                setGotoweDoZebrania(true);
                System.out.println("Przetwor jest gotowy do zebrania");
                wzrostTimer.cancel();
            }
        }, getCzasProdukcji() * 1000);
    }

    private void uruchomLicznikOdliczania() {
        odliczanieTimer = new Timer();
        odliczanieTimer.scheduleAtFixedRate(new TimerTask() {
            int czasDoZebrania = getCzasProdukcji();

            public void run() {
                czasDoZebrania--;
                System.out.println("Pozostały czas do końca produkcji: " + czasDoZebrania + " sekund");
                if (czasDoZebrania <= 0) {
                    odliczanieTimer.cancel();
                }
            }
        }, 1000, 1000);
    }
}
