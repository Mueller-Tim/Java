import java.lang.annotation.Documented;

/**
 * This class provides the functionality to
 * make a cup of tea.
 * 
 * @author tebe
 */
public class Tea extends CaffeinatedDrink implements Drinkable{

  @Override
  public void brew(){
    System.out.println("Dip tea bag");
  }

  @Override
  public void AddIngredients(){
    System.out.println("Add lemon");
  }
}
