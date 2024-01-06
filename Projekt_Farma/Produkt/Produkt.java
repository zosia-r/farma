package Produkt;

import java.io.File;
import Stodola.Stodola;
import java.util.Timer;
import java.util.TimerTask;
import Gra.Gra;

public class Produkt {

    private String nazwa;
    private boolean gotoweDoZebrania;
    private int cena;
    private int cenaWytworzenia;
    private int czasProdukcji;
    private File ikonka;
    private Timer odliczanieTimer;
    private Timer wzrostTimer;
    private long pozostalyCzas ;
    private String adresIkonki;

    public Produkt(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int czasProdukcji, String adresIkonki)
    {
        this.nazwa = nazwa;
        this.gotoweDoZebrania = gotoweDoZebrania;
        this.cena = cena;
        this.cenaWytworzenia = cenaWytworzenia;
        this.czasProdukcji = czasProdukcji;

        this.adresIkonki = adresIkonki;

        ikonka = new File(adresIkonki);
    }

    public String getNazwa() { return nazwa; }
    public boolean getGotoweDoZebrania() { return gotoweDoZebrania; }
    public int getCena() { return cena; }
    public int getCenaWytworzenia() {return cenaWytworzenia; }
    public int getCzasProdukcji() { return czasProdukcji; }
    public String getAdresIkonki() { return adresIkonki; }
    public File getIkonka() { return ikonka; }
    public Timer getWzrostTimer()
    {
        return wzrostTimer;
    }
    public Timer getOdliczanieTimer()
    {
        return odliczanieTimer;
    }


    public void setNazwa(String nazwa) { this.nazwa=nazwa; }
    public void setGotoweDoZebrania(boolean gotoweDoZebrania) { this.gotoweDoZebrania=gotoweDoZebrania; }
    public void setCena(int cena) { this.cena=cena; }
    public void setCzasProdukcji(int czasProdukcji) { this.czasProdukcji=czasProdukcji; }
    public void setIkonka(String adresIkonki) { ikonka=new File(adresIkonki); }
    public void setPozostalyCzas(long pozostalyCzas) { this.pozostalyCzas=pozostalyCzas; }

    public void zbierz()
    {
        if(gotoweDoZebrania) {
            Gra.getInstance().getFarmaGracza().getStodola().dodajProdukt(this);
            //usuwanie z tablicy upraw/zwierzat/przetworow
        }
        else
            System.out.print("Produkt nie jest gotowy do zbioru\n");
    }
    public void uruchomLiczniki() {
        pozostalyCzas = czasProdukcji;
        wzrostTimer = new Timer();
        odliczanieTimer = new Timer();


        wzrostTimer.schedule(new TimerTask() {
            public void run() {
                setGotoweDoZebrania(true);
                pozostalyCzas = 0; // Produkt jest gotowy wiec pozostaly czas to 0
                System.out.println("Produkt jest gotowy do zebrania");
                wzrostTimer.cancel();
            }
        }, getCzasProdukcji() * 1000);

        odliczanieTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                pozostalyCzas--;
                if (pozostalyCzas <= 0) {
                    odliczanieTimer.cancel();
                }
            }
        }, 1000, 1000);
    }
    //metoda zwracajaca czas pozotsaly do zebrania produktu
    public long getPozostalyCzas() {
        if (odliczanieTimer != null) {
            return pozostalyCzas;
        } else {
            return czasProdukcji; //produkt nie zaczal sie jeszcze"wytwarzac" wiec czas pozostaly do zebrania to czas produkcji
        }
    }

}
