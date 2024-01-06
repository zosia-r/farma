package zwierzeta;

import Produkt.Produkt;

public class Kura extends Zwierze {
	
	

    public Kura() {
        super();
        this.setAdresIkonkigotowe("grafika/kuryGotowe.png");
        this.setAdresIkonkiJedzace("grafika/kuryJedzace.png");
        this.setAdresIkonkiGlodne("grafika/kury.png");
    }

    public void znies() {
        if (getGlodne() == false) {
            setProduktZ(new ProduktZw("jajka", false, 16, 4, 10, "grafika/jajko.png"));
            setGlodne(true);
        }

    }
}
