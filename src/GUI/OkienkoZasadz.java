package GUI;

import javax.swing.*;

import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;
import Kawalek_Ziemi.Pole_Uprawne;
import Pole.Owoc;
import Pole.Uprawa;
import Pole.Zboze;
import Produkt.Produkt;
import Stodola.Stodola;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkienkoZasadz extends JButton {

    String[] uprawy = {"jabłka", "gruszki", "winogrona", "pszenica", "żyto"};
    JFrame sadzenie = new JFrame("Sadzenie");
    private int x;
    private int y;

    public OkienkoZasadz(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        init();
    }

    public JFrame init() {
        // Tworzymy nowe okno

        // Ustawiamy domyślne działanie po naciśnięciu przycisku zamknięcia
    	sadzenie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ustawiamy kolor tła całego okna na brązowy
        // newFrame.getContentPane().setBackground(new Color(139, 69, 19)); // RGB dla brązowego koloru

        // Dodajemy etykietę z tekstem do nowego okna
        JLabel label = new JLabel("Wybierz produkt do zasadzenia:");
        label.setHorizontalAlignment(JLabel.CENTER);
        sadzenie.getContentPane().add(label, BorderLayout.NORTH);

        // Ustawiamy nowy zarządca komponentów BorderLayout
        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        
        // Tworzymy przyciski upraw
        for (int i = 0; i < uprawy.length; i++) {
            JButton productButton = przyciskUprawy(uprawy[i]);
            mainPanel.add(productButton);
        }

        // Dodajemy panel główny do nowego okna
        sadzenie.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Ustawiamy rozmiar okna
        sadzenie.setSize(400, 250);

        // Ustawiamy okno na środku ekranu
        sadzenie.setLocationRelativeTo(null);

        // Ustawiamy widoczność okna
        sadzenie.setVisible(true);

        return sadzenie;
    }
    
    private JButton przyciskUprawy(String nazwa) {
    	JButton productButton = new JButton(nazwa);
        JPanel panel = new JPanel(new BorderLayout());

        // Nazwa na górze
        JLabel nameLabel = new JLabel(nazwa, JLabel.CENTER);
        panel.add(nameLabel, BorderLayout.NORTH);

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
        
        
        Produkt coZasadzic = Gra.getInstance().getFarmaGracza().getStodola().getProdukt(nazwa);
        
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + nazwa + coZasadzic.getCenaWytworzenia());
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                
                // Sprawdzenie czy Gracza stać na zakup nasion
                if (Gra.getInstance().getLiczbaMonet() < coZasadzic.getCenaWytworzenia()) {
                	System.out.println("Gracza nie stać na zasadzenie wybranej uprawy");
                	String message = "Nie masz wystarczającej liczby monet na zakup " + nazwa +" (cena: "+ coZasadzic.getCenaWytworzenia()+")";
                    JOptionPane.showMessageDialog(null, message, "Brak środków", JOptionPane.WARNING_MESSAGE);
                }
                else {
                	Kawalek_Ziemi[][] kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
                    Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];
                    
                    Gra.getInstance().setLiczbaMonet(Gra.getInstance().getLiczbaMonet()-coZasadzic.getCenaWytworzenia());
                    
                    if (kawalekZiemi instanceof Pole_Uprawne) {
                        Pole_Uprawne poleUprawne = (Pole_Uprawne) kawalekZiemi;
                        if (nazwa.equals("pszenica") || nazwa.equals("żyto")) {
                        	poleUprawne.zasadz(new Zboze(coZasadzic.getNazwa(), coZasadzic.getGotoweDoZebrania(), coZasadzic.getCena(), coZasadzic.getCenaWytworzenia(), coZasadzic.getCenaZakupu(), coZasadzic.getCzasProdukcji(), "", true));
                        }
                        else {
                        	poleUprawne.zasadz(new Owoc(coZasadzic.getNazwa(), coZasadzic.getGotoweDoZebrania(), coZasadzic.getCena(), coZasadzic.getCenaWytworzenia(), coZasadzic.getCenaZakupu(), coZasadzic.getCzasProdukcji(), "", true, true));
                        }
                    }
                    
                    sadzenie.dispose();
                }
                
            }
            
        });
        
        return productButton;
    }

}
