import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse speichert Informationen eines Fahrrads.
 * @author Marc Rennhard
 */
public class Fahrrad 
{
  private String modell;
  private int preis;
  private int stueckzahl;
  private int gaenge;
  private List<Verkauf> verkaeufe;
  
  /**
   * Konstruktor, um ein Fahrrad zu erzeugen.
   * @param modell Das Modell 
   * @param preis Der Preis
   * @param stueckzahl Die verfuegbare Stueckzahl
   * @param gaenge Die Anzah der Gaenge
   */
  public Fahrrad(String modell, int preis, int stueckzahl, int gaenge)
  {
    this.modell = modell;
    this.preis = preis;
    this.stueckzahl = stueckzahl;
    this.gaenge = gaenge;
    verkaeufe = new ArrayList<Verkauf>();
  }
  
  /**
   * Kaufe das Fahrrad in der gewuenschten Stueckzahl.
   * @param stueckzahlKaufen Die gewuenschte Stueckzahl
   * @param kunde Der Kunde
   * @return Informationen ueber das Ergebnis des Kaufs
   */
  public String kaufen(int anzahl, Kunde kunde)
  {
    if (anzahl <= stueckzahl) {
      stueckzahl -= anzahl;
      Verkauf verkauf = new Verkauf(kunde, anzahl, anzahl * preis);
      verkaeufe.add(verkauf);
      return String.format("%s hat %d Stueck des Modells %s zu insgesamt CHF %d gekauft", kunde.gibInfo(), anzahl, modell, (anzahl * preis));
      
    } else {
      return String.format("Es hat leider nur noch %d Stueck des Modells %s an Lager", stueckzahl, modell);
    }
  }
  
  /**
   * Liefert das Modell des Fahrrads.
   * @return Das Modell
   */
  public String gibModell() 
  {
    return modell;
  }
  
  /**
   * Setzt den Preis des Fahrrads.
   * @param preis Der Preis
   */
  public void setzePreis(int preis) 
  {
    if (preis > 0) {
      this.preis = preis;
    }
  }
  
  /**
   * Setze die verfuegbare Stueckzahl des Fahrrads.
   * @param stueckzahl Die Stueckzahl
   */
  public void setzeStueckzahl(int stueckzahl) 
  {
    if (stueckzahl >= 0) {
      this.stueckzahl = stueckzahl;
    }
  }
  
  /**
   * Liefert die Anzahl Gaenge des Fahrrads.
   * @return Die Anzahl Gaenge
   */
  public int gibGaenge()
  {
    return gaenge;
  }
  
  /**
   * Gibt Informationen des Fahrrads zurueck.
   * @return Informationen des Verkaufs
   */
  public String gibInfo() {
    String resultat = String.format("Modell %s, %d Fahrzeuge zu je CHF %d an Lager\n", modell, stueckzahl, preis);
    resultat += "Bereits erfolgte Verkaeufe:\n";
    for (Verkauf verkauf : verkaeufe) {
	  resultat += verkauf.gibInfo() + "\n";
    }
    return resultat;
  }
}
