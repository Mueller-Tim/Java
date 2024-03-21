import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen Briefdrucker
 * 
 * @author tebe
 */
public class Briefdrucker {

	private final BriefdruckStrategie briefdruckStrategie;


	public Briefdrucker(BriefdruckStrategie briefdruckStrategie){
		this.briefdruckStrategie = briefdruckStrategie;
	}

	public void druckeBrief(Brief brief){
		briefdruckStrategie.druckeBrief(brief);
	}

	public void druckeSerienBrief(ArrayList<Adresse> empfängerListe, Adresse sender, Inhalt inhalt){
		for(Adresse empfänger: empfängerListe){
			briefdruckStrategie.druckeBrief(new Brief(empfänger, sender, inhalt));
		}
	}
}
