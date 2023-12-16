package Kawalek_Ziemi;
import Pole.Uprawa;

public abstract class Pole_Uprawne extends Kawalek_Ziemi
{
    private Uprawa uprawa;

    public Pole_Uprawne(int x, int y, Uprawa uprawa)
    {
        super(x, y);
        uprawa=null;
    }



    public void zasadzUprawe(Uprawa nowaUprawa)
    {
        if(uprawa==null)
        {
            uprawa=nowaUprawa;
            System.out.println("Zasadzono uprawę na polu uprawnym");
        }
        else
        {
            System.out.println("to pole jest juz zasiane");
        }
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
}
