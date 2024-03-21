import javax.swing.text.html.HTMLDocument;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Die Klasse MessApp steuert einen Messablauf, um die Performance des
 * Zufallszahlengenerators zu messen.
 */
public class MessApp {
  private Messkonduktor messkonduktor;
  private int[][] gesamtMessung;
  int[] messresultate;


  private MessApp(){

  }

  /**
   * Fuehrt eine Messung durch.
   */
  public static void main(String[] args){
    MessApp messApp = new MessApp();

    messApp.initialisieren();
    messApp.analyseDurchfuehren();
    messApp.berechneUndDruckeMittelwerteMessreihe();
    messApp.berechneUndDruckeMittelwerteMessung2();
    //messApp.berechneUndDruckeMittelwerteMessung();
  }

  private void initialisieren() {
    // TODO Objektsammlung und Messkonduktor erzeugen

    gesamtMessung = new int[10][20];
    messkonduktor = new Messkonduktor(400_000);

  }

  private void analyseDurchfuehren() {
    // TODO Benutzen Sie 'messkonduktor' um die Messungen
    // durchzufuehren und in der Objektsammlung zu speichern.
    for(int[] messungen: gesamtMessung){
      messungen = messkonduktor.messungenDurchfuehren(messungen);
    }
  }

  private void berechneUndDruckeMittelwerteMessreihe() {
    // TODO Implementieren Sie die Methode.
    int reihennummer = 0;
    for(int[] messreihe: gesamtMessung){
      double summe = 0;
      reihennummer++;
      for(int messung: messreihe){
        summe += messung;
      }
      System.out.println("Der mittlere Messwert der Reihe " + reihennummer + ": " + (summe / messreihe.length) + "ms.");
    }
  }

  private void berechneUndDruckeMittelwerteMessung2() {
    // TODO Implementieren Sie die Methode.

    for(int messungen = 0; messungen < gesamtMessung[0].length; messungen++){
      int summe = 0;
      for(int messreihe = 0; messreihe < gesamtMessung.length; messreihe++){
        summe += gesamtMessung[messreihe][messungen];
      }
      System.out.println("Der mittlere Messwert über alle " + (messungen + 1) + ".Messungen pro Messreihe:" + (summe / gesamtMessung[0].length) + "ms.");
    }

  }

  private void berechneUndDruckeMittelwerteMessung() {
    // TODO Implementieren Sie die Methode.
    int[][] transposed2DArray = transpose2DArray(gesamtMessung);
    int messungsSpalte = 0;
    for(int[] messung: transposed2DArray){
      double sume = 0;
      messungsSpalte++;
      for(int messReihe: messung){
        sume += messReihe;
      }
      System.out.println("Der mittlere Messwert über alle  " + messungsSpalte + ".Messung pro Messreihe:" + (sume / messung.length) + "ms.");
    }

  }


  private int[][] transpose2DArray(int[][] arrayName){
    int[][] transposed2DArray = new int[arrayName[0].length][arrayName.length];
    for(int messreihe = 0; messreihe < arrayName.length; messreihe++){
      for(int messung = 0; messung < arrayName[0].length; messung++ ){
        transposed2DArray[messung][messreihe] = arrayName[messreihe][messung];
      }

    }
    return transposed2DArray;
  }
}