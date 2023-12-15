import Farma.Farma;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame {
    private static final int wymiarFarmy = 5;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setTitle("Interfejs Graficzny Farmy");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JPanel panel = new JPanel(new GridLayout(wymiarFarmy, wymiarFarmy));

        // Tworzenie farmy
        Farma farma = new Farma("Moja Farma", wymiarFarmy);

        // Dodawanie przycisków do panelu
        for (int i = 0; i < wymiarFarmy; i++) {
            for (int j = 0; j < wymiarFarmy; j++) {
                JButton button = createButton(farma.getKawałki_ziemi()[i][j]);
                panel.add(button);
            }
        }

        // Dodawanie panelu do ramki
        this.add(panel);

        this.setVisible(true);
    }

    private JButton createButton(int kawałekZiemi) {
        JButton button = new JButton();
        Farma farma = new Farma("Moja Farma", wymiarFarmy);
        // Ustawianie akcji po kliknięciu przycisku
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Wyświetlanie informacji na temat kawałka ziemi po kliknięciu
                System.out.println("Jest to pole: "+farma.getKawałki_ziemi());
            }
        });

        return button;
    }
}
