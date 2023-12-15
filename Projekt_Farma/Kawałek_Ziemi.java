package Kawałek_Ziemi;
import Farma.Farma;

public abstract class Kawałek_Ziemi extends Farma
{
    private int x;  // pierwsza współrzędna kawałka
    private int y;  // druga współrzędna kawałka

    public Kawałek_Ziemi(int x, int y)
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
        super.getStan();
        System.out.println("Współrzędne punktu to: ("+getX()+","+getY()+")");
    }
}
