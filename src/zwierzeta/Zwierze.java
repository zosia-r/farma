package zwierzeta;

import java.io.File;

import Produkt.Produkt;

public abstract class Zwierze {


    private String nazwa;
    private int cena = 30;
    private boolean glodne;
    private File ikonka;
    private ProduktZw produktZ;
    private String adresIkonkiGlodne;
    private String adresIkonkiJedzace;
    private String adresIkonkigotowe;

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

    public void setProduktZ(ProduktZw produktZ)
    {
        this.produktZ = produktZ;
    }



    public boolean getGlodne() {
        return glodne;
    }

    public void setGlodne(boolean glodne) {
        this.glodne = glodne;
    }




	public String getAdresIkonkiGlodne() {
		return adresIkonkiGlodne;
	}




	public void setAdresIkonkiGlodne(String adresIkonkiGlodne) {
		this.adresIkonkiGlodne = adresIkonkiGlodne;
	}




	public String getAdresIkonkiJedzace() {
		return adresIkonkiJedzace;
	}




	public void setAdresIkonkiJedzace(String adresIkonkiJedzace) {
		this.adresIkonkiJedzace = adresIkonkiJedzace;
	}




	public String getAdresIkonkigotowe() {
		return adresIkonkigotowe;
	}




	public void setAdresIkonkigotowe(String adresIkonkigotowe) {
		this.adresIkonkigotowe = adresIkonkigotowe;
	}

    


}
