package Katastrofa;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Pole_Uprawne;


public class KatastrofaUprawy implements Katastrofa
{
    private final Kawalek_Ziemi [][] kawalkiZiemi;
    private final String komunikat;
    public KatastrofaUprawy(String komunikat)
    {
        kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
        this.komunikat = komunikat;
    }


    public void katastrofa()
    {
        boolean okienko=false;

        for(int i = 0; i<kawalkiZiemi.length; i++)
        {
            for(int j= 0; j<kawalkiZiemi[i].length; j++)
            {
                if(kawalkiZiemi[i][j] instanceof Pole_Uprawne)
                {
                    if(((Pole_Uprawne) kawalkiZiemi[i][j]).get_uprawa() != null)
                    {
                        ((Pole_Uprawne) kawalkiZiemi[i][j]).przrwijWzrostUprawy();
                        okienko=true;
                    }
                }
            }
        }
        if(okienko)
        {
            OkienkoKatastrofa okienkoKatastrofa = new OkienkoKatastrofa(komunikat);
        }
    }
}
