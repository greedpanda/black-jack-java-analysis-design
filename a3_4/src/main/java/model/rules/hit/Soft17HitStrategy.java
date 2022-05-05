package model.rules.hit;

import model.Card;
import model.Player;
import model.rules.Visitor;

/**
 * A strategy in which the dealer will hit if they have 17 while
 * an ace in their hand is being counted as an 11.
 */
public class Soft17HitStrategy extends HitStrategy {
  private static final int hitThreshold = 17;

  /**
   * Check whether the dealer will hit.
   *
   * @param dealer the player to check.
   * @return True if the dealer hits.
   */
  public boolean doHit(Player dealer) {
    boolean hit = false;

    if (dealer.calcScore() < hitThreshold) {
      hit = true;
    } else if (dealer.calcRawScore() >= hitThreshold) {
      int score = dealer.calcRawScore();
      for (Card card : dealer.getHand()) {
        if (card.getValue().equals(Card.Value.Ace)) {
          if (score == hitThreshold) {
            hit = true;
          }
          score -= 10;
        }
      }
    }
    return hit;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
