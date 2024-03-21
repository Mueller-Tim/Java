import java.util.Objects;

public class Artist {
	private String name;
	private int gage;
	private final static int MIN_GAGE = 0;

	public Artist(String name, int gage){
		this.name = Objects.requireNonNull(name, "Pleas enter a real value for a name");
		this.gage = checkIsMinGage(gage) ? gage : MIN_GAGE;
	}

	public void setGage(int gage){
		this.gage = checkIsMinGage(gage) ? gage : this.gage;
	}

	public String getName(){
		return name;
	}

	public int getGage(){
		return gage;
	}
	public void printArtist(){
		System.out.println("Artist: " + getName() + ", Gage: " + getGage());
	}
	private boolean checkIsMinGage(int newGage){
		if (newGage >= MIN_GAGE) {
			return true;
		}
		return false;
	}


}
