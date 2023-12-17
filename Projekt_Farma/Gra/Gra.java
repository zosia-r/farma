package Gra;
import Farma.Farma;

public class Gra
{
    private int liczbaMonet;   // 200 na początku gry
    private int pozostalyCzas; // w sekundach
    private Farma farmaGracza; // farma gracza
    public Gra()
    {
        this.liczbaMonet=200;
        this.pozostalyCzas=300;
        this.farmaGracza = new Farma();
    }
    public Gra(int liczbaMonet, int pozostalyCzas, Farma farmaGracza)
    {
        this.liczbaMonet=liczbaMonet;
        this.pozostalyCzas=pozostalyCzas;
        this.farmaGracza=farmaGracza;
    }
    public int getLiczbaMonet()
    {
        return liczbaMonet;
    }
    public int getPozostalyCzas()
    {
        return pozostalyCzas;
    }
    public Farma getFarmaGracza()
    {
        return farmaGracza;
    }
    public void setLiczbaMonet(int liczbaMonet)
    {
        this.liczbaMonet=liczbaMonet;
    }
    public void setPozostalyCzas(int pozostalyCzas)
    {
        this.pozostalyCzas=pozostalyCzas;
    }
    public void setFarmaGracza(Farma farmaGracza)
    {
        this.farmaGracza=farmaGracza;
    }
    public void getStan()
    {
        System.out.println("Obecna liczba monet: "+getLiczbaMonet());
        System.out.println("Pozostały czas gry: "+getPozostalyCzas()+" sekund");

    }
    public void rozpocznijGre()
    {

    }
    public void odejmijMonety(int iloscMonet) {
        if (iloscMonet > 0 && iloscMonet <= liczbaMonet) {
            liczbaMonet -= iloscMonet;
        } else {
            System.out.println("Nieprawidłowa ilość monet do odjęcia.");
        }
    }

    public void odejmijCzas(int iloscSekund) {
        if (iloscSekund > 0 && iloscSekund <= pozostalyCzas) {
            pozostalyCzas -= iloscSekund;
        } else {
            System.out.println("Nieprawidłowa ilość czasu do odjęcia.");
        }
    }

}
