package model;

import model.rules.hit.IntfHitStrategy;
import model.rules.factory.EpicRulesFactory;
import model.rules.hit.Soft17HitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Soft17Test {

  EpicRulesFactory rulesFactory = new EpicRulesFactory();
  Dealer dealer = new Dealer(rulesFactory);
  Player player = new Player();

  private boolean usesSoftSeventeen() {
    IntfHitStrategy IntfHitStrategy = rulesFactory.getHitRule();
    return IntfHitStrategy instanceof Soft17HitStrategy;
  }

  @Test
  public void softSeventeenLogicTest() {
    if (usesSoftSeventeen()) {
      if (dealer.newGame(player)) {
        dealer.clearHand();

        // create some cards relevant to the test
        Card.Mutable aceOfSpades = new Card.Mutable(Card.Color.Spades, Card.Value.Ace, false);
        Card.Mutable sixOfSpades = new Card.Mutable(Card.Color.Spades, Card.Value.Six, false);
        Card.Mutable aceOfHearts = new Card.Mutable(Card.Color.Hearts, Card.Value.Ace, false);
        Card.Mutable kingOfSpades = new Card.Mutable(Card.Color.Spades, Card.Value.King, false);
        Card.Mutable sevenOfSpades = new Card.Mutable(Card.Color.Spades, Card.Value.Seven, false);
        Card.Mutable fiveOfSpades = new Card.Mutable(Card.Color.Spades, Card.Value.Five, false);

        // give dealer an ace + six hand, a soft 17
        dealer.dealCard(aceOfSpades);
        dealer.dealCard(sixOfSpades);
        assertFalse(dealer.isGameOver());

        // add an ace to the hand, now 18
        dealer.dealCard(aceOfHearts);
        assertTrue(dealer.isGameOver());

        dealer.clearHand();

        // give dealer a hard 17
        dealer.dealCard(sevenOfSpades);
        dealer.dealCard(kingOfSpades);
        assertTrue(dealer.isGameOver());

        dealer.clearHand();

        // give dealer a 16 with no ace
        dealer.dealCard(sixOfSpades);
        dealer.dealCard(kingOfSpades);
        assertFalse(dealer.isGameOver());

        // add an ace, value 1, so hard 17
        dealer.dealCard(aceOfSpades);
        assertTrue(dealer.isGameOver());

        dealer.clearHand();

        // 11 + 5 + 1
        dealer.dealCard(aceOfHearts);
        dealer.dealCard(aceOfSpades);
        dealer.dealCard(fiveOfSpades);
        assertFalse(dealer.isGameOver());

        dealer.clearHand();

        // 7 aces because why not
        for (int i = 0; i < 7; i++) {
          dealer.dealCard(aceOfSpades);
        }
        assertFalse(dealer.isGameOver());

        // but 8 aces we stop at
        dealer.dealCard(aceOfSpades);
        assertTrue(dealer.isGameOver());
      }
    }
  }
}