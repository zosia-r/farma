package Gra;
import java.util.ArrayList;
import java.util.Comparator;

public class TablicaWynikow {
    private ArrayList<WynikKoncowy> wyniki;
    private WynikCompare_Monety Comparator1;

    public TablicaWynikow()
    {
        wyniki = new ArrayList<WynikKoncowy>();
        StworzKomparator();
    }
    static class WynikCompare_Monety implements Comparator<WynikKoncowy>
    {
        public int compare(WynikKoncowy w1, WynikKoncowy w2)
        {
            if(w1.getMonety() > w2.getMonety()) return -1;
            if(w1.getMonety() < w2.getMonety()) return 1;
            return 0;
        }
    }

    public void StworzKomparator()
    {
        Comparator1 = new WynikCompare_Monety();
    }

    public ArrayList<WynikKoncowy> getWyniki()
    {
        return wyniki;
    }

    public WynikCompare_Monety getComparator1()
    {
        return Comparator1;
    }



}
