import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Diese Klasse implementiert ein Briefprogramm.
 *
 * @author tebe
 */
public class Briefprogramm {

	HashMap<Brief, Briefdrucker> formatierteBriefe = new HashMap<>();
	Briefdrucker standartBriefdrucker = new Briefdrucker(new StandardBriefdruckStrategie());
	Briefdrucker fensterBriefdrucker = new Briefdrucker(new FensterBriefdruckStrategie());

	/**
	 * main Methode.
	 *
	 * @param args Es werden keine Kommandozeilenparameter verwendet
	 */
	public static void main(String[] args) {
		Briefprogramm briefprogramm = new Briefprogramm();
		briefprogramm.start();
	}

	private void start() {
		//briefFormatieren(dummiBriefe().get(0), standartBriefdrucker);
		//briefFormatieren(dummiBriefe().get(1), fensterBriefdrucker);
		standartBriefdrucker.
				druckeAlleBriefe();
	}


	private void druckeAlleBriefe() {
		for (Brief brief : formatierteBriefe.keySet()) {
			formatierteBriefe.get(brief).druckeBrief(brief);
			System.out.println();
		}
	}

	private void briefFormatieren(Brief brief, Briefdrucker briefdrucker) {
		formatierteBriefe.put(brief, briefdrucker);
	}

	private ArrayList<Brief> dummiBriefe() {
		ArrayList<Brief> briefe = new ArrayList<>();
		Adresse emma = new Adresse("Emma", "Watson", "Bahnhofstrasse", 12, 8404, "Winterthur");
		Adresse thor = new Adresse("Thor", "Thorgin", "Ecklinweg", 42, 8303, "Zürich");
		Adresse toni = new Adresse("Toni", "Stark", "Oberstrasse", 14, 8399, "Wald");
		Inhalt reklamation = new Inhalt("22.11.2022", "Reklamation", "Guten Tag", "Wie können sie es wagen");
		Inhalt lob = new Inhalt("01.02.2021", "Lob", "Guten Tag", "Sehr gut gemacht!");
		briefe.add(new Brief(emma, thor, reklamation));
		briefe.add(new Brief(toni, emma, lob));
		return briefe;
	}
}