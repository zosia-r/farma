package GUI;

import Gra.Gra;
import Produkt.Produkt;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sklep extends JButton {
    public Sklep() {
        super();
        init();
    }

    public void init() {
        this.setText("SKLEP");
        this.setFont(new Font("Monospaced", Font.BOLD, 15));
        this.setBackground(Color.decode("#eee3da"));
        this.addActionListener(new SklepListener());
    }

    private static void wyswietlSklep()
    {
        if (Gra.getInstance().getPozostalyCzas() > 0)
        {
            LinkedHashMap<Produkt, Integer> produkty = Gra.getInstance().getFarmaGracza().getStodola().getProdukty();

            JDialog ramka = new JDialog();
            ramka.setTitle("Sklep");
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

            ramka.setContentPane(panel);
            ramka.setVisible(true);
        }
    }

    private static void wyswietlPanelZakupu(String nazwaProduktu)
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
        int cenaZakupu = Gra.getInstance().getFarmaGracza().getStodola().getCenaZakupuProduktu(nazwaProduktu);
        int ilosc = Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(nazwaProduktu);
        textArea.setText("Posiadasz: " + ilosc + "\n" + "Cena zakupu: " + cenaZakupu);
        textArea.setEditable(false);

        panel.add(textArea, BorderLayout.CENTER);

        JButton sprzedaj = new JButton("kup");
        sprzedaj.setFont(new Font("Monospaced", Font.BOLD, 15));
        sprzedaj.addActionListener(new SprzedajListener(nazwaProduktu, szczegoly));

        panel.add(sprzedaj, BorderLayout.SOUTH);
        szczegoly.add(panel);

        szczegoly.setVisible(true);
    }

    private static void wyswietlSprzedaz(String nazwaProduktu, JDialog szczegoly)
    {
        JDialog dialog = new JDialog();
        dialog.setTitle("kupno");
        dialog.setFont(new Font("Monospaced", Font.BOLD, 15));
        dialog.setSize(150, 75);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);

        JPanel panel = new JPanel();

        int maks = Gra.getInstance().getLiczbaMonet()/Gra.getInstance().getFarmaGracza().getStodola().getCenaZakupuProduktu(nazwaProduktu);
        Integer[] numbers = new Integer[maks];
        for (int i = 0; i < maks; i++) {
            numbers[i] = i + 1;
        }
        JComboBox<Integer> comboBox = new JComboBox<>(numbers);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OkButtonListener(nazwaProduktu, comboBox, dialog, szczegoly));

        panel.add(comboBox);
        panel.add(okButton);
        dialog.add(panel);

        dialog.setVisible(true);
    }


    static class SprzedajListener implements ActionListener
    {
        private String nazwaProduktu;
        private JDialog szczegoly;

        private SprzedajListener(String nazwaProduktu, JDialog szczegoly)
        {
            this.nazwaProduktu = nazwaProduktu;
            this.szczegoly = szczegoly;
        }
        public void actionPerformed(ActionEvent e) {
            wyswietlSprzedaz(nazwaProduktu, szczegoly);
        }
    }
    static class SklepListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            wyswietlSklep();
        }
    }

    static class OkButtonListener implements ActionListener
    {
        private String nazwaProduktu;
        JComboBox<Integer> comboBox;
        JDialog dialog;
        JDialog szczegoly;
        private OkButtonListener(String nazwaProduktu, JComboBox<Integer> comboBox, JDialog dialog, JDialog szczegoly)
        {
            this.nazwaProduktu = nazwaProduktu;
            this.comboBox = comboBox;
            this.dialog = dialog;
            this.szczegoly = szczegoly;
        }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (comboBox.getSelectedItem() != null) {
                int ile = (int) comboBox.getSelectedItem();
                System.out.println(ile);
                Gra.getInstance().getFarmaGracza().getStodola().kup(nazwaProduktu, ile);
            }
            dialog.dispose();
            szczegoly.dispose();
        }
    }

    private static class ProduktListener implements ActionListener {
        private final String nazwaProduktu;

        public ProduktListener(String nazwaProduktu, JButton button) {
            this.nazwaProduktu = nazwaProduktu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            wyswietlPanelZakupu(nazwaProduktu);
        }
    }
}
