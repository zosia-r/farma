package Gra;
import Farma.Farma;

public class Gra
{
    private int liczbaMonet;   // 200 na początku gry
    private int pozostałyCzas; // w sekundach
    private Farma farmaGracza; // farma gracza
    public Gra()
    {
        liczbaMonet=200;
        pozostałyCzas=300;
    }
    public Gra(int liczbaMonet, int pozostałyCzas, Farma farmaGracza)
    {
        this.liczbaMonet=liczbaMonet;
        this.pozostałyCzas=pozostałyCzas;
        this.farmaGracza=farmaGracza;
    }
    public int getLiczbaMonet()
    {
        return liczbaMonet;
    }
    public int getPozostałyCzas()
    {
        return pozostałyCzas;
    }
    public Farma getFarmaGracza()
    {
        return farmaGracza;
    }
    public void setLiczbaMonet(int liczbaMonet)
    {
        this.liczbaMonet=liczbaMonet;
    }
    public void setPozostałyCzas(int pozostałyCzas)
    {
        this.pozostałyCzas=pozostałyCzas;
    }
    public void setFarmaGracza(Farma farmaGracza)
    {
        this.farmaGracza=farmaGracza;
    }
    public void getStan()
    {
        System.out.println("Obecna liczba monet: "+getLiczbaMonet());
        System.out.println("Pozostały czas gry: "+getPozostałyCzas()+" sekund");

    }
    public void rozpocznijGre()
    {

    }
    public void odejmijMonety(int ilośćMonet) {
        if (ilośćMonet > 0 && ilośćMonet <= liczbaMonet) {
            liczbaMonet -= ilośćMonet;
        } else {
            System.out.println("Nieprawidłowa ilość monet do odjęcia.");
        }
    }

    public void odejmijCzas(int ilośćSekund) {
        if (ilośćSekund > 0 && ilośćSekund <= pozostałyCzas) {
            pozostałyCzas -= ilośćSekund;
        } else {
            System.out.println("Nieprawidłowa ilość czasu do odjęcia.");
        }
    }

}
