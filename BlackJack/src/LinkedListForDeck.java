import java.util.Random;

public class LinkedListForDeck 
{
    private Node head;
    private Node tail;
    private int count = 0;

    class Node
    {
        private String value;
        private String cardName;
        private Node next;

        Node(String newValue, String newCardName)
        {
            value = newValue;
            cardName = newCardName;
            next = null;
        }
    }

    public void generateCardsForDeck(int decks)
    {
        String [] suits= {"Hearts", "Diamonds", "Clubs", "Spades"};
        String [] faceAndNumCards = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jacks", "Queens", "Kings"};

        for(int deckCount = 0; deckCount < decks; deckCount++)
        {
            for(int suitCount = 0; suitCount < 4; suitCount++)
            {
                for(int faceAndNumCardsCount = 0; faceAndNumCardsCount < 13; faceAndNumCardsCount++)
                {
                    String cardCreation = faceAndNumCards[faceAndNumCardsCount]+ " of " + suits[suitCount] ;
                    addCardFirst(checkValueOfCard(faceAndNumCards[faceAndNumCardsCount]), cardCreation);
                }
            }
        }
    }

    public void addCardFirst(String value, String cardName)
    {
        Node newNode = new Node(value, cardName); // Create a new node

        if(isEmpty())
        {
            head = tail = newNode;
            count++;
        }else
        {
            Node temp = head;
            head = newNode;
            head.next = temp;
            count++;
        }
    }

    
    public void shuffleCards(LinkedListForDeck deckOfCards, Random rand)
    {
        try
        {
            int size = deckOfCards.size();

            for(int i = 1; i <= size; i++)
            {
                int spot = rand.nextInt(size);
                swap(i, spot);
            }
            System.out.println("Cards have been shuffled.");

        }catch(CustomException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }

    public void swap(int card, int randomCard) throws CustomException
    {
        // https://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
        // Check the link out.
        if(isEmpty())
        {
            throw new CustomException("Deck is empty");
        }else
        {

            if(card == randomCard)
            {
                return;
            }else
            {

                Node currentFirst = head;
                Node previousFirst = null;

                Node currentSecond = head;
                Node previousSecond = null;

                for(int i = 1; i < randomCard; i++)
                {
                    previousFirst = currentFirst;
                    currentFirst = currentFirst.next;
                }

                for(int i = 1; i < card; i++)
                {
                    previousSecond = currentSecond;
                    currentSecond = currentSecond.next;
                }

                if (currentFirst == null || currentSecond == null)
                    return;
        
                // If x is not head of linked list
                if (previousFirst != null)
                    previousFirst.next = currentSecond;
                else // make y the new head
                    head = currentSecond;
        
                // If y is not head of linked list
                if (previousSecond != null)
                    previousSecond.next = currentFirst;
                else // make x the new head
                    head = currentFirst;

                Node temp = currentFirst.next;
                currentFirst.next = currentSecond.next;
                currentSecond.next = temp;

            }
        }
    }

    public double compareValToAllCards(int val) throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("There are no cards in the deck.");
        }else
        {
            Node current = head;
            double count = 0;

            while(current != null)
            {
                String tempVal = getCardValue(current);

                if(tempVal.equals("1 or 11"))
                {
                    if(11 <=  val)
                    {
                        count++;
                    }else if(1 <= val)
                    {
                        count++;
                    }
                }else
                {
                    int tempValInt = Integer.parseInt(tempVal);
                    
                    if(tempValInt <= val)
                    {
                        count++;
                    }
                }

                current = current.next;
            }
            return count;
        }
    }

    public void removeFirst() throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("Deck is empty. Can't remove the first card");
        }else
        {
            Node temp = head;
            head = head.next;
            temp = null;
            count--;
        }
    }

    public String grabNodeAtI(int index) throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("Deck is empty");
        }else
        {
            Node currentCard = head;

            for(int i = 1; i < index; i++)
            {
                currentCard = currentCard.next;
            }

            String card = currentCard.cardName + ", value: " + currentCard.value + "; ";
            return card;
        }
    }

    // Create a method that removes a card temporarily, so it doesn't affect the shuffle method process?
    public String checkValueOfCard(String faceCard)
    {
        if(faceCard.equals("Jacks") || faceCard.equals("Queens") || faceCard.equals("Kings"))
        {
            return "10";
        }else if(faceCard.equals("Ace"))
        {
            return "1 or 11";
        }else
        {
            return faceCard;
        }
    }

    public boolean isEmpty()
    {
        if(count == 0)
        {
            return true;
        }else
        {
            return false;
        }
    }

    public int size()
    {
        return count;
    }

    public void printCards()
    {
        Node current = head;

        while(current != null)
        {
            System.out.print(current.cardName + ", value: " + current.value + "; ");
            current = current.next;
        }
        
        System.out.println();
    }

    public String returnFirstCardName() throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("Deck is empty");
        }else
        {
            return head.cardName;
        }
    }

    public String returnFirstCardVal() throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("Deck is empty");
        }else
        {
            return head.value;
        }    
    }

    public String getCardValue(Node current)
    {
        return current.value;
    }
}