package GUI;

import Gra.Gra;
import Kawalek_Ziemi.*;
import Obserwator.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    private int wymiarFarmy;
    private final int szerokoscFarmy = 400;
    private final int szerokoscEkranu = szerokoscFarmy;
    private final int wysokoscFarmy = 400;
    private final int wysokoscPaskaInformacyjnego = 100;
    private final int wysokoscTimera = 40;
    private final int wysokoscEkranu = wysokoscFarmy + wysokoscPaskaInformacyjnego + wysokoscTimera;

    public GUI() {
        this.wymiarFarmy = Gra.getInstance().getFarmaGracza().getWymiar();
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
        Timer timer3 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_PasekInformacyjny();
            }
        });
        timer3.start();

        this.setVisible(true);
    }

    private void GUI_Timer()
    {
        JPanel panel_timer = new JPanel();
        panel_timer.setPreferredSize(new Dimension(szerokoscEkranu,wysokoscTimera));
        panel_timer.setBackground(Color.decode("#f0bd8d"));

        JLabel czas = new JLabel(Gra.getInstance().getPozostalyCzas()/60 + ":" + Gra.getInstance().getPozostalyCzasS()); //czas poczatkowy
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                czas.setText(Gra.getInstance().getPozostalyCzas() / 60 + ":" + Gra.getInstance().getPozostalyCzasS()); //timer co sekunde zmienia wyswietlany czas
                if(Gra.getInstance().getPozostalyCzas()==0) { //jak czas sie skonczy to timer sie zatrzymuje i wyswietla "Koniec gry!"
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
        createButton(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[0][0]).setIcon(new ImageIcon("pustePoleUprawne.png"));
        // Dodawanie panelu do ramki
        this.add(panel,BorderLayout.CENTER);

    }

    private void GUI_PasekInformacyjny() {
        // Tworzenie gornego paska z informacjami
        JPanel panel_informacje = new JPanel();
        panel_informacje.setLayout(new BorderLayout());
        panel_informacje.setPreferredSize(new Dimension(szerokoscEkranu,wysokoscPaskaInformacyjnego));
        panel_informacje.setBackground(Color.decode("#f0bd8d"));

        JPanel panelNazwa = new JPanel();
        panelNazwa.setLayout(new BoxLayout(panelNazwa, BoxLayout.Y_AXIS));
        panelNazwa.setBackground(Color.decode("#f0bd8d"));
        JLabel nazwa = new JLabel(" " + Gra.getInstance().getFarmaGracza().getNazwaFarmy());
        JLabel endl = new JLabel(" ");
        nazwa.setFont(new Font("Monospaced", Font.BOLD, 12));
        panelNazwa.add(endl);
        panelNazwa.add(nazwa);
        panel_informacje.add(panelNazwa, BorderLayout.WEST);

        JPanel panelMonety = new JPanel();
        panelMonety.setLayout(new BoxLayout(panelMonety, BoxLayout.Y_AXIS));
        panelMonety.setBackground(Color.decode("#f0bd8d"));
        ImageIcon ikonaMonety = new ImageIcon("grafika/monety.png");
        Image malyObrazek = ikonaMonety.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon maleMonety = new ImageIcon(malyObrazek);
        JLabel monety = new JLabel(String.valueOf(Gra.getInstance().getLiczbaMonet()) + " ");
        monety.setFont(new Font("Monospaced", Font.BOLD, 20));
        JLabel etykietaMonety = new JLabel(maleMonety);
        panelMonety.add(etykietaMonety);
        panelMonety.add(monety);
        panel_informacje.add(panelMonety, BorderLayout.EAST);


        JLabel tytul = new JLabel("FARMA", SwingConstants.CENTER);
        tytul.setFont(new Font("Monospaced", Font.BOLD, 50));
        tytul.setForeground(Color.decode("#26180b"));
        panel_informacje.add(tytul, BorderLayout.CENTER);


        JPanel przyciski = new JPanel(new FlowLayout());
        przyciski.setBackground(Color.decode("#f0bd8d"));
        // Przycisk do otwierania stodoly
        StodolaPodglad buttonStodola = new StodolaPodglad();
        przyciski.add(buttonStodola);

        // Przycisk do wyswietlania informacji o produktach
        KatalogProduktow buttonKatalog = new KatalogProduktow();
        przyciski.add(buttonKatalog);

        panel_informacje.add(przyciski, BorderLayout.SOUTH);

        // Dodawanie panelu do ramki
        this.add(panel_informacje,BorderLayout.NORTH);
    }

    private JButton createButton(Kawalek_Ziemi kawalekZiemi) {
        JButton button = new JButton();
        button.setText("wybierz");
        button.setBackground(Color.decode("#c49568"));
        button.setFont(new Font("Monospaced", Font.BOLD, 12));
        button.setBorder(new LineBorder(Color.decode("#26180b"),2,false));

        //zmienianie koloru po najechaniu na przycisk
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(false);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(true);
            }
        });

        // Ustawianie akcji po kliknięciu przycisku
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Wyświetlanie informacji na temat kawałka ziemi po kliknięciu
                if(Gra.getInstance().getPozostalyCzas()!=0) {

                    int kawalek_ziemi_x = (button.getY()-1)/72;
                    int kawalek_ziemi_y = (button.getX()-1)/72;
                    Kawalek_Ziemi pole = Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];

                    System.out.println("Jest to pole: "+(kawalek_ziemi_x)+", "+(kawalek_ziemi_y));
                    if ((pole instanceof Pole_Uprawne) || (pole instanceof Zagroda) || (pole instanceof Przetwornia)) {
                        ArrayList <Obserwator> obserwatorzy = Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].getObserwatorzy();
                        InformacjeProduktPole InformacjePole = (InformacjeProduktPole) obserwatorzy.get(0);
                        System.out.println("To pole jest okreslone, czy gotowe: " + InformacjePole.isGotoweDoZebrania() + ", czy produkuje: " + InformacjePole.isCzyProdukuje());

                        if (InformacjePole.isCzyProdukuje()) {
                            System.out.println("Trwa produkcja, czy gotowe: " + InformacjePole.getPozostalyCzas()+ " produkuje: " + InformacjePole.getNazwa());
                            if(InformacjePole.isGotoweDoZebrania()) {
                                System.out.println("Zbieram");
                                if (pole instanceof Pole_Uprawne) {
                                    Pole_Uprawne poleUprawne = (Pole_Uprawne) Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];
                                    poleUprawne.zbierzUprawe();
                                }
                                else if (pole instanceof Zagroda) {
                                    Zagroda zagroda = (Zagroda) Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];
                                    zagroda.zbierzProdukt();
                                }
                                else if (pole instanceof Przetwornia) {
                                	Przetwornia przetwornia = (Przetwornia) Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];
                                	przetwornia.zbierzPrzetwor();
                                }
                            }
                            // Mozna dodac opcje przerwania produkcji
                        }
                        else {
                            if (pole instanceof Pole_Uprawne) {
                                System.out.println("mozna zasadzic, nic nie jest produkowane");
                                // Tworzymy nowe okno
                                OkienkoZasadz newFrame = new OkienkoZasadz(kawalek_ziemi_x, kawalek_ziemi_y);
                            }
                            if (pole instanceof Zagroda) {
                                Zagroda zagroda = (Zagroda) Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];

                                if (zagroda.getZwierze() == null) {
                                    // Tworzymy nowe okno
                                    System.out.println("mozna kupic zwierzeta, zagroda jest pusta");
                                    OkienkoZwierzeta newFrame = new OkienkoZwierzeta(kawalek_ziemi_x, kawalek_ziemi_y);
                                } else {
                                    System.out.println("zagroda jest zajeta");
                                    // Tworzymy nowe okno
                                    OkienkoNakarm newFrame = new OkienkoNakarm(kawalek_ziemi_x, kawalek_ziemi_y);
                                }
                                // Tworzymy nowe okno
                                //OkienkoZasadz newFrame = new OkienkoZwierzeta(kawalek_ziemi_x, kawalek_ziemi_y);
                            }
                            if (pole instanceof Przetwornia) {
                                System.out.println("mozna wybrac przetwor, nic nie jest produkowane");
                                // Tworzymy nowe okno
                                OkienkoProdukcja newFrame = new OkienkoProdukcja(kawalek_ziemi_x, kawalek_ziemi_y);
                            }
                        }

                        if (pole instanceof Pole_Uprawne) {
                            System.out.println("To pole uprawne");
                        }
                        else if (pole instanceof Zagroda) {
                            System.out.println("To zagroda stan:");
                            pole.getStan();
                        }
                        else if (pole instanceof Przetwornia) {
                            System.out.println("To przetwornia");
                        }
                    }

                    else {
                        System.out.println("To nieokreslone pole");
                    }
                }
            }
        });

        // Tworzenie menu kontekstowego
        JPopupMenu popupMenu = new JPopupMenu();

        // Dodawanie opcji do menu kontekstowego
        JMenuItem poleUprawneItem = new JMenuItem("Pole Uprawne");
        poleUprawneItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int kawalek_ziemi_x = (button.getY()-1)/72;
                int kawalek_ziemi_y = (button.getX()-1)/72;
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y] = new Pole_Uprawne(kawalek_ziemi_x, kawalek_ziemi_y);
                // podmiana elementu z kawałkaa Pole Uprawne
                ImageIcon icon = new ImageIcon("grafika/pustePoleUprawne.png");
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].setIkonka(icon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
                button.setIcon(new ImageIcon(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].getIkonka()));
                button.setBorder(new LineBorder(Color.decode("#260e05"),2,false));
                button.setText("");
                System.out.println("Kawałek ziemi o współrzędnych (" + ((button.getX()/76)+1) + ", " + (button.getY()/72+1) + ") stał się Polem Uprawnym. ");
            }
        });
        popupMenu.add(poleUprawneItem);

        JMenuItem przetworniaItem = new JMenuItem("Przetwornia");
        przetworniaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int kawalek_ziemi_x = (button.getY()-1)/72;
                int kawalek_ziemi_y = (button.getX()-1)/72;
                //  podmiana elementu z kawałka na Przetwornię
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y] = new Przetwornia(kawalek_ziemi_x, kawalek_ziemi_y);
                ImageIcon icon = new ImageIcon("grafika/pustaPrzetwornia.png");
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].setIkonka(icon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
                button.setIcon(new ImageIcon(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].getIkonka()));
                button.setText("");
                button.setBorder(new LineBorder(Color.decode("#4b6120"),2,false));
                System.out.println("Kawałek ziemi o współrzędnych (" + ((button.getX()/76)+1) + ", " + (button.getY()/72+1) + ") stał się Przetwórnią. ");
            }
        });
        popupMenu.add(przetworniaItem);

        JMenuItem zagrodaItem = new JMenuItem("Zagroda");
        zagrodaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // podmiana elementu z kawałka na zagrode
                int kawalek_ziemi_x = (button.getY()-1)/72;
                int kawalek_ziemi_y = (button.getX()-1)/72;
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y] = new Zagroda(kawalek_ziemi_x, kawalek_ziemi_y);
                ImageIcon icon = new ImageIcon("grafika/pustaZagroda.png");
                Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].setIkonka(icon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
                button.setIcon(new ImageIcon(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].getIkonka()));
                button.setBorder(new LineBorder(Color.decode("#4b6120"),2,false));
                button.setText("");
                System.out.println("Kawałek ziemi o współrzędnych (" + ((button.getX()/76)+1) + ", " + (button.getY()/72+1) + ") stał się Zagrodą. ");
            }
        });
        popupMenu.add(zagrodaItem);
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                int kawalek_ziemi_x = (button.getY()-1)/72;
                int kawalek_ziemi_y = (button.getX()-1)/72;
                Kawalek_Ziemi pole = Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y];
                if (me.getButton() == MouseEvent.BUTTON3) {
                    // czy już zagospodarowany :\
                    if (pole instanceof Przetwornia == false && pole instanceof Zagroda == false && pole instanceof Pole_Uprawne == false) {
                        // Jeśli nie, pokazujemy menu wyboru
                        popupMenu.show(me.getComponent(), me.getX(), me.getY());
                    }
                }
            }
        });


        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kawalek_ziemi_x = (button.getY()-1)/72;
                int kawalek_ziemi_y = (button.getX()-1)/72;
                if (Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y] != null) button.setIcon(new ImageIcon(Gra.getInstance().getFarmaGracza().getKawalki_ziemi()[kawalek_ziemi_x][kawalek_ziemi_y].getIkonka()));
            }
        });
        timer.start();



        return button;
    }
}
