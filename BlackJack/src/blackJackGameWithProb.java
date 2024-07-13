//import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class blackJackGameWithProb 
{

    public static void main(String[]args)
    {

        /*
         * TODO:
         * 1) Then give the user options:
         *  . stand(The two cards the player has is acceptable)
         *  . hit (Player gets another card) 
         *  . Double down (double wager and dealer will give you another card) 
         *  . Split(If the player has two equal values, he or she has the option to put out a second wager and the dealer will split the two cards so that each card will become the first card on two new hands.) 
         * /

        // I might as well try to make this the class that actually lets the user play blackjack
        // Max players that can play black jack is 7, min is 2.

        // For this test file let's say that there are 2 players. You and the dealer.
        // Keep track of players in the main method.

        /*
        * Now for the actual complicated part:
        * 1) Remove cards from the main deck and insert them into the cardsOnTable object to represent that players are getting their cards.
        * 2) After placing the cards into the cardsOnTable method, calculate the probabilities for each player.
        * 3) Then insert the cards back into the deckOfCards and re-shuffle.
        */

        // Would it be appropriate to let the addCardFirst method take in a node instead of a String? That would complicate the process. 


        try
        {
            final long startTime = System.currentTimeMillis();
            Random rand = new Random();
            Scanner input = new Scanner(System.in);

            //Scanner input = new Scanner(System.in);
            LinkedListForDeck deckOfCards = new LinkedListForDeck(); // Linked list 

            int deck = 1;
            int players = 2;
            int playerCountWithoutDealer = players - 1;

            LinkedListForCardsOnTable dealer; LinkedListForCardsOnTable playerTwo; LinkedListForCardsOnTable playerThree;
            LinkedListForCardsOnTable playerFour; LinkedListForCardsOnTable playerFive; LinkedListForCardsOnTable playerSix;
            LinkedListForCardsOnTable playerSeven;

            LinkedListForCardsOnTable [] arrayOfPlayers = new LinkedListForCardsOnTable[players];

            String [] arrayOfPlayerTotalValues = new String[players];
            String [] playerProbabilities = new String[players - 1];
            // Intialize all the objects

            deckOfCards.generateCardsForDeck(deck);

            System.err.println();
            System.out.println("_________________________________________________");
            System.out.println("|        Black jack game (with probability)      |");
            System.out.println("|________________________________________________|");
            System.out.println();
            
            deckOfCards.shuffleCards(deckOfCards, rand);
            System.out.println();

            System.out.println("Which seat would you like to take?");
            int playerSeat = playerSeat(players, input);


            for(int i = 0; i < players; i++) // This for loop will give cards out to the dealer and players. At the same time, it will get rid of cards in the deckOfCards linked list.
            {
                if(i == 0)
                {
                    String player = "Dealer";
                    dealer = new LinkedListForCardsOnTable();
                    arrayOfPlayers[i] = dealer;

                    getTwoCards(player, dealer, deckOfCards);

                }else
                {
                
                    String playerName = "Player " + i;

                    switch(i)
                    {
                        case 1:
                            playerTwo = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerTwo;

                            getTwoCards(playerName, playerTwo, deckOfCards);
                            
                            break;
                        case 2:
                            playerThree = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerThree;
                            
                            getTwoCards(playerName, playerThree, deckOfCards);

                            break;
                        case 3:
                            playerFour = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerFour;
                            
                            getTwoCards(playerName, playerFour, deckOfCards);

                            break;
                        case 4:
                            playerFive = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerFive;
                            
                            getTwoCards(playerName, playerFive, deckOfCards);

                            break;
                        case 5:
                            playerSix = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerSix;

                            getTwoCards(playerName, playerSix, deckOfCards);

                            break;
                        case 6:
                            playerSeven = new LinkedListForCardsOnTable();
                            arrayOfPlayers[i] = playerSeven;

                            getTwoCards(playerName, playerSeven, deckOfCards);

                            break;
                    }
                    
                }
            }

            System.out.println("Cards on table");
            System.out.println("______________");

            for(int i = 0; i < players; i++)
            {
                arrayOfPlayers[i].printCards();
            }

            int countForPlayerValue = 0; // This temporary variable keeps track of the size of the arrayOfPlayerTotalValues array for the for loop below.

            for(int i = 0; i < players; i++)
            {
                if(i >= 1)
                {
                    String totalValForPlayer = "Player total: " + arrayOfPlayers[i].calculateTotalValForPlayer();
                    arrayOfPlayerTotalValues[countForPlayerValue] = totalValForPlayer;
                    countForPlayerValue++;
                }
            }

            // total value and probability loop could be unnecassary. The program only needs to calulate probability for the person playing.
            // Or I could make an AI that for each player to have the choice the hit or stand.
            System.out.println("Total values for each player ");
            System.out.println("____________________________");


            for(int i = 0; i < playerCountWithoutDealer; i++)
            {
                System.out.println(arrayOfPlayerTotalValues[i]);
            }

            System.out.println();

            System.out.println("Probabilites for each player");
            System.out.println("____________________________");

            for(int i = 0; i < playerCountWithoutDealer; i++)
            {
                System.out.println(arrayOfPlayerTotalValues[i]);
                playerProbabilities[i] = calculateProbability(arrayOfPlayerTotalValues[i], deckOfCards);
            }

            /*  The section below will require user input, for their next move. Should there be wagers?
            *  . stand(The two cards the player has is acceptable)
            *  . hit (Player gets another card) 
            *  . Double down (double wager and dealer will give you another card) 
            *  . Split(If the player has two equal values, he or she has the option to put out a second wager and the dealer will split the two cards so that each card will become the first card on two new hands.) 
            */

            playerChoice(input, arrayOfPlayers[playerSeat], deckOfCards ,playerSeat);

            arrayOfPlayers[playerSeat].printCards();
            System.out.println(arrayOfPlayerTotalValues[playerSeat - 1]);

            arrayOfPlayers[playerSeat].addThirdCardValueToPlayerHand(arrayOfPlayers[playerSeat].getFirstCardVal(), arrayOfPlayerTotalValues[playerSeat - 1]);

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time took around " + (endTime - startTime) + " ms");

        }catch(CustomException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }

    static int playerSeat(int players, Scanner input)
    {
        int seat = 0;
        boolean loop = true;
        
        while(loop)
        {
            try
            {
                int options = players - 1;

                System.out.println("Your seat options are from 1 - " + options);
                seat = input.nextInt();

                if(seat > options || seat < 1)
                {
                    throw new CustomException("Your input isn't within the seat range. Please try again");
                }else
                {
                    loop = false;
                }

            }catch(CustomException ex)
            {
                System.out.println(ex.getMessage());
                System.out.println();
            }catch(java.util.InputMismatchException ex)
            {
                System.out.println("You input the wrong data type. Please try again.");
                System.out.println();
                input.next();
            }
        }

        return seat;
    }

    static void playerChoice(Scanner input, LinkedListForCardsOnTable playerHand, LinkedListForDeck deckOfCards, int playerSeat)
    {
        boolean loop = true;
        int playerChoice = 0;

        while(loop)
        {
            try
            {
                System.out.println("What's your move player " + playerSeat + "?");

                System.out.println("1. Hit");
                System.out.println("2. Stand");
                System.out.println();

                playerChoice = input.nextInt();

                if(playerChoice < 1 || playerChoice > 2)
                {
                    throw new CustomException("Your choice is invalid. Please try again.");
                }else if(playerChoice == 1)
                {
                    String cardName = deckOfCards.returnFirstCardName(); // Grab the string that the node contains of the card.
                    String cardVal = deckOfCards.returnFirstCardVal();

                    playerHand.addCardFirst(cardVal, cardName, "Player " + playerSeat);
                    deckOfCards.removeFirst();
                    
                    loop = false;
                }else
                {
                    loop = false;
                }
                
            }catch(CustomException ex)
            {
                System.out.println(ex.getMessage());
                System.err.println();
            }catch(java.util.InputMismatchException ex)
            {
                System.out.println("You input the wrong data type. Please try again.");
                System.out.println();
                input.next();
            }

        }

    }

    static int pointsLeft(int value)
    {
        int whatThePlayerNeeds = 21 - value;
        return whatThePlayerNeeds;
    }

    static void getTwoCards(String playerName, LinkedListForCardsOnTable player, LinkedListForDeck deckOfCards)
    {

        try
        {
            String cardName;
            String cardVal;

            for(int i = 0; i < 2; i++)
            {
                cardName = deckOfCards.returnFirstCardName(); // Grab the string that the node contains of the card.
                cardVal = deckOfCards.returnFirstCardVal();

                player.addCardFirst(cardVal, cardName, playerName);
                deckOfCards.removeFirst();
            }

        }catch(CustomException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    static String calculateProbability(String cardsTotalValue, LinkedListForDeck deck) // This method is buggy again.
    {
        // This array should return a string value of the probabilities and the method can also print out the probabilities.
        /*
        * This overly complex method takes a card value string that is obtained from the array of cards that each player has (LinkedListForCardsOnTable data type).
        * and it also takes in the deck LinkedList to compare values from the card total string. 
        * 
        */ 

        // This method should also handle the possible moves for the players using the choiceForPlayer method.
        String probabilityStr = "";

        try
        {
            DecimalFormat df = new DecimalFormat("0.00");

            String tempCardsTotalValue = cardsTotalValue.substring(10, cardsTotalValue.length());
            int sizeOfDeck = deck.size();

            int possibleTotalOne = 0; int possibleTotalTwo = 0; int possibleTotalThree = 0;
            double allCardsLeftOverOne = 0; double allCardsLeftOverTwo = 0; double allCardsLeftOverThree = 0;

            if(tempCardsTotalValue.equals("Total card values can either be 2, 22, or 12"))
            {
                possibleTotalOne = 2;
                possibleTotalTwo = 22;
                possibleTotalThree = 12;
            }else
            {
                tempCardsTotalValue = tempCardsTotalValue.replaceAll("[^0-9]", " ");
                tempCardsTotalValue = tempCardsTotalValue.replaceAll(" +", " ");
                int size = tempCardsTotalValue.length();

                String firstNumStr = ""; String secondNumStr = "";

                int spaceCount = 0;

                if(size == 4)
                {
                    for(int i = 0; i < size; i++)
                    {
                        if(tempCardsTotalValue.charAt(i) == ' ')
                        {
                            spaceCount++;
                        }else if(spaceCount == 1)
                        {
                            firstNumStr += String.valueOf(tempCardsTotalValue.charAt(i));
                        }else
                        {
                            secondNumStr += String.valueOf(tempCardsTotalValue.charAt(i));
                        }
                    }

                    possibleTotalOne = Integer.parseInt(firstNumStr);
                    possibleTotalTwo = Integer.parseInt(secondNumStr);

                }else if(size == 5 || size == 6)
                {
                    for(int i = 0; i < size; i++)
                    {
                        if(tempCardsTotalValue.charAt(i) == ' ')
                        {
                            spaceCount ++;
                        }else if(Character.isDigit(tempCardsTotalValue.charAt(i)) && spaceCount == 1)
                        {
                            firstNumStr += String.valueOf(tempCardsTotalValue.charAt(i));
                        }else if(Character.isDigit(tempCardsTotalValue.charAt(i)) && (spaceCount == 2))
                        {
                            secondNumStr += String.valueOf(tempCardsTotalValue.charAt(i));
                        }
                    }

                    possibleTotalOne = Integer.parseInt(firstNumStr);
                    possibleTotalTwo = Integer.parseInt(secondNumStr);
                }else if(size == 3 || size == 2)
                {
                    for(int i = 0; i < size; i++)
                    {
                        if(tempCardsTotalValue.charAt(i) == ' ')
                        {
                            spaceCount++;
                        }else if(Character.isDigit(tempCardsTotalValue.charAt(i)))
                        {
                            firstNumStr += String.valueOf(tempCardsTotalValue.charAt(i));
                        }
                    }

                    possibleTotalOne = Integer.parseInt(firstNumStr);
                }
            }

            if(possibleTotalOne != 0 && possibleTotalTwo != 0 && possibleTotalThree != 0)
            {
                int remainingFirstVal = pointsLeft(possibleTotalOne); 
                int remainingSecVal = pointsLeft(possibleTotalTwo); 
                int remainingThirdVal = pointsLeft(possibleTotalThree);

                allCardsLeftOverOne = deck.compareValToAllCards(remainingFirstVal);
                allCardsLeftOverTwo = deck.compareValToAllCards(remainingSecVal);
                allCardsLeftOverThree = deck.compareValToAllCards(remainingThirdVal);

                System.out.println("There are " + allCardsLeftOverOne + " cards that are less than " + remainingFirstVal);
                System.out.println("There are " + allCardsLeftOverTwo + " cards that are less than " + remainingSecVal);
                System.out.println("There are " + allCardsLeftOverThree + " cards that are less than " + remainingThirdVal);

                double probOne =   (allCardsLeftOverOne / sizeOfDeck) * 100;
                double probTwo = (allCardsLeftOverTwo / sizeOfDeck) * 100;
                double probThree = (allCardsLeftOverThree / sizeOfDeck) * 100;

                probabilityStr += df.format(probOne) + "," + df.format(probTwo) + "," + df.format(probTwo);

                System.out.println("There's a " + df.format(probOne) + "% chance of player (num) getting a card that is less than " + remainingFirstVal + "\n"
                + "There's a " + df.format(probTwo) + "% chance of player (num) getting a card that is less than " + remainingSecVal + "\n"
                + "There's a " + df.format(probThree) + "% chance of player (num) getting a card that is less than " + remainingThirdVal + "\n");

                choiceForPlayer(probabilityStr);
                System.out.println();

            }else if(possibleTotalOne != 0 && possibleTotalTwo != 0)
            {
                int remainingFirstVal = pointsLeft(possibleTotalOne);
                int remainingSecVal = pointsLeft(possibleTotalTwo);

                allCardsLeftOverOne = deck.compareValToAllCards(remainingFirstVal);
                allCardsLeftOverTwo = deck.compareValToAllCards(remainingSecVal);

                System.out.println("There are " + allCardsLeftOverOne + " cards that are less than " + remainingFirstVal);
                System.out.println("There are " + allCardsLeftOverTwo + " cards that are less than " + remainingSecVal);

                double probOne = (allCardsLeftOverOne / sizeOfDeck) * 100;
                double probTwo = (allCardsLeftOverTwo / sizeOfDeck) * 100;

                probabilityStr += df.format(probOne) + "," + df.format(probTwo);

                System.out.println("There's a " + df.format(probOne) + "% chance of player (num) getting a card that is less than " + remainingFirstVal + "\n"
                + "There's a " + df.format(probTwo) + "% chance of player (num) getting a card that is less than " + remainingSecVal + "\n");

                choiceForPlayer(probabilityStr);

                System.out.println();

            }else if(possibleTotalOne != 0)
            {
                int remainingFirstVal  = pointsLeft(possibleTotalOne);
                allCardsLeftOverOne = deck.compareValToAllCards(remainingFirstVal);
                double probOne = (allCardsLeftOverOne / sizeOfDeck) * 100;

                System.out.println("There are " + allCardsLeftOverOne + " cards that are less than " + remainingFirstVal);

                probabilityStr += df.format(probOne);

                System.out.println("There's a " + df.format(probOne) + "% chance of player (num) getting a card that is less than " + remainingFirstVal + "\n");

                choiceForPlayer(probabilityStr);

                System.out.println();
            }

        }catch(CustomException ex)
        {
            System.out.println(ex.getMessage());
        }catch(java.lang.NumberFormatException ex)
        {
            System.out.println("Number format error.");
        }
        
        return probabilityStr;
    }

    static void choiceForPlayer(String probability)
    {

         /*
             * I should create different output depending on the probability.
             * - hit
             *  - prob == 0, impossible to gain anything from a hit.
             *  - prob == 100, certain to gain from a hit.
             *  - prob == 50, even chance of gaining or losing.
             *  - prob < 50, unlikely to gain anything from a hit.
             *  - prob > 50 likely to gain froma hit
             * 
             * - stand
             *  - prob == 0, impossible to gain, so stand.
             *  - prob == 100, certain to gain, don't stand.
             *  - prob == 50, even chance, you can stand or not stand.
             *  - prob < 50, unlikely to gain, so stand.
             *  - prob > 50, likely to gain, so don't stand.
             */

        String [] tempA = probability.split(",");
        int size = tempA.length;

        for(int i = 0; i < size; i++)
        {
            double temp = Double.parseDouble(tempA[i]);

            if(temp == 0)
            {
                System.out.println("With a probability of " + tempA[i] + "%. It would be a bad idea to hit. It's best just to stand");
            }else if(temp == 100)
            {
                System.out.println("With a probability of " + tempA[i] + "%. It would be a good idea to hit. It would be a bad idea to stand");
            }else if(temp == 50)
            {
                System.out.println("With a probability of " + tempA[i] + "%. You have an even chance of going for a hit ot a stand.");

            }else if(temp < 50)
            {
                System.out.println("With a probability of " + tempA[i] + "%. Going for a hit is a bit risky. It's better idea to stand");

            }else if(temp > 50)
            {
                 System.out.println("With a probability of " + tempA[i] + "%. The likelyhood of going for a hit is a good idea. It's risky to stand");
            }
        }
    }
}