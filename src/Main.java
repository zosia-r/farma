
import Gra.Gra;
import Kawalek_Ziemi.Pole_Uprawne;
import Produkt.Produkt;
import Pole.*;
import GUI.*;

public class Main {
    public static void main(String[] args)
    {
        // zeby timer dzialal poprawnie (po wcisnieciu START) nie moze byc wywolywane Gra.getInstance() w mainie,
        // wiec finalnie bedzie tu tylko Start start = new Start();
        
        /* Gra.getInstance();

        Gra.getInstance().getFarmaGracza().getStodola().wyswietlProdukty();

        Pole_Uprawne p = new Pole_Uprawne(1, 2);
        Owoc o = new Owoc("jabłko", true, 2, 1,1, "a", true, true);
        p.zasadz(o);
        p.zasadz(o); */

        ////
        Start start = new Start();

    }
}
