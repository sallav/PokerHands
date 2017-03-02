
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pokerhands.*;
import static pokerhands.PokerHands.CARD_STRING_ORDER;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
/**
 *
 * @author Salla
 */
public class HandCheckerTest {
    
    HandChecker checker;
    CardType ctype = new BasicCardType();
    Comparator order = (Comparator)ctype;
    
    public HandCheckerTest() {
        checker = new HandChecker(ctype);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void AllStraightsTest(){
        for(int i=1; i<14; i++){
            Hand hand = new Hand(order);
            for(int j=0; j<5; j++){
                int sum = i+j;
                if(sum>13) sum -= 13;
                Card card = new Card((sum), "Hertta");
                hand.addCard(card);
            }
            checker.checkForHands(hand);
            Assert.assertEquals("värisuora", hand.getHands());
        }
    }
   
    @Test
    public void FullHouseTest(){
        for(int i=0; i<4; i++){
                for(int j=i+1; j<5; j++){
                    Hand hand = new Hand(order);
                    for(int k=0; k<5; k++){
                        if(k==i) hand.addCard(new Card(6, "Ruutu"));
                        else if(k==j) hand.addCard(new Card(6, "Hertta"));
                        else hand.addCard(new Card(11, "X"));
                    }
                    checker.checkForHands(hand);
                    Assert.assertEquals("täyskäsi", hand.getHands());
                }
        }
    }
    
    @Test
    public void MultiTest(){
        String[] hands = {"neloset", "kolmoset", "pari", "J high", "värisuora"};    //testattavat kombinaatiot
        for(int k=0; k<5; k++){
            for(int i=0; i<5; i++){
                Hand hand = new Hand(order); 
                for(int j=0; j<5; j++){
                    int a = j + k;
                    if(a>4) a-=5;
                    if(a>i) hand.addCard(new Card(11, "X"));            
                    else hand.addCard(new Card(j+2, "Y"));
                }
                checker.checkForHands(hand);
                Assert.assertEquals(hands[i], hand.getHands());
            }
        }
    }
    
    @Test
    public void MultiTestForOrder(){
        Random rand = new Random();
        String[] hands = {"neloset", "kolmoset", "pari", "J high", "värisuora"};    //testattavat kombinaatiot
        for(int t=0; t<20; t++){                                                          
            for(int i=0; i<5; i++){
                Hand hand = new Hand(order); 
                ArrayList<Card> cards = new ArrayList<>();
                for(int j=0; j<5; j++){                     //käydään läpi eri kombinaatioita
                    if(j>i) cards.add(new Card(11, "X"));
                    else cards.add(new Card(j+2, "Y"));
                }for(int k=5; k>0; k--){                    //arvotaan järjestys
                    int ind = rand.nextInt(k);
                    hand.addCard(cards.remove(ind));
                }
                checker.checkForHands(hand);
                Assert.assertEquals(hands[i], hand.getHands());
            }
        }
    }
    
    @Test
    public void MultiTestForOrder2(){
        Random rand = new Random();
        String[] hands = {"neloset", "täyskäsi", "kaksi paria", "pari", "pari"};    //testattavat kombinaatiot
        for(int t=0; t<20; t++){                                                          
            for(int i=0; i<5; i++){
                Hand hand = new Hand(order); 
                ArrayList<Card> cards = new ArrayList<>();
                for(int j=0; j<5; j++){                     //käydään läpi eri kombinaatioita
                    if(j==0) cards.add(new Card(3, "Y"+j));
                    else if(j>i) cards.add(new Card(11, "X"+j));
                    else cards.add(new Card(j+2, "Y"+j));
                }for(int k=5; k>0; k--){                    //arvotaan järjestys
                    int ind = rand.nextInt(k);
                    hand.addCard(cards.remove(ind));
                }
                checker.checkForHands(hand);
                Assert.assertEquals(hands[i], hand.getHands());
            }
        }
    }
    
}
