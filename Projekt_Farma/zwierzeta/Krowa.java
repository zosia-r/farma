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
            setProduktZ(new Produkt("mleko", true, 15, 3, 10, "ad"));
            setGlodne(true);
        }

    }



}
