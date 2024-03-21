# Wie schreibe ich eine einfache Klasse?

## Aufgabe

In diesem Projekt finden Sie die Klasse `Employee`. Diese ist derzeit noch leer. Ihre Aufgabe ist es, diese Klasse zu vervollständigen. Dazu können Sie das Projekt mit der IDE Ihrer Wahl bearbeiten. Mit IntelliJ und BlueJ haben wir das Projekt erfolgreich getestet.

Sie finden in der JavaDoc von `Employee` mehrere Hinweise, welche Felder, Konstruktoren und Methoden, benötigt werden.
Diese müssen Sie implementieren.

Nehmen Sie die verfügbaren Unterrichtsmaterialen zum Thema Klassenentwurf zur Hilfe und beginnen Sie dann mit der 
Implementierung. Sobald Sie alles "TODOs" erledigt haben, können Sie Ihre Lösung testen.

> Das Dozierendenteam ist immer an Rückmeldungen interessiert, wie wir unsere Aufgaben verbessern können. Bitte schicken Sie ihr **Feedback** zu dieser Aufgabe an [Michael Wahler](michael.wahler@zhaw.ch).

## Testen Ihrer Lösung

Die Klasse `EmployeeChecker` überprüft Ihre Implementierung und gibt Ihnen Hinweise auf allfällige Probleme. Dazu bietet Sie eine `main`-Methode an. Diese können Sie entweder aus Ihrer IDE (BlueJ, IntelliJ, Eclipse, Visual Studio, ...) oder von einem Terminal (Konsole, Powershell, ...) aus aufrufen.

Aufruf über die Konsole mit den beiden Befehlen:
```
javac EmployeeChecker.java
java EmployeeChecker
```

Wenn Sie die Tests laufen lassen, bevor Sie die Klasse `Employee` ergänzt haben, werden Sie natürlich eine Fehlermeldung erhalten.

### Beschreibung der Tests

Zunächst wird Ihre Klasse `Employee` darauf getestet, ob
- sie alle benötigten Felder besitzt,
- diese Felder die richtigen Typen und Namen haben, 
- sie einen Konstruktor besitzt, der die Felder initialisieren kann,
- sie getter-Methoden besitzt, mit denen man die Werte der Felder auslesen kann und
- sie setter-Methoden für die veränderbaren Felder besitzt.

Keine Sorge, falls Sie die Klasse nicht sofort richtig entwerfen. Die Tests in `EmployeeChecker` geben Ihnen jeweils Hinweise auf Fehler und Lösungen.

Im zweiten Teil der Tests wird ein Objekt der Klasse instanziiert und getestet, ob
- der Konstruktor die Werte der Felder setzt und
- die Werte der Felder über die getter-Methoden korrekt zurückgegeben wird.

Auch hier erhalten Sie Hinweise, falls etwas nicht sofort funktioniert.

### Abschluss der Übung

Wenn Sie alles richtig implementiert haben, erhalten Sie schlussendlich die Meldung:

```
**************************
* All unit tests passed! *
**************************
```

Herzlichen Glückwunsch, Sie haben erfolgreich eine Klasse implementiert!

