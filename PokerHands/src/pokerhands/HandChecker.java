
package pokerhands;

import java.util.Set;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * Luokka käsien tarkasteluun. Määrittää korteista löytyvät pokerikädet.
 * @author Salla
 */
public class HandChecker {
    
    final private String[] values;
    final private Comparator comparator;
    
    /**
     * Konstruktorille annetaan parametrina korteissa käytetty arvomerkintä
     * @param values String[] arvomerkintä
     */
    public HandChecker(CardType cardtype){
        this.values = cardtype.ranks();
        this.comparator = (Comparator)cardtype;
    }
    
    /**
     * checkForHands tarkastaa kädestä löytyvät pokerikädet ja merkkaa ne käden tietoihin.
     * @param hand Hand luokan olio, josta halutaan tietää pokerikädet.
     */
    public void checkForHands(Hand hand){
        try{
            switch(hand.numberCardinality()){               //kuinka monta eri numeroa kädessä on
                case 2: testForFullHouse(hand);             //Täyskäsi tai neloset
                        break;
                case 3: testForTriple(hand);                //kaksi paria tai kolmoset
                        break;
                case 4: hand.setHands("pari");              // pari 
                        break;
                case 5: checkRanksAndSuits(hand);           //Onko väri tai suora?
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testForFullHouse(Hand hand) throws Exception {
        Set multiples = hand.getMultiples();
            switch(multiples.size()){
                case 2: hand.setHands("täyskäsi");              //täyskäsi
                        break;
                case 1: hand.setHands("neloset");               //neloset
            }
    }
        
    private void testForTriple(Hand hand) throws Exception {
        if(hand.getFrequent()==null)  hand.setHands("kaksi paria");     //kaksi paria
        else    hand.setHands("kolmoset");                              //kolmoset
    }
    
    private void checkRanksAndSuits(Hand hand) throws Exception {
        String hands = "";
        boolean flush = testFlush(hand);                  //onko väri?
        if(flush) hands = "väri";        
        if(straight(hand)) hands += "suora";              //onko suora?
        else if(!flush) hands += values[hand.high()-1] + " high";    //korkein kortti (high)
        hand.setHands(hands);
    }

    private boolean testFlush(Hand hand) throws Exception {
            return (hand.getSuits()==1);            //väri jos maiden määrä on yksi
    }
    
    private boolean straight(Hand hand) throws Exception {
            TreeMap<Integer, Card> ranks = hand.getRanks();
            Set<Integer> keys = ranks.navigableKeySet();
            return compareRanks(keys, ranks.lastKey(), ranks.firstKey());
    }
    
    private boolean compareRanks(Set<Integer> keys, Integer last, Integer first) throws Exception{
        int maxdist = comparator.compare(last, first);      //ensimmäisen ja viimeisen arvon etäisyys
        switch(maxdist){
            case 4:  return true;                   //suora
            case 12: for(Integer key: keys){           //mahdollinen suora tutkitaan
                        int diff = Math.abs(comparator.compare(last, key));
                        if(diff!=1 && diff!=12 && diff!=9) return false;
                        last = key; 
                        }
                        return true;
            default: return false;
        }
    }

}
