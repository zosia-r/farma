package Pole;

public class Rosliny_Paszowe extends Uprawa {

    private boolean czyPasza;

    public Rosliny_Paszowe(String nazwa, boolean gotoweDoZebrania, int cena, int czasProdukcji, String adresIkonki,boolean czyPasza)
    {
        super(nazwa, gotoweDoZebrania, cena, czasProdukcji, adresIkonki);
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