package Pole;

public class Owoc extends Uprawa {

    private boolean naSok;
    private boolean naDzem;

    public Owoc(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int cenaZakupu, int czasProdukcji, String adresIkonki, boolean naSok, boolean naDzem)
    {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, cenaZakupu, czasProdukcji, adresIkonki);
        this.naSok = naSok;
        this.naDzem = naDzem;
    }

    public boolean getNaSok() {
        return naSok;
    }

    public void setNaSok(boolean naSok) {
        this.naSok = naSok;
    }

    public boolean getNaDzem() {
        return naDzem;
    }

    public void setNaDzem(boolean naDzem) {
        this.naDzem = naDzem;
    }

}
