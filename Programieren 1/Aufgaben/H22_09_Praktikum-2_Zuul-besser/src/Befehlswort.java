import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse haelt eine Aufzaehlung der akzeptierten Befehlswoerter.
 * Mit ihrer Hilfe werden eingetippte Befehle erkannt.
 *
 * @author  tebe
 * @version 1.0
 */
public enum Befehlswort {

	UNBEKANNT("unbekannt"),
	GEHE("gehe"),
	HILFE("hilfe"),
	BEENDEN("beenden"),
	UMSEHEN("umsehen"),
	UEBERNIMM("uebernimm"),
	NIMM("nimm");

	private String befehl = "";

	private Befehlswort(String befehl) {
		this.befehl = befehl;
	}

	public static Befehlswort gibBefehlswort(String wort) {
		for (Befehlswort befehlswort : Befehlswort.values()) {
			if (befehlswort.befehl.equals(wort)) {
				return befehlswort;
			}
		}
		return UNBEKANNT;
	}

	public static String gibBefehlsworteAlsText(){
		String befehlswortListe = "";
		for(Befehlswort befehlswort : Befehlswort.values()){
			if(!befehlswort.equals(UNBEKANNT)){
				befehlswortListe = befehlswortListe + befehlswort.befehl + " ";
			}
		}

		return befehlswortListe;
	}

	public String getBefehl(){
		return befehl;
	}
}


