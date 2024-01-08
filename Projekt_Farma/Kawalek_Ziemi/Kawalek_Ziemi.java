package Kawalek_Ziemi;

import Obserwator.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public abstract class Kawalek_Ziemi implements Podmiot
{
    private int x;  // pierwsza współrzędna kawałka
    private int y;  // druga współrzędna kawałka
    private Image ikonka = null;
    private Image ikonkaPodstawowa = null;

    //podmiot
    private ArrayList<Obserwator> obserwatorzy;
    //w naszym przypadku bedzie tylko jeden obserwator ale oficjalnie jest to zbior obserwatorow
    
    public Kawalek_Ziemi(int x, int y)
    {
        this.x=x;
        this.y=y;
        
        this.obserwatorzy = new ArrayList<>();
        this.obserwatorzy.clear();
        this.obserwatorzy.add(new InformacjeProduktPole());

        Timer timer2 = new Timer(100, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powiadomObserwatorow();

                InformacjeProduktPole obserwatorIkonkaPola = (InformacjeProduktPole) obserwatorzy.get(0);
                if (obserwatorIkonkaPola.isCzyZagroda()) {
                	// wybieranie ikonki jesli pole jest zagroda
                	if (obserwatorIkonkaPola.getZwierze() == null) {
            			ikonka = ikonkaPodstawowa;
            		}
                	else if (obserwatorIkonkaPola.getZwierze() != null) {
                		ImageIcon icon;
                		if (obserwatorIkonkaPola.isCzyProdukuje() == false) {
                			icon = new ImageIcon(obserwatorIkonkaPola.getZwierze().getAdresIkonkiGlodne());
                		} else if (obserwatorIkonkaPola.isGotoweDoZebrania() == false) {
                			icon = new ImageIcon(obserwatorIkonkaPola.getZwierze().getAdresIkonkiJedzace());
                		}  else {
                			icon = new ImageIcon(obserwatorIkonkaPola.getZwierze().getAdresIkonkigotowe());
                		}
            			ikonka = icon.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT);
            		}
                }
                else if (obserwatorIkonkaPola.getNazwa().equals("")) {
                	// wybieranie ikonki jesli na polu nic nie jest produkowane
                	ikonka = ikonkaPodstawowa;
                }
                else if (obserwatorIkonkaPola.isGotoweDoZebrania() == false) {
                	// wybieranie ikonki jesli produkt jest w trakcie produkcji
                	ImageIcon icon;
                	if (obserwatorIkonkaPola.getNazwa().equals("pszenica") || obserwatorIkonkaPola.getNazwa().equals("żyto")) {
                		icon = new ImageIcon("grafika/zasianeZboze.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("jabłka") || obserwatorIkonkaPola.getNazwa().equals("gruszki")) {
                	    icon = new ImageIcon("grafika/zasadzoneDrzewo.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("winogrona")) {
                	    icon = new ImageIcon("grafika/zasadzonaWinorosl.png");
                	} 
                	else {
                		icon = new ImageIcon("grafika/pustePoleUprawne");
                	}
                	ikonka = icon.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT);
                	
                }
                else {
                	// wybieranie ikonki jesli produkt jest wyprodukowany
                	ImageIcon icon;
                	if (obserwatorIkonkaPola.getNazwa().equals("pszenica")) {
                		icon = new ImageIcon("grafika/gotowaPszenica.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("żyto")) {
                		icon = new ImageIcon("grafika/gotoweZyto.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("jabłka")) {
                	    icon = new ImageIcon("grafika/gotowaJablon.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("gruszki")) {
                	    icon = new ImageIcon("grafika/gotowaGrusza.png");
                	} else if (obserwatorIkonkaPola.getNazwa().equals("winogrona")) {
                	    icon = new ImageIcon("grafika/gotoweWinogrono.png");
                	} 
                	else {
                		icon = new ImageIcon("grafika/pustePoleUprawne");
                	}
                	ikonka = icon.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT);
                }
            }
        });

        // Uruchamiamy timer co 0.1 sekundy
        timer2.start();
        
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public Image getIkonka() {
    	return ikonka;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setIkonka(Image ikonka)
    {
        this.ikonka=ikonka;
        this.ikonkaPodstawowa=ikonka;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public void getStan()
    {
        System.out.println("Współrzędne punktu to: ("+getX()+","+getY()+")");
    }

    public ArrayList<Obserwator> getObserwatorzy() { return obserwatorzy; }

    public void dodajObserwatora(Obserwator o)
    {
        obserwatorzy.add(o);
    }

    public void usunObserwatora(Obserwator o)
    {
        int i = obserwatorzy.indexOf(o);
        if(i>=0)
            obserwatorzy.remove(i);
    }

    public abstract void powiadomObserwatorow();
    //bedzie zaimplementowana w kolejnych klasach (pole uprawne, zagroda, przetwornia)
    
}

