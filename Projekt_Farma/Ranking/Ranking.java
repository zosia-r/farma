package Ranking;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import Gra.*;
import javax.sound.sampled.*;


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
//        playMusic();
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
//    private void playMusic() {
//        try {
//            URL url = this.getClass().getClassLoader().getResource("Victory-Theme-Happy-Wheels.wav");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            float range = volume.getMaximum() - volume.getMinimum();
//            float gain = (range * 0.6f) + volume.getMinimum();
//            volume.setValue(gain);
//
//            clip.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }
}
