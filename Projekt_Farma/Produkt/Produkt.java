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
    private int cenaZakupu;
    private int czasProdukcji;
    private File ikonka;
    private Timer odliczanieTimer;
    private Timer wzrostTimer;
    private long pozostalyCzas ;
    private String adresIkonki;

    public Produkt(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int cenaZakupu, int czasProdukcji, String adresIkonki)
    {
        this.nazwa = nazwa;
        this.gotoweDoZebrania = gotoweDoZebrania;
        this.cena = cena;
        this.cenaWytworzenia = cenaWytworzenia;
        this.cenaZakupu = cenaZakupu;
        this.czasProdukcji = czasProdukcji;
        this.adresIkonki = adresIkonki;

        ikonka = new File(adresIkonki);
    }

    public String getNazwa() { return nazwa; }
    public boolean getGotoweDoZebrania() { return gotoweDoZebrania; }
    public int getCena() { return cena; }
    public int getCenaWytworzenia() {return cenaWytworzenia; }
    public int getCenaZakupu()
    {
        return cenaZakupu;
    }
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
            Gra.getInstance().getFarmaGracza().getStodola().dodajProdukt(this, 1);
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
    
    public String zCzegoWytworzyc() {
    	if (nazwa.equals("mleko") || nazwa.equals("jajka") || nazwa.equals("mięso")) {
    		return "pszenica / żyto";
    	} else if (nazwa.equals("chleb pszenny")) {
    		return "jajko i pszenica";
    	}else if (nazwa.equals("chleb żytni")) {
    		return "jajko i żyto";
    	} else if (nazwa.equals("dżem jabłkowy")) {
    		return "2 jabłka";
    	} else if (nazwa.equals("dżem gruszkowy")) {
    		return "2 gruszki";
    	} else if (nazwa.equals("sok jabłkowy")) {
    		return "jabłko";
    	} else if (nazwa.equals("sok gruszkowy")) {
    		return "gruszka";
    	} else if (nazwa.equals("masło")) {
    		return "mleko";
    	} else if (nazwa.equals("szynka")) {
    		return "2 mięsa";
    	} else if (nazwa.equals("kiełbasa")) {
    		return "mięso";
    	} else {
    		return (getCenaWytworzenia() + "");
    	}
    			
    }

}
