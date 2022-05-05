package view.gui;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Card;
import model.Game;
import model.Observer;
import model.PlayerType;
import model.rules.factory.EpicRulesFactory;
import view.Language;

/**
 * Gui controller class.
 */
public class GuiController implements Observer {
  @FXML
  private ImageView dealerOne;
  @FXML
  private ImageView dealerTwo;
  @FXML
  private ImageView dealerThree;
  @FXML
  private ImageView dealerFour;
  @FXML
  private ImageView dealerFive;
  @FXML
  private ImageView dealerSix;
  @FXML
  private ImageView dealerSeven;
  @FXML
  private ImageView dealerEight;
  @FXML
  private ImageView dealerNine;
  @FXML
  private ImageView dealerTen;
  @FXML
  private ImageView dealerEleven;
  @FXML
  private ImageView playerOne;
  @FXML
  private ImageView playerTwo;
  @FXML
  private ImageView playerThree;
  @FXML
  private ImageView playerFour;
  @FXML
  private ImageView playerFive;
  @FXML
  private ImageView playerSix;
  @FXML
  private ImageView playerSeven;
  @FXML
  private ImageView playerEight;
  @FXML
  private ImageView playerNine;
  @FXML
  private ImageView playerTen;
  @FXML
  private ImageView playerEleven;

  @FXML
  private ImageView deck;
  @FXML
  private ImageView dealerNewCard;
  @FXML
  private ImageView playerNewCard;

  @FXML
  private Label playerLabel;
  @FXML
  private Label dealerLabel;
  @FXML
  private Label playerScore;
  @FXML
  private Label dealerScore;
  @FXML
  private Label info;

  @FXML
  private Button hitButton;
  @FXML
  private Button standButton;
  @FXML
  private Button playButton;
  @FXML
  private Button rulesButton;
  @FXML
  private Button quitButton;

  private boolean startedOnce = false;

  private ImageView[] playerHand;
  private ImageView[] dealerHand;

  private Language language;
  private ResourceBundle resourceBundle;
  private Game game;
  private RulesVisitor rulesVisitor;
  private CardGraphic cardGraphic;
  private ArrayList<CardUpdate> newCards;
  private CardUpdate nextUpdate;

  /**
   * Controller constructor, called by GuiLoader.
   */
  public GuiController() {
    this.game = new Game(new EpicRulesFactory()); // new Game(new LesserEpicRulesFactory());
    this.language = Language.SWEDISH; // Language.SWEDISH;
    this.resourceBundle = ResourceBundle.getBundle("view.res.Bundle" + language.str);
    this.rulesVisitor = new RulesVisitor(language);
    this.cardGraphic = new CardGraphic();
    game.subscribeNewCard(this);
    cardGraphic.loadCardGraphic();
    newCards = new ArrayList<>();
  }

  // Has to be done AFTER initialization.
  private void postInitialization() {
    playerHand = new ImageView[]{playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven,
      playerEight, playerNine, playerTen, playerEleven};
    dealerHand = new ImageView[]{dealerOne, dealerTwo, dealerThree, dealerFour, dealerFive, dealerSix, dealerSeven,
      dealerEight, dealerNine, dealerTen, dealerEleven};
    deck.setImage(cardGraphic.getBackGraphic());
    playerLabel.setText(resourceBundle.getString("Player") + ":");
    dealerLabel.setText(resourceBundle.getString("Dealer") + ":");
    playButton.setText(resourceBundle.getString("guiplay"));
    hitButton.setText(resourceBundle.getString("guihit"));
    rulesButton.setText(resourceBundle.getString("guirules"));
    standButton.setText(resourceBundle.getString("guistand"));
    quitButton.setText(resourceBundle.getString("guiquit"));
    rulesButton.setDisable(false);
    dealerNewCard.toFront();
    playerNewCard.toFront();
    startedOnce = true;
  }

  /**
   * Hit button action event.

   * @param e event.
   */
  public void hit(ActionEvent e) {
    newCards.clear(); // to not show old updates
    game.hit();
    parseUpdates();
  }

  /**
   * Stand button action event.

   * @param e event.
   */
  public void stand(ActionEvent e) {
    newCards.clear(); // to not show old updates
    game.stand();
    parseUpdates();
  }

  /**
   * Play button action event.

   * @param e event.
   */
  public void play(ActionEvent e) {
    if (!startedOnce) {
      postInitialization();
    }
    newCards.clear();
    if (game.newGame()) {
      playButton.setDisable(true);
      resetGame();
      parseUpdates();
    }
  }

  /**
   * Rules button action event.

   * @param e event.
   */
  public void rules(ActionEvent e) {
    game.askRules(rulesVisitor);
    Alert alert = new Alert(Alert.AlertType.INFORMATION,
            rulesVisitor.getNewGameRules() + rulesVisitor.getHitRules() + rulesVisitor.getTieRules());
    alert.setTitle(resourceBundle.getString("guirules"));
    alert.setHeaderText(resourceBundle.getString("guirulesheader"));
    alert.showAndWait();
  }

  /**
   * Quit button action event.

   * @param e event.
   */
  public void quit(ActionEvent e) {
    Runtime.getRuntime().halt(0);
  }

  private void announceWinner() {
    if (game.isGameOver()) {
      if (game.isDealerWinner()) {
        info.setText(resourceBundle.getString("dealerwins"));
      } else {
        info.setText(resourceBundle.getString("playerwins"));
      }
      playButton.setDisable(false);
    }
  }

  private void resetGame() {
    for (ImageView iv : playerHand) {
      iv.setImage(null);
    }
    for (ImageView iv : dealerHand) {
      iv.setImage(null);
    }
    info.setText("");
    playerScore.setText("");
    dealerScore.setText("");
    playerNewCard.setImage(null);
    dealerNewCard.setImage(null);
    nextUpdate = null;
  }

  // updateHandsAndScore is done before every new card is animated and after the last card is animated
  // it replaces the old hand (containing the movable new card) with the new hand,
  // taken directly from the model if positioning is correct,
  // the only visible effect of this method is the score update.
  private void updateHandsAndScore() {
    if (nextUpdate != null) {
      dealerNewCard.setImage(null);
      playerNewCard.setImage(null);
      int count = 0;
      if (nextUpdate.getReceiver() == PlayerType.Dealer) {
        for (Card c : nextUpdate.getCurrentHand()) {
          dealerHand[count++].setImage(cardGraphic.getImage(c));
        }
        dealerScore.setText(String.valueOf(nextUpdate.getScore()));
      } else {
        for (Card c : nextUpdate.getCurrentHand()) {
          playerHand[count++].setImage(cardGraphic.getImage(c));
        }
        playerScore.setText(String.valueOf(nextUpdate.getScore()));
      }
    }
  }

  // parseUpdates goes through every update in newCards updated by the observer
  // --> during play, multiple cards are checked
  // --> during hit, one is checked
  // --> during stand, as many as the dealer takes
  // nextUpdate is essentially: as soon as I start animating card N+1,
  // update the hands/score to reflect the effect of card N (no matter who received it)
  // pause update code from:
  // https://stackoverflow.com/questions/65438635/javafx-thread-sleep-or-pause-in-actionevent/65440707#65440707
  private void parseUpdates() {
    addFinal();
    hitButton.setDisable(true);
    standButton.setDisable(true);

    int wait = 0;
    for (CardUpdate cu : newCards) {
      wait += 1;
      if (cu.getWasHidden()) {
        info.setText(resourceBundle.getString("guiflipping"));
      }
      PauseTransition pause = new PauseTransition(Duration.seconds(wait));
      pause.setOnFinished(e -> {
        updateHandsAndScore();
        if (!cu.getIsFinal()) {
          if (cu.getWasHidden()) {
            revealHidden(cu);
          } else if (cu.getReceiver() == PlayerType.Dealer) {
            dealerNewCard.setTranslateX(0);
            dealerNewCard.setImage(cardGraphic.getImage(cu.getCard()));
            animateCard(dealerNewCard, PlayerType.Dealer);
          } else {
            playerNewCard.setTranslateX(0);
            playerNewCard.setImage(cardGraphic.getImage(cu.getCard()));
            animateCard(playerNewCard, PlayerType.Player);
          }
          nextUpdate = cu;
        } else {
          updateHandsAndScore();
          announceWinner();
        }
      });
      pause.play();
    }

    // Waits for the cards to be dealt to enable the hit and stand buttons.
    PauseTransition disableButtons = new PauseTransition(Duration.seconds(wait));
    disableButtons.setOnFinished(f -> {
      hitButton.setDisable(false);
      standButton.setDisable(false);
    });
    disableButtons.play();
  }

  private void revealHidden(CardUpdate cu) {
    String toPrint = resourceBundle.getString(cu.getReceiver().toString()) + " " + resourceBundle.getString(
            "guirevealed") + " ";

    // ace of hearts || hjärter ess, syntax swap.
    if (language.equals(Language.ENGLISH)) {
      toPrint += cu.getCard().getValue() + resourceBundle.getString("of") + cu.getCard().getColor();
    } else {
      toPrint += resourceBundle.getStringArray("colors")[cu.getCard().getColor().ordinal()]
              + resourceBundle.getString("of")
              + resourceBundle.getStringArray("values")[cu.getCard().getValue().ordinal()];
    }

    info.setText(toPrint + "!");
    updateHandsAndScore();
  }

  // addFinal adds a fake card update to the end of the observer data structure
  // the code knows that when it reaches this, it's time to check who won
  // the data structure is always cleared when you press hit/stand,
  // but BEFORE telling the game to hit/stand, so it doesn't clear the updates
  // this solution makes it smoother to check for winner.
  private void addFinal() {
    CardUpdate finalCard = new CardUpdate(null, null, null, 0, false, true);
    newCards.add(finalCard);
  }

  private void animateCard(Node node, PlayerType owner) {
    int displacement = -325;
    if (owner == PlayerType.Dealer) {
      for (ImageView iv : dealerHand) {
        if (iv.getImage() != null) {
          displacement += 20;
        }
      }
    } else {
      for (ImageView iv : playerHand) {
        if (iv.getImage() != null) {
          displacement += 20;
        }
      }
    }
    Timeline timeline = new Timeline();
    timeline.setCycleCount(1);
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
            new KeyValue(node.translateXProperty(), (displacement), Interpolator.EASE_OUT)));
    timeline.play();
  }

  /**
   * Stores observer updates, the card and the snapshot of the hand/score.

   * @param card the new card dealt.
   * @param receiver the receiver of the new card.
   * @param wasHidden true if the card was hidden, false otherwise.
   */
  public void updateNewCard(Card card, PlayerType receiver, boolean wasHidden) {
    if (receiver == PlayerType.Dealer) {
      Iterable<Card> hand = game.getDealerHand();
      CardUpdate cardUpdate = new CardUpdate(card, receiver, hand, game.getDealerScore(), wasHidden, false);
      newCards.add(cardUpdate);
    } else {
      Iterable<Card> hand = game.getPlayerHand();
      CardUpdate cardUpdate = new CardUpdate(card, receiver, hand, game.getPlayerScore(), wasHidden, false);
      newCards.add(cardUpdate);
    }
  }

  /**
   * Supporting class for the Observer new card update.
   */
  static class CardUpdate {

    private Card card;
    private PlayerType receiver;
    private Iterable<Card> currentHand;
    private int score;
    private boolean wasHidden;
    private boolean isFinal; // used for a fake card to mark the end of the data structure

    CardUpdate(Card card, PlayerType receiver, Iterable<Card> currentHand, int score, boolean wasHidden,
               boolean isFinal) {
      this.card = card;
      this.receiver = receiver;
      this.currentHand = currentHand;
      this.score = score;
      this.wasHidden = wasHidden;
      this.isFinal = isFinal;
    }

    Card getCard() {
      return card;
    }

    PlayerType getReceiver() {
      return receiver;
    }

    Iterable<Card> getCurrentHand() {
      return currentHand;
    }

    int getScore() {
      return score;
    }

    boolean getWasHidden() {
      return wasHidden;
    }

    boolean getIsFinal() {
      return isFinal;
    }
  }

}
