public class Kohaesion {

	private int zahl = 1;

	public void setZahl(int zahl) {
		this.zahl = zahl;
	}

	public int getZahl() {
		return zahl;
	}

	public void erhoeheZahl(int zahl){
		this.zahl = this.zahl + zahl;
	}

	public void senkeZahl(int zahl){
		this.zahl = this.zahl - zahl;
	}
}