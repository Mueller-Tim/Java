import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorthaeufigkeitsanalyseTest {

	private Worthaeufigkeitsanalyse worthaeufigkeitsanalyse;
	private final static String[] satzzeichen = {".", ",", "?", "!", "\"", ":", ";"};
	private Map<String, Integer> worthaeufigkeit;

	/**
	 * Erstellt für jeden einzelnen Test ein neues Worthäufigkeitsanalyseobjekt her
	 */
	@BeforeEach
	 void setUp() {
		worthaeufigkeitsanalyse = new Worthaeufigkeitsanalyse();
		worthaeufigkeit = new HashMap<>();
	}

	/**
	 * Äquivalenzklasse 1: mit zu entfernendem Zeichen am Anfang
	 */
	@Test
	void entferneSatzzeichenAmAnfang(){
		for(String zeichen : satzzeichen){
			assertEquals("Haus", worthaeufigkeitsanalyse.entferneSatzzeichen(zeichen + "Haus"));
		}
	}

	/**
	 * Äquivalenzklasse 2: ohne zu entfernendem Zeichen am Anfang
	 */
	@Test
	void entferneSatzzeicheNichtAmAnfang(){
		String text = "Stahl";
		assertEquals("Stahl", worthaeufigkeitsanalyse.entferneSatzzeichen(text));

	}


	/**
	 * Äquivalenzklasse 3: mit zu entfernendem Zeichen am Ende
	 */
	@Test
	void entferneSatzzeichenAmEnde(){
		for(String zeichen : satzzeichen){
			assertEquals("Rot", worthaeufigkeitsanalyse.entferneSatzzeichen("Rot" + zeichen));
		}
	}

	/**
	 * Äquivalenzklasse 4: ohne zu entfernendem Zeichen am Ende
	 */
	@Test
	void entferneSatzzeichenNichtAmEnde(){
		String text = "Grün";
		assertEquals("Grün", worthaeufigkeitsanalyse.entferneSatzzeichen(text));
	}

	/**
	 * Äquivalenzklasse 5: Satzzeichen in der Mitte nicht
	 */
	@Test
	void entferneSatzzeichenNichtInDerMitte(){
		for(String zeichen : satzzeichen){
			assertEquals("Hil" + zeichen + "fe", worthaeufigkeitsanalyse.entferneSatzzeichen("Hil" + zeichen + "fe" ));
		}
	}

	/**
	 * Äquivalenzklasse 6: null Werte
	 */
	@Test
	void entferneSatzzeichenMitNull(){
		assertThrows(IllegalArgumentException.class, () -> worthaeufigkeitsanalyse.entferneSatzzeichen(null));
	}


	/**
	 * Äquivalenzklasse 1: Wörter mit Gross- und Kleinbuchstaben
	 */
	@Test
	void verarbeiteTextGrossKleinSchreibung(){
		String satz = "Ein Haus hat ein blaues Fenster und ein rotes Fenster.";
		String text = "Ein";
		worthaeufigkeitsanalyse.verarbeiteText(satz);
		assertEquals(2, worthaeufigkeitsanalyse.getWoerterHaeufigkeit(text));
	}

	/**
	 * Äquivalenzklasse 2: Wörter mit Gross- und Kleinbuchstaben
	 */

	void verarbeiteText(){
		worthaeufigkeit.put("ein", 3);
		worthaeufigkeit.put("haus", 1);
		worthaeufigkeit.put("hat", 1);
		worthaeufigkeit.put("blaues", 1);
		worthaeufigkeit.put("fenster", 2);
		worthaeufigkeit.put("und", 1);
		worthaeufigkeit.put("rotes", 1);
		String text = "Ein Haus hat ein blaues Fenster und ein rotes Fenster.";
		worthaeufigkeitsanalyse.verarbeiteText(text);
		//assertEquals(worthaeufigkeit, worthaeufigkeitsanalyse.getWoerterHaeufigkeit());
	}
}