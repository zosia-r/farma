
import Kawalek_Ziemi.Pole_Uprawne;
import Stodola.Stodola;
import Produkt.Produkt;
import Pole.*;
import GUI.*;

public class Main {
    public static void main(String[] args)
    {
        //tworzenie stodoly i dodawanie do niej produktow
        Stodola.getInstance();
        Stodola.getInstance().wyswietlProdukty();

        Produkt produkt1 = new Produkt("pszenica",false,20,5, 30,"adres");
        produkt1.zbierz();
        produkt1.setGotoweDoZebrania(true);
        produkt1.zbierz();

        Produkt produkt2 = new Produkt("jabłka",true,10,2, 15,"adres");
        produkt2.zbierz();

        Stodola.getInstance().wyswietlProdukty();

        Pole_Uprawne p = new Pole_Uprawne(1, 2);
        Owoc o = new Owoc("jabłko", true, 2, 1,1, "a", true, true);
        p.zasadz(o);
        p.zasadz(o);


        ////
        Start start = new Start();

    }
}
