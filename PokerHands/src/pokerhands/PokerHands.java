
package pokerhands;

import java.util.*;
/**
 * Purpose of this program is to draw 3 hands from a card deck and determine found pokerhands.
 * @author Salla
 */
public class PokerHands {

    /**
     * Korttien merkkijonoesitysten vertailuun.
     */
    public static final Comparator<String> CARD_STRING_ORDER = new Comparator<String>() { 
        
    @Override
        public int compare(String f, String s){
            String[] first = f.split(" ");
            String[] second = s.split(" ");
            int diff = value(first[1])-value(second[1]);
            if(diff==0)   return first[0].compareTo(second[0]);
            return diff;
            }  
            
        private int value(String rank){
            try{
                return Integer.parseInt(rank);
            }catch(Exception e){
                return 11 + "JQKA".indexOf(rank);
            }
        }
            
                
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CardType type = new BasicCardType();
        Deck cardDeck = new Deck(type);
        cardDeck.shuffle();
        HandChecker checker = new HandChecker(type);
        
        for(int i=0; i<3; i++){
            Hand hand = cardDeck.drawHand(5);
            checker.checkForHands(hand);
            System.out.println(hand.orderedPrint(CARD_STRING_ORDER));
        }

    }
    
}
