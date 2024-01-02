package zwierzeta;

import Produkt.Produkt;

public class Kura extends Zwierze {


    public Kura() {
        super();
    }

    public void znies() {
        if (getGlodne() == false) {
            setProduktZ(new Produkt("jajka", true, 16, 4, 10, "ad"));
            setGlodne(true);
        }

    }
}
