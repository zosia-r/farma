package Produkt;

import java.io.File;
import Stodola.Stodola;

public class Produkt {

    private String nazwa;
    private boolean gotoweDoZebrania;
    private int cena;
    private int czasProdukcji;
    private File ikonka;

    public Produkt(String nazwa, boolean gotoweDoZebrania, int cena, int czasProdukcji, String adresIkonki)
    {
        this.nazwa=nazwa;
        this.gotoweDoZebrania=gotoweDoZebrania;
        this.cena=cena;
        this.czasProdukcji=czasProdukcji;
        ikonka = new File(adresIkonki);
    }

    public String getNazwa() { return nazwa; }
    public boolean getGotoweDoZebrania() { return gotoweDoZebrania; }
    public int getCena() { return cena; }
    public int getCzasProdukcji() { return czasProdukcji; }
    public File getIkonka() { return ikonka; }

    public void setNazwa(String nazwa) { this.nazwa=nazwa; }
    public void setGotoweDoZebrania(boolean gotoweDoZebrania) { this.gotoweDoZebrania=gotoweDoZebrania; }
    public void setCena(int cena) { this.cena=cena; }
    public void setCzasProdukcji(int czasProdukcji) { this.czasProdukcji=czasProdukcji; }
    public void setIkonka(String adresIkonki) { ikonka=new File(adresIkonki); }

    public void zbierz()
    {
        if(gotoweDoZebrania) {
            Stodola.dodajProdukt(this);
            //usuwanie z tablicy upraw/zwierzat/przetworow
        }
        else
            System.out.print("Produkt nie jest gotowy do zbioru\n");
    }

}