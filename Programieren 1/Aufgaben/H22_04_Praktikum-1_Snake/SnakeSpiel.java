import java.awt.Point;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Spielklasse des Spiels Snake.
 * <p>
 * Ziel dieses Spiels ist es alle Goldstuecke einzusammeln und
 * die Tuer zu erreichen, ohne sich selber zu beissen oder in
 * die Spielfeldbegrenzung reinzukriechen.
 */
public class SnakeSpiel {
  private Schlange schlange;
  private Tuer tuer;
  private Spielfeld spielfeld;
  private ArrayList<Goldstueck> goldStueck;


  private boolean spielLaeuft = true;
  private Scanner scanner;

  public static void main(String[] args) {
    new SnakeSpiel().spielen();
  }

  /**
   * Startet das Spiel.
   */
  public void spielen() {
    scanner = new Scanner(System.in);
    spielInitialisieren();
    while (spielLaeuft) {
      zeichneSpielfeld();
      ueberpruefeSpielstatus();
      if (!spielLaeuft) {
        askPlayerForANewGame();
      }
      fuehreSpielzugAus();
    }
    scanner.close();
  }

  private void askPlayerForANewGame(){
    String answer;
    do {
      System.out.println("\nDo you want to play again?\nYes / No");
      answer = scanner.next();
    } while (!(answer.equals("Yes") || answer.equals("No")));
    if (answer.equals("Yes")){
      spielLaeuft = true;
      spielen();
    }
    else {
      spielLaeuft = false;
    }
  }

  private void spielInitialisieren() {
    tuer = new Tuer(0, 5);
    schlange = new Schlange(30, 2);
    spielfeld = new Spielfeld(40, 10);
    erstelleGoldStuecke();
  }

  private void erstelleGoldStuecke(){
    System.out.println("Please specify the desired number of gold pieces.");
    int anzahlGoldStuecke;
    do{
      System.out.println("The maximum amount of gold pieces is 398.");
      anzahlGoldStuecke = scanner.nextInt();
    } while (anzahlGoldStuecke < 1 || anzahlGoldStuecke > 398);

    goldStueck = new ArrayList<>();
    for(int i = 0; i < anzahlGoldStuecke; i++){
      Goldstueck neuesGoldStueck = new Goldstueck();
      do {
        Point neuesGoldStueckStandort = neuesGoldStueck.setPointGoldStueck(spielfeld.erzeugeZufallspunktInnerhalb());
      } while (goldStueck.contains(neuesGoldStueck) || neuesGoldStueck.equals(tuer.getLocation()) || neuesGoldStueck.equals(schlange.gibPosition()));

      goldStueck.add(new Goldstueck(neuesGoldStueck));
    }

  }



  private void zeichneSpielfeld() {
    char ausgabeZeichen;
    for (int y = 0; y < spielfeld.gibHoehe(); y++) {
      for (int x = 0; x < spielfeld.gibBreite(); x++) {
        Point punkt = new Point(x, y);
        ausgabeZeichen = '.';
        if (schlange.istAufPunkt(punkt)) {
          ausgabeZeichen = '@';
        } else if (istEinGoldstueckAufPunkt(punkt)) {
          ausgabeZeichen = '$';
        } else if (tuer.istAufPunkt(punkt)) {
          ausgabeZeichen = '#';
        }
        if (schlange.istKopfAufPunkt(punkt)) {
          ausgabeZeichen = 'S';
        }
        System.out.print(ausgabeZeichen);
      }
      System.out.println();
    }
  }

  private boolean istEinGoldstueckAufPunkt(Point punkt) {
    return goldStueck != null && goldStueck.contains(punkt);
  }

  private void ueberpruefeSpielstatus() {
    if (istEinGoldstueckAufPunkt(schlange.gibPosition())) {
      goldStueck.remove(schlange.gibPosition());
      schlange.wachsen();
      System.out.println("Goldstueck eingesammelt.");
    }
    if (istVerloren()) {
      System.out.println("Verloren!");
      spielLaeuft = false;
    }
    if (istGewonnen()) {
      System.out.println("Gewonnen!");
      spielLaeuft = false;
    }
  }

  private boolean istGewonnen() {
    return goldStueck == null &&
      tuer.istAufPunkt(schlange.gibPosition());
  }

  private boolean istVerloren() {
    return schlange.istKopfAufKoerper() ||
      !spielfeld.istPunktInSpielfeld(schlange.gibPosition());
  }

  private void fuehreSpielzugAus() {
    char eingabe = liesZeichenVonTastatur();
    schlange.bewege(eingabe);
  }

  private char liesZeichenVonTastatur() {
    char konsolenEingabe = scanner.next().charAt(0);
    return konsolenEingabe;
  }
}