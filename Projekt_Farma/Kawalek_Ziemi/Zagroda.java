package Kawalek_Ziemi;
import Gra.Gra;
import Obserwator.Obserwator;
import Produkt.Produkt;
import Wyjatek.*;
import zwierzeta.*;
public class Zagroda extends Kawalek_Ziemi {
    private Zwierze zwierze;
    private Produkt pszenica;
    private Produkt zyto;

    public Zagroda(int x, int y)
    {
        super(x, y);
        zwierze = null;
        pszenica = new Produkt("pszenica", false, 7, 2, 10, "grafika/pszenica.png");
        zyto = new Produkt("żyto", false, 8, 2,10, "grafika/zyto.png");
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
        powiadomObserwatorow();
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
        powiadomObserwatorow();
    }
    
    public void rozpocznijProdukcje() {
    	if (zwierze!=null) {
    		if (zwierze instanceof Kura) {
    			((Kura) zwierze).znies();
    		} else if (zwierze instanceof Krowa) {
    			((Krowa) zwierze).dojenie();
    		} else if (zwierze instanceof Swinia) {
    			((Swinia) zwierze).dajMieso();
    		}
    	}
        powiadomObserwatorow();
    }
    
    public void nakarmPszenica()
    {
        if(zwierze!=null && zwierze.getGlodne()==true) {
            if(Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("pszenica")!=0) {
                System.out.println("Nakarmiono");
                Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(pszenica);
                zwierze.setGlodne(false);
                rozpocznijProdukcje();
            } else {
                System.out.println("Nie masz pszenicy do nakarmienia zwierzecia");
            }
        }
        powiadomObserwatorow();
    }
    
    public void nakarmZyto()
    {
        if(zwierze!=null && zwierze.getGlodne()==true) {
            if(Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("żyto")!=0) {
            	Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(zyto);
                zwierze.setGlodne(false);
                rozpocznijProdukcje();
            } else {
                System.out.println("Nie masz zyta do nakarmienia zwierzecia");
            }
        }
        powiadomObserwatorow();
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
        powiadomObserwatorow();
    }
    
    //podmiot
    public void powiadomObserwatorow()
    {
        for(int i=0;i<getObserwatorzy().size();i++)
        {
            Obserwator obs = (Obserwator) getObserwatorzy().get(i);
            
            if (zwierze == null || zwierze.getProduktZ() == null) {
            	obs.aktualizacja("",false,0,null, zwierze, true);
            }
            else obs.aktualizacja(zwierze.getProduktZ().getNazwa(),zwierze.getProduktZ().getGotoweDoZebrania(),zwierze.getProduktZ().getCzasProdukcji(),zwierze.getProduktZ().getIkonka(), zwierze, true);
        }
    }
}
