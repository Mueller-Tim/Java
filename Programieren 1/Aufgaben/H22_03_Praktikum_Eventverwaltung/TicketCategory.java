import java.util.Objects;

public class TicketCategory {
	private String description;
	private int pricePerTicket;
	private int ticketAmount;
	private int ticketSoled;
	private final static int MIN_PRICE_PER_TICKET = 0;
	private final static int MIN_AMOUNT_OF_TICKET = 10;

	public TicketCategory(String description, int pricePerTicket, int ticketAmount){
		this.description = Objects.requireNonNull(description, "Pleas enter a real description.");
		this.pricePerTicket = checkIsMinPricePerTicket(pricePerTicket) ? pricePerTicket : MIN_PRICE_PER_TICKET;
		this.ticketAmount = checkIsMinAmountOfTicket(ticketAmount) ? ticketAmount : MIN_AMOUNT_OF_TICKET;
		this.ticketSoled = 0;
	}

	// Kann man Objects.requir... auch in eine andere Methode auslagern?

	public void setDescription(String description){
		this.description = Objects.requireNonNull((description), "Pleas enter a real description.");
	}


	public void setPricePerTicket(int pricePerTicket){
		this.pricePerTicket = checkIsMinPricePerTicket(pricePerTicket) ? pricePerTicket : this.pricePerTicket;
	}

	public void setTicketAmount(int ticketAmount){
		if (ticketAmount < (this.ticketAmount - ticketSoled)){
			this.ticketAmount = checkIsMinAmountOfTicket(ticketAmount) ? ticketAmount : this.ticketAmount;
		}
	}

	public String getDescription(){
		return description;
	}

	public int getPricePerTicket(){
		return pricePerTicket;
	}

	public int getTicketAmount(){
		return ticketAmount;
	}

	public int getTicketSoled(){
		return ticketSoled;
	}
	public void printTicketCategory(){
		System.out.println(getDescription() + ": Sold " + getTicketSoled() + " from " + getTicketAmount() + ", Earnings: CHF" + getTicketSoled() * getPricePerTicket());

	}
	public boolean buyTicket(int desiredAmountOfTicket){
		if (desiredAmountOfTicket > 0 && desiredAmountOfTicket <= (ticketAmount - ticketSoled)){
			ticketSoled += desiredAmountOfTicket;
			System.out.println("Amount of ticket: " + desiredAmountOfTicket + "\nPrice per ticket: " + pricePerTicket
					+ "\nTotal price: " + desiredAmountOfTicket * pricePerTicket);
			return true;
		}
		else if (desiredAmountOfTicket > 0){
			System.out.println("Not enough tickets left. \nPleas buy the desired amount of tickets in a new Category\nOr buy maximal " + ticketAmount + " tickets in this category");
			return false;
		}
		else{
			System.out.println("Pleas enter a real value for the desired amount of tickets to buy");
			return false;
		}
	}
	private boolean checkIsMinPricePerTicket(int newPricePerTicket){
		if (newPricePerTicket >= MIN_PRICE_PER_TICKET) {
			return true;
		}
		return false;
	}

	private boolean checkIsMinAmountOfTicket(int newAmountOfTicket){
		if (newAmountOfTicket >= MIN_AMOUNT_OF_TICKET) {
			return true;
		}
		return false;
	}

}
