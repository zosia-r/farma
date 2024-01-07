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
        produkty.put(new Produkt("pszenica",false,20,5,10,"grafika/pszenica.png"),0);
        produkty.put(new Produkt("żyto", false, 22, 5,10, "grafika/zyto.png"), 0);
        //owoce
        produkty.put(new Produkt("jabłka",false,10,2, 15,"grafika/jablko.png"),0);
        produkty.put(new Produkt ("gruszki", false, 12,2,  15, "grafika/gruszka.png"), 0);
        produkty.put(new Produkt("winogrona", false, 15,3,  15, "grafika/winogrono.png"), 0);
        //odzwierzęce
        produkty.put(new Produkt("mleko", false, 15, 3, 10, "grafika/mleko.png"), 0);
        produkty.put(new Produkt("jajka", false, 16, 4, 10, "grafika/jajko.png"), 0);
        produkty.put(new Produkt("mięso", false, 25, 6, 10,"grafika/mieso.png"), 0);
        //przetwory
        produkty.put(new Produkt("chleb pszenny", false, 44, 11, 10, "grafika/chlebPszenny.png"), 0);
        produkty.put(new Produkt("chleb żytni", false, 45, 11, 10, "grafika/chlebZytni.png"), 0);
        produkty.put(new Produkt("dżem jabłkowy", false, 37, 9, 8, "grafika/dzemJablkowy.png"), 0);
        produkty.put(new Produkt("dżem gruszkowy", false, 38, 9, 8, "grafika/dzemGruszkowy.png"), 0);
        produkty.put(new Produkt("sok jabłkowy", false, 35, 8, 9, "grafika/sokJablkowy.png"), 0);
        produkty.put(new Produkt("sok gruszkowy", false, 36, 9, 9, "grafika/sokGruszkowy.png"), 0);
        produkty.put(new Produkt("masło", false, 40, 10, 10, "grafika/maslo.png"), 0);
        produkty.put(new Produkt("szynka", false, 55, 13, 12, "grafika/szynka.png"), 0);
        produkty.put(new Produkt("kiełbasa", false, 56, 14, 12, "grafika/kielbasa.png"), 0);

    }

    public Map<Produkt, Integer> getProdukty()
    {
        return produkty;
    }
    public void dodajProdukt(Produkt p)
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
    public static void usunProdukt(Produkt p)
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            if(p.getNazwa().equals(entry.getKey().getNazwa()) && entry.getValue() > 0)
            {
                entry.setValue(entry.getValue() - 1);
                break;
            }
        }
    }

    public void sprzedaj (String nazwa, int ile)
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            if(nazwa.equals(entry.getKey().getNazwa()))
            {
                if (entry.getValue() - ile > 0)
                    entry.setValue(entry.getValue() - ile);
                else
                    entry.setValue(0);
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

    public static int sprawdzDostepnosc(String nazwa)
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

    public int getCenaProduktu(String nazwa)
    {
        for (Produkt produkt : produkty.keySet())
        {
            if (produkt.getNazwa().equals(nazwa))
                return produkt.getCena();
        }
        return 0;
    }

    public int getWycena ()
    {
        Set<Map.Entry<Produkt, Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt, Integer>> iteratorProduktow = entries.iterator();

        int suma = 0;
        while (iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt, Integer> entry = iteratorProduktow.next();
            suma += entry.getKey().getCena()*entry.getValue();
        }
        return suma;
    }

    public int getCenaWytworzeniaProduktu(String nazwa)
    {
        for (Produkt produkt : produkty.keySet())
        {
            if (produkt.getNazwa().equals(nazwa))
                return produkt.getCenaWytworzenia();
        }
        return 0;
    }

    public Produkt getProdukt(String nazwa)
    {
        for (Produkt produkt : produkty.keySet())
        {
            if (produkt.getNazwa().equals(nazwa))
                return produkt;
        }
        return null;
    }

}
