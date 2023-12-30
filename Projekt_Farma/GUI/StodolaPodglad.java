package GUI;

import Produkt.Produkt;
import Stodola.Stodola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class StodolaPodglad extends JButton
{
    public StodolaPodglad()
    {
        super();
        init();
    }
    public void init()
    {
        this.setText("STODOŁA");
        this.setFont(new Font("Monospaced", Font.BOLD, 15));
        this.addActionListener(new StodolaListener());
    }

    class StodolaListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            showLista();
        }

        private void showLista()
        {
            Map<Produkt, Integer> produkty = Stodola.getInstance().getProdukty();

            JDialog ramka = new JDialog();
            ramka.setTitle("Stodoła");
            ramka.setFont(new Font("Monospaced", Font.BOLD, 15));
            ramka.setSize(200, 300);
            ramka.setResizable(false);
            ramka.setLocationRelativeTo(null);
            ramka.setAlwaysOnTop(true);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            list.setFont(new Font("Monospaced", Font.BOLD, 15));

            // dodaje elementy do listy na podstawie zawartości mapy
            for (Map.Entry<Produkt, Integer> entry : produkty.entrySet())
            {
                if (entry.getValue() != 0 )
                {
                    String pair = entry.getKey().getNazwa() + " - " + entry.getValue();
                    listModel.addElement(pair);
                }
            }
            // klikanie w produkty
            list.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    JList<String> list = (JList<String>) e.getSource();
                    if (e.getClickCount() == 2)
                    {
                        int index = list.locationToIndex(e.getPoint());
                        String wybranyProdukt = listModel.getElementAt(index).split(" - ")[0];
                        wyswietlSzczegoly(wybranyProdukt);
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(list);
            ramka.getContentPane().add(scrollPane, BorderLayout.CENTER);

            ramka.setVisible(true);
        }

        private static void wyswietlSzczegoly(String wybranyProdukt) {
            JDialog szczegoly = new JDialog();
            szczegoly.setSize(150, 150);
            szczegoly.setFont(new Font("Monospaced", Font.BOLD, 15));
            szczegoly.setLayout(new BorderLayout());
            szczegoly.setAlwaysOnTop(true);
            szczegoly.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea(3, 20);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
            int cenaProduktu = Stodola.getInstance().getCenaProduktu(wybranyProdukt);
            textArea.setText("cena: " + cenaProduktu + ";");

            JLabel label = new JLabel(wybranyProdukt);
            label.setFont(new Font("Monospaced", Font.BOLD, 15));

            JButton sprzedaj = new JButton("Sprzedaj");
            sprzedaj.setFont(new Font("Monospaced", Font.BOLD, 15));
            sprzedaj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // produkt.sprzedaj (wybierz ile, zwieksz monety, zmniejsz produkty itp)
                    JOptionPane.showMessageDialog(szczegoly, "Dokonano sprzedaży!");
                }
            });

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(sprzedaj);

            szczegoly.add(textArea, BorderLayout.CENTER);
            szczegoly.add(label, BorderLayout.NORTH);
            szczegoly.add(buttonPanel, BorderLayout.SOUTH);

            szczegoly.setVisible(true);
        }

    }
}
