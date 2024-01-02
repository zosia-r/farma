package GUI;

import Farma.Farma;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private int wymiarFarmy;
    private final int szerokoscFarmy = 400;
    private final int szerokoscEkranu = szerokoscFarmy;
    private final int wysokoscFarmy = 400;
    private final int wysokoscPaskaInformacyjnego = 100;
    private final int wysokoscTimera = 40;
    private final int wysokoscEkranu = wysokoscFarmy + wysokoscPaskaInformacyjnego + wysokoscTimera;

    Gra gra;

    public GUI(Gra gra) {
        this.gra = gra;
        this.wymiarFarmy = gra.getFarmaGracza().getWymiar();
        init();
    }

    private void init() {
        setTitle("FARMA");
        setSize(szerokoscEkranu, wysokoscEkranu);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createAndShowGUI();
    }


    private void createAndShowGUI() {
        GUI_Timer();

        GUI_Farma();

        GUI_PasekInformacyjny();

        this.setVisible(true);
    }

    private void GUI_Timer()
    {
        JPanel panel_timer = new JPanel();
        panel_timer.setPreferredSize(new Dimension(szerokoscEkranu,wysokoscTimera));
        panel_timer.setBackground(Color.decode("#f0bd8d"));

        JLabel czas = new JLabel(gra.getPozostalyCzas()/60 + ":" + gra.getPozostalyCzasS()); //czas poczatkowy
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                czas.setText(gra.getPozostalyCzas() / 60 + ":" + gra.getPozostalyCzasS()); //timer co sekunde zmienia wyswietlany czas
                if(gra.getPozostalyCzas()==0) { //jak czas sie skonczy to timer sie zatrzymuje i wyswietla "Koniec gry!"
                    ((Timer) e.getSource()).stop();
                    czas.setText("Koniec gry!");
                }
            }
        });
        timer.start();

        czas.setFont(new Font("Monospaced", Font.BOLD, 20));
        czas.setForeground(Color.BLACK);
        czas.setHorizontalAlignment(JLabel.CENTER);
        czas.setVerticalAlignment(JLabel.CENTER);
        panel_timer.add(czas,BorderLayout.CENTER);

        this.add(panel_timer,BorderLayout.SOUTH);
    }

    private void GUI_Farma() {
        JPanel panel = new JPanel(new GridLayout(wymiarFarmy, wymiarFarmy));
        panel.setPreferredSize(new Dimension(szerokoscFarmy,wysokoscFarmy));
        panel.setBackground(Color.decode("#f0bd8d"));

        // Tworzenie farmy


        // Dodawanie przycisków do panelu
        for (int i = 0; i < wymiarFarmy; i++) {
            for (int j = 0; j < wymiarFarmy; j++) {
                JButton button = createButton(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[i][j]);
                panel.add(button);
            }
        }
        // Dodawanie panelu do ramki
        this.add(panel,BorderLayout.CENTER);

    }

    private void GUI_PasekInformacyjny() {
        // Tworzenie gornego pasaka z informacjami
        JPanel panel_informacje = new JPanel();
        panel_informacje.setPreferredSize(new Dimension(szerokoscEkranu,wysokoscPaskaInformacyjnego));
        panel_informacje.setBackground(Color.decode("#f0bd8d"));

        // Tekst wyswietlany na pasku
        JLabel tekst = new JLabel("Nazwa Farmy: "+ gra.getFarmaGracza().getNazwaFarmy( )
                + ", Monety = " + gra.getLiczbaMonet());

        // Obsluga sposobu wyswietlania
        tekst.setPreferredSize(new Dimension(szerokoscEkranu, wysokoscPaskaInformacyjnego/2));
        tekst.setFont(new Font(null, Font.BOLD, 20));
        tekst.setHorizontalAlignment(JLabel.CENTER);
        tekst.setVerticalAlignment(JLabel.CENTER);

        panel_informacje.add(tekst);

        // Przycisk do otwierania stodoly
        StodolaPodglad buttonStodola = new StodolaPodglad();
        panel_informacje.add(buttonStodola);

        // Dodawanie panelu do ramki
        this.add(panel_informacje,BorderLayout.NORTH);
    }

    private JButton createButton(Kawalek_Ziemi kawalekZiemi) {
        JButton button = new JButton();
        // Ustawianie akcji po kliknięciu przycisku
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Wyświetlanie informacji na temat kawałka ziemi po kliknięciu
                if(gra.getPozostalyCzas()!=0)
                   System.out.println("Jest to pole: "+((button.getY()-1)/72+1)+", "+(button.getX()/77+1));
            }
        });

        return button;
    }
}
