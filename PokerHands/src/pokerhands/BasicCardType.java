
package pokerhands;

import java.util.*;
/**
 * Luokka korttien vertailua varten
 * @author Salla
 */
public class BasicCardType implements Comparator<Integer>, CardType {

    final private String[] suits = {"Hertta", "Ruutu", "Risti", "Pata"};
    final private String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};    
    final private int[] values = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    
    @Override
    public int compare(Integer first, Integer other){
        try{
            return values[first-1] - values[other-1];
        }catch(Exception e){
            return first-other;
        }
    }
    
    /**
     * Palauttaa käytössä olevan arvomerkinnän
     * @return käytettävät arvot taulukkona
     */
    @Override
    public String[] ranks(){
        return ranks;
    }
    
    /**
     * Palauttaa käytössä olevat maat
     * @return käytettävät maat taulukkona
     */
    @Override
    public String[] suits(){
        return suits;
    }
    
}
