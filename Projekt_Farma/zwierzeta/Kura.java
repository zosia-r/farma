package zwierzeta;

import Produkt.Produkt;

public class Kura extends Zwierze {
	
	
	public Kura()
	{
		super();
	}
	
	public void znies()
	{
		if (getGlodne() == false)
		{
			produktyZ.add(new Produkt("jajko", true, 2, 0, "ikonka_jajka"));
			setGlodne(true);
		}
		
	}