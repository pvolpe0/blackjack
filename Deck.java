import java.util.Random;

public class Deck {
	
	private static final int DECK_SIZE = 52;
	private static final int SUIT_SIZE = 13;
	private static final int NUM_OF_SUITS = 4;
	
	public Card[] theDeck;
	private int deckQuantity; // how many decks we put in the deck
	private int cardQuantity; // total number of cards in the deck (deckQuantity * DECK_SIZE)
	private int index;
	private int marker; // which card down the deck are we on?
	private int occupancy; // how many cards are currently in the deck
	private static Random generator = new Random();
	
	public Deck( boolean complete, int quantity ) {
		
		deckQuantity = quantity;
		cardQuantity = deckQuantity * DECK_SIZE;
		theDeck = new Card[cardQuantity];
		
		if ( complete ){
			
			for( int decks = 1; decks <= deckQuantity; decks++){
				
				for( int suit = 1; suit <= NUM_OF_SUITS; suit++){
			
					for( int cardNum = 1; cardNum <= SUIT_SIZE; cardNum++){
				
						theDeck[index] = new Card( cardNum, suit );
						index++;
					}
				}
			
				setOccupancy( cardQuantity );
				setMarker( cardQuantity - 1);
			}
		}
		else {
			
			for ( int index = 0; index < cardQuantity; index++){
				
				theDeck[index] = null;
			}
			
			setOccupancy( 0 );
		}
	}
	
	
	
	
	public void shuffle(){
		
		int shuffleCount = 0;
		int randIndex;
		Card[] tempDeck = new Card[cardQuantity];
		
		// while loop that processes the whole deck
		while ( shuffleCount != cardQuantity ){
			
			// finds a random number between 0 and cardQuantity ( 52 )
			randIndex = generator.nextInt() % cardQuantity;
			
			// if the deck at the random index is null, then that card has
			// already been processes, so find a new random index.
			if ( randIndex < 0 || theDeck[randIndex] == null )
				continue;

		
			// this saves a random card in theDeck to the tempDeck
			tempDeck[shuffleCount++] = theDeck[randIndex];
			
			// set the used card to null
			theDeck[randIndex] = null;
			
		}
		
		for( int i = 0; i < cardQuantity; i++){
			
			theDeck[i] = tempDeck[i];
		}
	}
	
	public void push( Card card ){
		
		theDeck[marker] = card;
		marker++;
		occupancy++;
		
		if ( this.isFull() )
			this.setMarker(cardQuantity - 1);
	}
	
	public Card pop(){

		if ( this.isEmpty() ){
			
			return null;
		}
		
		Card theCard;

		theCard = theDeck[marker];
		theDeck[marker] = null;
		
		marker--;
		occupancy--;
		
		if ( this.isEmpty() )
			setMarker( 0 );
		
		return theCard;
	}
	
	public boolean isEmpty() {
		
		if ( occupancy == 0 )
			return true;
		else
			return false;
	}

	private boolean isFull() {
		
		if (occupancy == cardQuantity)
			return true;
		else
			return false;
	}
	public int getMarker() {
		return marker;
	}

	public void setMarker(int marker) {
		this.marker = marker;
	}

	public int getOccupancy() {
		return occupancy;
	}

	private void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}

	@Override
	public String toString() {
	
		String string = new String("");
		for (int i = 0; i < cardQuantity; i++){
			
			if ( theDeck[i] == null )
				continue;
			string += (i + 1) + ". " + theDeck[i] + "\n";
		}
		
		string += "\n\n";
		string += " Occupancy = " + occupancy;
		string += "\n Marker = " + marker;
		
		return string;
		
	}


	
	
}
