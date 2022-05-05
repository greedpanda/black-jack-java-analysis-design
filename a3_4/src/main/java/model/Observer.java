package model;

/**
 * Encapsulates the observer functionality.
 */
public interface Observer {

  /**
   * Informs observers that a card has been dealt OR an existing card has been revealed.
   *
   * @param card The card in question.
   * @param receiver The owner of a previously hidden card or the receiver of a new card.
   * @param wasHidden True if an existing card is revealed, false if a new card is dealt, even if the new card is
   *                  hidden.
   */
  void updateNewCard(Card card, PlayerType receiver, boolean wasHidden);

}
