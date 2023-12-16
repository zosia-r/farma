package Kawalek_Ziemi;

public class Kawalek_Ziemi
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
}

