
package pokerhands;

import java.util.*;
/**
 * Deck luokan olio vastaa korttipakkaa
 * @author Salla
 */
public class Deck {
    
    private final CardType cardtype;
    private final ArrayList<Card> cards;
    private final String[] suits;
    private final String[] ranks;
    
    /**
     * Konstruktori asettaa korttipakalle kortin maat ja arvot, sekä korttien vertailua varten CardComparator olion.
     * Lisäksi alustetaan lista kortteja varten, jonka alustava kapasiteetti on maat kerrottuna arvoilla..
     * @param cardtype käytettävä korttityyppi
     */
    public Deck(CardType cardtype){
        this.cardtype = cardtype;
        this.suits = cardtype.suits();
        this.ranks = cardtype.ranks();
        this.cards = new ArrayList<Card>(this.suits.length*this.ranks.length);    //kortit
        setCards();
    }
    
    private void setCards(){                        
        for(int i=0; i<suits.length; i++){          //luodaan korttipakan kortit
            for(int j=1; j<=ranks.length; j++){
                Card card = new Card(j, this.suits[i]);
                this.cards.add(card);
            }
        }
    }
    
    /**
     * Sekoittaa pakan kortit
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    /**
     * Nostaa pakasta käden, eli parametrin size osoittaman määrän kortteja.
     * @param size kuinka monta korttia nostetaan
     * @return Hand luokan olio, joka vastaa pakasta nostettua kättä
     */
    public Hand drawHand(int size){
        try{
            Comparator cardorder = (Comparator)this.cardtype;
            Hand hand = new Hand(cardorder);
            for(int i=0; i<size; i++){
                hand.addCard(draw());
            }
            return hand;
        }catch(Exception e){
            return null;
        }
    }
    
    private Card draw(){
        try{
            return this.cards.remove(0);        //ottaa pakasta päällimmäisen kortin
        }catch(Exception e){
            setCards();                         //jos ei onnistunut, sekoitetaan uusi pakka
            shuffle();
            return this.cards.remove(0);
        }
    }
    
}
