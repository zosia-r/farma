package Farma;

import Kawalek_Ziemi.Kawalek_Ziemi;

public class Farma
{
    private String nazwaFarmy;  // wyświetlana nazwa farmy gracza
    private int wymiar;   // wymiar farmy, długość boku
    private Kawalek_Ziemi[][] kawalki_ziemi; // tablica dwuwymiarowa o bokach długości wymiar x wymiar
    public Farma()
    {
        nazwaFarmy="FARMA";
        wymiar=20;
        kawalki_ziemi = new Kawalek_Ziemi[wymiar][wymiar];
    }
    public Farma(String nazwaFarmy, int wymiar)
    {
        this.nazwaFarmy=nazwaFarmy;
        this.wymiar=wymiar;
        kawalki_ziemi=new Kawalek_Ziemi[wymiar][wymiar];
    }
    public String getNazwaFarmy()
    {
        return nazwaFarmy;
    }
    public int getWymiar()
    {
        return wymiar;
    }
    public Kawalek_Ziemi[][] getKawalki_ziemi()
    {
        return kawalki_ziemi;
    }
    public void setNazwaFarmy(String nazwaFarmy)
    {
        this.nazwaFarmy=nazwaFarmy;
    }
    public void setWymiar(int wymiar)
    {
        this.wymiar=wymiar;
    }
    public void setKawalki_ziemi(Kawalek_Ziemi[][] kawalki_ziemi)
    {
        this.kawalki_ziemi=kawalki_ziemi;
    }
    public void getStan()
    {
        System.out.println("Farma gracza nosi nazwę: "+getNazwaFarmy()+", jest ona wymiaru : "+getWymiar()+" x "+getWymiar());
    }
}
