package Kawalek_Ziemi;
import Obserwator.Obserwator;
import przetwornia.Przetwor;
import Wyjatek.*;

public class Przetwornia extends Kawalek_Ziemi{
    private Przetwor przetwor;
    public Przetwornia(int x, int y)
    {
        super(x, y);
        przetwor=null;
    }

    public void rozpocznijProdukcjePrzetworu(Przetwor przetwor)
    {
        try
        {
            zacznijProdukcjePrzetworu(przetwor);
        }
        catch (FieldNotEmptyException e)
        {
            System.err.println(e.getMessage() + this.przetwor.getNazwa());
        }
    }
    public void zacznijProdukcjePrzetworu(Przetwor przetwor) throws FieldNotEmptyException
    {
        if (this.przetwor != null)
            throw new FieldNotEmptyException();
        this.przetwor = przetwor;
        System.out.println("rozpoczeto produkcje przetworu :D");

    }
    public void zbierzPrzetwor()
    {
        if((przetwor!=null) && (przetwor.getGotoweDoZebrania()))
        {
            przetwor.zbierz();
            przetwor=null;
        }
        else
        {
            //zrobic tak zeby opcja zbierz byla zablkowana(nie mozna jej klinac)
            System.out.println("Tego przetworu nie mozna jeszcze zebrac");
        }
    }
    public void przerwijProdukcjePrzetworu()
    {
        if(przetwor!=null)
        {
            przetwor.przerwijProdukcjePrzet();
            przetwor=null;
            System.out.println("Przerwano produkcje przetworu");
        }
        else
        {
            //opcja przerwijprodukcjeprzetworu ma byc zablokowana
            System.out.println("Przetwornia jest pusta , nie da sie przerwac produkcji");
        }
    }
    public Przetwor get_przetwor()
    {
        return przetwor;
    }
    public void set_przetwor(Przetwor przetwor)
    {
        this.przetwor=przetwor;
    }
    //-----------------------------------------------------------------------------------------

    //podmiot
    public void powiadomObserwatorow()
    {
        for(int i=0;i<getObserwatorzy().size();i++)
        {
            Obserwator obs = (Obserwator) getObserwatorzy().get(i);
            
            if (przetwor == null) {
            	obs.aktualizacja("",false,0,null);
            }
            else obs.aktualizacja(przetwor.getNazwa(),przetwor.getGotoweDoZebrania(),przetwor.getCzasProdukcji(),przetwor.getIkonka());
        }
    }



}

