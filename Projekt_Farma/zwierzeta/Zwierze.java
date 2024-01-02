package zwierzeta;

import Produkt.Produkt;

public abstract class Zwierze{


    private String nazwa;
    private int cena;
    private boolean glodne;
    private File ikonka;
    private Produkt produktZ;

    public Zwierze()
    {
        glodne = true;
        produktZ = null;
    }




    public File getIkonka() {
        return ikonka;
    }




    public void setIkonka(File ikonka) {
        this.ikonka = ikonka;
    }




    public String getNazwa() {
        return nazwa;
    }



    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }



    public int getCena() {
        return cena;
    }


    public void setCena(int cena) {
        this.cena = cena;
    }

    public Produkt getProduktZ() {
        return produktZ;
    }

    public void setProduktZ(Produkt produktZ)
    {
        this.produktZ = produktZ;
    }



    public boolean getGlodne() {
        return glodne;
    }

    public void setGlodne(boolean glodne) {
        this.glodne = glodne;
    }




}
