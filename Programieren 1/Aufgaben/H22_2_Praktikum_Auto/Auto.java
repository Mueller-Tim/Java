/**
 * Write a description of class Auto here.
 *
 * @Tim Müller
 * @29.09.2022
 */

/*
 * Frage: 
 * - Ich habe je eine Methode geschrieben um die Stringlänge von Marke und Typ zu überprüfen. Das gleiche mit der Hubliteranzahl.
 * Gibt es bessere Methoden?
 * Ich habe zwei separate Methoden für Marke und Typ für den Fall, dass es mal andere Anforderungen gibt.
 * - Gibt es eine Cleancode Reihenfolge in den Methoden? Z.b. zuerst alle get, dann set, oder nach den Variablen in den Datenfeldern sortiert?
 * - Methode Drucken, sieht kompliziert aus, dafür kein kopierter Code. Was ist besser einfacher zu lesender Code oder nicht kopierter?
 * Eventuelle andere Cleancode anmerkungen?
 */

public class Auto
{
    // instance variables - replace the example below with your own
    private String marke;
    private String typ;
    private int lagerbestand;
    private double hubraumInLiter;
    private boolean besitztTurbo;

    /**
     * Constructor for objects of class Auto
     */
    public Auto(String marke, String typ, double hubraumInLiter, boolean besitztTurbo){
        this.marke = ueberpruefungMarke(marke);
        this.typ = ueberpruefungTyp(typ);
        this.lagerbestand = 0;
        this.hubraumInLiter = ueberpruefungHubraum(hubraumInLiter);
        this.besitztTurbo = besitztTurbo;
    }

    public void setMarke(String marke){
        this.marke = ueberpruefungMarke(marke);
    }
    
    public void setTyp(String typ){
        this.typ = ueberpruefungTyp(typ);
    }
    
    public void setHubraum(double hubraumInLiter){
        this.hubraumInLiter = ueberpruefungHubraum(hubraumInLiter);
    }
    
    public void setBesitztTurbo(boolean besitztTurbo){
        this.besitztTurbo = besitztTurbo;
    }
    
    public void andereBestand(int bestandaenderung){
        if (bestandaenderung > 10 || bestandaenderung < -10){
            System.out.println("Der Bestand darf maximal um 10 Autos geändert werden!");
        } else if (this.lagerbestand + bestandaenderung < 0) {
            System.out.println("Der Lagerbegestand kann nicht negativ sein!");
        } else{
            int alterLagerbestand = this.lagerbestand;
            this.lagerbestand = this.lagerbestand + bestandaenderung;
            System.out.println("Alter Bestand: " + alterLagerbestand);
            System.out.println("Neuer Bestand: " + this.lagerbestand);
        }
    }
    
    public void druckeAuto(){        
        System.out.print(this.marke + " " + this.typ + ", " + this.hubraumInLiter + " Liter ");
        if (besitztTurbo){
            System.out.print(" turbo");
        }
        System.out.println();
        System.out.print("Code : " + this.marke.substring(0,3) + "-" + this.typ.substring(0,3) + "-" + this.hubraumInLiter);
        if (besitztTurbo){
            System.out.print("-t");
        }
        System.out.println();
        System.out.println("Lagerbestand: " + this.lagerbestand);
    }
    
    private String ueberpruefungMarke(String marke){
        if (marke.length() < 3 || marke.length() > 10){
            System.out.println("Achtung: " + marke + " ist keine gültige Marke!");
            if (this.marke == null){
                marke = "___";
            } else{
                marke = this.marke;
            }
        }
        return marke;
    }
    
    private String ueberpruefungTyp(String typ){
        if (typ.length() < 3 || typ.length() > 10){
            System.out.println("Achtung: " + typ + " ist kein gültiger Typ!");
            if (this.typ == null){
                typ = "___";
            } else{
                typ = this.typ;
            }
        }
        return typ;
    }
    
    private double ueberpruefungHubraum(double hubraumInLiter){
        if (hubraumInLiter < 0.5 || hubraumInLiter > 8){
            System.out.println("Achtung: " + hubraumInLiter + " Liter ist ein ungültiger Wert!");
            if (this.hubraumInLiter == 0){
                hubraumInLiter = 0;
            } else{
                hubraumInLiter = this.hubraumInLiter;
            }
        }
        return hubraumInLiter;
    }
}