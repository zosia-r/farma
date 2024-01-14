package Gra;
import Farma.Farma;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import Ranking.Ranking;
import java.io.ObjectOutput;
import java.io.*;
import java.util.*;
import java.util.Random;
import Strategia.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.FloatControl;

public class Gra implements Serializable
{
    private static Gra instance;
    private static final int CZAS_STARTOWY = 300; // czas w sekundach (5 minut)
    private int liczbaMonet;   // 200 na początku gry
    private int pozostalyCzas; // w sekundach
    private static Farma farmaGracza; // farma gracza
    Strategia strategiaInterfejs;

    private static ArrayList<String> Wyniki; //zeby stad pobrac dane do wyswietlanego rankingu
    private static int MonetyNaKoniec; //zeby uzyc w statycznej metodzie serializacji

    private TablicaWynikow tabwynikow;


    private Gra()
    {
        this.liczbaMonet = 200;
        this.pozostalyCzas = CZAS_STARTOWY;
        this.farmaGracza = new Farma();
        startTimer();
        uruchomTimerDoKatastrof();
        this.Wyniki = new ArrayList<>();
        this.tabwynikow = new TablicaWynikow();
//        playMusic();
    }
    public static Gra getInstance()
    {
        if (instance == null)
            instance = new Gra();
        return instance;
    }
    public int getLiczbaMonet()
    {
        return liczbaMonet;
    }
    public int getPozostalyCzas()
    {
        return pozostalyCzas;
    }
    public String getPozostalyCzasS() //metoda potrzebna zeby ladnie wyswietlac sekundy (np. 2:01 zamiast 2:1)
    {
        if(pozostalyCzas%60<10)
            return "0"+pozostalyCzas%60;
        else
            return String.valueOf(pozostalyCzas%60);
    }

    public static ArrayList<String> getWyniki()
    {
        return Wyniki;
    }

    public  TablicaWynikow getTabwynikow()
    {
        return tabwynikow;
    }
    public Farma getFarmaGracza()
    {
        return farmaGracza;
    }
    public void setLiczbaMonet(int liczbaMonet)
    {
        this.liczbaMonet=liczbaMonet;
    }
    public void setPozostalyCzas(int pozostalyCzas)
    {
        this.pozostalyCzas=pozostalyCzas;
    }
    public void setFarmaGracza(Farma farma)
    {
        this.farmaGracza = farma;
    }

    public void setMonetyNaKoniec(int MonetyNaKoniec)
    {
        this.MonetyNaKoniec = MonetyNaKoniec;
    }
    public void getStan()
    {
        System.out.println("Obecna liczba monet: "+getLiczbaMonet());
        System.out.println("Pozostały czas gry: "+aktualizujCzas());

    }
    public void rozpocznijGre()
    {
        //zapytanie o nazwe farmy
        //podział na typy zagospodarowania
    }
    public void odejmijMonety(int iloscMonet) {
        if (iloscMonet > 0) {
            liczbaMonet -= iloscMonet;
        } else {
            System.out.println("Nieprawidłowa ilość monet do odjęcia.");
        }
    }
    public void dodajMonety(int iloscMonet)
    {
        if (iloscMonet > 0)
            liczbaMonet += iloscMonet;
        else
            System.out.println("Nieprawidłowa ilość monet.");
    }
    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (pozostalyCzas > 0) {
                    pozostalyCzas--;
                    aktualizujCzas();
                } else {
                    koniecGry();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    private String aktualizujCzas() {
        int minuty = pozostalyCzas / 60;
        int sekundy = pozostalyCzas % 60;
        return minuty + " minut " + sekundy + " sekund";
    }

    private void koniecGry() {
        System.out.println("Koniec gry!");
        // Tutaj zapis wyniku do pliku z rankingiem?
        dodajMonety(farmaGracza.getStodola().getWycena());
        setMonetyNaKoniec(liczbaMonet);
        Serializacja();
        Deserializacja();
        Wyniki.clear();
        for(int i=0; i<tabwynikow.getWyniki().size(); i++)
        {
            if (tabwynikow.getWyniki().get(i).equals(""))
            {
                tabwynikow.getWyniki().remove(i);
            }
        }
        tabwynikow.getWyniki().sort(new TablicaWynikow.WynikCompare_Monety());
        //Collections.sort(tabwynikow.getWyniki(), tabwynikow.getComparator1());
        for(int j=0; j<tabwynikow.getWyniki().size(); j++)
        {
            Wyniki.add(((WynikKoncowy)((tabwynikow.getWyniki()).get(j))).toString());
        }
        Ranking ranking = new Ranking();

    }

    private static void Serializacja()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Tabela_wynikow.txt", true))) {
            writer.write(farmaGracza.getNazwaFarmy() + "," + MonetyNaKoniec);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserializacja()
    {
        tabwynikow.getWyniki().clear();
        tabwynikow.StworzKomparator();
        try (BufferedReader reader = new BufferedReader(new FileReader("Tabela_wynikow.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] czesci = line.split(",");
                tabwynikow.getWyniki().add(new WynikKoncowy(czesci[0], Integer.parseInt(czesci[1])));
                Collections.sort(tabwynikow.getWyniki(), tabwynikow.getComparator1());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void uruchomTimerDoKatastrof() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                wywolanieKatastrofy();
            }
        }, 0, 20 * 1000);  // timer wywoluje metode katastrofa() co 20s
    }
    public void wywolanieKatastrofy() {

        if (CzyBedzieKatastrofa() == 1 && pozostalyCzas>1 && pozostalyCzas<299) {

            Random random = new Random();
            int wylosowanaLiczba = random.nextInt(4) + 1;

            if (wylosowanaLiczba == 1) {
                strategiaInterfejs = new Strategia1("Twoja stodoła spłonęła. Tracisz wszystkie produkty");
                strategiaInterfejs.katastrofa();

            } else if (wylosowanaLiczba == 2) {
                strategiaInterfejs = new Strategia2("Twoje uprawy zostały zniszczone") ;
                strategiaInterfejs.katastrofa();

            } else if (wylosowanaLiczba == 3) {
                strategiaInterfejs = new Strategia3();
                strategiaInterfejs.katastrofa();

            } else if(wylosowanaLiczba == 4){
                strategiaInterfejs = new Strategia4("Wygląda na to, że nie masz prądu. Niestety twoje przetwórnie teraz nie działają");
                strategiaInterfejs.katastrofa();
            }
        }
    }
    public int CzyBedzieKatastrofa()
    {
        // prawdopodobieństwa dla 1(bedzie katastrofa) i 0 (nie bedzie katastrofy) -w procentach
        double prawdopodobienstwo_1 = 25;
        double prawdopodobienstwo_0 = 100 - prawdopodobienstwo_1;

        Random random = new Random();
        int wylosowanaLiczba=random.nextInt(100) + 1;
        if (wylosowanaLiczba < prawdopodobienstwo_1) {
            return 1;
        } else {
            return 0;
        }

    }
//    private void playMusic() {
//        try {
//            if (pozostalyCzas > 1) {
//                URL url = this.getClass().getClassLoader().getResource("ost-farma-sample2.wav");
//                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioIn);
//                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                float range = volume.getMaximum() - volume.getMinimum();
//                float gain = (range * 0.6f) + volume.getMinimum();
//                volume.setValue(gain);
//                clip.loop(Clip.LOOP_CONTINUOUSLY);
//                clip.start();
//
//                // Dodajemy nowy wątek, który będzie sprawdzał pozostalyCzas co sekundę
//                new Thread(() -> {
//                    while (clip.isRunning()) {
//                        if (pozostalyCzas < 10) {
//                            clip.stop();
//                            break;
//                        }
//                        try {
//                            Thread.sleep(1000); // Czekamy sekundę przed kolejnym sprawdzeniem
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }
}
