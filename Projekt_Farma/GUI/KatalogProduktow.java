package GUI;
import Gra.Gra;
import Produkt.Produkt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
public class KatalogProduktow extends JButton{
    public KatalogProduktow() {
        super();
        init();
    }

    public void init() {
        this.setText("KATALOG PRODUKTÓW");
        this.setFont(new Font("Monospaced", Font.BOLD, 15));
        this.addActionListener(new ListaProduktowListener());
    }

    static class ListaProduktowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Map<Produkt, Integer> produkty = Gra.getInstance().getFarmaGracza().getStodola().getProdukty();

            JFrame ramka = new JFrame("KATALOG PRODUKTÓW");
            ramka.setSize(600, 400);
            ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(0, 3, 20, 20));
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

            for (Map.Entry<Produkt, Integer> entry : produkty.entrySet()) {
                Produkt produkt = entry.getKey();

                ImageIcon ikona = new ImageIcon(produkt.getIkonka().getPath());
                Image scaledImage = ikona.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JLabel iconlabel = new JLabel(scaledIcon);
                panel.add(iconlabel);

                JLabel nazwaLabel = new JLabel( produkt.getNazwa());
                JLabel cenaSprzedarzyLabel = new JLabel("Cena Sprzedaży: " + produkt.getCena());
                JLabel cenaWytworzeniaLabel = new JLabel("Cena Wytworzenia: " + produkt.getCenaWytworzenia());
                JLabel czasProdukcjiLabel = new JLabel("Czas Produkcji: " + produkt.getCzasProdukcji());

                JPanel infoPanel = new JPanel(new GridLayout(0, 1));
                infoPanel.add(nazwaLabel);
                infoPanel.add(cenaSprzedarzyLabel);
                infoPanel.add(cenaWytworzeniaLabel);
                infoPanel.add(czasProdukcjiLabel);

                panel.add(infoPanel);

            }

            ramka.add(scrollPane);


            ramka.setLocationRelativeTo(null);
            ramka.setVisible(true);



        }
    }

}
