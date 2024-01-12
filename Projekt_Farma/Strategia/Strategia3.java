package Strategia;

import GUI.OkienkoKatastrofa;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Zagroda;

public class Strategia3 implements Strategia{

    private final Kawalek_Ziemi [][] kawalki_ziemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
    private final String komunikat = "Twoje zwierzęta są chore, nie mogą teraz nic produkować";

    public void katastrofa()
    {
        boolean okienko=false;
        for(int i = 0;i<kawalki_ziemi.length;i++)
        {
            for(int j= 0;j<kawalki_ziemi[i].length;j++)
            {
                if(kawalki_ziemi[i][j] instanceof Zagroda)
                {
                    if(((Zagroda) kawalki_ziemi[i][j]).getZwierze() != null) {
                        ((Zagroda) kawalki_ziemi[i][j]).usunZwierze();
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
