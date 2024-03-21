import java.util.ArrayList;
import java.util.List;

public class DrinkMaker {

	private List<CaffeinatedDrink> caffeinatedDrinks = new ArrayList<>();

	public static void main(String[] args){
		DrinkMaker drinkMaker = new DrinkMaker();
		drinkMaker.start();

	}

	private void addDrinks(CaffeinatedDrink caffeinatedDrink){
		caffeinatedDrinks.add(caffeinatedDrink);
	}

	private void start(){
		addDrinks(new Tea());
		addDrinks(new Coffee());
		addDrinks(new Coffee());
		addDrinks(new Tea());
		addDrinks(new Coffee());
		printDrinks();
	}

	private void printDrinks(){
		for(CaffeinatedDrink caffeinatedDrink: caffeinatedDrinks){
			caffeinatedDrink.prepare();
			((Drinkable) caffeinatedDrink).drink();
			System.out.println();
		}
	}
}
