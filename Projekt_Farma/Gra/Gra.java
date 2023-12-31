package Gra;
import Farma.Farma;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import Ranking.Ranking;
import java.io.ObjectOutput;
import java.io.*;
import java.util.*;

public class Gra implements Serializable
{
    private static Gra instance;
    private static final int CZAS_STARTOWY = 300; // czas w sekundach (5 minut)
    private int liczbaMonet;   // 200 na początku gry
    private int pozostalyCzas; // w sekundach
    private static Farma farmaGracza; // farma gracza

    private static ArrayList<String> Wyniki; //zeby stad pobrac dane do wyswietlanego rankingu
    private static int MonetyNaKoniec; //zeby uzyc w statycznej metodzie serializacji


    private Gra()
    {
        this.liczbaMonet = 200;
        this.pozostalyCzas = CZAS_STARTOWY;
        this.farmaGracza = new Farma();
        startTimer();
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

    public ArrayList<String> getWyniki()
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

}
