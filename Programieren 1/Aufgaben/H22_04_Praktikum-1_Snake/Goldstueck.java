import java.awt.*;
import java.util.Random;

public class Goldstueck {
	private int wertProGoldstück;
	private Point aktuellerOrt;
	private final static int MAX_GOLD_VALUE = 5;
	private Random random;
	private int goldValue;

	public Goldstueck(){
		aktuellerOrt = new Point();
		goldValue = random.nextInt(MAX_GOLD_VALUE)+1;
	}

	public int getGoldValue(){
		return goldValue;
	}

	public Point getPointGoldStueck(){
		return aktuellerOrt;
	}

	public void setPointGoldStueck(Point neuerOrt){
		aktuellerOrt.setLocation(neuerOrt);
	}


}
