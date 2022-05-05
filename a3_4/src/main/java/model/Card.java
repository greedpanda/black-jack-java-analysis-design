package model;

/**
 * Represents an immutable common playing card that is hidden i.e. face down.
 */
public class Card {

  /**
   * Represents the four playing card colors, as well as a hidden color.
   */
  public enum Color {
    Hearts("H"), Spades("S"), Diamonds("D"), Clubs("C"), Count("count"), Hidden("hidden");

    public final String color;
    Color(String color) {
      this.color = color;
    }
  }

  /**
   * Represents the 13 card values, as well as a hidden value.
   */
  public enum Value {
    Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"), Eight("8"), Nine("9"),
    Ten("T"), Knight("J"), Queen("Q"), King("K"), Ace("A"), Count("count"), Hidden("hidden");

    public final String value;

    Value(String value) {
      this.value = value;
    }
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