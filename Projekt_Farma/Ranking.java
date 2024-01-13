package Ranking;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Gra.*;

public class Ranking extends JFrame {
    private ArrayList<String> wyniki;

    public Ranking() {
        this.wyniki = Gra.getWyniki();

        setTitle("Ranking Wyników");
        setSize(480, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Ranking wyników", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLUE);
        panel.add(titleLabel);

        for (int i = 0; i < wyniki.size(); i++) {
            String wynik = wyniki.get(i);
            String[] parts = wynik.split(" ");
            JLabel wynikLabel = new JLabel((i + 1) + ". " + parts[0] + " | " + parts[1]+parts[2]);
            wynikLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(wynikLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void wczytajWyniki() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Tabela_wynikow.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wyniki.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
