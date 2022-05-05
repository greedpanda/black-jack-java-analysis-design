package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a player in the Black Jack game. A Player has a hand of cards.
 */
public class Player extends Observable {

  private List<Card.Mutable> hand;
  protected final int maxScore = 21;

  public Player() {
    hand = new LinkedList<Card.Mutable>();
  }

  /**
   * Adds a card to the Player's hand.
   * Announces a non-mutable version of the card to all observers.
   * A hidden card will not be flipped by this method.

   * @param addToHand The card to add to the hand.
   */
  public void dealCard(Card.Mutable addToHand) {
    hand.add(addToHand);
    Card newCard = new Card(addToHand.getColor(), addToHand.getValue(), false);
    notifyNewCard(newCard);
  }

  /**
   * Returns the cards in hand.

   * @return the cards in the Player's hand
   */
  public Iterable<Card> getHand() {
    return new LinkedList<Card>(hand);
  }

  /**
   * Removes all cards from the hand.
   */
  public void clearHand() {
    hand.clear();
  }

  /**
   * Shows all cards in the hand.
   */
  public void showHand() {
    for (Card.Mutable c : hand) {
      c.show(true);
    }
  }

  /**
   * Calculates the score of the hand according to Black Jack rules.

   * @return The score.
   */
  public int calcScore() {
    int score = calcRawScore();
    if (score > maxScore) {
      for (Card c : getHand()) {
        if (c.getValue() == Card.Value.Ace && score > maxScore) {
          score -= 10;
        }
      }
    }

    return score;
  }

  /**
   * Calculates the raw score of the hand (Ace = 11 always).

   * @return The raw score.
   */
  public int calcRawScore() {
    // the number of scores is dependent on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int[] cardScores = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
    assert (cardScores.length == Card.Value.Count.ordinal())
            : "Card Scores array size does not match number of card values";

    int rawScore = 0;

    for (Card c : getHand()) {
      if (c.getValue() != Card.Value.Hidden) {
        rawScore += cardScores[c.getValue().ordinal()];
      }
    }

    return rawScore;
  }
}
