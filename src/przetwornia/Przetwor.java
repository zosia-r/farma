package przetwornia;
import Produkt.Produkt;
import java.util.Timer;
import java.util.TimerTask;
import Stodola.Stodola;
import Gra.Gra;

public class Przetwor extends Produkt {    

    public Przetwor(Produkt produkt) {
        super(produkt.getNazwa(), produkt.getGotoweDoZebrania(), produkt.getCena(), produkt.getCenaWytworzenia(), produkt.getCenaZakupu(), produkt.getCzasProdukcji(), produkt.getAdresIkonki());
        if (CzyMoznaPrzetworzyc(produkt)) {
        	System.out.println("Mozna zrobic produkt !");
        	ZabranieSkladnikow(produkt);
            uruchomLiczniki();
            }
    }
    
    public Przetwor(Produkt produkt, boolean czyprawdziwy) {
        super(produkt.getNazwa(), produkt.getGotoweDoZebrania(), produkt.getCena(), produkt.getCenaWytworzenia(), produkt.getCenaZakupu(), produkt.getCzasProdukcji(), produkt.getAdresIkonki());
        if (CzyMoznaPrzetworzyc(produkt) && czyprawdziwy) {
        	System.out.println("Mozna zrobic produkt !");
        	ZabranieSkladnikow(produkt);
            uruchomLiczniki();
            }
    }
    
    public boolean CzyMoznaPrzetworzyc (Produkt produkt) {
    	if (produkt.getNazwa().equals("chleb pszenny")) {
			if ((Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("pszenica") > 0) && (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jajka") > 0)) {
				return true;
    		}
			else {
				System.out.println(" NIE Mozna zrobic chleb pszenny");
				return false;
			}
    	} else if (produkt.getNazwa().equals("chleb żytni")) {
			if ((Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("żyto") > 0) && (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jajka") > 0)) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("dżem jabłkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jabłka") > 1) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("dżem gruszkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("gruszki") > 1) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("sok jabłkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jabłka") > 0) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("sok gruszkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("gruszki") > 0) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("masło")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mleko") > 0) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("szynka")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mięso") > 1) {
				return true;
    		}
			else return false;
    	} else if (produkt.getNazwa().equals("kiełbasa")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mięso") > 0) {
				return true;
    		}
			else return false;
    	}
    	else {
    		System.out.println("Nie znaleziono produktu");
    	}
    	
    	
    	return false;
    }
    
    public void ZabranieSkladnikow (Produkt produkt) {
    	if (produkt.getNazwa().equals("chleb pszenny")) {
    		if ((Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("pszenica") > 0) && (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jajka") > 0)) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("pszenica"));
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("jajka"));
			}
			else {
				System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
			}
    	} else if (produkt.getNazwa().equals("chleb żytni")) {
			if ((Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("żyto") > 0) && (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jajka") > 0)) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("żyto"));
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("jajka"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("dżem jabłkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jabłka") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("jabłka"));
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("jabłka"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("dżem gruszkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("gruszki") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("gruszki"));
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("gruszki"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("sok jabłkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("jabłka") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("jabłka"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("sok gruszkowy")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("gruszki") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("gruszki"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("masło")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mleko") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("mleko"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("szynka")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mięso") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("mięso"));
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("mięso"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	} else if (produkt.getNazwa().equals("kiełbasa")) {
			if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc("mięso") > 1) {
				Gra.getInstance().getFarmaGracza().getStodola().usunProdukt(Gra.getInstance().getFarmaGracza().getStodola().getProdukt("mięso"));
			}
			else System.out.println("Zabraklo skladnikow!!!!!!!!!!!!!");
    	}
    	else {
    		System.out.println("Nie znaleziono produktu");
    	}
    }
    
    public void przerwijProdukcjePrzet() {
        setGotoweDoZebrania(false);
        getWzrostTimer().cancel();
        getOdliczanieTimer().cancel();
        //for (int i = 0; i <= iloscPotrzebnegoProduktu; i++) {
        //    Gra.getInstance().getFarmaGracza().getStodola().dodajProdukt(ProduktPotrzebnyDoWyprodukowania);
        //}

    }

}
