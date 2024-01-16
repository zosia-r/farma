package zwierzeta;

import java.util.Timer;
import java.util.TimerTask;
import Produkt.Produkt;
public class ProduktZw extends Produkt {

    public ProduktZw(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int cenaZakupu, int czasProdukcji, String adresIkonki)
    {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, cenaZakupu, czasProdukcji, adresIkonki);
        uruchomLiczniki();
    }

    public void usunietoZwierze()
    {
        setGotoweDoZebrania(false);
        getWzrostTimer().cancel();
        getOdliczanieTimer().cancel();
    }
}
