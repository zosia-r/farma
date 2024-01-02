package zwierzeta;

import Produkt.Produkt;

public class Swinia extends Zwierze {

    public Swinia()
    {
        super();
    }


    public void dajMieso()
    {
        if(getGlodne()==false)
        {
            setProduktZ(new Produkt("mięso", true, 25, 6, 10,"ad"));
            setGlodne(true);



        }
    }



}
