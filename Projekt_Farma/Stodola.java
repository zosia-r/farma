package Stodola;

import Produkt.Produkt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class Stodola {

    static private Map<Produkt, Integer> produkty = new HashMap<>();

    public Stodola()
    {
        produkty.put(new Produkt("Pszenica",true,20,30,"ad1"),0);
        produkty.put(new Produkt("Jablko",true,10,15,"ad2"),0);
    }

    public static void dodajProdukt(Produkt p)
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            if(p.getNazwa().equals(entry.getKey().getNazwa()))
            {
                entry.setValue(entry.getValue()+1);
                break;
            }
        }
    }

    public void wyswietlProdukty()
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            System.out.print(entry.getKey().getNazwa() + ": " + entry.getValue() + "\n");
        }
    }

    public int sprawdzDostepnosc(String nazwa)
    {
        Set<Map.Entry<Produkt, Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt, Integer>> iteratorProduktow = entries.iterator();
        while (iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt, Integer> entry = iteratorProduktow.next();
            if (nazwa.equals(entry.getKey().getNazwa()))
                return entry.getValue();
        }
        return 0;
    }

}
