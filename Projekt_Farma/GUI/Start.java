package GUI;

import Gra.Gra;
import Farma.Farma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame
{
    private int szerokosc = 600;
    private int wysokosc = 300;

    private JLabel tytul;
    private JTextField poleTekstowe;

    public Start()
    {
        super();
        init();
    }
    private void init()
    {
        this.setTitle("FARMA");
        this.setSize(szerokosc, wysokosc);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        wstawKomponenty();

        this.setVisible(true);
    }

    private static class Tlo extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Image obrazek = new ImageIcon("C:\\Users\\Zosia\\OneDrive\\Pulpit\\ist\\FARMA\\ikonki\\trawa2.png").getImage();
            g.drawImage(obrazek, 0, 0, this);

        }
    }

    private void wstawKomponenty()
    {
        Tlo tlo = new Tlo();
        tlo.setLayout(new BorderLayout());
        this.getContentPane().add(tlo);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        tytul = new JLabel("FARMA", SwingConstants.CENTER);
        tytul.setFont(new Font("Monospaced", Font.BOLD, 100));
        panel.add(tytul, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel etykieta = new JLabel("Wpisz nazwę swojej farmy");
        etykieta.setFont(new Font("Monospaced", Font.BOLD, 10));
        poleTekstowe = new JTextField(15);
        JButton button = new JButton("START!");
        button.setFont(new Font("Monospaced", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(etykieta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(poleTekstowe, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(button, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        tlo.add(panel);

        poleTekstowe.addActionListener(new poleTekstoweListerer());
        button.addActionListener(new buttonListener());
    }

    class poleTekstoweListerer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String nazwa = poleTekstowe.getText();
            Farma.getInstance().setNazwaFarmy(nazwa);
        }

    }

    class buttonListener implements ActionListener
    {
       @Override
       public void actionPerformed(ActionEvent e)
        {
            GUI gui = new GUI(Gra.getInstance());
            zamknijRamke();
        }
    }

    private void zamknijRamke()
    {
        this.dispose();
    }

}


