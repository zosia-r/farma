package Kawalek_Ziemi;

import Obserwator.*;
import java.util.ArrayList;

public abstract class Kawalek_Ziemi implements Podmiot
{
    private int x;  // pierwsza współrzędna kawałka
    private int y;  // druga współrzędna kawałka

    public Kawalek_Ziemi(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public void getStan()
    {
        System.out.println("Współrzędne punktu to: ("+getX()+","+getY()+")");
    }

    //podmiot
    private ArrayList<Obserwator> obserwatorzy;
    //w naszym przypadku bedzie tylko jeden obserwator ale oficjalnie jest to zbior obserwatorow

    public ArrayList<Obserwator> getObserwatorzy() { return obserwatorzy; }

    public void dodajObserwatora(Obserwator o)
    {
        obserwatorzy.add(o);
    }

    public void usunObserwatora(Obserwator o)
    {
        int i = obserwatorzy.indexOf(o);
        if(i>=0)
            obserwatorzy.remove(i);
    }

    public abstract void powiadomObserwatorow();
    //bedzie zaimplementowana w kolejnych klasach (pole uprawne, zagroda, przetwornia)
    
}

