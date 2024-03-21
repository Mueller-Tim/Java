public class Person {
	private String name;
	private String iD;

	public Person(String name, String id){
		this.name = name;
		this.iD = id;
	}

	public String gibInfo(){
		return name + ", ID " + iD;
	}
}
