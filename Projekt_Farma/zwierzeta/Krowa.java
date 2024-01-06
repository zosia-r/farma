package zwierzeta;

import Produkt.Produkt;

public class Krowa extends Zwierze {
	
    public Krowa()
    {
        super();
        this.setAdresIkonkigotowe("grafika/krowyGotowe.png");
        this.setAdresIkonkiJedzace("grafika/krowyJedzace.png");
        this.setAdresIkonkiGlodne("grafika/krowy.png");
    }

    public void dojenie()
    {
        if (getGlodne() == false)
        {
            setProduktZ(new ProduktZw("mleko", false, 15, 3, 10, "grafika/mleko.png"));
            setGlodne(true);
        }

    }



}
