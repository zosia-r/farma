package GUI;

import Farma.Farma;
import Gra.Gra;
import Kawalek_Ziemi.Kawalek_Ziemi;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private int wymiarFarmy;
    private final int szerokoscFarmy = 400;
    private final int szerokoscEkranu = szerokoscFarmy;
    private final int wysokoscFarmy = 400;
    private final int wysokoscPaskaInformacyjnego = 100;
    private final int wysokoscEkranu = wysokoscFarmy + wysokoscPaskaInformacyjnego;

    Gra gra;

    public GUI(Gra gra) {
        this.gra = gra;
        this.wymiarFarmy = gra.getFarmaGracza().getWymiar();
        init();
    }

    private void init() {
        setTitle("Interfejs Graficzny Farmy");
        setSize(szerokoscEkranu, wysokoscEkranu);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createAndShowGUI();
    }


    private void createAndShowGUI() {
        GUI_Farma();

        GUI_PasekInformacyjny();

        this.setVisible(true);
    }

    private void GUI_Farma() {
        JPanel panel = new JPanel(new GridLayout(wymiarFarmy, wymiarFarmy));

        panel.setPreferredSize(new Dimension(szerokoscFarmy,wysokoscFarmy));

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
                System.out.println("Jest to pole: "+((button.getY()-1)/72+1)+", "+(button.getX()/77+1));
            }
        });

        return button;
    }
}