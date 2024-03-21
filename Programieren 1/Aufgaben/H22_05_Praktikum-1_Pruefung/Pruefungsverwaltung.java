import java.util.ArrayList;

/**
 * Bietet Funktionalitaeten zum Speichern von Pruefungsergebnissen von einer
 * Vielzahl von Studenten. Aus den gespeicherten Ergebnissen lassen sich
 * personalisierte Antworttext generieren.
 */
public class Pruefungsverwaltung {

  private ArrayList<Pruefungsergebnis> pruefungsergebnisse;

  public Pruefungsverwaltung(){
    pruefungsergebnisse = new ArrayList<>();

  }

  /**
   * Speichert ein Pruefungsergebnis.
   * 
   * @param ergebnis Das Pruefungsergebnis.
   */
  public void speichereErgebnis(Pruefungsergebnis ergebnis) {
    pruefungsergebnisse.add(ergebnis);
    // TODO Implementieren gemaess Aufgabenstellung
  }

  /**
   * Gibt pro gespeichert Ergebnis einen Text auf die Konsole aus.
   * Je nachdem ob der Kandidate die Pruefung bestanden (>= 4.0) oder nicht
   * bestanden (< 4.0) hat, wird ein Text in folgendem Format ausgegeben:
   * <p>
   * Max Muster, Sie haben an der Prüfung die Note 3.0 erzielt und
   * sind somit durchgefallen!
   * <p>
   * Herzliche Gratulation Max Muster! Sie haben an der Prüfung die Note 4.5
   * erzielt und haben somit bestanden!
   */
  public void druckeAntworttexte() {
    // TODO Implementieren gemaess Aufgabenstellung
    for(Pruefungsergebnis ergebnis : pruefungsergebnisse){
      double gerundeteNote = rundeAufHalbeNote(ergebnis.getNote());
      if (gerundeteNote >= 4.0){
        System.out.println("Herzliche Gratulation " + ergebnis.getStudent() + "! Sie haben an der Prüfung die Note " + gerundeteNote + "\nerzielt und haben somit bestanden!");
      }
      else {
        System.out.println(ergebnis.getStudent() + ", Sie haben an der Prüfung die Note " + gerundeteNote + " erzielt und\nsind somit durchgefallen!");
      }
    }
  }

  private double rundeAufHalbeNote(double note) {
    return Math.round(note * 2) / 2.0;
  }
}
