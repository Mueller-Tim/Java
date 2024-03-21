public class Anwendung {

	private Parser parser;
	private Kontroller kontroller;

	public Anwendung(){
		parser = new Parser();
		kontroller = new Kontroller();
	}

	public static void main(String args[]){
		Anwendung anwendung = new Anwendung();
		anwendung.start();
	}

	public void start(){
		boolean isPlaying = true;
		while (isPlaying){
			isPlaying = kontroller.verarbeiteBefehl(parser.liefereBefehl());
		}
		beenden();
	}

	public void beenden(){
		parser.closeScanner();
		System.exit(0);
	}

}
