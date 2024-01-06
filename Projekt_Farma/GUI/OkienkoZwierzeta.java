package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Zagroda;
import zwierzeta.Krowa;
import zwierzeta.Kura;
import zwierzeta.Swinia;

public class OkienkoZwierzeta extends JButton {

    String[] zwierzeta = {"krowy", "kury", "swinki"};
    JFrame zwierzetaOkno = new JFrame("Zwierzęta");
    private Kura dowolneZwierze = new Kura();
    private int x;
    private int y;

    public OkienkoZwierzeta(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        init();
    }

    public JFrame init() {
        // Tworzymy nowe okno

        // Ustawiamy domyślne działanie po naciśnięciu przycisku zamknięcia
    	zwierzetaOkno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ustawiamy kolor tła całego okna na brązowy
        // newFrame.getContentPane().setBackground(new Color(139, 69, 19)); // RGB dla brązowego koloru

        // Dodajemy etykietę z tekstem do nowego okna
        JLabel label = new JLabel("Kup zwierzeta (cena: " + dowolneZwierze.getCena() + ")");
        label.setHorizontalAlignment(JLabel.CENTER);
        zwierzetaOkno.getContentPane().add(label, BorderLayout.NORTH);

        // Ustawiamy nowy zarządca komponentów BorderLayout
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        
        // Tworzymy przyciski upraw
        for (int i = 0; i < zwierzeta.length; i++) {
            JButton productButton = przyciskZwierzecia(zwierzeta[i]);
            mainPanel.add(productButton);
        }

        // Dodajemy panel główny do nowego okna
        zwierzetaOkno.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Ustawiamy rozmiar okna
        zwierzetaOkno.setSize(400, 150);

        // Ustawiamy okno na środku ekranu
        zwierzetaOkno.setLocationRelativeTo(null);

        // Ustawiamy widoczność okna
        zwierzetaOkno.setVisible(true);

        return zwierzetaOkno;
    }
    
    private JButton przyciskZwierzecia(String nazwa) {
    	JButton zwierzePrzycisk = new JButton();

    	ImageIcon ikonka;
        // Obrazek w środku
    	if (nazwa.equals("krowy")) {
    		Krowa krowa = new Krowa();
    		ikonka = new ImageIcon(krowa.getAdresIkonkiGlodne());
    		ikonka = new ImageIcon(ikonka.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT));
    		zwierzePrzycisk.setIcon(ikonka);
    	} else if (nazwa.equals("kury")) {
    		Kura kura = new Kura();
    		ikonka = new ImageIcon(kura.getAdresIkonkiGlodne());
    		ikonka = new ImageIcon(ikonka.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT));
    		zwierzePrzycisk.setIcon(ikonka);
    	} else if (nazwa.equals("swinki")) {
    		Swinia swinia = new Swinia();
    		ikonka = new ImageIcon(swinia.getAdresIkonkiGlodne());
    		ikonka = new ImageIcon(ikonka.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT));
    		zwierzePrzycisk.setIcon(ikonka);
    	} else {
    		System.out.println("Nie znaleziono ikonki");
    	}
    	
    	zwierzePrzycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + nazwa);
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                
                // Sprawdzenie czy Gracza stać na zakup zwierzecia
                if (Gra.getInstance().getLiczbaMonet() < dowolneZwierze.getCena()) {
                	System.out.println("Gracza nie stać na kupno zwierzecia");
                	String message = "Nie masz wystarczającej liczby monet na zakup " + nazwa +" (cena: " + dowolneZwierze.getCena() + ")";
                    JOptionPane.showMessageDialog(null, message, "Brak środków", JOptionPane.WARNING_MESSAGE);
                }
                else {
                	Kawalek_Ziemi[][] kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
                    Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];
                    
                    Gra.getInstance().setLiczbaMonet(Gra.getInstance().getLiczbaMonet()-dowolneZwierze.getCena());
                    
                    if (kawalekZiemi instanceof Zagroda) {
                    	Zagroda zagroda = (Zagroda) kawalekZiemi;
                    	if (nazwa.equals("krowy")) {
                    		zagroda.sprobujKupicZwierze(new Krowa());
                    	} else if (nazwa.equals("kury")) {
                    		zagroda.sprobujKupicZwierze(new Kura());
                    	} else if (nazwa.equals("swinki")) {
                    		zagroda.sprobujKupicZwierze(new Swinia());
                    	}
                    }
                    
                    zwierzetaOkno.dispose();
                }
                
            }
            
        });
        
        return zwierzePrzycisk;
    }

}
