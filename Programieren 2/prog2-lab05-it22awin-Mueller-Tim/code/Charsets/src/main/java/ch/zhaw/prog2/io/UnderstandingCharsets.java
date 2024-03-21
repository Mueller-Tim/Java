package ch.zhaw.prog2.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//import static sun.security.util.Debug.args;


public class UnderstandingCharsets {



    public static void main(String[] args) {

        String defaultPath = (args.length >= 1)? args[0] : "./CharSetEvaluation_Default.txt";
        String ASCIIPath = (args.length >= 1) ? args[0] : "./CharSetEvaluation_ASCII.txt";


        /* Teilaufgabe a
         * In der Vorlesung haben Sie gelernt, dass Java-Klassen fuer Unicode entworfen wurden.
         * Nun ist Unicode aber nicht der einzige Zeichensatz und Java unterstuetz durchaus Alternativen.
         * Welche Zeichensaetze auf einem System konkret unterstuetzt werden haengt von der Konfiguration des Betriebssystems JVM ab.
         * Schreiben Sie ein Programm, welches alle Unterstuetzten Zeichensaetze auf der Konsole (System.out) ausgibt,
         * zusammen mit dem Standardzeichensatz.
         * https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html
         */

        UnderstandingCharsets understandingCharsets = new UnderstandingCharsets();

        // ToDo: Print default character set
        understandingCharsets.printDefaultSet();

        // Todo: Print all available character sets
        understandingCharsets.printAllSet();



        /* Ende Teilaufgabe a */


        /* Teilaufgabe b
         * Ergänzen Sie die Klasse so, dass sie einzelne Zeichen (also Zeichen für Zeichen) im Standardzeichensatz
         * von der Konsole einliest und in zwei Dateien schreibt einmal im Standardzeichensatz und einmal im
         * Zeichensatz `US-ASCII`.
         * Die Eingabe des Zeichens `q` soll das Program ordentlich beenden.
         * Die Dateien sollen `CharSetEvaluation_Default.txt` und `CharSetEvaluation_ASCII.txt` genannt und
         * werden entweder erzeugt oder, falls sie bereits existieren, geöffnet und der Inhalt überschrieben.
         * Testen Sie Ihr Program mit den folgenden Zeichen: a B c d € f ü _ q
         * Öffnen Sie die Textdateien nach Ausführung des Programs mit einem Texteditor und erklären Sie das Ergebnis.
         * Öffnen Sie die Dateien anschliessend mit einem HEX-Editor und vergleichen Sie.
         */

        understandingCharsets.writeInTwoSets(defaultPath, ASCIIPath);


    }

    private void printDefaultSet(){
        System.out.println("Default character set:\n" + Charset.defaultCharset() + "\n");
    }

    private void printAllSet(){
        System.out.println("All available character set:");
        for (Charset charset: Charset.availableCharsets().values()){
            System.out.println("- " + charset);
        }
    }

    private void writeInTwoSets(String firstPath, String secondPath){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileOutputStream firstStream = new FileOutputStream(firstPath);
            FileOutputStream secondStream = new FileOutputStream(secondPath);
            BufferedWriter firstWriter = new BufferedWriter(new OutputStreamWriter(firstStream, Charset.defaultCharset()));
            BufferedWriter secondWriter = new BufferedWriter(new OutputStreamWriter(secondStream, StandardCharsets.US_ASCII))){
            System.out.println("Enter characters, 'q' to stop");

            boolean isRunning = true;

            while (isRunning){
                char character = (char) reader.read();
                if(System.lineSeparator().charAt(0) == character){
                    continue;
                }

                if(character == 'q'){
                    System.out.println("End");
                    isRunning = false;
                } else{
                    writeCharacter(firstWriter, character, "Default");
                    writeCharacter(secondWriter, character, "ASCII");
                }
            }

        } catch (IOException e) {
            System.err.println("Cancelled because of IOException" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void writeCharacter(BufferedWriter bufferedWriter, char character, String encoding) throws IOException {
        System.out.printf("Output using %s Encoding%n", encoding);
        bufferedWriter.write(character);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

}

