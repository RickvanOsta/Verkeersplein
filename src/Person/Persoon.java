package Person;

public class Persoon {
	
	private Datum datum;
	private String roepnaam;
	private String tussenvoegsel;
	private String achternaam;

	public Persoon(String naam, String tussen, String achternaam, String rijb, String geb){
		//Datum d = new Datum(rijb, geb);
		//this.datum = d;
		roepnaam = naam;
		tussenvoegsel = tussen;
		this.achternaam = achternaam;
	}
	
	public Datum getDatum(){
		return datum;
	}
	
	public String getNaam(){
		return roepnaam + " " + tussenvoegsel + " " + achternaam;
	}
	
}
