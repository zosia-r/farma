package zwierzeta;

import java.util.ArrayList;
import Produkt.Produkt;

public abstract class Zwierze{
	
	
	private String nazwa;
	private int cena;
	private boolean glodne;
	private File ikonka;
	ArrayList<Produkt> produktyZ;
	
	public Zwierze()
	{
		glodne = true;
		produktyZ = new ArrayList<>();
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



	public ArrayList<Produkt> getProduktyZ() {
		return produktyZ;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}



	public boolean getGlodne() {
		return glodne;
	}

	public void setGlodne(boolean glodne) {
		this.glodne = glodne;
	}

	
	

}
