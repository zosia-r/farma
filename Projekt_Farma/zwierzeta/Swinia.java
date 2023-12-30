package zwierzeta;

import Produkt.Produkt;

public class Swinia extends Zwierze {
	
	private int ileZjadla;
	private boolean zywa;
	private Rzeznik rzeznik;
	
	public Swinia()
	{
		super();
		ileZjadla = 0;
		zywa = true;
	}
	
	public void Rosnij()
	{
		if (getGlodne() == false)
		{
			ileZjadla++;
			setGlodne(true);
		}
		
	}
	
	public void Rzeznia()
	{
	    if(ileZjadla >= 3)
	            {
	                produktyZ.add(new Produkt("Mieso", true, 2, 0, "ikonka_miesa"));
	                zywa = false;
	                rzeznik.zabijSwinie();
	                
	                
	                
	            }
	}
	
	

	public int getIleZjadla() 
	{
		return ileZjadla;
	}

	public void setIleZjadla(int ileZjadla) {
		this.ileZjadla = ileZjadla;
	}

	public boolean getZywa() {
		return zywa;
	}

	public void setZywa(boolean zywa) {
		this.zywa = zywa;
	}
	
	

}
