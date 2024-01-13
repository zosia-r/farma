package Gra;

public class WynikKoncowy implements Comparable<WynikKoncowy> {

    private String nazwa;
    private int monety;

    public WynikKoncowy()
    {
        nazwa = "FARMA";
        monety = 0;
    }

    public WynikKoncowy(String nazwa, int monety)
    {
        this.nazwa = nazwa;
        this.monety = monety;
    }

    public String getNazwa()
    {
        return nazwa;
    }

    public int getMonety()
    {
        return monety;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public void setMonety(int monety)
    {
        this.monety = monety;
    }

    public String toString()
    {
        return nazwa + " Wynik: " + monety;
    }

    @Override
    public int compareTo(WynikKoncowy other) {
        return Integer.compare(this.monety, other.monety);
    }


}
