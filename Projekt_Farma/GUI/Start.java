package GUI;

import Gra.Gra;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame
{
    private int szerokosc = 600;
    private int wysokosc = 336;

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
            Image obrazek = new ImageIcon("grafika/farma.gif").getImage();
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
        tytul.setForeground(Color.decode("#26180b"));
        panel.add(tytul, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel etykieta = new JLabel("Wpisz nazwę swojej farmy");
        etykieta.setFont(new Font("Monospaced", Font.BOLD, 15));
        etykieta.setForeground(Color.decode("#f0bd8d"));
        poleTekstowe = new JTextField(15);
        //ograniczam liczbe wpisywanych znakow do 9
        ((AbstractDocument) poleTekstowe.getDocument()).setDocumentFilter(new DocumentFilter() {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= 9)
                super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if ((fb.getDocument().getLength() + text.length() - length) <= 9)
                super.replace(fb, offset, length, text, attrs);
        }
        });

        poleTekstowe.setFont(new Font("Monospaced", Font.BOLD, 15));
        poleTekstowe.setBackground(Color.decode("#f0bd8d"));
        poleTekstowe.setBorder(new LineBorder(Color.decode("#26180b"),1));
        JButton button = new JButton("START!");
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setForeground(Color.decode("#f0bd8d"));
        button.setBackground(Color.decode("#26180b"));
        button.setBorder(new LineBorder(Color.decode("#f0bd8d"),3,true));

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

        button.addActionListener(new buttonListener());
    }

    class buttonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String nazwa = poleTekstowe.getText();
            Gra.getInstance().getFarmaGracza().setNazwaFarmy(nazwa);
            GUI gui = new GUI();
            zamknijRamke();
        }
    }

    private void zamknijRamke()
    {
        this.dispose();
    }

}


