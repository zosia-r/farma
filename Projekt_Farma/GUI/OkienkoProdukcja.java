package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import Kawalek_Ziemi.Przetwornia;
import Produkt.Produkt;
import przetwornia.Przetwor;

public class OkienkoProdukcja extends JButton {

    String[] produkty = {"chleb pszenny", "chleb żytni", "dżem jabłkowy", "dżem gruszkowy", "sok jabłkowy", "sok gruszkowy", "masło", "szynka", "kiełbasa"};
    JFrame produkcja = new JFrame("Wybierz przetwór do produkcji:");
    private int x;
    private int y;

    public OkienkoProdukcja(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        init();
    }

    public JFrame init() {
        // Tworzymy nowe okno

        // Ustawiamy domyślne działanie po naciśnięciu przycisku zamknięcia
    	produkcja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ustawiamy nowy zarządca komponentów BorderLayout
        JPanel mainPanel = new JPanel(new GridLayout(3, 3));
        
        // Tworzymy przyciski upraw
        for (int i = 0; i < produkty.length; i++) {
            JButton productButton = przyciskProduktu(produkty[i]);
            mainPanel.add(productButton);
        }

        // Dodajemy panel główny do nowego okna
        produkcja.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Ustawiamy rozmiar okna
        produkcja.setSize(400, 250);

        // Ustawiamy okno na środku ekranu
        produkcja.setLocationRelativeTo(null);

        // Ustawiamy widoczność okna
        produkcja.setVisible(true);

        return produkcja;
    }
    
    private JButton przyciskProduktu(String nazwa) {
    	JButton productButton = new JButton();
        JPanel panel = new JPanel(new BorderLayout());

        // Obrazek w środku
        ImageIcon ikonka = new ImageIcon(Gra.getInstance().getFarmaGracza().getStodola().getProdukt(nazwa).getAdresIkonki());
        ikonka = new ImageIcon(ikonka.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        
        JLabel imageLabel = new JLabel(ikonka, JLabel.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);
        
        productButton.setIcon(ikonka);
        

        // Tekst na dole
        JLabel textLabel = new JLabel("Koszt: " + Gra.getInstance().getFarmaGracza().getStodola().getProdukt(nazwa).getCenaWytworzenia(), JLabel.CENTER);
        panel.add(textLabel, BorderLayout.SOUTH);
        
        Dimension buttonSize = new Dimension(150, 100); // Dostosuj rozmiar przycisku według potrzeb
        productButton.setPreferredSize(buttonSize);
        productButton.setMinimumSize(buttonSize);
        productButton.setMaximumSize(buttonSize);
        
        productButton.setContentAreaFilled(false); // Ustawienie przezroczystości tła
        productButton.setBorderPainted(false); // Usunięcie rysowania ramki
        productButton.setFocusPainted(false); // Usunięcie podświetlenia przy fokusowaniu
        
        
        Produkt produkt = Gra.getInstance().getFarmaGracza().getStodola().getProdukt(nazwa);
        
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + nazwa + produkt.getCenaWytworzenia());
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                Przetwor przykladowy = new Przetwor(produkt, false);
                // Sprawdzenie czy Gracza stać na produkcje
                if (!przykladowy.CzyMoznaPrzetworzyc(produkt)) {
                	System.out.println("Gracz nie ma wszystkich potrzebnych skladnikow na dany przetwor");
                	String message = "Nie masz wystarczającej liczby potrzebnych skladnikow na " + nazwa;
                    JOptionPane.showMessageDialog(null, message, "Brak składników", JOptionPane.WARNING_MESSAGE);
                }
                else {
                	System.out.println("Z warunku wynika że można rozpoczac produkcje");
                	Kawalek_Ziemi[][] kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
                    Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];
                    
                    if (kawalekZiemi instanceof Przetwornia) {
                    	Przetwornia przetwornia = (Przetwornia) kawalekZiemi;
                    	przetwornia.rozpocznijProdukcjePrzetworu(new Przetwor(produkt));
                    }
                    
                    produkcja.dispose();
                }
                
            }
            
        });
        
        return productButton;
    }

}
