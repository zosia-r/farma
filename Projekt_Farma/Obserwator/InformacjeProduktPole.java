package Obserwator;

import java.io.File;

public class InformacjeProduktPole implements Obserwator{
	
	private boolean czyProdukuje = false; // jezeli nic nie jest produkowane na polu to mozna wyswietlac pole bez ikony produktu
	private String nazwa; // nazwa produktu produkowanego na polu
	private boolean GotoweDoZebrania; // czy zebranie produktu jest mozliwe
	private int PozostalyCzas;
	private File ikonka; // ikonka produktu
	
	@Override
	public void aktualizacja(String nazwa, boolean GotoweDoZebrania, int PozostalyCzas, File ikonka) {
		this.nazwa = nazwa;
		this.GotoweDoZebrania = GotoweDoZebrania;
		this.PozostalyCzas = PozostalyCzas;
		this.ikonka = ikonka;
		if (nazwa != null) {
			czyProdukuje = true;
		}
		else {
			czyProdukuje = false;
		}
	}

}