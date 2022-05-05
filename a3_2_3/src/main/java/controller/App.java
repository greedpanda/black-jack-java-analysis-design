package controller;

import model.Game;
import model.rules.factory.EpicRulesFactory;
import model.rules.factory.RulesFactory;
import view.InternationalView;
import view.RulesVisitor;

/**
 * Starts the application using the console.
 */
public class App {
  /**
   * Starts the game.

  * @param args Not used.
  */
  public static void main(String[] args) {
    RulesFactory rulesFactory = new EpicRulesFactory();  // Change to another one if you'd like Mr. Casino Owner.
    Game g = new Game(rulesFactory);
    InternationalView v = new InternationalView();
    Player ctrl = new Player(g, v);

    while (ctrl.play()) {

    }
  }
}