import java.util.Objects;

public interface Drinkable {


	default void drink(){
		System.out.println("I drink a " + getClass().getSimpleName());
	}
}
