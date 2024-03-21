public abstract class CaffeinatedDrink {

	public final void prepare() {
		boilWater();
		brew();
		pourInCup();
		AddIngredients();
	}

	private void boilWater() {
		System.out.println("Boil water");
	}

	protected abstract void brew();

	private void pourInCup(){
		System.out.println("Pour in cup");
	}

	protected abstract void AddIngredients();
}
