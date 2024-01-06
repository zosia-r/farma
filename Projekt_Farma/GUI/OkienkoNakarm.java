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

public class OkienkoNakarm extends JButton {

    String[] opcje = {"pszenica", "żyto", "usuń zwierzę"};
    JFrame opcjeOkno = new JFrame("Nakarm");
    private int x;
    private int y;

    public OkienkoNakarm(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        init();
    }

    public JFrame init() {
        // Tworzymy nowe okno

        // Ustawiamy domyślne działanie po naciśnięciu przycisku zamknięcia
    	opcjeOkno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ustawiamy kolor tła całego okna na brązowy
        // newFrame.getContentPane().setBackground(new Color(139, 69, 19)); // RGB dla brązowego koloru

        // Dodajemy etykietę z tekstem do nowego okna
        JLabel label = new JLabel("Wybierz pokarm");
        label.setHorizontalAlignment(JLabel.CENTER);
        opcjeOkno.getContentPane().add(label, BorderLayout.NORTH);

        // Ustawiamy nowy zarządca komponentów BorderLayout
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        
        // Tworzymy przyciski upraw
        for (int i = 0; i < opcje.length; i++) {
            JButton productButton = przyciskNakarm(opcje[i]);
            mainPanel.add(productButton);
        }

        // Dodajemy panel główny do nowego okna
        opcjeOkno.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Ustawiamy rozmiar okna
        opcjeOkno.setSize(400, 150);

        // Ustawiamy okno na środku ekranu
        opcjeOkno.setLocationRelativeTo(null);

        // Ustawiamy widoczność okna
        opcjeOkno.setVisible(true);

        return opcjeOkno;
    }
    
    private JButton przyciskNakarm(String nazwa) {
    	JButton Przycisk;
    	if (nazwa.equals("pszenica") || nazwa.equals("żyto")) Przycisk = new JButton();
    	else Przycisk = new JButton(nazwa);

    	ImageIcon ikonka;
        // Obrazek w środku
    	if (nazwa.equals("pszenica") || nazwa.equals("żyto")) {
    		ikonka = new ImageIcon(Gra.getInstance().getFarmaGracza().getStodola().getProdukt(nazwa).getAdresIkonki());
    		ikonka = new ImageIcon(ikonka.getImage().getScaledInstance(74, 70, Image.SCALE_DEFAULT));
    		Przycisk.setIcon(ikonka);
    	}
    	
    	Przycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj możesz dodać obsługę naciśnięcia przycisku dla każdego produktu
                System.out.println("Naciśnięto przycisk: " + nazwa);
                //gra.getFarmaGracza().getKawalki_ziemi()[x][y].zasadz(new Owoc(coZasadzic.getNazwa(),coZasadzic.getGotoweDoZebrania(),coZasadzic.getCena(),coZasadzic.getCenaWytworzenia(),coZasadzic.getCzasProdukcji(),"",true,true));
                
                // Sprawdzenie czy Gracza stać na zakup nasion
                if (nazwa.equals("pszenica") || nazwa.equals("żyto")) {
                	if (Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(nazwa) <= 0) {
                		System.out.println("Gracz nie ma "+ nazwa + "Wiec nie moze nim nakarmic zwierzecia");
                    	String message = "Nie masz wystarczającej liczby " + nazwa +" aby nakrarmić zwierzę";
                        JOptionPane.showMessageDialog(null, message, "Brak " + nazwa, JOptionPane.WARNING_MESSAGE);
                	}
                	else {
                		Kawalek_Ziemi[][] kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
                        Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];
                        
                        if (kawalekZiemi instanceof Zagroda) {
                        	Zagroda zagroda = (Zagroda) kawalekZiemi;
                            if (nazwa.equals("pszenica")) {
                            	zagroda.nakarmPszenica();
                            	System.out.println("Nakarmiono pszenica");
                            }
                            else if (nazwa.equals("żyto")) {
                            	zagroda.nakarmZyto();
                            	System.out.println("Nakarmiono zytem");
                            }
                        }
                        
                        opcjeOkno.dispose();
                	}
                	
                }
                else {
                	Kawalek_Ziemi[][] kawalkiZiemi = Gra.getInstance().getFarmaGracza().getKawalki_ziemi();
                    Kawalek_Ziemi kawalekZiemi = kawalkiZiemi[x][y];
                    
                    if (kawalekZiemi instanceof Zagroda) {
                    	Zagroda zagroda = (Zagroda) kawalekZiemi;
                    	zagroda.usunZwierze();
                    	System.out.println("usunieto zwierze");
                        opcjeOkno.dispose();
                    }
                    
                }
                
            }
            
        });
        
        return Przycisk;
    }

}