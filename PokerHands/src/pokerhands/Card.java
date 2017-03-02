
package pokerhands;

/**
 * Card luokan olio vastaa pelipakan pelikorttia 
 * @author Salla
 */
public class Card implements Comparable<Card>{
    
    private final String suit;
    private final int rank;
    
    /**
     * Konstruktori asettaa kortille maan ja arvon.
     * @param rank kortin arvo
     * @param suit kortin maa
     */
    public Card(int rank, String suit){
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * getValue palauttaa kortin arvon 
     * @return int, kortin arvo
     */
    public int getValue(){
        return this.rank;
    }
    
    /**
     * getSuit palauttaa kortin maan
     * @return String, kortin maa
     */
    public String getSuit(){
        return this.suit;
    }
    
    /**
     * Palauttaa merkkijonoesityksenä kortin maan ja arvon käytettävällä arvomerkinnällä
     * @param values käytettävä arvomerkintä
     * @return kortin maa ja arvo merkkijonoesityksenä
     */
    public String toString(String[] values) throws Exception{
        return this.suit + " " + values[this.rank-1];
    }
    
    @Override
    public String toString(){
        return this.suit + " " + this.rank;
    }
   
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Card)) return false;
        Card other = (Card)object;
        if(other.getValue()==this.getValue()) return true;
        else return false;
    }
    
    @Override
    public int hashCode(){
        return this.rank;
    }
 
    @Override
    public int compareTo(Card other){
            return this.rank - other.getValue();
    }
}
