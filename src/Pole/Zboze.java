package Pole;

public class Zboze extends Uprawa
{
    private boolean czyPasza;

    public Zboze(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int cenaZakupu, int czasProdukcji, String adresIkonki,boolean czyPasza)
    {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, cenaZakupu, czasProdukcji, adresIkonki);
        this.czyPasza = czyPasza;
    }

    public boolean getCzyPasza() {
        return czyPasza;
    }

    public void setCzyPasza(boolean czyPasza) {
        this.czyPasza = czyPasza;
    }

    public void wytworzPasze()
    {
        czyPasza = true;
    }
}
