package Katastrofa;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Przetwornia;


public class KatastrofaPrzetwornie implements Katastrofa
{
    private final Kawalek_Ziemi [][] kawalkiZiemi;
    private final String komunikat;
    public KatastrofaPrzetwornie(String komunikat)
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
                if(kawalkiZiemi[i][j] instanceof Przetwornia)
                {
                    if(((Przetwornia) kawalkiZiemi[i][j]).get_przetwor() != null)
                    {
                        ((Przetwornia) kawalkiZiemi[i][j]).przerwijProdukcjePrzetworu();
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
