package model.rules.factory;

import model.rules.hit.HitStrategy;
import model.rules.newgame.NewGameStrategy;
import model.rules.win.WinStrategy;

/**
 * Abstract Factory pattern interface.
 */
public interface RulesFactory {

  /**
   * Creates the rule for whether the dealer will hit or not.
   *
   * @return The hit rule for the Dealer.
   */
  HitStrategy getHitRule();

  /**
   * Crates the rule to use when starting a new game.
   * Currently, American and International new game rules
   * are available.
   *
   * @return The rule to use when starting a new game.
   */
  NewGameStrategy getNewGameRule();

  /**
   * Crates the rule to use for deciding the winner.
   * Currently, Player and Dealer win on tie
   * are available.
   *
   * @return The rule for deciding the winner.
   */
  WinStrategy getWinStrategy();
}
