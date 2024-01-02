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
        zwierze = null;
    }

    public void nakarm()
    {
        zwierze.setGlodne(false);
    }

    public void zbierzProdukt()
    {
        zwierze.getProduktZ().zbierz();
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
