
public class Player {

	private static final int HAND_SIZE = 10;
	
	private static int players;
	
	private Card[] myHand = new Card[HAND_SIZE];
	private String name;
	private boolean real;
    private int handStrength;
    private boolean in;
    private short win;
	private int marker;
	private int occupancy;
	private int playerNumber;
	
	public Player(String name, boolean real) {

		this.name = name;
		this.real = real;
		playerNumber = ++players;
        in = true;
	}

    public void setWin ( short win ) {

        this.win = win;
    }
    
    public short getWin() {

        return win;
    }
   
    public void setIn( boolean in ) {

        this.in = in;
    }

    public boolean getIn() {

        return in;
    }

    public int getHandStrength() {

        return handStrength;
    }
	
	public void push( Card card ){
		
		myHand[marker] = card;
		marker++;
		occupancy++;
	}
	
	public Card pop(){

		if ( this.isEmpty() ){
			
			return null;
		}
		
		Card theCard;

		theCard = myHand[marker];
		myHand[marker] = null;
		
		marker--;
		occupancy--;
		
		if ( this.isEmpty() )
			marker = 0;
		
		return theCard;
	}
	
	public int findHand(){
		
		int sum = 0;
		boolean acePresent = false;
		int value;
		
		marker = 0;
		
		while ( myHand[marker] != null ){
			
			value = myHand[marker].getValue();
			
			if ( value == 1)
				acePresent = true;
			sum += value;
			
			marker++;
		}
		
		if ( acePresent && sum + 10 <= 21)
			handStrength = sum + 10;
		else
			handStrength = sum;

        return handStrength;
	}


	public int getPlayerNumber() {
		return playerNumber;
	}

	public boolean isRealPlayer() {
		return real;
	}


	@Override
	public String toString() {
		
		String string = new String();
		
		string += this.getName() + " has the hand: \n\n";
		
			for (int i = 0; i < occupancy; i++)
				string += myHand[i] + "\n";
			
		string += "Strength is: " + handStrength;
			
		return string;
	}

	public String getName() {

		return name;
	}

	private boolean isEmpty() {
		
		if (occupancy == 0)
			return true;
		else
			return false;
	}
	
	
	
	
	
}
