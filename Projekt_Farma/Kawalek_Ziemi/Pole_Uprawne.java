package Kawalek_Ziemi;
import Pole.Uprawa;
import Obserwator.Obserwator;
import Wyjatek.*;

public class Pole_Uprawne extends Kawalek_Ziemi
{
    private Uprawa uprawa;

    public Pole_Uprawne(int x, int y, Uprawa uprawa)
    {
        super(x, y);
        uprawa=null;
    }

    public void zasadz(Uprawa uprawa)
    {
        try
        {
            zasadzUprawe(uprawa);
        }
        catch (FieldNotEmptyException e)
        {
            System.err.println(e.getMessage() + this.uprawa.getNazwa());
        }
    }
    
    public void zasadzUprawe(Uprawa uprawa) throws FieldNotEmptyException
    {
        if (this.uprawa != null)
            throw new FieldNotEmptyException();
        this.uprawa = uprawa;
        System.out.println("zasadzono uprawę :D");

    }

    public void zbierzUprawe()
    {
        if((uprawa!=null) && (uprawa.getGotoweDoZebrania()))
        {
            uprawa.zbierz();
            uprawa=null;
        }
        else
        {
            //zrobic tak zeby opcja zbierz byla zablkowana(nie mozna jej klinac)
            System.out.println("Tej uprawy nie mozna jeszcze zebrac");
        }
    }

    public void przrwijWzrostUprawy()
    {
        if(uprawa!=null)
        {
            uprawa.przerwijWzrost();
            uprawa=null;
            System.out.println("Przerwano wzrost uprawy");
        }
        else
        {
            //opcja przerwijwzrost ma byc zablkowana
            System.out.println("Pole uprawne jest puste , nie da sie przerwac wzrostu");
        }
    }

    public Uprawa get_uprawa()
    {
        return uprawa;
    }
    public void set_uprawa(Uprawa uprawa)
    {
        this.uprawa=uprawa;
    }

    //podmiot
    public void powiadomObserwatorow()
    {
        for(int i=0;i<getObserwatorzy().size();i++)
        {
            Obserwator obs = (Obserwator) getObserwatorzy().get(i);
            obs.aktualizacja(uprawa.getNazwa(),uprawa.getGotoweDoZebrania(),uprawa.getCzasProdukcji(),uprawa.getIkonka());
        }
    }
    
}
