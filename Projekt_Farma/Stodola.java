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
        //rośliny paszowe
        produkty.put(new Produkt("pszenica",true,20,10,"ad"),0);
        produkty.put(new Produkt("żyto", true, 22, 10, "ad3"), 0);
        //owoce
        produkty.put(new Produkt("jabłka",true,10,15,"ad"),0);
        produkty.put(new Produkt ("gruszki", true, 12, 15, "ad"), 0);
        produkty.put(new Produkt("winogrona", true, 15, 15, "ad"), 0);
        //odzwierzęce
        produkty.put(new Produkt("mleko", true, 15, 10, "ad"), 0);
        produkty.put(new Produkt("jajka", true, 16, 10, "ad"), 0);
        produkty.put(new Produkt("mięso", true, 25, 10,"ad"), 0);
        //przetwory
        produkty.put(new Produkt("chleb pszenny", true, 44, 10, "ad"), 0);
        produkty.put(new Produkt("chleb żytni", true, 45, 10, "ad"), 0);
        produkty.put(new Produkt("dżem jabłkowy", true, 37, 8, "ad"), 0);
        produkty.put(new Produkt("dżem gruszkowy", true, 38, 8, "ad"), 0);
        produkty.put(new Produkt("sok jabłkowy", true, 35, 9, "ad"), 0);
        produkty.put(new Produkt("sok gruszkowy", true, 36, 9, "ad"), 0);
        produkty.put(new Produkt("masło", true, 40, 10, "ad"), 0);
        produkty.put(new Produkt("szynka", true, 55, 12, "ad"), 0);
        produkty.put(new Produkt("kiełbasa", true, 56, 12, "ad"), 0);
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
