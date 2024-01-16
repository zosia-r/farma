package Stodola;

import Produkt.Produkt;
import Gra.Gra;

import java.util.*;

public class Stodola {

    static private LinkedHashMap<Produkt, Integer> produkty = new LinkedHashMap<>();

    public Stodola()
    {
        //rośliny paszowe
        produkty.put(new Produkt("pszenica", false, 7, 2, 13, 10, "grafika/pszenica.png"),0);
        produkty.put(new Produkt("żyto", false, 8, 2,15,10, "grafika/zyto.png"), 0);
        //owoce
        produkty.put(new Produkt("jabłka", false, 10, 4, 20,15,"grafika/jablko.png"),0);
        produkty.put(new Produkt ("gruszki", false, 10,4,  20, 15, "grafika/gruszka.png"), 0);
        produkty.put(new Produkt("winogrona", false, 15 ,5,  20,15, "grafika/winogrono.png"), 0);
        //odzwierzęce
        produkty.put(new Produkt("mleko", false, 15, 0, 20,10, "grafika/mleko.png"), 0);
        produkty.put(new Produkt("jajka", false, 16, 0, 18,10, "grafika/jajko.png"), 0);
        produkty.put(new Produkt("mięso", false, 20, 0, 30,10,"grafika/mieso.png"), 0);
        //przetwory
        produkty.put(new Produkt("chleb pszenny", false, 60, 0, 62, 10, "grafika/chlebPszenny.png"), 0);
        produkty.put(new Produkt("chleb żytni", false, 65, 0, 67, 10, "grafika/chlebZytni.png"), 0);
        produkty.put(new Produkt("dżem jabłkowy", false, 45, 0, 47,8, "grafika/dzemJablkowy.png"), 0);
        produkty.put(new Produkt("dżem gruszkowy", false, 45, 0, 47,8, "grafika/dzemGruszkowy.png"), 0);
        produkty.put(new Produkt("sok jabłkowy", false, 20, 0, 22,9, "grafika/sokJablkowy.png"), 0);
        produkty.put(new Produkt("sok gruszkowy", false, 20, 0, 22,9, "grafika/sokGruszkowy.png"), 0);
        produkty.put(new Produkt("masło", false, 30, 0, 32,10, "grafika/maslo.png"), 0);
        produkty.put(new Produkt("szynka", false, 70, 0, 72,15, "grafika/szynka.png"), 0);
        produkty.put(new Produkt("kiełbasa", false, 35, 0, 37,10, "grafika/kielbasa.png"), 0);

    }

    public LinkedHashMap<Produkt, Integer> getProdukty()
    {
        return produkty;
    }
    public void dodajProdukt(Produkt p, int ile)
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            if(p.getNazwa().equals(entry.getKey().getNazwa()))
            {
                entry.setValue(entry.getValue()+ile);
                break;
            }
        }
    }
    public void usunProdukt(Produkt p)
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

    public void kup (String nazwa, int ile)
    {
        Set<Map.Entry<Produkt,Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt,Integer>> iteratorProduktow = entries.iterator();
        while(iteratorProduktow.hasNext())
        {
            Map.Entry<Produkt,Integer> entry = iteratorProduktow.next();
            if(nazwa.equals(entry.getKey().getNazwa()))
            {
                entry.setValue(entry.getValue() + ile);
                dodajProdukt(getProdukt(nazwa), ile);
                Gra.getInstance().odejmijMonety(ile*getCenaZakupuProduktu(nazwa));
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

    public int getCenaZakupuProduktu(String nazwa)
    {
        for (Produkt produkt : produkty.keySet())
        {
            if (produkt.getNazwa().equals(nazwa))
                return produkt.getCenaZakupu();
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
    
    public void usunWszystkieProdukty() {
        Set<Map.Entry<Produkt, Integer>> entries = produkty.entrySet();
        Iterator<Map.Entry<Produkt, Integer>> iteratorProduktow = entries.iterator();
        while (iteratorProduktow.hasNext()) {
            Map.Entry<Produkt, Integer> entry = iteratorProduktow.next();
            entry.setValue(0);
        }
    }

}
