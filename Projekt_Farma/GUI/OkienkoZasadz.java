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

    String[] owoce = {"jabłka", "gruszki", "winogrona"};
    String[] zboza = {"pszenica", "żyto"};
    Gra gra;
    JFrame newFrame = new JFrame("Nowe Okno");
    private int x;
    private int y;

    public OkienkoZasadz(int x, int y, Gra gra) {
        super();
        this.x = x;
        this.y = y;
        this.gra = gra;
        init();
    }

    public JFrame init() {
        // Tworzymy nowe okno

        // Ustawiamy domyślne działanie po naciśnięciu przycisku zamknięcia
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ustawiamy kolor tła całego okna na brązowy
        newFrame.getContentPane().setBackground(new Color(139, 69, 19)); // RGB dla brązowego koloru

        // Dodajemy etykietę z tekstem do nowego okna
        JLabel label = new JLabel("Wybierz produkt do zasadzenia:");
        label.setHorizontalAlignment(JLabel.CENTER);
        newFrame.getContentPane().add(label, BorderLayout.NORTH);

        // Ustawiamy nowy zarządca komponentów BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tworzymy panel dla przycisków owocowych
        JPanel owocePanel = new JPanel(new GridLayout(0, 3));

        // Tworzymy przyciski owocowe
        for (int i = 0; i < owoce.length; i++) {
            JButton productButton = new JButton(owoce[i]);
            JPanel panel = new JPanel(new BorderLayout());

            // Nazwa na górze
            JLabel nameLabel = new JLabel("Nazwa Produktu", JLabel.CENTER);
            panel.add(nameLabel, BorderLayout.NORTH);

            // Obrazek w środku
            ImageIcon imageIcon = new ImageIcon("sciezka/do/obrazka.jpg");
            JLabel imageLabel = new JLabel(imageIcon, JLabel.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            // Tekst na dole
            JLabel textLabel = new JLabel("Krótki Tekst", JLabel.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);
            
            configureButtonOwoc(productButton);
            owocePanel.add(productButton);
        }

        // Tworzymy panel dla przycisków zbożowych
        JPanel zbozaPanel = new JPanel(new GridLayout(0, 2));

        // Tworzymy przyciski zbożowe
        for (int i = 0; i < zboza.length; i++) {
            JButton productButton = new JButton(zboza[i]);
            configureButtonZboze(productButton);
            zbozaPanel.add(productButton);
        }

        // Dodajemy panele z przyciskami do panelu głównego
        mainPanel.add(owocePanel, BorderLayout.NORTH);
        mainPanel.add(zbozaPanel, BorderLayout.SOUTH);

        // Ograniczamy szerokość paneli
        owocePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, owocePanel.getPreferredSize().height));
        zbozaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, zbozaPanel.getPreferredSize().height));

        // Dodajemy panel główny do nowego okna
        newFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Ustawiamy rozmiar okna
        newFrame.setSize(500, 250);

        // Ustawiamy okno na środku ekranu
        newFrame.setLocationRelativeTo(null);

        // Ustawiamy widoczność okna
        newFrame.setVisible(true);

        return newFrame;
    }

    private void configureButtonOwoc(JButton button) {
        // Ustawiamy przycisk jako kwadratowy
        Dimension buttonSize = new Dimension(150, 100); // Dostosuj rozmiar przycisku według potrzeb
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);
        
        Stodola stodola = new Stodola();
        Produkt coZasadzic = stodola.getProdukt(button.getText());
        
        // Miejsce na wstawienie obrazka
        // ImageIcon imageIcon = new ImageIcon("sciezka/do/obrazka.jpg");
        // button.setIcon(imageIcon);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + button.getText() + coZasadzic.getCenaWytworzenia());
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                Kawalek_Ziemi[][] kawalkiZiemi = gra.getFarmaGracza().getKawalki_ziemi();
                Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];

                if (kawalekZiemi instanceof Pole_Uprawne) {
                    Pole_Uprawne poleUprawne = (Pole_Uprawne) kawalekZiemi;
                    // Wywołanie metody zasadz na obiekcie poleUprawne
                    poleUprawne.zasadz(new Owoc(coZasadzic.getNazwa(), coZasadzic.getGotoweDoZebrania(), coZasadzic.getCena(), coZasadzic.getCenaWytworzenia(), coZasadzic.getCzasProdukcji(), "", true, true));
                }
                
                newFrame.dispose();
            }
            
        });
    }
    
    private void configureButtonZboze(JButton button) {
        // Ustawiamy przycisk jako kwadratowy
        Dimension buttonSize = new Dimension(150, 100); // Dostosuj rozmiar przycisku według potrzeb
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);
        
        
        Stodola stodola = new Stodola();
        Produkt coZasadzic = stodola.getProdukt(button.getText());
        
        // Miejsce na wstawienie obrazka
        // ImageIcon imageIcon = new ImageIcon("sciezka/do/obrazka.jpg");
        // button.setIcon(imageIcon);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + button.getText() + coZasadzic.getCenaWytworzenia());
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                Kawalek_Ziemi[][] kawalkiZiemi = gra.getFarmaGracza().getKawalki_ziemi();
                Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];

                if (kawalekZiemi instanceof Pole_Uprawne) {
                    Pole_Uprawne poleUprawne = (Pole_Uprawne) kawalekZiemi;
                    
                    // Wywołanie metody zasadz na obiekcie poleUprawne
                    poleUprawne.zasadz(new Zboze(coZasadzic.getNazwa(), coZasadzic.getGotoweDoZebrania(), coZasadzic.getCena(), coZasadzic.getCenaWytworzenia(), coZasadzic.getCzasProdukcji(), "", true));
                }
                
                newFrame.dispose();
            }
            
        });
    }
}
