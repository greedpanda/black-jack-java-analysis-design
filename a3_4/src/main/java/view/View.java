package view;

import model.Card;
import model.rules.Visitor;

/**
 * Encapsulates the generic view functionality.
 */
public interface View {

  /**
   * Shows a welcome message.
   */
  void displayWelcomeMessage();

  /**
   * For getting the visitor object that asks about the rules.
   *
   * @return A Visitor object.
   */
  Visitor getRulesVisitor();

  /**
   * Returns the player choice enum corresponding to the pressed character.

   * @return player choice.
   */
  PlayerOption gameChoice();

  /**
   * Displays the cards and scores of all players.

   * @param dealerHand the dealer's hand.
   * @param dealerScore the dealer's score.
   * @param playerHand the player's hand.
   * @param playerScore the player's score.
   */
  void displayHands(Iterable<Card> dealerHand, int dealerScore, Iterable<Card> playerHand, int playerScore);

  void displayHandsUpdate(Iterable<Card> dealerHand, Iterable<Card> playerHand);

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  void displayGameOver(boolean dealerIsWinner);

  /**
   * Prints an error message if a new game could not be started.
   */
  void newGameFailed();

}