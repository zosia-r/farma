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
        this.addActionListener(new StodolaListener());
    }

    static class StodolaListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Map<Produkt, Integer> produkty = Gra.getInstance().getFarmaGracza().getStodola().getProdukty();

            JDialog ramka = new JDialog();
            ramka.setTitle("Stodoła");
            ramka.setFont(new Font("Monospaced", Font.BOLD, 15));
            ramka.setSize(400, 400);
            ramka.setResizable(false);
            ramka.setLocationRelativeTo(null);
            ramka.setAlwaysOnTop(true);

            JPanel panel = new JPanel(new GridLayout(0, 3));

            for (Map.Entry<Produkt, Integer> entry : produkty.entrySet())
            {
                ImageIcon ikona = new ImageIcon(entry.getKey().getIkonka().getPath());
                Image scaledImage = ikona.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JButton button = new JButton(scaledIcon);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);

                button.addActionListener(new ProduktListener(entry.getKey().getNazwa()));
                panel.add(button);
            }

            ramka.getContentPane().add(panel, BorderLayout.CENTER);
            ramka.setVisible(true);
        }

        private static class ProduktListener implements ActionListener {
            private final String nazwaProduktu;

            public ProduktListener(String nazwaProduktu) {
                this.nazwaProduktu = nazwaProduktu;
            }

            @Override
            public void actionPerformed(ActionEvent e)
            {
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


                Sprzedaj sprzedaj = new Sprzedaj(nazwaProduktu);

                panel.add(sprzedaj, BorderLayout.SOUTH);
                szczegoly.add(panel);

                szczegoly.setVisible(true);
            }

        }

    }
}
