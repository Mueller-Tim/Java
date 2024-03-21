public class NumberedAccount extends Account{

	private int number;
	private final static int MIN_NUMBER = 1_000;

	public NumberedAccount(String owner, int placement){
		super(owner);
		number = MIN_NUMBER + placement-1;
	}

	public NumberedAccount(String owner, int balance, int placement){
		super(owner, balance);
		number = MIN_NUMBER + placement-1;
	}

	@Override
	public String getOwner(){
		return Integer.toString(number);
	}
}
