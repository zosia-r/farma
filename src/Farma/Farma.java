package Farma;

import Kawalek_Ziemi.Kawalek_Ziemi;
import Stodola.Stodola;

public class Farma
{
    private String nazwaFarmy;  // wyświetlana nazwa farmy gracza
    private int wymiar;   // wymiar farmy, długość boku
    private Kawalek_Ziemi[][] kawalki_ziemi; // tablica dwuwymiarowa o bokach długości wymiar x wymiar
    private Stodola stodola;
    public Farma()
    {
        nazwaFarmy="FARMA";
        wymiar = 5;
        kawalki_ziemi = new Kawalek_Ziemi[wymiar][wymiar];
        stodola = new Stodola();
    }

    public Stodola getStodola() { return stodola; }
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
    public void setStodola(Stodola stodola) { this.stodola = stodola; }
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
