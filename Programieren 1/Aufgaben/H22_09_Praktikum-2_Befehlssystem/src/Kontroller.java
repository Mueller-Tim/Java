/**
 * Diese Klasse verarbeitet Befehle vom Typ
 * {@link Befehl Befehl} und löst die dazu 
 * passenden Aktionen aus.
 * 
 * @author tebe
 *
 */
public class Kontroller {
	/**
	 * Verarbeite einen gegebenen Befehl
	 * 
	 * @param befehl
	 *            Der zu verarbeitende Befehl.
	 * @return 'false', wenn ein Abbruchbefehl verarbeitet wurde
	 */
	public boolean verarbeiteBefehl(Befehl befehl) {
		boolean macheWeiter = true;
		String befehlswort = befehl.gibBefehlswort();
		switch (Befehlswort.gibBefehlswort(befehlswort)){
			case UNBEKANNT:
				System.out.println("Ich weiss nicht, was Sie meinen...");
				break;
			case GEHE:
				System.out.println("Befehl " + befehlswort.toUpperCase() + " " + befehl.gibZweitesWort() + " wird ausgefuehrt");
				break;
			case HILFE:
				System.out.println("Gueltige Befehle: "	+ Befehlswort.gibBefehlsworteAlsText());
				break;
			case BEENDEN:
				System.out.println("Befehl " + Befehlswort.BEENDEN.getBefehl().toUpperCase() + " wird ausgefuehrt.");
				macheWeiter = false;
				break;
			default:
				System.out.println("Befehlswort ohne zugehoerige Aktion: Abbruch.");
				macheWeiter = false;

		}
		return macheWeiter;
	}
}
