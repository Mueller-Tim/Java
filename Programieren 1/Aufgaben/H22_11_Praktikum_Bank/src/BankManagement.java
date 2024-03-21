import java.util.ArrayList;
import java.util.HashMap;

public class BankManagement {

	private HashMap<String, Account> accounts = new HashMap<>();

	public static void main(String args[]){
		BankManagement bankManagement = new BankManagement();
		bankManagement.start();
	}

	public void printOneAccount(Account account){
		System.out.println(account);
	}

	public void printOneAccount2(Account account){

		if(account instanceof SalaryAccount){
			System.out.println("Inhaber: " + account.getOwner() + ", Kontostand: " +
					account.convertIntoFrancs(account.getBalance()) + ", Ueberzugslimite: " +
					account.convertIntoFrancs(((SalaryAccount) account).getOverdraft()));

		} else {
			System.out.println("Inhaber: " + account.getOwner() + ", Kontostand: " +
					account.convertIntoFrancs(account.getBalance()));
		}
	}

	public void printAllAccounts(){
		for(Account account: accounts.values()){
			printOneAccount(account);
		}
	}

	public void start(){
		accounts.put("Jolly Jumper", new Account("Jolly Jumper", 300000));
		accounts.put("Donald Duck", new SalaryAccount("Donald Duck", -200000, 500000));
		accounts.put("Emma Watson", new NumberedAccount("Emma Watson", 10000000, addNumberedAccount()));

		printAllAccounts();
	}

	public int addNumberedAccount(){
		ArrayList<NumberedAccount> numberedAccounts = new ArrayList<>();
		for(Account account: accounts.values()){
			if(account instanceof NumberedAccount){
				numberedAccounts.add((NumberedAccount) account);
			}
		}
		return numberedAccounts.size() + 1;
	}
}
