import java.util.InputMismatchException;
import java.util.Scanner;


public class Main 
{
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        Cards newCards;
        //Need to create loop for user input
        
        try
        {
            System.out.println(" _________________________________________________________________");
            System.out.println("|              Black jack probability calculator                  |");
            System.out.println("|_________________________________________________________________|");
            System.out.println("(Don't use this program if you are already in the middle of a game)");
    
            System.out.println("How many deck of cards are being used (max deck of cards is nine)? ");
            String deckSizeTemp = userInput.nextLine();
            int deckSize = Integer.parseInt(deckSizeTemp);
            deckCheck(deckSize);

            newCards = new Cards(deckSize);
            newCards.makeCards(deckSize);
            //newCards.shuffleCards();

            System.out.println("How many players are playing (include the dealer as well)?");
            String playerTemp = userInput.nextLine();
            int players = Integer.parseInt(playerTemp);
            playerCheck(players);
            
            //I could put this for loop in a while loop to prevent the user from entering the wrong information.

            // I need to input all of the user's input into a linkedlist 
            for(int i = 0; i < players; i++)
            {
                //Cards on the table can be
                if(i == 0)
                {
                    System.out.println("What card does the dealer have?");
                    System.out.println("Input the correct choice to represent the cards");
                    SuitChoice();
                    String suitChoiceTemp = userInput.nextLine();
                    int suitChoice = Integer.parseInt(suitChoiceTemp);
                    suitChoiceCheck(suitChoice);

                    faceAndNumCardsChoice();
                    String faceAndNumCardsChoiceTemp = userInput.nextLine();
                    int faceAndNumCardsChoice = Integer.parseInt(faceAndNumCardsChoiceTemp);
                    faceAndNumCardsCheck(faceAndNumCardsChoice);

                    
                    newCards.getRidOfCards(suitChoice, faceAndNumCardsChoice);
                    newCards.addCardsToCardOnTable(suitChoice, faceAndNumCardsChoice);

                    //Add card to cards on the table.

                }else
                {
                    //This loop needs to run twice because each player always two cards at the start of each round, unlike the dealer. 
                    for(int j = 0; j < 2; j++)
                    {
                        if(j == 1)
                        {
                            System.out.println("What's player's" + i + " first card?");
                            SuitChoice();
                            String suitChoiceTemp = userInput.nextLine();
                            int suitChoice = Integer.parseInt(suitChoiceTemp);
                            suitChoiceCheck(suitChoice);

                            faceAndNumCardsChoice();
                            String faceAndNumCardsChoiceTemp = userInput.nextLine();
                            int faceAndNumCardsChoice = Integer.parseInt(faceAndNumCardsChoiceTemp);
                            faceAndNumCardsCheck(faceAndNumCardsChoice);

                            newCards.getRidOfCards(suitChoice, faceAndNumCardsChoice);
                            newCards.addCardsToCardOnTable(suitChoice, faceAndNumCardsChoice);

                        }else
                        {
                            System.out.println("What's player's" + i + " second card?");
                            SuitChoice();
                            String suitChoiceTemp = userInput.nextLine();
                            int suitChoice = Integer.parseInt(suitChoiceTemp);
                            suitChoiceCheck(suitChoice);

                            faceAndNumCardsChoice();
                            String faceAndNumCardsChoiceTemp = userInput.nextLine();
                            int faceAndNumCardsChoice = Integer.parseInt(faceAndNumCardsChoiceTemp);
                            faceAndNumCardsCheck(faceAndNumCardsChoice);

                            newCards.getRidOfCards(suitChoice, faceAndNumCardsChoice);
                            newCards.addCardsToCardOnTable(suitChoice, faceAndNumCardsChoice);
                            
                        }
                    }
                    System.out.println("");
                }
            }

            newCards.getCardsOnTable().clear();


        }catch(InputMismatchException exception)
        {
            System.out.println("Wrong data type.");
        }catch(CustomException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    static void SuitChoice()
    {
        System.out.println("0. Hearts");
        System.out.println("1. Diamonds");
        System.out.println("2. Clubs");
        System.out.println("3. Spades");
    }

    static void suitChoiceCheck(int choice) throws CustomException
    {
        if(choice < 0 || choice > 3)
        {
            throw new CustomException("Your choice isn't valid");
        }
    }

    static void faceAndNumCardsChoice()
    {
        System.out.println("0. Ace");
        System.out.println("1. 1");
        System.out.println("2. 2");
        System.out.println("3. 3");
        System.out.println("4. 4");
        System.out.println("5. 5");
        System.out.println("6. 6");
        System.out.println("7. 7");
        System.out.println("8. 8");
        System.out.println("9. 9");
        System.out.println("10. Jacks");
        System.out.println("11. Queens");
        System.out.println("12. Kings");
    }

    static void faceAndNumCardsCheck(int choice) throws CustomException
    {
        if(choice < 0 || choice > 12)
        {
            throw new CustomException("Your choice isn't valid");
        }
    }

    static void deckCheck(int deck) throws CustomException
    {
        if(deck > 9)
        {
            throw new CustomException("Deck size is larger than nine");
        }else if(deck <= 0)
        {
            throw new CustomException("Deck size is less than or equal to 0");
        }else
        {
            System.out.println("Valid deck size");
        }
    }

    static void playerCheck(int players) throws CustomException
    {
        if(players < 2)
        {
            throw new CustomException("There aren't enough players");
        }else if(players > 7)
        {
            throw new CustomException("There are too many players");
        }else
        {
            System.out.println("Valid amount of players.");
        }
    }
}
