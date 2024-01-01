package Gra;
import Farma.Farma;

public class Gra
{
    private static Gra instance;
    private int liczbaMonet;   // 200 na początku gry
    private int pozostalyCzas; // w sekundach
    private Farma farmaGracza; // farma gracza
    private Gra()
    {
        this.liczbaMonet = 200;
        this.pozostalyCzas = 300;
        this.farmaGracza = new Farma();
    }
    public static Gra getInstance()
    {
        if (instance == null)
            instance = new Gra();
        return instance;
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
    public void setFarmaGracza(Farma farma)
    {
        this.farmaGracza = farma;
    }
    public void getStan()
    {
        System.out.println("Obecna liczba monet: "+getLiczbaMonet());
        System.out.println("Pozostały czas gry: "+getPozostalyCzas()+" sekund");

    }
    public void rozpocznijGre()
    {
        //zapytanie o nazwe farmy
        //podział na typy zagospodarowania
    }
    public void odejmijMonety(int iloscMonet) {
        if (iloscMonet > 0 && iloscMonet <= liczbaMonet) {
            liczbaMonet -= iloscMonet;
        } else {
            System.out.println("Nieprawidłowa ilość monet do odjęcia.");
        }
    }

}
