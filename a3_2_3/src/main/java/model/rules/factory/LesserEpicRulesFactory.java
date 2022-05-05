package model.rules.factory;

import model.rules.hit.BasicHitStrategy;
import model.rules.hit.HitStrategy;
import model.rules.newgame.InternationalNewGameStrategy;
import model.rules.newgame.NewGameStrategy;
import model.rules.win.PlayerTieWinStrategy;
import model.rules.win.WinStrategy;

/**
 * Creates concrete rules with only the most epic of options.
 */
public class LesserEpicRulesFactory implements RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.
   *
   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new BasicHitStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.
   *
   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new InternationalNewGameStrategy();
  }

  /**
   * Crates the rule to use for deciding the winner.
   *
   * @return The rule to use.
   */
  public WinStrategy getWinStrategy() {
    return new PlayerTieWinStrategy();
  }
}