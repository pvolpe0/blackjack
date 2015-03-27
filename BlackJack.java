import java.util.Scanner;

public class BlackJack {

    private static final boolean FULL = true;
    private static final boolean EMPTY = false;
    private static final short LOSS = 0;
    private static final short TIE = 1;
    private static final short WIN = 2;
    private static final String lossString = "LOSS";
    private static final String tieString  = "TIE ";
    private static final String winString  = "WIN ";

    /* Variable Declarations */
    private static Deck theDeck;
    private static Deck oldCards;
    private static Player[] players;
    private static Player dealer, currentPlayer;
    private static Card current;
    private static int highStrength;
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






        	
       /*---------------------------------------------------*/
       /*                 INTRO BLOCK                       */
       /*---------------------------------------------------*/
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


       /*---------------------------------------------------*/
       /*           DECK INITIALIZATION BLOCK               */
       /*---------------------------------------------------*/
        if (totalPlayers > 12)
            deckQuantity = 3;
        else if ( totalPlayers > 6)
            deckQuantity = 2;
        else
            deckQuantity = 1;

        theDeck = new Deck( FULL, deckQuantity );
        oldCards = new Deck( EMPTY, deckQuantity );
        theDeck.shuffle();



       /*---------------------------------------------------*/
       /*           PLAYER INITIALIZATION BLOCK             */
       /*---------------------------------------------------*/
        players = new Player[totalPlayers + 1];  // plus 1 because dealer
        winners = new Player[totalPlayers];
        dealer = players[0] = new Player( "Dealer", false);

        while( ++i <= totalPlayers) {

            System.out.println( "\nEnter name of player " + i);
            name = new String( scanner.next() );
            System.out.println( "\nWill " + name + " be a (H)uman or (C)omputer?" );
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



        /*---------------------------------------------------*/
        /*                  PLAYERS BLOCK                    */ 
        /*---------------------------------------------------*/
        
        i = 0;
        int j = 0;
        while( i <= totalPlayers ){
            command = 0;
            done = false;

            currentPlayer = players[i];


            System.out.println( "\n****************************************************************************************" );
            System.out.println( "****************************************************************************************\n" );
            System.out.println(currentPlayer.getName() + "'s turn.\n\n");

            hit( currentPlayer, theDeck);
            hit( currentPlayer, theDeck);

            if ( currentPlayer.findHand() == 21 ){

                System.out.println( "BLACKJACK!");
                done = true;
                winners[j++] = currentPlayer;
            }

            System.out.println( currentPlayer );

            while (!done && i != 0){

                System.out.println( "\n****************************************************************************************" );
                System.out.println( "****************************************************************************************\n" );
                System.out.println( currentPlayer );
                System.out.println( "Select an option: (h)it, (s)tay, show (m)y hand, show (d)ealer's hand");
                command = scanner.next().charAt(0);


                switch(command){

                    case 'h':

                        hit( currentPlayer, theDeck);
                        if ( currentPlayer.findHand() > 21 ){

                            System.out.println( currentPlayer );
                            System.out.println( currentPlayer.getName() + " has BUSTED!");
                            currentPlayer.setIn( false );
                            done = true;
                        }
                        break;
                    case 's':
                        done = true;
                        break;
                    case 'm':

                        System.out.println( "\n****************************************************************************************" );
                        System.out.println( "****************************************************************************************\n" );
                        System.out.println( currentPlayer );
                        break;
                    case 'd':

                        System.out.println( "\n****************************************************************************************" );
                        System.out.println( "****************************************************************************************\n" );
                        System.out.println( dealer );
                        break;
                    default:
                        continue;

                }

                if ( currentPlayer.getIn() && currentPlayer.getHandStrength() > highStrength )
                   highStrength = currentPlayer.getHandStrength();


            }
            i++;

        }



        System.out.println( "\n****************************************************************************************" );
        System.out.println( "****************************************************************************************\n" );
        System.out.println( "End of players turn");
    

       /*---------------------------------------------------*/
       /*                DEALERS BLOCK                      */
       /*---------------------------------------------------*/
       System.out.println( dealer.getName() + "'s turn.");
        while ( dealer.getHandStrength() < 17 ) {

        
            System.out.println( "\n****************************************************************************************" );
            System.out.println( "****************************************************************************************\n" );
            hit( dealer, theDeck);
            System.out.println( "Dealer hits...\n" );
            if ( dealer.findHand() > 21 ){

                System.out.println( dealer.getName() + " has BUSTED!");
                dealer.setIn( false );
            }


            System.out.println( dealer );
        }

        if ( dealer.getIn() ) {

            System.out.println( "\n****************************************************************************************" );
            System.out.println( "****************************************************************************************\n" );
            System.out.println( "Dealer stands with strength " + dealer.getHandStrength() ); 
        }


       /*---------------------------------------------------*/
       /*             DETERMINE WINNERS BLOCK               */
       /*---------------------------------------------------*/

       i = 1;
    
       while( i <= totalPlayers ){

           currentPlayer = players[i];
           
           // if player busted
           if ( !currentPlayer.getIn() ) {

               currentPlayer.setWin( LOSS );
           }
           else { // player hasnt busted

               // if dealer busted
               if ( !dealer.getIn() )
                   currentPlayer.setWin( WIN );
               
               else { // if dealer hasnt busted
               
                   if ( currentPlayer.getHandStrength() > dealer.getHandStrength() )
                       currentPlayer.setWin( WIN );

                   else if ( currentPlayer.getHandStrength() < dealer.getHandStrength() )
                       currentPlayer.setWin( LOSS );

                   else 
                       currentPlayer.setWin( TIE );
               }
           }
           i++;
       }

       /*---------------------------------------------------*/
       /*               PRINT RESULTS BLOCK                 */
       /*---------------------------------------------------*/


       System.out.println( "\n****************************************************************************************" );
       System.out.println( "****************************************************************************************" );
       System.out.println( "****                                    RESULTS                                     ****" ); 
       System.out.println( "****************************************************************************************" );
       System.out.println( "****************************************************************************************" );

 
       System.out.println( "****************************************************************************************" ); 
       System.out.println( "*          NAME            |           STRENGTH           |           RESULT           *" );
       System.out.println( "***************************|******************************|*****************************" );
       System.out.print(   "* Dealer                   |              " + dealer.getHandStrength() ); 
       System.out.println( "              |                            *" );
       System.out.println( "*--------------------------|------------------------------|----------------------------*" );

       i = 1;

       while( i <= totalPlayers ) {

          currentPlayer = players[i];
          
          System.out.print( "* " + currentPlayer.getName() );
          
          for ( j = 0; j < 25 - currentPlayer.getName().length(); j++ )
              System.out.print( ' ' );
          
          System.out.print( '|' );
          System.out.print( "              " + currentPlayer.getHandStrength() );
          System.out.print( "              |            " );

          if ( currentPlayer.getWin() == LOSS )
              System.out.print( lossString );

          else if ( currentPlayer.getWin() == TIE)
              System.out.print( tieString );

          else
              System.out.print( winString );

          System.out.println( "            *" );
          i++;
       }

       System.out.println( "****************************************************************************************" ); 
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
