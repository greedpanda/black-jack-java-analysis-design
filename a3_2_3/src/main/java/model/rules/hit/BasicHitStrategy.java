package model.rules.hit;

import model.Player;
import model.rules.Visitor;

/**
 * A strategy in which the dealer will hit if they have at most 17 points.
 */
public class BasicHitStrategy extends HitStrategy {
  private static final int hitLimit = 17;

  public boolean doHit(Player dealer) {
    return dealer.calcScore() < hitLimit;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}