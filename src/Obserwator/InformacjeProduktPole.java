package Obserwator;

import java.io.File;

import zwierzeta.Zwierze;

public class InformacjeProduktPole implements Obserwator{
	
	private boolean czyProdukuje = false; // jezeli nic nie jest produkowane na polu to mozna wyswietlac pole bez ikony produktu
	private String nazwa = ""; // nazwa produktu produkowanego na polu
	private boolean GotoweDoZebrania; // czy zebranie produktu jest mozliwe
	private long PozostalyCzas;
	private File ikonka; // ikonka produktu
	private Zwierze zwierze;
	private boolean czyZagroda = false;
	
	public InformacjeProduktPole() {
		this.nazwa = nazwa;
		this.GotoweDoZebrania = GotoweDoZebrania;
		this.PozostalyCzas = PozostalyCzas;
		this.ikonka = ikonka;
		if (!nazwa.equals("")) {
		    czyProdukuje = true;
		} else {
		    czyProdukuje = false;
		}
	}
	
	public void aktualizacja(String nazwa, boolean GotoweDoZebrania, long PozostalyCzas, File ikonka, Zwierze zwierze, boolean czyZagroda) {
		this.nazwa = nazwa;
		this.GotoweDoZebrania = GotoweDoZebrania;
		this.PozostalyCzas = PozostalyCzas;
		this.ikonka = ikonka;
		if (!nazwa.equals("")) {
		    czyProdukuje = true;
		} else {
		    czyProdukuje = false;
		}
		this.zwierze = zwierze;
		this.czyZagroda = czyZagroda;
	}

	public boolean isCzyProdukuje() {
		return czyProdukuje;
	}

	public void setCzyProdukuje(boolean czyProdukuje) {
		this.czyProdukuje = czyProdukuje;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public boolean isGotoweDoZebrania() {
		return GotoweDoZebrania;
	}

	public void setGotoweDoZebrania(boolean gotoweDoZebrania) {
		GotoweDoZebrania = gotoweDoZebrania;
	}

	public long getPozostalyCzas() {
		return PozostalyCzas;
	}

	public void setPozostalyCzas(int pozostalyCzas) {
		PozostalyCzas = pozostalyCzas;
	}

	public File getIkonka() {
		return ikonka;
	}

	public void setIkonka(File ikonka) {
		this.ikonka = ikonka;
	}

	public Zwierze getZwierze() {
		return zwierze;
	}

	public void setZwierze(Zwierze zwierze) {
		this.zwierze = zwierze;
	}

	public boolean isCzyZagroda() {
		return czyZagroda;
	}

	public void setCzyZagroda(boolean czyZagroda) {
		this.czyZagroda = czyZagroda;
	}
	
	

}
