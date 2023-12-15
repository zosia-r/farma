package Farma;
import Gra.Gra;
public class Farma extends Gra
{
    private String nazwaFarmy;  // wyświetlana nazwa farmy gracza
    private int wymiar;   // wymiar farmy, długość boku
    private  int [][] kawałki_ziemi; // tablica dwuwymiarowa o bokach długości wymiar x wymiar
    public Farma()
    {
        nazwaFarmy="FARMA";
        wymiar=20;
        kawałki_ziemi = new int[wymiar][wymiar];
    }
    public Farma(String nazwaFarmy, int wymiar)
    {
        this.nazwaFarmy=nazwaFarmy;
        this.wymiar=wymiar;
        kawałki_ziemi=new int[wymiar][wymiar];
    }
    public String getNazwaFarmy()
    {
        return nazwaFarmy;
    }
    public int getWymiar()
    {
        return wymiar;
    }
    public int[][] getKawałki_ziemi()
    {
        return kawałki_ziemi;
    }
    public void setNazwaFarmy(String nazwaFarmy)
    {
        this.nazwaFarmy=nazwaFarmy;
    }
    public void setWymiar(int wymiar)
    {
        this.wymiar=wymiar;
    }
    public void setKawałki_ziemi(int[][] kawałki_ziemi)
    {
        this.kawałki_ziemi=kawałki_ziemi;
    }
    public void getStan()
    {
        super.getStan();
        System.out.println("Farma gracza nosi nazwę: "+getNazwaFarmy()+", jest ona wymiaru : "+getWymiar()+" x "+getWymiar());
    }
}
