package model.rules.factory;

import model.rules.hit.HitStrategy;
import model.rules.hit.Soft17HitStrategy;
import model.rules.newgame.AmericanNewGameStrategy;
import model.rules.newgame.NewGameStrategy;
import model.rules.win.DealerTieWinStrategy;
import model.rules.win.WinStrategy;

/**
 * Creates concrete rules with only the most epic of options.
 */
public class EpicRulesFactory implements RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.
   *
   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new Soft17HitStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.
   *
   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }

  /**
   * Crates the rule to use for deciding the winner.
   *
   * @return The rule to use.
   */
  public WinStrategy getWinStrategy() {
    return new DealerTieWinStrategy();
  }
}