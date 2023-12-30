package Pole;
import java.util.Timer;
import java.util.TimerTask;
import Produkt.Produkt;

public abstract class Uprawa extends Produkt
{
    private Timer odliczanieTimer;
    private Timer wzrostTimer;

    public Uprawa(String nazwa, boolean gotoweDoZebrania, int cena, int cenaWytworzenia, int czasProdukcji, String adresIkonki)
    {
        super(nazwa, gotoweDoZebrania, cena, cenaWytworzenia, czasProdukcji, adresIkonki);
        uruchomLicznikCzasu();
        uruchomLicznikOdliczania();

    }

    public void przerwijWzrost()
    {
        setGotoweDoZebrania(false);
        wzrostTimer.cancel();
        odliczanieTimer.cancel();
    }

    private void uruchomLicznikCzasu()
    {
        wzrostTimer=new Timer();
        wzrostTimer.schedule(new TimerTask() {
            public void run() {
                setGotoweDoZebrania(true);
                System.out.println("Uprawa jest gotowa do zebrania");
                wzrostTimer.cancel();
            }
        },getCzasProdukcji()*1000);
    }

    private void uruchomLicznikOdliczania()
    {
        odliczanieTimer = new Timer();
        odliczanieTimer.scheduleAtFixedRate(new TimerTask()
        {
            int czasDoZebrania=getCzasProdukcji();
            public void run(){
                czasDoZebrania--;
                System.out.println("Pozostały czas do zebrania: "+czasDoZebrania+" sekund");
                if(czasDoZebrania<=0)
                {
                    odliczanieTimer.cancel();
                }
            }
        },1000,1000);


    }
}

