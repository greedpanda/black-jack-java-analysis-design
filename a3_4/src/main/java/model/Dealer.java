package model;

import model.rules.Visitor;
import model.rules.factory.RulesFactory;
import model.rules.hit.HitStrategy;
import model.rules.newgame.NewGameStrategy;
import model.rules.win.WinStrategy;

/**
 * Represents a dealer player that handles the deck of cards and runs the game using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinStrategy winStrategy;

  /**
   * Initializing constructor.

   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {
    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    winStrategy = rulesFactory.getWinStrategy();
  }

  /**
   * Allows a Visitor to find out the rules used by the dealer.
   *
   * @param visitor The curious visitor.
   */
  public void askRules(Visitor visitor) {
    newGameRule.accept(visitor);
    hitRule.accept(visitor);
    winStrategy.accept(visitor);
  }

  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play against.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(deck, this, player);
    }
    return false;
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @param showCard True if the card is to be shown, False otherwise.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player, Boolean showCard) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      Card.Mutable c;
      c = deck.getCard();
      c.show(showCard);
      player.dealCard(c);

      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check against.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return winStrategy.isDealerWinner(this, player);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has chosen to take no more cards, it is the dealers turn.
   */
  public boolean stand() {
    if (deck != null) {
      showHand();
      while (hitRule.doHit(this) == true) {
        hitRule.doHit(this);
        hit(this, true);
      }
      return true;
    } else {
      return false;
    }
  }

  public int getMaxScore() {
    return maxScore;
  }
}