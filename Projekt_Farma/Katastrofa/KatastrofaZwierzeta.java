package Katastrofa;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Zagroda;
import Strategia.Strategia;

public class KatastrofaZwierzeta implements Katastrofa {

    private final Kawalek_Ziemi [][] kawalkiZiemi;
    private final String komunikat;

    public KatastrofaZwierzeta(String komunikat)
    {
        kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
        this.komunikat = komunikat;
    }

    public void katastrofa()
    {
        boolean okienko=false;

        for(int i = 0; i< kawalkiZiemi.length; i++)
        {
            for(int j = 0; j< kawalkiZiemi[i].length; j++)
            {
                if(kawalkiZiemi[i][j] instanceof Zagroda)
                {
                    if(((Zagroda) kawalkiZiemi[i][j]).getZwierze() != null) {
                        ((Zagroda) kawalkiZiemi[i][j]).usunZwierze();
                        okienko=true;
                    }
                }
            }
        }
        if(okienko) {
            OkienkoKatastrofa okienkoKatastrofa = new OkienkoKatastrofa(komunikat);
        }
    }
}
