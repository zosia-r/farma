
import Stodola.Stodola;
import Produkt.Produkt;

public class Main {
    public static void main(String[] args)
    {
//        // Tworzymy ramkę (okno)
//        JFrame frame = new JFrame("Prosty Interfejs Graficzny");
//        // Tworzymy przycisk
//        JButton button = new JButton("Gra farma!");
//        // Dodajemy obsługę zdarzeń (event listener) do przycisku
//        button.addActionListener(e -> {
//            // Wyświetlamy komunikat po kliknięciu przycisku
//            JOptionPane.showMessageDialog(frame, "under construction");
//        });
//        // Dodajemy przycisk do ramki
//        frame.getContentPane().add(button);
//        // Ustawiamy rozmiar ramki
//        frame.setSize(300, 200);
//        // Ustawiamy operację zamknięcia ramki
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // Wyświetlamy ramkę
//        frame.setVisible(true);

        // Uruchomienie GUI
        GUI gui = new GUI();
		gui.setVisible(true);

        //tworzenie stodoly i dodawanie do niej produktow
        Stodola stodola = new Stodola();
        stodola.wyswietlProdukty();

        Produkt produkt1 = new Produkt("Pszenica",false,20,30,"adres");
        produkt1.zbierz();
        produkt1.setGotoweDoZebrania(true);
        produkt1.zbierz();

        Produkt produkt2 = new Produkt("Jablko",true,10,15,"adres");
        produkt2.zbierz();

        stodola.wyswietlProdukty();
    }
}

