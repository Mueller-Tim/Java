import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Zaehlt die Anzahl Vorkommnisse von Woertern ueber mehrere Zeichenketten.
 * Es lassen sich eine beliebige Anzahl an Zeichenketten uebergeben. Die
 * Statistik wird fortlaufend gefuehrt. Die Wortzaehlung laesst sich jederzeit
 * ausgeben. Die Satzeichen . , ? ! " : ; werden entfernt. Alle Buchstaben
 * werden in Kleinbuchstaben umgewandelt um beispielsweise das Wort 'die'
 * inmitten eines Satzes und das Wort 'Die' am Anfang eines Satzes als gleiches
 * Wort werten zu koennen.
 *
 * @version 1.0
 * @author XXXX
 */
public class Worthaeufigkeitsanalyse {
  // Anstelle der Map dürfen Sie auch andere Datentypen verwenden. Testen Sie auch TreeMap.
  private Map<String, Integer> woerterHaeufigkeit = new HashMap<>();
  //private File file = new File("C:\\Users\\TimMu\\OneDrive\\Informatik\\Module\\Programmieren_1\\Programme\\H22_05_Praktikum-2_Wortstatistik\\src");


  public String insertFile(String pathname) throws FileNotFoundException {
    File file = new File(pathname);
    Scanner scanner = new Scanner(file);
    String text = scanner.nextLine();
    return text;
  }



  /**
   * Nimmt die uebergebene Zeichenkette in die Worthaeufigkeitsanalyse auf.
   *
   * @param text zu verarbeitende Zeichenkette
   */
  public void verarbeiteText(String text) {
    // TODO Ihre Implementation

    removeIllegalSymbols(text);

    String[] textArray = text.split(" ");
    stringToLowerCase(textArray);

    calculateWordFrequency(textArray);
  }

  private void removeIllegalSymbols(String oldText){
    HashSet<Character> illegalSymbols = new HashSet<>();
    illegalSymbols.add('.');
    illegalSymbols.add(',');
    illegalSymbols.add('?');
    illegalSymbols.add('!');
    illegalSymbols.add('"');
    illegalSymbols.add(':');
    illegalSymbols.add(';');
    String newText = "";
    for(int i = 0; i < oldText.length(); i++){
      if(!(illegalSymbols.contains(oldText.charAt(i)))){
        newText += oldText.charAt(i);
      }
    }
  }

  private void calculateWordFrequency(String[] textArray){

    for(String word: textArray){
      int counter = woerterHaeufigkeit.getOrDefault(word, 0);
      woerterHaeufigkeit.put(word, counter + 1);
    }
  }

  private void stringToLowerCase(String[] textArray){
    for(int i = 0; i < textArray.length; i++){
      textArray[i] = textArray[i].toLowerCase();
    }
  }



  /**
   * Ausgabe der Worthaeufigkeitsanalyse auf der Konsole.
   */
  public void druckeStatistik() {
    for (Map.Entry<String, Integer> wortHaeufigkeit : woerterHaeufigkeit.entrySet()) {
      System.out.printf("%3d %-40s%n", wortHaeufigkeit.getValue(), wortHaeufigkeit.getKey());
    }
  }

  public static void main(String[] args) {
    Worthaeufigkeitsanalyse hauefigkeitsanalyse = new Worthaeufigkeitsanalyse();
    //hauefigkeitsanalyse.verarbeiteText("Fritz sagt: \"Die Softwareentwicklung ist meine Leidenschaft!\"");
    //hauefigkeitsanalyse.verarbeiteText("Hans meint, er teile die Leidenschaft mit Fritz.");
    //hauefigkeitsanalyse.verarbeiteText("John fuegt hinzu, dass die Softwareentwicklung nicht nur aus Programmieren bestehe, sondern es sich dabei um einen komplexen Prozess, bestehend aus vielen kleinen Komponenten, handelt.\"");
    hauefigkeitsanalyse.druckeStatistik();
  }
}