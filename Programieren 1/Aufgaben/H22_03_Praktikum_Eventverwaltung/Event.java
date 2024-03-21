import java.util.Scanner;

public class Event {

	Scanner scanner = new Scanner(System.in);
	Artist artist;

	// TicketCategory[] ticketCategories;
	TicketCategory firstTicketCategory;
	TicketCategory secondTicketCategory;
	TicketCategory thirdTicketCategory;

	public Event(){

	}

	public Event(Artist artist, TicketCategory firstTicketCategory, TicketCategory secondTicketCategory, TicketCategory thirdTicketCategory){
		this.artist = artist;
		this.firstTicketCategory = firstTicketCategory;
		this.secondTicketCategory = secondTicketCategory;
		this.thirdTicketCategory = thirdTicketCategory;
	}

	public static void main(String[] args){

	}

	public void setArtist(Artist newArtist){
		artist = newArtist;
	}

	public void setArtistGage(int newGage){
		artist.setGage(newGage);
	}

	/*
	public void setTicketCategory2(TicketCategory newTicketCategory, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		whichTicketCategorySlot(ticketCategorySlot) = newTicketCategory;
	}
	 */

	public void setTicketCategory(TicketCategory newTicketCategory, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		if (ticketCategorySlot == 1){
			firstTicketCategory = newTicketCategory;
		} else if (ticketCategorySlot == 2){
			secondTicketCategory = newTicketCategory;
		} else {
			thirdTicketCategory = newTicketCategory;
		}
	}

	public void setTicketCategoryDescription(String newDescription, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		whichTicketCategorySlot(ticketCategorySlot).setDescription(newDescription);
	}

	public void setTicketCategoryPricePerTicket(int newPricePerTicket, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		whichTicketCategorySlot(ticketCategorySlot).setPricePerTicket(newPricePerTicket);
	}

	public void setTicketCategoryAmountOfTicket(int newAmountOfTicket, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		whichTicketCategorySlot(ticketCategorySlot).setTicketAmount(newAmountOfTicket);
	}

	public Artist getArtist(){
		return artist;
	}

	public void buyTicket(int desiredAmountOfTicket, int ticketCategorySlot){
		testIsCategory(ticketCategorySlot);
		if (!whichTicketCategorySlot(ticketCategorySlot).buyTicket(desiredAmountOfTicket)){
			buyTicket(desiredAmountOfTicket, ticketCategorySlot);
		}
	}

	private TicketCategory whichTicketCategorySlot(int ticketCategorySlot){
		if (ticketCategorySlot == 1){
			return firstTicketCategory;
		} else if (ticketCategorySlot == 2){
			return secondTicketCategory;
		} else {
			return thirdTicketCategory;
		}
	}

	private void testIsCategory(int ticketCategorySlot){
		if (ticketCategorySlot >= 1 && ticketCategorySlot <= 3){
			whichTicketCategorySlot(ticketCategorySlot);
		} else{
			System.out.println("Pleas enter 1, 2 or 3 for a ticket category slot");
			ticketCategorySlot = scanner.nextInt();
			testIsCategory(ticketCategorySlot);
		}
	}



	public void printEvent(){
		artist.printArtist();
		firstTicketCategory.printTicketCategory();
		secondTicketCategory.printTicketCategory();
		thirdTicketCategory.printTicketCategory();
	}


}
