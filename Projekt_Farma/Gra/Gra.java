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


    private Gra()
    {
        this.liczbaMonet = 200;
        this.pozostalyCzas = CZAS_STARTOWY;
        this.farmaGracza = new Farma();
        startTimer();
        uruchomTimerDoKatastrof();
        this.Wyniki = new ArrayList<>();
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
        for(int i=0; i<Wyniki.size(); i++)
        {
            if (Wyniki.get(i).equals(""))
            {
                Wyniki.remove(i);
            }
        }
        Wyniki.sort(Gra::porownajWyniki);
        Ranking ranking = new Ranking();

    }

    private static void Serializacja()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Tabela_wynikow.txt", true))) {
            writer.write(farmaGracza.getNazwaFarmy() + ", Wynik: " + MonetyNaKoniec);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserializacja()
    {
        Wyniki.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("Tabela_wynikow.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Wyniki.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //zeby byly malejaco w rankingu
    private static int porownajWyniki(String linia1, String linia2) {
        int monety1 = Integer.parseInt(linia1.split(":")[1].trim());
        int monety2 = Integer.parseInt(linia2.split(":")[1].trim());
        return Integer.compare(monety2, monety1);
    }

    public void uruchomTimerDoKatastrof() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                katastrofa();
            }
        }, 0, 20 * 1000);  // timer wywoluje metode katastrofa() co 20s
    }
    public  String katastrofa() {

        String  komunikat=null;//ten komunikat bedziemy pozniej wyswietlac w wyskakujacym okienku informaujacym o katastrofie

        if (CzyBedzieKatastrofa() == 1) {

            Random random = new Random();
            int wylosowanaLiczba = random.nextInt(4) + 1;

            if (wylosowanaLiczba == 1) {
                strategiaInterfejs = new Strategia1();
                strategiaInterfejs.katastrofa();
                komunikat= "Twoja stodoła spłoneła.Tracisz wszystkie produkty";

            } else if (wylosowanaLiczba == 2) {
                strategiaInterfejs = new Strategia2();
                strategiaInterfejs.katastrofa();
                komunikat="Twoje uprawy zostały zniszczone";

            } else if (wylosowanaLiczba == 3) {
                strategiaInterfejs = new Strategia3();
                strategiaInterfejs.katastrofa();
                komunikat= "Twoje zwierzęta są chore, nie mogą teraz nic produkować";

            } else if(wylosowanaLiczba == 4){
                strategiaInterfejs = new Strategia4();
                strategiaInterfejs.katastrofa();
                komunikat="Wygląda na to, że nie masz prądu, niestety twoje przetwórnie teraz nie działają";
            }
        }
        return komunikat;
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


}
