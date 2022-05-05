package view;

import java.util.ResourceBundle;
import model.Card;
import model.PlayerType;

/**
 * Implements an english console view.
 */
public class InternationalView implements View {

  /**
   * More language can be added but a flag is needed for the languages
   * that invert the "value/color" (like Swedish does).
   */
  enum Language {
    ENGLISH("En"), SWEDISH("Sv");

    public final String str;
    Language(String str) {
      this.str = str;
    }
  }

  // Change enum to "SWEDISH" for Swedish
  private Language language = Language.ENGLISH;
  private ResourceBundle resourceBundle = ResourceBundle.getBundle("view.res.Bundle" + language.str);
  private RulesVisitor rulesVisitor = new RulesVisitor(language);

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {

    cleanConsole();

    System.out.println(resourceBundle.getString("welcome"));
    System.out.println("----------------------");
    System.out.println(resourceBundle.getString("choice"));
  }

  public RulesVisitor getRulesVisitor() {
    return rulesVisitor;
  }

  private void cleanConsole() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
  }

  /**
   * Returns the player choice in the game menu.

   * @return the player choice.
   */
  public PlayerOption gameChoice() {
    PlayerOption playerChoice = null;

    while (playerChoice == null) {
      int c =  getInput();

      if (c == 'p') {
        playerChoice = PlayerOption.PLAY;
      } else if (c == 'h') {
        playerChoice = PlayerOption.HIT;
      } else if (c == 's') {
        playerChoice = PlayerOption.STAND;
      } else {
        playerChoice = PlayerOption.QUIT;
      }
    }
    return playerChoice;
  }

  private int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  /**
   * Displays the cards and scores of all players.

   * @param dealerHand the dealer's hand.
   * @param dealerScore the dealer's score.
   * @param playerHand the player's hand.
   * @param playerScore the player's score.
   */
  public void displayHands(Iterable<Card> dealerHand, int dealerScore, Iterable<Card> playerHand, int playerScore) {

    if (dealerHand.iterator().hasNext()) {
      displayHand(PlayerType.Dealer, dealerHand, dealerScore, false);
    }

    if (playerHand.iterator().hasNext()) {
      displayHand(PlayerType.Player, playerHand, playerScore, false);
    }
  }

  /**
   * Displays only the cards of all players.

   * @param dealerHand the dealer's hand.
   * @param playerHand the player's hand.
   */
  public void displayHandsUpdate(Iterable<Card> dealerHand, Iterable<Card> playerHand) {

    // cleanConsole();    // shows only the updated hands in the console.

    displayHand(PlayerType.Dealer, dealerHand, 0, true);

    displayHand(PlayerType.Player, playerHand, 0, true);

    System.out.println();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      System.out.println(resourceBundle.getString("interruptedexception"));
    }
  }

  private void displayHand(PlayerType name, Iterable<model.Card> hand, int score, boolean inline) {
    System.out.print(resourceBundle.getString(name.toString()) + resourceBundle.getString("has"));

    if (!inline) {
      System.out.println();
    }

    boolean firstCard = inline;
    for (model.Card c : hand) {
      displayCard(c, inline, firstCard);
      firstCard = false;
    }

    if (!inline) {
      System.out.println(resourceBundle.getString("score") + score);
    }
    System.out.println();
  }

  private void displayCard(model.Card card, boolean inline, boolean firstCard) {
    if (inline && !firstCard) {
      System.out.print(", ");
    }
    displayTranslatedCard(card, inline);
  }

  private void displayTranslatedCard(model.Card card, boolean inline) {
    if (language.equals(Language.ENGLISH)) {
      System.out.print("" + card.getValue()
              + resourceBundle.getString("of")
              + card.getColor());
    } else {
      if (card.getColor() == model.Card.Color.Hidden) {
        System.out.print(resourceBundle.getString("hidden"));
      } else {
        System.out.print(""
                + resourceBundle.getStringArray("colors")[card.getColor().ordinal()]
                + resourceBundle.getString("of")
                + resourceBundle.getStringArray("values")[card.getValue().ordinal()]);
      }
    }
    if (!inline) {
      System.out.println();
    }
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println(resourceBundle.getString("gameover"));
    if (dealerIsWinner) {
      System.out.println(resourceBundle.getString("dealerwins"));
    } else {
      System.out.println(resourceBundle.getString("playerwins"));
    }

  }

  public void newGameFailed() {
    System.out.println("Cannot start a new game.");
  }
}
