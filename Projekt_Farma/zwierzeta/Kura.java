package zwierzeta;

import Produkt.Produkt;

public class Kura extends Zwierze {


    public Kura() {
        super();
    }

    public void znies() {
        if (getGlodne() == false) {
            setProduktZ(new ProduktZw("jajka", false, 16, 4, 10, "ad"));
            setGlodne(true);
        }

    }
}
