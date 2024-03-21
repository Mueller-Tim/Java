import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse speichert Informationen eines Motorrads.
 * @author Marc Rennhard
 */
public class Motorrad 
{
  private String modell;
  private int preis;
  private int stueckzahl;
  private int leistung;
  private boolean abs;
  private List<Verkauf> verkaeufe;
  
  /**
   * Konstruktor, um ein Motorrad zu erzeugen.
   * @param modell Das Modell 
   * @param preis Der Preis
   * @param stueckzahl Die verfuegbare Stueckzahl
   * @param leistung Die Leistung in PS
   * @param abs Ob das Motorrad ABS hat
   */
  public Motorrad(String modell, int preis, int stueckzahl, int leistung, boolean abs)
  {
    this.modell = modell;
    this.preis = preis;
    this.stueckzahl = stueckzahl;
    this.leistung = leistung;
    this.abs = abs;
    verkaeufe = new ArrayList<Verkauf>();
  }
  
  /**
   * Kaufe das Motorrad in der gewuenschten Stueckzahl.
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
   * Liefert das Modell des Motorrads.
   * @return Das Modell
   */
  public String gibModell() 
  {
    return modell;
  }
  
  /**
   * Setzt den Preis des Motorrads.
   * @param preis Der Preis
   */
  public void setzePreis(int preis) 
  {
    if (preis > 0) {
      this.preis = preis;
    }
  }
  
  /**
   * Setze die verfuegbare Stueckzahl des Motorrads.
   * @param stueckzahl Die Stueckzahl
   */
  public void setzeStueckzahl(int stueckzahl) 
  {
    if (stueckzahl >= 0) {
      this.stueckzahl = stueckzahl;
    }
  }
  
  /**
   * Liefert die Leistung des Motorrads.
   * @return Die Leistung
   */
  public int gibLeistung()
  {
    return leistung;
  }
  
  /**
   * Liefert ob das Motorrad ABS hat.
   * @return Ob das Motorrad ABS hat (true) oder nicht
   */
  public boolean gibAbs()
  {
    return abs;
  }
  
  /**
   * Gibt Informationen des Motorrads zurueck.
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
