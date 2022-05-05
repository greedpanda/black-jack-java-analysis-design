package model.rules.win;

import model.Dealer;
import model.Player;

/**
 * Rule interface that encapsulates whether the dealer or the player wins.
 */
public interface IntfWinStrategy {
  /**
   * Checks whether the player or the dealer wins.
   *
   * @param dealer the AI dealer of the game.
   * @param player the human player of the game.
   * @return True if the dealer wins, false if the player wins.
   */
  boolean isDealerWinner(Dealer dealer, Player player);
}
