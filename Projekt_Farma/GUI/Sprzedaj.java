package GUI;

import Gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sprzedaj extends JButton
{
    private final String produkt;
    public Sprzedaj(String produkt)
    {
        super("Sprzedaj " + produkt);
        this.produkt = produkt;
        init();
    }

    private void init()
    {
        this.setFont(new Font("Monospaced", Font.BOLD, 15));
        this.addActionListener(new SprzedajListener());
    }

    private class SprzedajListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JDialog dialog = new JDialog();
            dialog.setTitle("sprzedaż");
            dialog.setFont(new Font("Monospaced", Font.BOLD, 15));
            dialog.setSize(150, 150);
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);
            dialog.setAlwaysOnTop(true);

            JPanel panel = new JPanel();

            int dostepnosc = Gra.getInstance().getFarmaGracza().getStodola().sprawdzDostepnosc(produkt);
            Integer[] numbers = new Integer[dostepnosc];
            for (int i = 0; i < dostepnosc; i++) {
                numbers[i] = i + 1;
            }
            JComboBox<Integer> comboBox = new JComboBox<>(numbers);

            JButton ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int ile = (int) comboBox.getSelectedItem();
                    System.out.println(ile);
                    Gra.getInstance().getFarmaGracza().getStodola().sprzedaj(produkt, ile);
                    int zarobek = ile * Gra.getInstance().getFarmaGracza().getStodola().getCenaProduktu(produkt);
                    Gra.getInstance().dodajMonety(zarobek);
                    dialog.dispose();
                }
            });

            panel.add(comboBox);
            panel.add(ok);
            dialog.add(panel);

            dialog.setVisible(true);
        }
    }


}
