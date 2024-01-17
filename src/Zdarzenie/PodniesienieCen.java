package Zdarzenie;

import GUI.OkienkoZdarzenie;
import Gra.Gra;
import Produkt.Produkt;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PodniesienieCen implements Zdarzenie {

    private final String komunikat;

    public PodniesienieCen(String komunikat) {
        this.komunikat = komunikat;
    }
    public void zdarzenie() {
        Map<Produkt, Integer> produkty = Gra.getInstance().getFarmaGracza().getStodola().getProdukty();
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            entry.getKey().setCena(entry.getKey().getCena()*3/2);
        }
        new OkienkoZdarzenie(komunikat);
    }
}
