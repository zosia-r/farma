package zwierzeta;

import Produkt.Produkt;

public class Krowa extends Zwierze {

    public Krowa()
    {
        super();
    }

    public void dojenie()
    {
        if (getGlodne() == false)
        {
            setProduktZ(new ProduktZw("mleko", false, 15, 3, 10, "ad"));
            setGlodne(true);
        }

    }



}
