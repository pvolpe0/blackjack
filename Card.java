/*
 * Filename: Card.java
 *
 *
 */
public class Card {

	private static final String charValues[] = {"A", "2", "3", "4", "5", "6", "7",
						    "8", "9", "10", "J", "Q", "K"};
	
	private int value; // number of card
	private String suit; // suit of card
	private String charNum; // character representation of value
	
	// constructor
	public Card (int value, int suit) {
		
		this.value = value;

		charNum = charValues[ value - 1 ];
		
		if ( value > 10 )
			this.value = 10;
		
		switch ( suit ){
		
			case 1:
				this.suit = new String( "Spades" );
				break;
			case 2:
				this.suit = new String( "Clubs" );
				break;
			case 3:
				this.suit = new String( "Hearts" );
				break;
			case 4:
				this.suit = new String( "Diamond" );
				break;
		}
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
	
		return charNum + " of " + suit;
	}
	
	
	
	
	
	
}
