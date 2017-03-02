
package pokerhands;

import java.util.*;
/**
 * Hand luokan olio vastaa pelaajan kättä, eli pelaajan saamaa kokoelmaa kortteja
 * @author Salla
 */
public class Hand {
    
    final private CardType cardtype;
    final private String[] marking;                    //arvo merkintä
    final private TreeMap<Integer, Card> ranks;        //käden korttien arvot
    final private Map<Integer, Card> multiples;        //toistuvat arvot
    private Card frequent;                          //yleisin kortti 
    final private Set<String> suits;                //kädessä esiintyvät maat    
    final private List<String> cards;               //käden kortit merkkijonoesityksenä
    private String hands;                           //pokerikädet    
    
    /**
     * Konstruktori asettaa kädelle CardComparator olion, joka määrittää korttien järjestyksen.
     * @param comparator CardComparator luokan olio
     */
    public Hand(Comparator cardtype){
        this.ranks = new TreeMap<Integer, Card>(cardtype);
        this.multiples = new TreeMap<Integer, Card>();
        this.frequent = null;
        this.suits = new HashSet<String>();
        this.cards = new Vector<String>();
        this.hands = "";
        this.cardtype = (CardType)cardtype;
        this.marking = this.cardtype.ranks();
    }
    
    /**
     * addCard lisää käteen parametrina annetun kortin.
     * @param card Card luokan olio
     */
    public void addCard(Card card){
        try{
            cards.add(card.toString(this.marking)); //korttien merkkijonoesitys muistiin             
            suits.add(card.getSuit());              //maat muistiin
            Card last = ranks.put(card.getValue(), card);      //lisätään kortin arvo käden arvoihin, last:iin talletetaan viimeksi talletettu saman arvoinen kortti (tai null jos saman arvoista korttia ei ole talletettu)
            Card previous = multiples.put(last.getValue(), last);  //jos numeroa on ollut aiemminkin lisätään se toistuviin arvoihin
            if(previous!=null) frequent = previous;     //jos arvoa on ollut enemmän kuin kaksi kpl niin merkataan se yleisimmäksi
        }catch(Exception e){                        //tyhjät arvot jätetään huomioimatta   
        }
    }
    
    /**
     * setHands asettaa pokerikädet
     * @param hands pokerikädet merkkijonoesityksenä
     */
    public void setHands(String hands){
        this.hands = hands;
    }
    
    /**
     * Palauttaa kädestä löytyvät pokerikädet, jos ne on asetettu.
     * @return String, pokerikädet
     */
    public String getHands(){
        return this.hands;
    }
    
    /**
     * getRanks palauttaa kädestä löytyvät arvot
     * @return TreeMap<Integer, Card>, jossa kortteja, jotka sisältävät kädessä olevat arvot
     */
    public TreeMap<Integer, Card> getRanks(){
        return this.ranks;
    }
    
    /**
     * getMultiples palauttaa kädessä toistuvat arvot
     * @return Set<Integer>, jossa kädessä toistuneet arvot 
     */
    public Set<Integer> getMultiples(){
        return this.multiples.keySet();
    }
    
    /**
     * getFrequent palauttaa kortin, jonka arvoa on esiintynyt enemmän kuin kaksi kertaa kädessä.
     * Muussa tapauksessa palauttaa null.
     * @return Card luokan olio, jolla on kädessä eniten toistunut arvo tai tyhjän arvo, jos korteissa ei ole paria suurempaa pokerikättä.
     */
    public Card getFrequent(){
        return this.frequent;        
    }
    
    /**
     * Palauttaa kädessä esiintyvien maiden määrän.
     * @return int, kädessä esiintyvien maiden määrän lukuarvo
     */
    public int getSuits(){
        return this.suits.size();
    }
    
    /**
     * Palauttaa käden korkeimman kortin arvon.
     * @return int, käden korkein arvo
     * @throws java.lang.Exception
     */
    public int high() throws Exception{
        return ranks.lastKey();
    }
    
    /**
     * Palauttaa kädessä esiintyvien eri numeroiden määrän.
     * @return int käden numerojen määrä
     */
    public int numberCardinality(){
        return ranks.size();
    }
    
    /**
     * Palauttaa kädestä merkkijonoesityksen, jossa käden kortit on järjestetty parametrina annetun Comparator
     * rajapinnan toteuttavan luokan olion osoittaman järjestyksen mukaan, sekä niiden jälkeen kädestä löytyvät
     * pokerikädet, jos ne on asetettu.
     * @param order Comparator, joka osoittaa korttien järjestämiseen käytettävän järjestyksen
     * @return String, käden tiedot
     */
    public String orderedPrint(Comparator<String> order){
        try{
            Collections.sort(cards, order);
            return toString();
        }catch(IllegalArgumentException e){
            Collections.sort(cards);
            return toString();
        }catch(Exception e){
            return "";
        }
    }
    
    @Override
    public String toString(){
        try{
            String hand = "Cards: ";
            for(String card: cards){
                hand += card + ", ";
            }
            return hand + "Pokerhands: " + this.hands;
        }catch(Exception e){
            return "";
        }
    }
    

}
