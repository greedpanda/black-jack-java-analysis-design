package model;

/**
 * Represents an immutable common playing card that is hidden i.e. face down.
 */
public class Card {

  /**
   * Represents the four playing card colors, as well as a hidden color.
   */
  public enum Color {
    Hearts, Spades, Diamonds, Clubs, Count, Hidden
  }

  /**
   * Represents the 13 card values, as well as a hidden value.
   */
  public enum Value {
    Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Knight, Queen, King, Ace, Count, Hidden
  }


  /**
   * Represents a Mutable playing card, that can be shown or hidden.
   */
  public static class Mutable extends Card {
    /**
      * Initialising constructor that creates a hidden card.

      * @param color The color of the card.
      * @param value The value of the card.
     */
    public Mutable(Color color, Value value, boolean isHidden) {
      super(color, value, isHidden);
    }

    /**
     * Initializing constructor that creates a hidden card.

      * @param show true if the card is to be shown.
      */
    public void show(boolean show) {
      isHidden = !show;
    }
  }

  private Color color;
  private Value value;
  protected boolean isHidden;

  /**
   * Initializing constructor that creates an optionally hidden card.

   * @param color The color of the card.
   * @param value The value of the card.
   * @param isHidden The hidden-ness of the card.
   */
  public Card(Color color, Value value, boolean isHidden) {
    this.value = value;
    this.color = color;
    this.isHidden = isHidden;
  }

  /**
   * Returns the color of the card or hidden if the card is face down.

   * @return The card color.
   */
  public Color getColor() {
    if (isHidden) {
      return Color.Hidden;
    }
    return color;
  }

  /**
   * Returns the value of the card or hidden if the card is face down.

   * @return The card value.
   */
  public Value getValue() {
    if (isHidden) {
      return Value.Hidden;
    }
    return value;
  }

  
}