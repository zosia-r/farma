package zwierzeta;

import Produkt.Produkt;

public class Krowa extends Zwierze {
	
	public Krowa()
	{
		super();
	}
	
	public void dojenie()
	{
		if (getGlodne() == false)
		{
			produktyZ.add(new Produkt("DzbanekMleka", true, 2, 0, "ikonka_mleka"));
			setGlodne(true);
		}
		
	}


	
}
