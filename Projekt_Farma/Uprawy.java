package Uprawy;
import Kawałek_Ziemi.Kawałek_Ziemi;
public abstract class Uprawy extends Kawałek_Ziemi
{
    private int czasWzrostu;
    private int cena_rośliny;

    public Uprawy(int x,int y,int czasWzrostu,int cena_rośliny)
    {
        super(x,y);
        this.czasWzrostu=czasWzrostu;
        this.cena_rośliny=cena_rośliny;
    }
    public int getCzasWzrostu()
    {
        return czasWzrostu;
    }
    public int getCena_rośliny()
    {
        return cena_rośliny;
    }
    public void setCzasWzrostu(int czasWzrostu)
    {
        this.czasWzrostu=czasWzrostu;
    }
    public void setCena_rośliny(int cenaRośliny)
    {
        this.cena_rośliny=cenaRośliny;
    }
    public void getStan()
    {
        super.getStan();
        System.out.println("Roślina będzie rosnąć: "+getCzasWzrostu()+" sekund , jej cena wynosi: "+getCena_rośliny());
    }

}
