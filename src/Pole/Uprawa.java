package Pole;
import java.util.Timer;
import java.util.TimerTask;
import Produkt.Produkt;

public abstract class Uprawa extends Produkt
{

    public Uprawa(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int cenaZakupu, int czasProdukcji, String adresIkonki)
    {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, cenaZakupu, czasProdukcji, adresIkonki);
        uruchomLiczniki();

    }

    public void przerwijWzrost()
    {
        setGotoweDoZebrania(false);
        getWzrostTimer().cancel();
        getOdliczanieTimer().cancel();
    }

}


