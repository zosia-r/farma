package Wyjatek;

public class FieldNotEmptyException extends Exception
{
    public FieldNotEmptyException()
    {
        super();
    }

    public String getMessage()
    {
        return "pole jest już zajęte przez: ";
    }
}
