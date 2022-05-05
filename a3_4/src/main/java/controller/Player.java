package controller;

import model.Game;
import model.Observer;
import model.PlayerType;
import view.PlayerOption;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements Observer {

  private Game game;
  private View view;

  /**
   * Player constructor.

   * @param game the game.
   * @param view the view.
   */
  public Player(Game game, View view) {
    this.game = game;
    this.view = view;
    game.subscribeNewCard(this);
  }

  /**
   * Runs the play use case.

   * @return True as long as the game should continue.
   */
  public boolean play() {
    view.displayWelcomeMessage();

    view.displayHands(game.getDealerHand(), game.getDealerScore(), game.getPlayerHand(), game.getPlayerScore());

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    PlayerOption playerChoice = view.gameChoice();

    if (playerChoice == PlayerOption.PLAY) {
      game.askRules(view.getRulesVisitor());
      if (!game.newGame()) {
        view.newGameFailed();
      }
    } else if (playerChoice == PlayerOption.HIT) {
      game.hit();
    } else if (playerChoice == PlayerOption.STAND) {
      game.stand();
    }

    return playerChoice != PlayerOption.QUIT;
  }

  /**
   * Displays only the updated hands of all players.
   * The card and receiver are passed and not used in the current
   * implementation, their purpose will be found in the GUI implementation.

   * @param card the new card dealt.
   * @param receiver the receiver of the new card.
   */
  public void updateNewCard(model.Card card, PlayerType receiver, boolean wasHidden) {
    view.displayHandsUpdate(game.getDealerHand(), game.getPlayerHand());
  }
}