public class LinkedListForCardsOnTable 
{
    private Node head;
    private Node tail;
    private int count = 0;

    public class Node
    {
        private String value;
        private String playerName;
        private String cardName;
        private Node next;

        Node(String newValue, String newCardName, String newPlayerName)
        {
            value = newValue;
            cardName = newCardName;
            playerName = newPlayerName; 
            next = null;
        }
    }

    public void addCardFirst(String value, String cardName, String playerName) // Add card to the front of the list.
    {
        Node newNode = new Node(value, cardName, playerName); // Create a new node

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

    public String searchByCardValByIndex(int index) throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("This player doesn't have any cards");
        }else // No reason to include other exceptions since the a loop controls the indexes that will be accesssed.
        {
            Node current = head;

            for(int i = 1; i < index; i++)
            {
                current = current.next;
            }

            return current.value;
        }
    }

    public String calculateTotalValForPlayer() throws CustomException
    {
        // This method needs to be better optimized somehow.

        if(isEmpty())
        {
            throw new CustomException("Player does not have any cards");
        }else
        {
            Node current = head;
            String valueOne = null;
            String valueTwo = null;

            for(int i = 1; i <= 2; i++)
            {
                if(i == 1)
                {
                    valueOne = current.value;
                }else if(i == 2)
                {
                    valueTwo = current.value;
                }

                current = current.next;
            }

            int intValueOne; int intValueTwo;
            int totalOne; int totalTwo; int total;

            if(valueOne.equals("1 or 11") && valueTwo.equals("1 or 11"))
            {
                return "Total card values can either be 2, 22, or 12";

            }else if(valueTwo.equals("1 or 11"))
            {
            
                intValueOne = Integer.parseInt(valueOne);
                
                totalOne = 1 + intValueOne;
                totalTwo = 11 + intValueOne;

                return "Total can be either " + totalOne + " or " + totalTwo;
            }else if(valueOne.equals("1 or 11"))
            {
                intValueTwo = Integer.parseInt(valueTwo);
                
                totalOne = 1 + intValueTwo;
                totalTwo = 11 + intValueTwo;

                return "Total can be either " + totalOne + " or " + totalTwo;
            }else
            {
                intValueTwo = Integer.parseInt(valueTwo);
                intValueOne = Integer.parseInt(valueOne);
                
                total = intValueOne + intValueTwo;

                return "Total card val is " + total;
            }
        }

    }
    public String getFirstCardVal() throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("There are no cards to return");
        }else
        {
            return head.value;
        }
    }

    public void addThirdCardValueToPlayerHand(String thirdCardValue, String totalValue)
    {
        totalValue = totalValue.replaceAll("[^0-9]", "");
        totalValue = totalValue.replaceAll(" +", " ");
        System.out.println(totalValue);

        int tempTotal = Integer.parseInt(totalValue);
        int totalValueOne = 0;
        int totalValueTwo = 0;
        int total = 0;

        if(thirdCardValue.equals("1 or 11"))
        {
            totalValueOne = tempTotal + 1;
            totalValueTwo = tempTotal + 11;
            System.out.println("Your final score is either " + totalValueOne + " or " + totalValueTwo);
        }else
        {
            total = Integer.parseInt(thirdCardValue) + tempTotal;
            System.out.println("Your finals score is " + total);
        }
    }

    public void removeCard(String card) throws CustomException 
    {
        // Maybe this method should take in a card name and search for that instead.

        if(isEmpty())
        {
            throw new CustomException("There are no cards in the list");
        }else
        {
            Node current = head;
            Node previous = null;

            while(!current.cardName.equals(card))
            {
                previous = current;
                current = current.next;
            }

            current = current.next;
            previous.next = current;
            count--;
        }
    }

    public String searchForCardByPlayerName(String playerName) throws CustomException
    {
        if(isEmpty())
        {
            throw new CustomException("Can't search for player name because there are no cards on the table.");
        }else
        {
            Node current = head;

            while(!current.playerName.equals(playerName))
            {
                current = current.next;
            }

            return current.cardName + " of " + current.value;
        }
    }

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

    public void printCards() throws CustomException
    {
        Node current = head;

        if(isEmpty())
        {
            throw new CustomException("Deck is empty");
        }else
        {
             if(current.playerName.equals("Dealer"))
            { 
                for(int i = 0; i <= 0; i++)
                {
                    System.out.println(current.playerName+ ": " + current.cardName + ", value: " + current.value);
                    current = current.next;
                }
                
                System.out.println();
            }else
            {
                while(current != null)
                {
                    System.out.println(current.playerName+ ": " + current.cardName + ", value: " + current.value);
                    current = current.next;
                }

                System.out.println();
            }
        }
    }

}
