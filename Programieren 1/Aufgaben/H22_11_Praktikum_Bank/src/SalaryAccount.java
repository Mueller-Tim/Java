public class SalaryAccount extends Account{

	private int overdraft;
	private final static int MAX_OVERDRAFT = 1_000_000;
	private final static int MIN_OVERDRAFT = 0;

	public SalaryAccount(String owner, int overdraft){
		super(owner);
		this.overdraft = validateOverdraft(overdraft);
	}

	public SalaryAccount(String owner, int balance, int overdraft){
		super(owner, balance);
		this.overdraft = validateOverdraft(overdraft);
	}
	@Override
	public int getMinAmount(){
		return super.getMinAmount() - overdraft;
	}

	private int validateOverdraft(int overdraft){
		if(overdraft < MAX_OVERDRAFT || overdraft > MIN_OVERDRAFT){
			return overdraft;
		}
		throw new IllegalArgumentException("Overdraft must be between " + convertIntoFrancs(MIN_OVERDRAFT) + " and " +
				convertIntoFrancs(MAX_OVERDRAFT));
	}

	public int getOverdraft(){
		return overdraft;
	}

	@Override
	public String toString(){
		return "Inhaber: " + getOwner() + ", Kontostand: " + convertIntoFrancs(getBalance())
				+ ", Ueberzugslimite: " + convertIntoFrancs(getOverdraft());
	}


}



