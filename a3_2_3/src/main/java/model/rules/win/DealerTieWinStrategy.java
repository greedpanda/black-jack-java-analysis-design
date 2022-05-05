package model.rules.win;

import model.Dealer;
import model.Player;
import model.rules.Visitor;

/**
 * Rule strategy that encapsulates whether the dealer or the player wins.
 */
public class DealerTieWinStrategy extends WinStrategy {
  /**
   * Checks whether the player or the dealer wins.
   *
   * @param dealer the AI dealer of the game.
   * @param player the human player of the game.
   * @return True if the dealer wins, false if the player wins.
   */
  public boolean isDealerWinner(Dealer dealer, Player player) {
    if (player.calcScore() > dealer.getMaxScore()) {
      return true;
    } else if (dealer.calcScore() > dealer.getMaxScore()) {
      return false;
    }
    return dealer.calcScore() >= player.calcScore();
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
