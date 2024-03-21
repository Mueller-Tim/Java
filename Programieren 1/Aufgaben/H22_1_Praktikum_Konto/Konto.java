
/**
 * Einfaches Bankkonto, mit welches man Geld ein- und auszahlen, 
 * auch den Jahreszinsberechnen berechnen und alle Kontoangaben ausdrucken kann.
 *
 * @author (Tim Mueller)
 * @version (22.09.2022)
 */
public class Konto
{
    private String kontoInhaber;
    private int kontoStandInFranken;
    private int zinssatzInProzent;

    /**
     * Konstostand ist null,Zinssatz ist 2 und Kontoinhaber ist frei wählbar.
     */
    public Konto(String kontoInhaber)
    {
        this.kontoInhaber = kontoInhaber;
        this.kontoStandInFranken = 0;
        this.zinssatzInProzent = 2;

    }
    
    /**
     * Konstostand ist null,Kontoinhaber und Zinssatz sind frei wählbar.
     */
    public Konto(String kontoInhaber, int zinssatzInProzent){
        this.kontoInhaber = kontoInhaber;
        this.kontoStandInFranken = 0;
        this.zinssatzInProzent = zinssatzInProzent;
    }
    
    public int getZinssatzInProzent(){
        return this.zinssatzInProzent;
    }
    
    public void setZinssatzInProzent(int zinssatzInProzent){
        this.zinssatzInProzent = zinssatzInProzent;
    }

    public void geldEinzahlen(int betrag){
        this.kontoStandInFranken = kontoStandInFranken + betrag; 
    }
    
    public void geldAbheben(int betrag){
        this.kontoStandInFranken = kontoStandInFranken - betrag; 
    }
    
    public int zeigeJahreszins(){
        int jahreszins = this.kontoStandInFranken * this.zinssatzInProzent / 100;
        return jahreszins;
    }
    
    public void printKontoangaben(){
        System.out.println("Information zum Konto:");
        System.out.println("Kontoinhaber: " + this.kontoInhaber);
        System.out.println("Kontostand: CHF " + this.kontoStandInFranken);
        System.out.println("Zinsatz: " + this.zinssatzInProzent + "%");
    }
}
