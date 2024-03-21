public class Dozent extends Person{
	private String bueronummer;
	private String telefonNummer;

	public Dozent(String name, String iD, String bueronummer, String telefonNummer){
		super(name, iD);
		this.bueronummer = bueronummer;
		this.telefonNummer = telefonNummer;
	}

	public String gibBuero(){
		return  bueronummer;
	}

	public String gibTelefonnummer(){
		return telefonNummer;
	}
}
