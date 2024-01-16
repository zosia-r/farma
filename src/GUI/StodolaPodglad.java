package GUI;

import Gra.Gra;
import Produkt.Produkt;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class StodolaPodglad extends JButton {
    public StodolaPodglad() {
        super();
        init();
    }

    public void init() {
        this.setText("STODOŁA");
        this.setFont(new Font("Monospaced", Font.BOLD, 15));
        this.setBackground(Color.decode("#eee3da"));
        this.addActionListener(new StodolaListener());
    }

    static class StodolaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (Gra.getInstance().getPozostalyCzas() > 0)
            {
                LinkedHashMap<Produkt, Integer> produkty = Gra.getInstance().getFarmaGracza().getStodola().getProdukty();

                JDialog ramka = new JDialog();
                ramka.setTitle("Stodoła");
                ramka.setFont(new Font("Monospaced", Font.BOLD, 15));
                ramka.setSize(400, 400);
                ramka.setResizable(false);
                ramka.setLocationRelativeTo(null);
                ramka.setAlwaysOnTop(true);

                JPanel panel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        ImageIcon tlo = new ImageIcon("grafika/drewnianaSciana.png");
                        g.drawImage(tlo.getImage(), 0, 0, getWidth(), getHeight(), this);
                    }
                };

                panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

                for (Map.Entry<Produkt, Integer> entry : produkty.entrySet()) {
                    if (entry.getValue() > 0) {
                        ImageIcon ikona = new ImageIcon(entry.getKey().getIkonka().getPath());
                        Image scaledImage = ikona.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        JButton button = new JButton(scaledIcon);
                        button.setOpaque(false);
                        button.setContentAreaFilled(false);
                        button.setBorderPainted(false);

                        button.addActionListener(new ProduktListener(entry.getKey().getNazwa(), button));
                        panel.add(button);
                    }
                }

                ramka.setContentPane(panel);
                ramka.setVisible(true);
            }

        }
    }

    private static class ProduktListener implements ActionListener {
        private final String nazwaProduktu;
        private final JButton button;

        public ProduktListener(String nazwaProduktu, JButton button) {
            this.nazwaProduktu = nazwaProduktu;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog szczegoly = new JDialog();
            szczegoly.setTitle(nazwaProduktu);
            szczegoly.setFont(new Font("Monospaced", Font.BOLD, 15));
            szczegoly.setSize(150, 150);
            szczegoly.setResizable(false);
            szczegoly.setLocationRelativeTo(null);
            szczegoly.setAlwaysOnTop(true);

            JPanel panel = new JPanel(new BorderLayout());

            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
            int cena = Gra.getInstance().getFarmaGracza().getStodola().getCenaProduktu(nazwaProduktu);
            int ilosc = Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(nazwaProduktu);
            textArea.setText("Ilość: " + ilosc + "\n" + "Cena: " + cena);
            textArea.setEditable(false);

            panel.add(textArea, BorderLayout.CENTER);

            JButton sprzedaj = new JButton("sprzedaj");
            sprzedaj.setFont(new Font("Monospaced", Font.BOLD, 15));
            sprzedaj.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JDialog dialog = new JDialog();
                    dialog.setTitle("sprzedaż");
                    dialog.setFont(new Font("Monospaced", Font.BOLD, 15));
                    dialog.setSize(150, 75);
                    dialog.setResizable(false);
                    dialog.setLocationRelativeTo(null);
                    dialog.setAlwaysOnTop(true);

                    JPanel panel = new JPanel();

                    int dostepnosc = Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(nazwaProduktu);
                    Integer[] numbers = new Integer[dostepnosc];
                    for (int i = 0; i < dostepnosc; i++) {
                        numbers[i] = i + 1;
                    }
                    JComboBox<Integer> comboBox = new JComboBox<>(numbers);

                    JButton ok = new JButton("OK");
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (comboBox.getSelectedItem() != null) {
                                int ile = (int) comboBox.getSelectedItem();
                                System.out.println(ile);
                                Gra.getInstance().getFarmaGracza().getStodola().sprzedaj(nazwaProduktu, ile);
                                int zarobek = ile * Gra.getInstance().getFarmaGracza().getStodola().getCenaProduktu(nazwaProduktu);
                                Gra.getInstance().dodajMonety(zarobek);
                            }
                            dialog.dispose();
                            szczegoly.dispose();

                            int dostepnosc = Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(nazwaProduktu);

                            if (dostepnosc == 0) {
                                button.setVisible(false);
                            }
                        }
                    });

                    panel.add(comboBox);
                    panel.add(ok);
                    dialog.add(panel);

                    dialog.setVisible(true);
                }
            });

            panel.add(sprzedaj, BorderLayout.SOUTH);
            szczegoly.add(panel);

            szczegoly.setVisible(true);
        }
    }
}
