package model.rules.newgame;

import model.Dealer;
import model.Deck;
import model.Player;
import model.rules.Visitor;

/**
 * A new game strategy in which the player and dealer both receive two cards.
 * The dealer's second card is hidden.
 */
public class AmericanNewGameStrategy extends NewGameStrategy {

  /**
   * Attempts to start a new game.
   *
   * @param dealer The dealer to deal cards to.
   * @param player The player to deal cards to.
   * @return True when done.
   */
  public boolean newGame(Dealer dealer, Player player) {
    dealer.hit(player, true);
    dealer.hit(dealer, true);
    dealer.hit(player, true);
    dealer.hit(dealer, false);

    return true;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}