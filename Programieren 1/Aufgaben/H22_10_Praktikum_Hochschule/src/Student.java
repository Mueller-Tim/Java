public class Student extends Person{
	private int credits;

	public Student(String name, String iD){
		super(name, iD);
		credits = 0;
	}

	public int gibCredits(){
		return credits;
	}

	public void erhoeheCredits(int crediterhoehung){
		credits += crediterhoehung;
	}
}
