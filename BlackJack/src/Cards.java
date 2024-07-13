import java.util.LinkedList;
import java.util.Random;


public class Cards 
{
    /*
     * Notes:
     * 2-10 are face values cards. 2 is worth 2 points, 3 is worth three points, etc.
     * Jack, Queen, and King are worth ten points.
     * Ace can be worth 11 or a 1
     * 
     * First, the class needs to create all the cards. 
     *
    */
    private  String [] suits= {"Hearts", "Diamonds", "Clubs", "Spades"};
    private String [] faceAndNumCards = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jacks", "Queens", "Kings"};
    private LinkedList<String> cards = new LinkedList<String>();
    private LinkedList<String> cardsOnTable = new LinkedList<String>();
    private int decks;


    public Cards()
    {
        cards = null;
        decks = 0;
    }

    public Cards(int NewDecks)
    {
        decks = NewDecks;
    }

    public LinkedList<String> getCards()
    {
        return cards;
    }

    //This method could be useless considering what the program does.
    public void shuffleCards()
    {
        int size = cards.size();
        Random randSpot = new Random();
 
        for(int i = 0; i < size; i++)
        {
            int spot = randSpot.nextInt(size);
            String temp = cards.get(i);
            String newSpot = cards.get(spot);
 
            cards.set(i, newSpot);
            cards.set(spot, temp);
        }
    }

    public void makeCards(int NewDecks)
    {

        for(int deckCount = 0; deckCount < decks; deckCount++)
        {
            for(int suitCount = 0; suitCount < suits.length; suitCount++)
            {
                for(int faceAndNumCardsCount = 0; faceAndNumCardsCount < faceAndNumCards.length; faceAndNumCardsCount++)
                {
                    String cardCreation = faceAndNumCards[faceAndNumCardsCount]+ " of " + suits[suitCount] ;
                    cards.add(cardCreation);
                }
            }
        }
    }

    //Method to add cards that are on the table to the cardsOnTheTable Linked List
    public void addCardsToCardOnTable(int suitChoice, int faceAndNumCardChoice)
    {
        //Specify the player and dealer cards.
        String cardCreation = faceAndNumCards[faceAndNumCardChoice]+ " of " + suits[suitChoice] ;
        cardsOnTable.add(cardCreation);
    }
 
    public void getRidOfCards(int suitChoice, int faceAndNumChoice)
    {

        String cardCreation = faceAndNumCards[faceAndNumChoice]+ " of " + suits[suitChoice] ;
        cardsOnTable.remove(cardCreation);
    }

    public LinkedList<String> getCardsOnTable()
    {
        return cardsOnTable;
    }


    @Override
    public String toString()
    {
        String allCards = "";

        for(int i = 0; i < cards.size(); i++)
        {
            if(i == cards.size()-1)
            {
                allCards += cards.get(i);
            }else
            {
                allCards += cards.get(i) + ", ";
            }
        }
        return allCards;
    }
}