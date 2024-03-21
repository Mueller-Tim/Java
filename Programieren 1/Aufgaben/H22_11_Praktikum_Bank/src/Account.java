public class Account {
	private String owner;
	private int balance;
	private final static int MAX_AMOUNT = 10_000_000;
	private final static int MIN_AMOUNT = 0;

	public Account(String owner){
		this.owner = owner;
		balance = 0;
	}

	public Account(String owner, int balance){
		this.owner = owner;
		this.balance = validateBalance(balance);
	}

	public void deposit(int money){
		if(balance + money > MAX_AMOUNT){
			balance = MAX_AMOUNT;
		} else if(balance + money < getMinAmount()){
			balance = getMinAmount();
		} else {
			balance= balance + money;
		}
	}

	public int getMinAmount(){
		return MIN_AMOUNT;
	}

	public double convertIntoFrancs(int rappen){
		return rappen/100.0;
	}

	public int convertIntoRappen(int francs){
		return francs * 100;
	}

	public String getOwner(){
		return owner;
	}

	public int getBalance(){
		return balance;
	}

	@Override
	public String toString(){
		return "Inhaber: " + getOwner() + ", Kontostand: " + convertIntoFrancs(getBalance());
	}

	public int validateBalance(int balance){
		if(balance < MAX_AMOUNT && balance > getMinAmount()) {
			return balance;
		} else{
			throw new IllegalArgumentException("Balance must be between " + convertIntoFrancs(getMinAmount()) + " and " +
					convertIntoFrancs(MAX_AMOUNT));
		}

	}


}
