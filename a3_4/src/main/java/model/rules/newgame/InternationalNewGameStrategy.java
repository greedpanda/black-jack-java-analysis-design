package model.rules.newgame;

import model.Dealer;
import model.Deck;
import model.Player;
import model.rules.Visitor;

/**
 * A new game strategy in which the player receives two cards and the dealer one.
 */
public class InternationalNewGameStrategy extends NewGameStrategy {

  /**
   * Attempts to start a new game.
   *
   * @param deck The deck to use to get cards from.
   * @param dealer The dealer to deal cards to.
   * @param player The player to deal cards to.
   * @return True when done.
   */
  public boolean newGame(Deck deck, Dealer dealer, Player player) {
    dealer.hit(player, true);
    dealer.hit(dealer, true);
    dealer.hit(player, true);

    return true;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}