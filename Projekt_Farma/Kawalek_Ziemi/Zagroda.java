package Kawalek_Ziemi;

import Obserwator.Obserwator;
import Wyjatek.*;
import zwierzeta.*;

public class Zagroda extends Kawalek_Ziemi {

    private Zwierze zwierze;


    public Zagroda(int x, int y, Zwierze zwierze)
    {
        super(x, y);
        zwierze = null;
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

            zwierze.setGlodne(false);
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
            obs.aktualizacja(zwierze.getProduktZ().getNazwa(),zwierze.getProduktZ().getGotoweDoZebrania(),zwierze.getProduktZ().getCzasProdukcji(),zwierze.getProduktZ().getIkonka());
        }
    }
}
