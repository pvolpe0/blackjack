import java.util.Scanner;

public class BlackJack {

	private static final boolean FULL = true;
	private static final boolean EMPTY = false;
	
	/* Variable Declarations */
	private static Deck theDeck;
	private static Deck oldCards;
	private static Player[] players;
	private static Card current;
	private static char command;
	
	
	public static void main(String[] args) {
		
	/*------------------------------------------- KEEP THIS --------------------------------------*/
		int totalPlayers; // how many players exist
		int deckQuantity; // how many decks to use
		String name;
		boolean real;
		
		Player[] winners;
		
		boolean done; // is the player done
		int i = 0; // player index
		

		
		

		
	/* ------------------------------------- DEVELOPMENT PURPOSES ------------------------------- */	
		
		printIntro();

		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Please enter number of players. (max 16)");
		
		while(true) {
			
			totalPlayers = scanner.nextInt();
			
			if ( totalPlayers <= 0 || totalPlayers > 16 ){
				System.out.println( "Incorrect amount of players. Please choose again");
				continue;
			}
			
			break;
		}
		
		if (totalPlayers > 12)
			deckQuantity = 3;
		else if ( totalPlayers > 6)
			deckQuantity = 2;
		else
			deckQuantity = 1;

		theDeck = new Deck( FULL, deckQuantity );
		oldCards = new Deck( EMPTY, deckQuantity );
		theDeck.shuffle();
		
		players = new Player[totalPlayers + 1];  // plus 1 because dealer
		winners = new Player[totalPlayers];
		players[0] = new Player( "Dealer", false);
		
		while( ++i <= totalPlayers) {
			
			System.out.println( "Enter name of player " + i);
			name = new String( scanner.next() );
			System.out.println( "Will " + name + " be a (H)uman or (C)omputer?" );
			command = scanner.next().charAt(0);
			switch (command){
			
			case 'H':
				real = true;
				break;
			case 'C':
				real = false;
				break;
			default:
				System.out.println("Unable to read command. Try again.");
				command = scanner.next().charAt(0);
				continue;
			}
			
			players[i] = new Player( name, real );
			
		}
		
		i = 0;
		int j = 0;
		
		
		
		while( i <= totalPlayers ){
			command = 0;
			done = false;
			System.out.println(players[i].getName() + "'s turn.\n\n");
			
			hit( players[i], theDeck);
			hit( players[i], theDeck);
			
			if ( players[i].findHand() == 21 ){
				
				System.out.println( "BLACKJACK!");
				done = true;
				winners[j++] = players[i];
			}
			
			System.out.println( players[i] );
			
			while (!done && i != 0){
				
				System.out.println( players[i] );
				System.out.println( "Select an option: (h)it, (s)tay, show (m)y hand, show (d)ealer's hand");
				command = scanner.next().charAt(0);
			
				
				switch(command){
				
				case 'h':
					
					hit( players[i], theDeck);
					if ( players[i].findHand() > 21 ){
						
						System.out.println( players[i]);
						System.out.println( players[i].getName() + " has BUSTED!");
						done = true;
					}
					break;
				case 's':
					done = true;
					break;
				case 'm':
					System.out.println( players[i]);
					break;
				case 'd':
					System.out.println( players[0] );
					break;
				default:
					continue;
				
				}
			
				
			}
			i++;
				
		}
		
		

	}
	
	private static void hit( Player player, Deck theDeck ){
	
		if (theDeck.isEmpty()){
			
			System.err.println( "The Deck is empty, could not draw a Card." );
			return;
		}
			
			
		
		current = theDeck.pop();
		player.push(current);
	}
	
	private static void printIntro(){



  
             System.out.println( "************************************************************************************************************" );
             System.out.println( "************************************************************************************************************" );
             System.out.println( "**                                                                                                        **" );
	     System.out.println( "**           XX      XX  XXXXXXXXXX  XX          XXXXXX      XXXXXX      XX  XX    XXXXXXXXXX             **" );
	     System.out.println( "**           XX      XX  XX          XX        XX      XX  XX      XX  XX  XX  XX  XX                     **" );
	     System.out.println( "**           XX  XX  XX  XXXXXX      XX        XX          XX      XX  XX  XX  XX  XXXXXX                 **" );
	     System.out.println( "**           XX  XX  XX  XX          XX        XX      XX  XX      XX  XX      XX  XX                     **" );
	     System.out.println( "**             XX  XX    XXXXXXXXXX  XXXXXXXX    XXXXXX      XXXXXX    XX      XX  XXXXXXXXXX             **" );
	     System.out.println( "**                                                                                                        **" );
	     System.out.println( "**                                       XXXXXXXXXX    XXXXXX                                             **" );
	     System.out.println( "**                                           XX      XX      XX                                           **" );
	     System.out.println( "**                                           XX      XX      XX                                           **" );
	     System.out.println( "**                                           XX      XX      XX                                           **" );
	     System.out.println( "**                                           XX        XXXXXX                                             **" );
	     System.out.println( "**                                                                                                        **" );
	     System.out.println( "**  XXXXXXXX    XX          XXXXXX      XXXXXX    XX    XX  XXXXXXXXXX    XXXXXX      XXXXXX    XX    XX  **" );
	     System.out.println( "**  XX    XX    XX        XX      XX  XX      XX  XX  XX        XX      XX      XX  XX      XX  XX  XX    **" );
	     System.out.println( "**  XXXXXXXXXX  XX        XXXXXXXXXX  XX          XXXX          XX      XXXXXXXXXX  XX          XXXX      **" );
	     System.out.println( "**  XX      XX  XX        XX      XX  XX      XX  XX  XX    XX  XX      XX      XX  XX      XX  XX  XX    **" );
	     System.out.println( "**  XXXXXXXXXX  XXXXXXXX  XX      XX    XXXXXX    XX    XX  XXXXXX      XX      XX    XXXXXX    XX    XX  **" );
             System.out.println( "**                                                                                                        **" );
             System.out.println( "************************************************************************************************************" );
             System.out.println( "************************************************************************************************************" );
             System.out.println( "**                                                                                                        **" );
	     System.out.println( "**         XXX     XXX                 XXX                        X                        XXX            **" );
             System.out.println( "**        XXXXXX XXXXXX               XXXXX                     XXXXX                     XXXXX           **" );
	     System.out.println( "**        XXXXXXXXXXXXX             XXXXXXXXX                 XXXXXXXXX                 XX  X  XX         **" );
	     System.out.println( "**         XXXXXXXXXXX             XXXXXXXXXXX               XXXXXXXXXXX               XXXXXXXXXXX        **" );
	     System.out.println( "**          XXXXXXXXX               XXXXXXXXX                 XXXXXXXXX                XXXXXXXXXXX        **" );
	     System.out.println( "**           XXXXXXX                  XXXXX                       X                     XX  X  XX         **" );
	     System.out.println( "**             XXX                     XXX                       XXX                       XXX            **" );
	     System.out.println( "**                                                                                                        **" );
             System.out.println( "************************************************************************************************************" );
             System.out.println( "************************************************************************************************************\n" );
	}

}
