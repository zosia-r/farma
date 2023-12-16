package Zboża;
import Pole.Uprawa;
public class Zboża extends Uprawa
{
    private String nazwa;
    private float szansanaZwiędnięcie;
    private int typ;
    public Zboża()
    {
        super(0,0,100,15);
        typ=655;
        nazwa="Pszenica";
        szansanaZwiędnięcie=20;
    }
    public Zboża(int x, int y,int czasWzrostu, int cenaRośliny, String nazwa, float szansanaZwiędnięcie,int typ)
    {
        super(x,y,czasWzrostu,cenaRośliny);
        this.nazwa=nazwa;
        this.szansanaZwiędnięcie=szansanaZwiędnięcie;
        this.typ=typ;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public float getSzansanaZwiędnięcie()
    {
        return szansanaZwiędnięcie;
    }
    public int getTyp()
    {
        return typ;
    }
    public void setNazwa(String nazwa)
    {
        this.nazwa=nazwa;
    }
    public void setSzansanaZwiędnięcie(float szansanaZwiędnięcie)
    {
        this.szansanaZwiędnięcie=szansanaZwiędnięcie;
    }
    public void setTyp(int typ)
    {
        this.typ=typ;
    }
    public void getStan()
    {
        super.getStan();
        System.out.println("Roślina nosi nazwę: "+getNazwa()+" ,jej typ to: "+getTyp()+" ,a jej szansa na zwiędnięcie wynosi: "+getSzansanaZwiędnięcie()+"%");
    }
}
