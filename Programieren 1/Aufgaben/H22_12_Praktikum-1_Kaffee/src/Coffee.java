/**
 * This class provides the functionality to
 * make a coffee.
 * 
 * @author tebe
 */
public class Coffee extends CaffeinatedDrink implements Drinkable{

  @Override
  public void brew(){
    System.out.println("Brew filter coffee");
  }

  @Override
  public void AddIngredients(){
    System.out.println("Add lemon");
  }
}
