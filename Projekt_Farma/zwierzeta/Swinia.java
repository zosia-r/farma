package zwierzeta;

import Produkt.Produkt;

public class Swinia extends Zwierze {

    public Swinia()
    {
        super();
        this.setAdresIkonkigotowe("grafika/swinkiGotowe.png");
        this.setAdresIkonkiJedzace("grafika/swinkiJedzace.png");
        this.setAdresIkonkiGlodne("grafika/swinki.png");
    }


    public void dajMieso()
    {
        if(getGlodne()==false)
        {
            setProduktZ(new ProduktZw("mięso", false, 25, 6, 10,"grafika/mieso.png"));
            setGlodne(true);



        }
    }



}
