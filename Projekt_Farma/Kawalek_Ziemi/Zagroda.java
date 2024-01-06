package Kawalek_Ziemi;
import Obserwator.Obserwator;
import Produkt.Produkt;
import Wyjatek.*;
import zwierzeta.*;
import Stodola.*;
public class Zagroda extends Kawalek_Ziemi {
    private Zwierze zwierze;
    private Produkt pszenica;
    private Produkt zyto;

    public Zagroda(int x, int y)
    {
        super(x, y);
        zwierze = null;
        pszenica = new Produkt("pszenica",true,20,5,10,"ad");
        zyto = new Produkt("żyto", true, 22, 5,10, "ad3");
    }
    public Zwierze getZwierze()
    {
        return zwierze;
    }
    public void setZwierze(Zwierze zwierze)
    {
        this.zwierze = zwierze;
    }
    public void KupZwierze(Zwierze zwierze) throws FieldNotEmptyException
    {
        if (this.zwierze != null)
            throw new FieldNotEmptyException();
        this.zwierze = zwierze;
        System.out.println("Kupiono zwierze do tej zagrody");
    }
    public void sprobujKupicZwierze(Zwierze zwierze)
    {
        try
        {
            KupZwierze(zwierze);
        }
        catch (FieldNotEmptyException e)
        {
            System.err.println(e.getMessage() + this.zwierze.getNazwa());
        }
    }
    public void usunZwierze()
    {
        if(zwierze!=null)
        {
            if(zwierze.getProduktZ()!=null) {
                ((ProduktZw) zwierze.getProduktZ()).usunietoZwierze();
            }
            zwierze = null;
        }
        else {
            System.out.println("Na tym polu nie ma zwierzęcia");
        }
    }
    public void nakarm()
    {
        if(zwierze!=null && zwierze.getGlodne()==true) {
            if(Stodola.sprawdzDostepnosc("pszenica")!=0) {
                Stodola.usunProdukt(pszenica);
                zwierze.setGlodne(false);
            } else {
                if(Stodola.sprawdzDostepnosc("zyto")!=0) {
                    Stodola.usunProdukt(zyto);
                    zwierze.setGlodne(false);
                } else {
                    System.out.println("Nie masz paszy do nakarmienia zwierzecia");
                }
            }
    }
    }
    public void zbierzProdukt()
    {
        if(zwierze != null) {
            if(zwierze.getProduktZ() != null && zwierze.getProduktZ().getGotoweDoZebrania()) {
                zwierze.getProduktZ().zbierz();
                zwierze.setProduktZ(null);
            } else {
                System.out.println("Tego produktu nie można jeszcze zebrać");
            }
        }
    }
    
    //podmiot
    public void powiadomObserwatorow()
    {
        for(int i=0;i<getObserwatorzy().size();i++)
        {
            Obserwator obs = (Obserwator) getObserwatorzy().get(i);
            
            if (zwierze == null) {
            	obs.aktualizacja("",false,0,null);
            }
            else obs.aktualizacja(zwierze.getProduktZ().getNazwa(),zwierze.getProduktZ().getGotoweDoZebrania(),zwierze.getProduktZ().getCzasProdukcji(),zwierze.getProduktZ().getIkonka());
        }
    }
}
