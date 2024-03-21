import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

	@Test
	void setArtist() {
		Event event = new Event();
		Artist artist = new Artist("test", 4);
		event.setArtist(artist);
		assertEquals(artist.getName(), event.getArtist().getName());
		assertEquals(artist.getGage(), event.getArtist().getGage());
	}

	@Test
	void setArtistGage() {
		Event event = new Event();
		Artist artist = new Artist("house", 5);
		event.setArtist(artist);
		assertEquals(artist.setGage(3), event.getArtist().setGage(3));
	}

	@Test
	void setTicketCategory() {
	}

	@Test
	void setTicketCategoryDescription() {
	}

	@Test
	void setTicketCategoryPricePerTicket() {
	}

	@Test
	void setTicketCategoryAmountOfTicket() {
	}

	@Test
	void buyTicket() {
	}

	@Test
	void printEvent() {
	}
}