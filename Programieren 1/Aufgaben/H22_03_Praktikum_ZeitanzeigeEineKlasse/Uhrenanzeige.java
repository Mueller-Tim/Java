import java.util.Scanner;

/**
 * Die Klassen Uhrenanzeige implementiert die Anzeige einer Digitaluhr.
 * Die Anzeige zeigt Stunden und Minuten. Der Anzeigebereich reicht von
 * 00:00 (Mitternacht) bis 23:59 (eine Minute vor Mitternacht).
 *
 * Eine Uhrenanzeige sollte minütlich "Taktsignale" (über die Operation
 * "taktsignalGeben") erhalten, damit sie die Anzeige aktualisieren 
 * kann. Dies geschieht, wie man es bei einer Uhr erwartet: Die 
 * Stunden erhöhen sich, wenn das Minutenlimit einer Stunde erreicht
 * ist.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class Uhrenanzeige
{
  private int stundenLimit;
  private int stundenWert;
  private int minutenLimit;
  private int minutenWert;
  private int sekundenLimit;
  private int sekundenWert;
  private String zeitanzeige;    // simuliert die tatsaechliche Anzeige

  /**
   * Konstruktor fuer ein Exemplar von Uhrenanzeige.
   * Mit diesem Konstruktor wird die Anzeige auf 00:00 initialisiert.
   */
  public Uhrenanzeige()
  {
    stundenLimit = 24;
    stundenWert = 0;
    minutenLimit = 60;
    minutenWert = 0;
    sekundenLimit = 60;
    sekundenWert = 0;
    anzeigeAktualisieren();
  }

  /**
   * Konstruktor fuer ein Exemplar von Uhrenanzeige.
   * Mit diesem Konstruktor wird die Anzeige auf den Wert
   * initialisiert, der durch 'stunde' und 'minute' 
   * definiert ist.
   */
  public Uhrenanzeige(int stunde, int minute, int sekunde)
  {
    stundenLimit = 24;
    stundenWert = 0;
    minutenLimit = 60;
    minutenWert = 0;
    sekundenLimit = 60;
    sekundenWert = 0;
    setzeUhrzeit(stunde, minute, sekunde);
  }

  public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);

    System.out.println("Stunde");
    int stunde = scanner.nextInt();
    System.out.println("Minute");
    int minute = scanner.nextInt();
    System.out.println("Sekunde");
    int sekunde = scanner.nextInt();
    System.out.println("Wie viele Sekunden sollen vergehen?");
    int anzahlSekunden = scanner.nextInt();

    Uhrenanzeige uhrMitWert = new Uhrenanzeige(stunde, minute, sekunde);

   for (int i = 0; i < anzahlSekunden; i++){
     uhrMitWert.taktsignalGeben();
     System.out.println(uhrMitWert.gibUhrzeit());
   }

  }

  /**
   * Diese Operation sollte einmal pro Minute aufgerufen werden -
   * sie sorgt dafuer, dass diese Uhrenanzeige um eine Minute
   * weiter gestellt wird.
   */
  public void taktsignalGeben()
  {
    sekundenWert = (sekundenWert + 1) % sekundenLimit;
    if (sekundenWert == 0){
      minutenWert = (minutenWert + 1) % minutenLimit;
      if(minutenWert == 0) {  // Limit wurde erreicht!
        stundenWert = (stundenWert + 1) % stundenLimit;
      }
    }
    anzeigeAktualisieren();
  }

  /**
   * Setze die Uhrzeit dieser Anzeige auf die gegebene 'stunde' und
   * 'minute'.
   */
  public void setzeUhrzeit(int stunde, int minute, int sekunde)
  {
    if((stunde >= 0) && (stunde < stundenLimit)) {
      stundenWert = stunde;
    }
    if((minute >= 0) && (minute < minutenLimit)) {
      minutenWert = minute;
    }
    if((sekunde >= 0) && (sekunde < sekundenLimit)){
      sekundenWert = sekunde;
    }
    anzeigeAktualisieren();
  }

  /**
   * Liefere die aktuelle Uhrzeit dieser Uhrenanzeige im Format SS:MM.
   */
  public String gibUhrzeit()
  {
    return zeitanzeige;
  }

  /**
   * Aktualisiere die interne Zeichenkette, die die Zeitanzeige haelt.
   */
  private void anzeigeAktualisieren()
  {
    if(stundenWert < 10) {
      zeitanzeige = "0" + stundenWert;
    }
    else {
      zeitanzeige = "" + stundenWert;
    }
    zeitanzeige = zeitanzeige + ":";
    if(minutenWert < 10) {
      zeitanzeige = zeitanzeige + "0" + minutenWert;
    }
    else {
      zeitanzeige = zeitanzeige + minutenWert;
    }
    zeitanzeige = zeitanzeige + ":";
    if(sekundenWert < 10){
      zeitanzeige = zeitanzeige + "0" + sekundenWert;
    }
    else {
      zeitanzeige = zeitanzeige + sekundenWert;
    }
  }
}
